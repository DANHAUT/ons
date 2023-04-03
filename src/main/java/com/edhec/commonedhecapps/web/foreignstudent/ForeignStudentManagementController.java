package com.edhec.commonedhecapps.web.foreignstudent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.edhec.commonedhecapps.model.common.ComCriteriaLView;
import com.edhec.commonedhecapps.service.CommonStudentAbroadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.edhec.commonedhecapps.model.foreignstudent.BookNivChoixIncoming;
import com.edhec.commonedhecapps.model.foreignstudent.ForeignStudent;
import com.edhec.commonedhecapps.model.foreignstudent.ForeignStudentView;
import com.edhec.commonedhecapps.model.foreignstudent.ForeignUniversity;
import com.edhec.commonedhecapps.service.ForeignStudentManagementService;
import com.edhec.commonedhecapps.web.AjaxUtils;
import com.edhec.commonedhecapps.web.foreignstudent.command.ForeignStudentCommand;
import com.edhec.commonedhecapps.web.StringUTF8Editor;
import com.edhec.commonedhecapps.model.common.ComContact;


@Controller
@SessionAttributes({"foreignStudentCommand","contact","foreignUniversity","foreignStudentViewList","university","school","scoYear","DDSem","comCriteriaNationalityViewList"})
@RequestMapping("/foreignstudent/")
public class ForeignStudentManagementController {


	@Autowired
	ForeignStudentManagementService foreignStudentManagementService;

	@Autowired
	CommonStudentAbroadService commonStudentAbroadService;

	@RequestMapping(value = "foreignStudentManagement", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request, SessionStatus sessionStatus) {
		
		ComContact university = null;
		ForeignStudentCommand foreignStudentCommand = new ForeignStudentCommand();

		String school = (request.getSession().getAttribute("school") != null)?request.getSession().getAttribute("school").toString():ForeignUniversity.SHYID_EDHEC;
		String scoYear = (request.getSession().getAttribute("scoYear") != null)?request.getSession().getAttribute("scoYear").toString():"";
		// pas de double diplome pour une inscription en septembre par l'université pour le semestre 2
		
		//Défini la periode d'inscription 0=SEM2 1=SEM1
		Calendar cal = Calendar.getInstance();
		int month_today = cal.get(Calendar.MONTH)+1;
		int day_today = cal.get(Calendar.DAY_OF_MONTH);
		String DDSem = (month_today>=9)||(month_today==8 && day_today>=20)?"0":"1"; 
		model.addAttribute("DDSem",DDSem);
		

		if(request.getSession().getAttribute("university") != null) {
			university = (ComContact)request.getSession().getAttribute("university");
					
		ComContact contact = foreignStudentManagementService.findContactById(university.getId());
		model.addAttribute("contact",contact);

		ForeignUniversity foreignUniversity = foreignStudentManagementService.findForeignUniversityByIdAndProgram(contact.getId(),school,scoYear);
		model.addAttribute("foreignUniversity", foreignUniversity);
		
		List<ForeignStudentView> foreignStudentViewList = foreignStudentManagementService.findForeignStudentByUniversityAndStatus(contact.getId(),"I",school,scoYear);
		if (!foreignStudentViewList.isEmpty()){
			for(ForeignStudentView student: foreignStudentViewList){
				if (student.getLastName()!=null) {student.setLastNameUnquoted(student.getLastName().replace("'", "\\'"));}
				if (student.getFirstName()!=null) {student.setFirstNameUnquoted(student.getFirstName().replace("'", "\\'"));}
				if (student.getHelp()!=null) {student.setHelpUnquoted(student.getHelp().replace("'", "\\'"));}
			}
		}
		model.addAttribute("foreignStudentViewList", foreignStudentViewList);
		}else{
			if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
				return "/foreignstudent/indexESPEME";
			}else{
				return "/foreignstudent/index";
			}
		}

		// Gets nationalities
		List<ComCriteriaLView> comCriteriaNationalityViewList = commonStudentAbroadService.findCriteriaByParameters("NATIONALITY",(LocaleContextHolder.getLocale()).getLanguage());
		if(comCriteriaNationalityViewList == null || comCriteriaNationalityViewList.size() == 0) {
			comCriteriaNationalityViewList = commonStudentAbroadService.findCriteriaByParameters("NATIONALITY","fr");
		}
		model.addAttribute("comCriteriaNationalityViewList", comCriteriaNationalityViewList);

		model.addAttribute("foreignStudentCommand",foreignStudentCommand);
		if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
			return "/foreignstudent/foreignstudentmanagementBBA";
		}else{
			return "/foreignstudent/foreignstudentmanagement";
		}
		
	}
	
	@RequestMapping(value = "addStudent", method = RequestMethod.POST)
	protected String addStudent(@RequestHeader(value="X-Requested-With", required=false) String requestedWith,@Valid ForeignStudentCommand foreignStudentCommand, BindingResult result, Model model, HttpServletRequest request) {
		
		String school = (request.getSession().getAttribute("school") != null)?request.getSession().getAttribute("school").toString():"";
		String scoYear = (request.getSession().getAttribute("scoYear") != null)?request.getSession().getAttribute("scoYear").toString():"";
		
		if (result.hasErrors()) {
			model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(requestedWith));
			if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
				return "/foreignstudent/foreignstudentmanagementBBA";
			}else{
				return "/foreignstudent/foreignstudentmanagement";
			}
		}
		 
		
		if(request.getSession().getAttribute("university") != null && foreignStudentCommand != null) {
			ComContact university = (ComContact)request.getSession().getAttribute("university");
			ForeignUniversity foreignUniversity = foreignStudentManagementService.findForeignUniversityByIdAndProgram(university.getId(),school,scoYear);
			
			//Calcul du nombre de places restantes
			int nbSemester = 0;
			int nbDoubleDiploma = 0;
			int nbSemesterTotal = 0;
			int nbDoubleDiplomaTotal = 0;
			int nbSemesterWanted = 0;
			int nbDoubleDiplomaWanted = 0;
			foreignStudentManagementService.updateForeignUniversityCapacity(university.getId(), school, scoYear);
			
			if (foreignUniversity.getUsedSemester()!=null){
				nbSemester = foreignUniversity.getUsedSemester().intValue();
			}
			if (foreignUniversity.getUsedDdSemester()!=null){
				nbDoubleDiploma = foreignUniversity.getUsedDdSemester().intValue();
			}
			if (foreignUniversity.getTotalSemester()!=null){
				nbSemesterTotal = foreignUniversity.getTotalSemester().intValue();
			}
			if (foreignUniversity.getDdSemester()!=null){
				nbDoubleDiplomaTotal = foreignUniversity.getDdSemester().intValue();
			}
			
			//Selection obligatoire d'un niveau
			if (foreignStudentCommand.getStudentLevel()==null){
				result.rejectValue("studentLevel", "foreignStd.levelmandatory");
				if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
					return "/foreignstudent/foreignstudentmanagementBBA";		
				}else{
					return "/foreignstudent/foreignstudentmanagement";
				}
			}
			
			
			//Recherche de la periode, campus, levelCode et dbleDegree du niveau selectionne
			BookNivChoixIncoming bookNivChoixIncoming = foreignStudentManagementService.findBookNivChoixIncomingbyCode(foreignStudentCommand.getStudentLevel(), scoYear);
			if (bookNivChoixIncoming==null){
				result.rejectValue("studentLevel", "foreignStd.levelunavailable");
				if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
					return "/foreignstudent/foreignstudentmanagementBBA";		
				}else{
					return "/foreignstudent/foreignstudentmanagement";
				}
			}
			String periode = bookNivChoixIncoming.getPeriodeCode();
			String campus = bookNivChoixIncoming.getCampus();
			String levelCode = bookNivChoixIncoming.getLevelCode();
			String dbleDegree = bookNivChoixIncoming.getDbleDegree();
			

			if (foreignStudentCommand.getForeignStudentId()!=null){
				ForeignStudent foreignStudent = foreignStudentManagementService.findForeignStudentById(foreignStudentCommand.getForeignStudentId());
				//mise a jour des places en double diplome avant modification
				if (foreignStudent!=null){
					if ( foreignStudent.getDoubleDiploma()!=null && foreignStudent.getDoubleDiploma().equalsIgnoreCase("Y"))
					{
						nbDoubleDiploma--;
					}else{
						//mise a jour des places en semestre avant modification
						String period = foreignStudent.getPeriodeCode();//foreignStudent.getFall() + foreignStudent.getWinter() + foreignStudent.getSpring() + "";
						
						/*if (period.equalsIgnoreCase("111")){
							nbSemester-=2;
						}else{
							if (!period.equalsIgnoreCase("000")&&!period.equalsIgnoreCase("00")&&!period.equalsIgnoreCase("0")&&!period.equalsIgnoreCase("")){
								nbSemester--;
							}
						}*/
						
						if (period.equalsIgnoreCase("S0")){
							nbSemester-=2;
						}else{
							if (period.equalsIgnoreCase("S1")||period.equalsIgnoreCase("S2")){
								nbSemester--;
							}
						}
					}
				}
			}

			if (dbleDegree.equalsIgnoreCase("Y")){
				  nbDoubleDiplomaWanted++;
			}else{
				if (periode.equalsIgnoreCase("S0")){
					nbSemesterWanted+=2;
				}else{
					nbSemesterWanted++;
				}
			}
			// Le nombre de places en echange double diplome est depasse
			if (nbDoubleDiplomaWanted>nbDoubleDiplomaTotal-nbDoubleDiploma){
				result.rejectValue("semesterUsed", "foreignStd.noddAvailable");
				if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
					return "/foreignstudent/foreignstudentmanagementBBA";		
				}else{
					return "/foreignstudent/foreignstudentmanagement";
				}
			}
			// Le nombre de places en echange simple est depasse
			if (nbSemesterWanted>nbSemesterTotal-nbSemester){
				if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
					result.rejectValue("semesterUsed", "foreignStd.noSemeseterAvailableBBA");
					return "/foreignstudent/foreignstudentmanagementBBA";		
				}else{
					result.rejectValue("semesterUsed", "foreignStd.noSemeseterAvailable");
					return "/foreignstudent/foreignstudentmanagement";
				}
			}
			
			//Existance de l'adresse email pour eviter qu'un meme etudiant soit inscrit dans deux programmes
			if (foreignStudentCommand.getStudentEmail()!=null){
				String cntId = foreignStudentManagementService.existForeignStudentEmail(foreignStudentCommand.getStudentEmail());
				if (foreignStudentCommand.getForeignStudentId()==null && cntId!=null){
					result.rejectValue("studentEmail", "foreignStd.usedemail");
					if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
						return "/foreignstudent/foreignstudentmanagementBBA";	
					}else{
						return "/foreignstudent/foreignstudentmanagement";
					}
				}
				if (foreignStudentCommand.getForeignStudentId()!=null && cntId!=null && !(foreignStudentCommand.getForeignStudentId().equalsIgnoreCase(cntId))){
					result.rejectValue("studentEmail", "foreignStd.usedemail");
					if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
						return "/foreignstudent/foreignstudentmanagementBBA";	
					}else{
						return "/foreignstudent/foreignstudentmanagement";
					}
				}
			}
			
			//Depassement des limites d'admission pour un niveau sur un campus ou une periode pour une annee scolaire
			/*String niveau = foreignStudentCommand.getStudentLevel();
			
			if (niveau==null){
				if (foreignStudentCommand.getPeriod().equalsIgnoreCase("111")){
					niveau = foreignStudentCommand.getFullS1studentLevel();
				}
				if (foreignStudentCommand.getPeriod().equalsIgnoreCase("110")){
					niveau = foreignStudentCommand.getS1studentLevel();
				}
				if (foreignStudentCommand.getPeriod().equalsIgnoreCase("011")){
					niveau = foreignStudentCommand.getS2studentLevel();
				}
			}*/
			String message = foreignStudentManagementService.existScoFSProgramLimit(school,scoYear,campus,levelCode,periode);
			if (message!=null){
				result.rejectValue("semesterUsed","ErrPerso", message);
				
				if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
					return "/foreignstudent/foreignstudentmanagementBBA";
				}else{
					return "/foreignstudent/foreignstudentmanagement";
				}				
			}
			
				
			String resultat = foreignStudentManagementService.createForeignStudent(university.getId(), school, scoYear, (ForeignStudentCommand)foreignStudentCommand);
			if (resultat!="OK"){
				result.rejectValue("semesterUsed", "foreignStd.error");
				if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
					return "/foreignstudent/foreignstudentmanagementBBA";
				}else{
					return "/foreignstudent/foreignstudentmanagement";
				}
			}
			
		}

		return "redirect:/foreignstudent/foreignStudentManagement";	
					
	}


	@RequestMapping(value = "deleteforeignStudent", method = RequestMethod.GET)
	protected String deleteforeignStudent(Model model, HttpServletRequest request, SessionStatus sessionStatus) {

		if(request.getParameter("foreignStudentId") != null) {
			foreignStudentManagementService.deleteForeignStudent(request.getParameter("foreignStudentId"));
		}
		return "redirect:/foreignstudent/foreignStudentManagement";
	}

	@RequestMapping(value = "deleteforeignStudentAdmin", method = RequestMethod.GET)
	protected String deleteforeignStudentAdmin(Model model, HttpServletRequest request, SessionStatus sessionStatus) {

		if(request.getParameter("foreignStudentId") != null) {
			foreignStudentManagementService.deleteForeignStudent(request.getParameter("foreignStudentId"));
		}

		if(request.getParameter("school")=="BBA") {
			return "redirect:/foreignstudent/indexBBA";
		}else{
			return "redirect:/foreignstudent/index";
		}
	}

	@RequestMapping(value = "registerforeignStudent", method = RequestMethod.GET)
	protected String registerforeignStudent(Model model, HttpServletRequest request, SessionStatus sessionStatus) {

		if(request.getParameter("foreignStudentId") != null) {
			foreignStudentManagementService.registerForeignStudent(request.getParameter("foreignStudentId"));
		}

		if(request.getParameter("school")=="BBA") {
			return "redirect:/foreignstudent/indexBBA";
		}else{
			return "redirect:/foreignstudent/index";
		}
	}

	@RequestMapping(value = "confirmforeignStudent", method = RequestMethod.GET)
	protected String confirmforeignStudent(Model model, HttpServletRequest request, SessionStatus sessionStatus) {

		if(request.getParameter("foreignStudentId") != null) {
			foreignStudentManagementService.confirmForeignStudent(request.getParameter("foreignStudentId"));
		}

		if(request.getParameter("school")=="BBA") {
			return "redirect:/foreignstudent/indexBBA";
		}else{
			return "redirect:/foreignstudent/index";
		}
	}

	@RequestMapping(value = "cancelStudent", method = RequestMethod.GET)
	protected String cancelStudent(Model model, HttpServletRequest request, SessionStatus sessionStatus) {

		if(request.getParameter("foreignStudentId") != null) {
			foreignStudentManagementService.cancelStudent(request.getParameter("foreignStudentId"));
		}

		if(request.getParameter("school")=="BBA") {
			return "redirect:/foreignstudent/indexBBA";
		}else{
			return "redirect:/foreignstudent/index";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		String format = "dd/MM/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(df,
				true, format.length()));
		binder.registerCustomEditor(String.class, new StringUTF8Editor(true));
	}
	
}
