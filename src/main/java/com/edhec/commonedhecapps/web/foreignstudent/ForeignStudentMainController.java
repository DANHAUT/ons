package com.edhec.commonedhecapps.web.foreignstudent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edhec.commonedhecapps.model.common.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.edhec.commonedhecapps.model.foreignstudent.ForeignStudentView;
import com.edhec.commonedhecapps.model.foreignstudent.ForeignUniversity;
import com.edhec.commonedhecapps.model.foreignstudent.ScoFsProgramLimit;
import com.edhec.commonedhecapps.service.ForeignStudentManagementService;
import com.edhec.commonedhecapps.service.UserService;
import com.edhec.commonedhecapps.model.User;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/foreignstudent/")
public class ForeignStudentMainController {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ForeignStudentMainController.class);
	
	@Autowired
	ForeignStudentManagementService foreignStudentManagementService;

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String foreignStudentIndex(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
    	
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication currentUser = ctx.getAuthentication();
		//UserDetails userDetails = (UserDetails) currentUser.getPrincipal();
		if(currentUser.getPrincipal()==null ||  currentUser.getPrincipal().toString().contentEquals("anonymousUser")){
			logger.info("Authentification Anonyme");
			return "anonyme";
		}
		User user = (User) currentUser.getPrincipal();
		if (user==null){
			logger.info("User disconnected");
			return "/foreignstudent/disconnected";
		}
		//User user = (User) userService.loadUserByUsername(userDetails.getUsername());

		//Affichage des roles de l'utilisateur connect�
		model.addAttribute("role","NONETUD");
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();	
		for (GrantedAuthority authority : authorities)
		{
			String role = authority.getAuthority();
			logger.info("Role : " + role);
			if (role.contentEquals("ROLE_ETUDEDHEC")){
				model.addAttribute("role",role);
			}
			if (role.contentEquals("ROLE_ETUDESPEME")){
				model.addAttribute("role",role);
			}
			request.getSession().setAttribute("role", role);
		}		
		
		
		ComContact contact = foreignStudentManagementService.findContactById(user.getCntId());
		if (contact==null){
			return "/notuniv";
		}

		String school = ForeignUniversity.SHYID_EDHEC; //Programme EDHEC
		String coordType = ForeignUniversity.FUNCTION_COORD_EDHEC;
		Calendar cal = new GregorianCalendar();
		String scoYear = cal.get(Calendar.YEAR) + 1 + "";
		String forceopen="N";
		String forceadmin="N";
		if(user.getUsername().contentEquals("univ0")){
			request.getSession().setAttribute("open", "Y");		
			forceopen="Y";
		}
		if(request.getParameter("open") != null && request.getParameter("open").toString().equalsIgnoreCase("Y")) {
			request.getSession().setAttribute("open", "Y");				
		}
		if (request.getSession().getAttribute("open") !=null && request.getSession().getAttribute("open").toString().equalsIgnoreCase("Y")){
			forceopen="Y";
		}
		if(request.getParameter("admin") != null && request.getParameter("admin").toString().equalsIgnoreCase("Y")) {
			request.getSession().setAttribute("open", "Y");
			request.getSession().setAttribute("admin", "Y");
		}
		if (request.getSession().getAttribute("admin") !=null && request.getSession().getAttribute("admin").toString().equalsIgnoreCase("Y")){
			forceopen="Y";
			forceadmin="Y";
		}
		model.addAttribute("forceopen", forceopen);
		model.addAttribute("forceadmin", forceadmin);
		if(request.getParameter("school") != null) {
			if (request.getParameter("school").toString().equalsIgnoreCase("bba")){
				school = ForeignUniversity.SHYID_ESPEME; //Programme ESPEME
				coordType = ForeignUniversity.FUNCTION_COORD_ESPEME;
				request.getSession().setAttribute("school", school);
			}else{
				request.getSession().setAttribute("school", ForeignUniversity.SHYID_EDHEC);
			}
		}
		if(request.getSession().getAttribute("school") != null) {
			if(request.getSession().getAttribute("school").toString().equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
				school = ForeignUniversity.SHYID_ESPEME; //Programme ESPEME
				coordType = ForeignUniversity.FUNCTION_COORD_ESPEME;				
			}
		}
		if(request.getParameter("scoyear") != null) {
			scoYear = request.getParameter("scoyear");
		}
		model.addAttribute("school", school);
		model.addAttribute("scoYear", scoYear);

		ForeignUniversity foreignUniversity = foreignStudentManagementService.findForeignUniversityByIdAndProgram(contact.getId(),school,scoYear);

		model.addAttribute("otherSchool", "");
		String otherSchool = (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME))?ForeignUniversity.SHYID_EDHEC:ForeignUniversity.SHYID_ESPEME;
		if (foreignUniversity==null){
			if (foreignStudentManagementService.findForeignUniversityByIdAndProgram(contact.getId(),otherSchool,scoYear)!=null)
			{
				return (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME))?"redirect:/edhec":"redirect:/bba";
			}else{
			return "/notuniv";}
		}else{
			if (foreignStudentManagementService.findForeignUniversityByIdAndProgram(contact.getId(),otherSchool,scoYear)!=null)
			{
				model.addAttribute("otherSchool", (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME))?"edhec":"bba");
			}
		}
		request.getSession().setAttribute("university", contact);
		request.getSession().setAttribute("scoYear", scoYear);
		request.getSession().setAttribute("school", school);
		
		//Verifie que les dates limites des inscriptions sont respect�es si non ouvert via parametre open=Y
		ScoFsProgramLimit scoFsProgramLimit = foreignStudentManagementService.existScoFSProgramClosed(school, scoYear);
		Date date = new Date();
		if (forceopen.equalsIgnoreCase("N"))
		{
			if (scoFsProgramLimit != null && date.after(scoFsProgramLimit.getDateLimit()))
			{
				return "/foreignstudent/closed";
			}
			if (scoFsProgramLimit != null && date.before(scoFsProgramLimit.getStartDate()))
			{
				return "/foreignstudent/closed";
			}
		}		
		
		List<ForeignStudentView> foreignStudentList = foreignStudentManagementService.findForeignStudentViewByUniversity(contact.getId(), school,scoYear);

		int nbSemester = 0;
		int nbDoubleDiploma = 0;
		foreignStudentManagementService.updateForeignUniversityCapacity(contact.getId(), school,scoYear);
		if (foreignUniversity.getUsedSemester()!=null){
		  nbSemester = foreignUniversity.getUsedSemester().intValue();
		}
		if (foreignUniversity.getUsedDdSemester()!=null){
			nbDoubleDiploma = foreignUniversity.getUsedDdSemester().intValue();
		}
		
		// Recherche des infos du coordinateur
		boolean coordinateurInfo = false;
		boolean coordinateurEmailCopy = true;
		boolean olaUse = false;
		ComArtificialPerson comArtificialPerson = foreignStudentManagementService.findComArtificialPersonById(contact.getId());
		if(comArtificialPerson != null) {
			for (ComJob comJob : comArtificialPerson.getComJobList()) {
				//Ajout 2014 pour forcer les coordinateurs à mettre à jour leurs infos chaque année
				int anneemodif = 0;
				int anneeactuelle = cal.get(Calendar.YEAR);
				if (comJob.getDateModified()!=null)
				{
					Calendar calbase = Calendar.getInstance();
					calbase.setTime(comJob.getDateModified());
					anneemodif = calbase.get(Calendar.YEAR);;
				}
				if (comJob.getDateTo()==null && anneemodif == anneeactuelle){coordinateurInfo=true;}
				if (comJob.getDateTo()==null && comJob.getCrtIdFunction()!=null && comJob.getCrtIdFunction().equalsIgnoreCase(coordType))
				{
					model.addAttribute("coordFirstname",comJob.getComNaturalPerson().getFirstName());
					if (comJob.getComNaturalPerson().getFirstName()==null || comJob.getComNaturalPerson().getFirstName().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
					if (comJob.getComNaturalPerson().getSoftwares()==null || comJob.getComNaturalPerson().getSoftwares().trim().equalsIgnoreCase("")){coordinateurEmailCopy=false;}
					model.addAttribute("coordLastname",comJob.getComNaturalPerson().getComContact().getName());
					if (comJob.getComNaturalPerson().getComContact().getName()==null || comJob.getComNaturalPerson().getComContact().getName().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
					ComMail comMail = foreignStudentManagementService.findComContactEmail(comJob.getMalId());
					if (comMail==null || comMail.getMail()==null || comMail.getMail().trim().equalsIgnoreCase("")){
						coordinateurInfo=false;
					}else{model.addAttribute("coordEmail",comMail.getMail());}
					model.addAttribute("coordTitle",comJob.getDescription());
					if (comJob.getDescription().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
					ComPhone comPhone = foreignStudentManagementService.findComContactPhone(comJob.getPhnId());
					if (comPhone!=null && comPhone.getPhone()!=null && !comPhone.getPhone().trim().equalsIgnoreCase("")){
						model.addAttribute("coordPhone",comPhone.getPhone());
					}
				}
			}
		}
		if (foreignUniversity.getAddress()==null || foreignUniversity.getAddress().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
		if (foreignUniversity.getZipcode()==null || foreignUniversity.getZipcode().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
		if (foreignUniversity.getTown()==null || foreignUniversity.getTown().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
		if (foreignUniversity.getCodeErasmus()!=null && !foreignUniversity.getCodeErasmus().trim().equalsIgnoreCase("") && (foreignUniversity.getOla()==null || foreignUniversity.getOla().trim().equalsIgnoreCase(""))){coordinateurInfo=false;olaUse=false;}
		if (foreignUniversity.getOla()!=null && foreignUniversity.getOla().equalsIgnoreCase("OLA")){olaUse=true;}

		model.addAttribute("coordinateurInfo",(coordinateurInfo)?"Y":"N");
		model.addAttribute("olaUse",(olaUse)?"OLA":"NO");
		model.addAttribute("coordinateurEmailCopy",(coordinateurEmailCopy)?"Y":"N");
		model.addAttribute("codeErasmus",foreignUniversity.getCodeErasmus());
		model.addAttribute("nbSemester", nbSemester);
		model.addAttribute("nbDoubleDiploma", nbDoubleDiploma);
		model.addAttribute("foreignStudentList", foreignStudentList);
		model.addAttribute("university", contact);
		model.addAttribute("foreignUniversity", foreignUniversity);

		if (!coordinateurInfo){
			return "redirect:/foreignstudent/correspondantManagement";
		}
		if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
			return "/foreignstudent/indexBBA";
		}else{
			return "/foreignstudent/index";
		}
    }

	@RequestMapping(value = "disconnected", method = RequestMethod.GET)
	public String disconnected(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException{
		logger.info("User disconnected");
		return "/foreignstudent/disconnected";
	}
}
