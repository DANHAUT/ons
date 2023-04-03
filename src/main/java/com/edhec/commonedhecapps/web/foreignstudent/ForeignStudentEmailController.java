package com.edhec.commonedhecapps.web.foreignstudent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import com.edhec.commonedhecapps.service.ForeignStudentManagementService;
import com.edhec.commonedhecapps.service.CommonStudentAbroadService;
import com.edhec.commonedhecapps.service.UserService;
import com.edhec.commonedhecapps.model.common.ComArtificialPerson;
import com.edhec.commonedhecapps.model.common.ComContact;
import com.edhec.commonedhecapps.model.common.ComJob;
import com.edhec.commonedhecapps.model.common.ComMail;
import com.edhec.commonedhecapps.model.common.ComPhone;
import com.edhec.mail.CommonEdhecMailSender;
import com.edhec.commonedhecapps.model.User;

@Controller
@RequestMapping("/foreignstudent/")
public class ForeignStudentEmailController {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ForeignStudentEmailController.class);
	
	@Autowired
	CommonStudentAbroadService commonStudentAbroadService;

	@Autowired
	ForeignStudentManagementService foreignStudentManagementService;

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "sendemail", method = RequestMethod.GET)
    public String foreignStudentIndex(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
    	
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication currentUser = ctx.getAuthentication();
		UserDetails userDetails = (UserDetails) currentUser.getPrincipal();		
		logger.info("userName : "+ userDetails.getUsername());

		User user = (User) userService.loadUserByUsername(userDetails.getUsername());
		if (user==null){
			logger.info("User disconnected");
			return "/foreignstudent/disconnected";
		}

		ComContact contact = foreignStudentManagementService.findContactById(user.getCntId());
		if (contact==null){
			return "/foreignstudent/notuniv";
		}

		String school = ForeignUniversity.SHYID_EDHEC; //Programme EDHEC
		String coordType = ForeignUniversity.FUNCTION_COORD_EDHEC;
		Calendar cal = new GregorianCalendar();
		String scoYear = cal.get(Calendar.YEAR) + 1 + "";
		Boolean isSem2 = cal.get(Calendar.MONTH)>=8;
		String forceopen="N";
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

		request.getSession().setAttribute("university", contact);
		request.getSession().setAttribute("scoYear", scoYear);
		
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
		boolean coordinateurInfo = true;
		String coordFirstname="";
		String coordLastname="";
		String coordEmail="";
		String coordTitle="";
		String coordPhone="";
		
		ComArtificialPerson comArtificialPerson = foreignStudentManagementService.findComArtificialPersonById(contact.getId());
		if(comArtificialPerson != null) {
			for (ComJob comJob : comArtificialPerson.getComJobList()) {
				if (comJob.getDateTo()==null && comJob.getCrtIdFunction()!=null && comJob.getCrtIdFunction().equalsIgnoreCase(coordType))
				{
					coordFirstname = comJob.getComNaturalPerson().getFirstName();
					if (comJob.getComNaturalPerson().getFirstName()==null || comJob.getComNaturalPerson().getFirstName().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
					coordLastname=comJob.getComNaturalPerson().getComContact().getName();
					if (comJob.getComNaturalPerson().getComContact().getName()==null || comJob.getComNaturalPerson().getComContact().getName().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
					ComMail comMail = foreignStudentManagementService.findComContactEmail(comJob.getMalId());
					if (comMail==null || comMail.getMail()==null || comMail.getMail().trim().equalsIgnoreCase("")){
						coordinateurInfo=false;
					}else{coordEmail=comMail.getMail();}
					coordTitle = comJob.getDescription();
					if (comJob.getDescription().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
					ComPhone comPhone = foreignStudentManagementService.findComContactPhone(comJob.getPhnId());
					if (comPhone!=null && comPhone.getPhone()!=null && !comPhone.getPhone().trim().equalsIgnoreCase("")){
						coordPhone=comPhone.getPhone();
					}
				}
			}
		}
		if (foreignUniversity.getAddress()==null || foreignUniversity.getAddress().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
		if (foreignUniversity.getZipcode()==null || foreignUniversity.getZipcode().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
		if (foreignUniversity.getTown()==null || foreignUniversity.getTown().trim().equalsIgnoreCase("")){coordinateurInfo=false;}
		//if (foreignUniversity.getCodeErasmus()!=null && !foreignUniversity.getCodeErasmus().trim().equalsIgnoreCase("") && (foreignUniversity.getOla()==null || foreignUniversity.getOla().trim().equalsIgnoreCase(""))){coordinateurInfo=false;}
		
		model.addAttribute("coordinateurInfo",(coordinateurInfo)?"Y":"N");
		model.addAttribute("codeErasmus",foreignUniversity.getCodeErasmus());
		model.addAttribute("nbSemester", nbSemester);
		model.addAttribute("nbDoubleDiploma", nbDoubleDiploma);
		model.addAttribute("foreignStudentList", foreignStudentList);
		model.addAttribute("university", contact);
		model.addAttribute("foreignUniversity", foreignUniversity);
		
		//Envoi du message récapitulatif
		ResourceBundle messagesBundle = ResourceBundle.getBundle("messages");
		ResourceBundle mailBundle = ResourceBundle.getBundle("mail");
		try {
			String[] Param = new String[100];
			Param[1]= coordFirstname + " " + coordLastname;
			Param[2]= "";
			Param[3]= "";
			Param[4]= "";
			Param[5]= "";
			Param[6]= "";
			Param[7]= "";
			Param[8]= "";
			Param[9]= coordEmail;
			int nbetud=foreignStudentList.size();
			int j=0;
			String textParam = "";
			for (int i=10;i<100;i+=5){
				if (nbetud>j){
					ForeignStudentView fsv = foreignStudentList.get(j);
					Param[i]= fsv.getFirstName() + " " + fsv.getLastName() + " (" + fsv.getEmail() + ")";
					textParam = "N/A";
					if (fsv.getModuleLib()!=null){textParam=fsv.getModuleLib();}
					if (textParam.equalsIgnoreCase("N/A")){textParam=fsv.getStudylevel();}
					Param[i+1]= textParam;
					textParam = "N/A";
					if (fsv.getPeriodeCode()!=null && fsv.getPeriodeCode().equalsIgnoreCase("S1")){textParam=messagesBundle.getString("foreignStd.fallSemesterabrg");}
					if (fsv.getPeriodeCode()!=null && fsv.getPeriodeCode().equalsIgnoreCase("S2")){textParam=messagesBundle.getString("foreignStd.springSemesterabrg");}
					if (fsv.getPeriodeCode()!=null && fsv.getPeriodeCode().equalsIgnoreCase("S0")){textParam=messagesBundle.getString("foreignStd.fullYearabrg");}
					if (fsv.getDoubleDiploma()!=null && fsv.getDoubleDiploma().equalsIgnoreCase("Y")){textParam=messagesBundle.getString("foreignStd.doubleDiplomabrg");}
					Param[i+2]= textParam;
					textParam = "N/A";
					if (fsv.getCampus()!=null && fsv.getCampus().equalsIgnoreCase("LILLE")){textParam=messagesBundle.getString("foreignStd.lille");}
					if (fsv.getCampus()!=null && fsv.getCampus().equalsIgnoreCase("NICE")){textParam=messagesBundle.getString("foreignStd.nice");}
					Param[i+3]= textParam;
					textParam = "N/A";
					if (fsv.getHelp()!=null){textParam=fsv.getHelp();}else{textParam="---";}
					Param[i+4]= textParam;
					j++;
				}else{
					textParam = "---";
					Param[i]= textParam;
					Param[i+1]= textParam;
					Param[i+2]= textParam;
					Param[i+3]= textParam;
					Param[i+4]= textParam;
				}
			}
			
			String emailAdminCampus ="";
			String emailBody ="";
			String emailObject = messagesBundle.getString("foreignStd.mailOSubject");
			if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
				emailAdminCampus ="ONSBBAEDHEC@edhec.edu";
				emailObject = "BBA EDHEC - " + emailObject;
				emailBody = "EmailUniversityConfirmationBBAEDHEC.html";
				if (isSem2) {emailBody = "EmailUniversityConfirmationBBAEDHEC2.html";}
			}else{
				emailAdminCampus ="ONSEDHEC@edhec.edu";
				emailObject = "EDHEC - " + emailObject;
				emailBody = "EmailUniversityConfirmation.html";
				if (isSem2) {emailBody = "EmailUniversityConfirmation2.html";}
			}

			CommonEdhecMailSender.commonEdhecMailSender(mailBundle.getString("mail.server"), mailBundle.getString("mail.server.port"), mailBundle.getString("mail.server.login"), mailBundle.getString("mail.server.pwd"), mailBundle.getString("mail.from"), (Param[9]==null)?null:new String[]{Param[9]}, new String[]{emailAdminCampus, mailBundle.getString("mail.toBcc")} , emailObject, directMailPub(emailBody,Param,"#"));
			logger.info("ForeignStudentEmailController sent an email from server : " + mailBundle.getString("mail.server") );
			
		} catch (Exception e) {
			logger.info("ForeignStudentEmailController didn't sent an email from server : " + mailBundle.getString("mail.server") + " error = " + e.getMessage());
		}
		
		
		if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
			return "redirect:/bba";
		}else{
			return "redirect:/edhec";
		}
    }
	
	public String directMailPub(String filename, String[] paramList, String separator){
		ClassLoader classLoader = this.getClass().getClassLoader();
		File f = new File(classLoader.getResource(filename).getFile());

        FileInputStream fs = null;
        InputStreamReader in = null;
        BufferedReader br = null;

        StringBuffer sb = new StringBuffer();
        
        String textinLine;

        try {
             fs = new FileInputStream(f);
             in = new InputStreamReader(fs);
             br = new BufferedReader(in);

            while(true)
            {
                textinLine=br.readLine();
                if(textinLine==null)
                    break;
                sb.append(textinLine);
            }
            
            String textToEdit = separator;
            
            for (int nbParam = 1; nbParam < paramList.length; nbParam++){
            	textToEdit = separator + nbParam + separator;
            	String textToReplace = "...";
            	if (paramList[nbParam]!=null){
            		textToReplace = paramList[nbParam];
            	}
               	int cnt = sb.indexOf(textToEdit);
                	while(cnt<sb.length() && cnt>0){
               		sb.replace(cnt,cnt+textToEdit.length(),textToReplace);
               		cnt = sb.indexOf(textToEdit);
                }
            }

              fs.close();
              in.close();
              br.close();

            } catch (FileNotFoundException e) {
            	logger.info(e.getMessage());
            } catch (IOException e) {
            	logger.info(e.getMessage());
            }

		return sb.toString();
	}
    
}
