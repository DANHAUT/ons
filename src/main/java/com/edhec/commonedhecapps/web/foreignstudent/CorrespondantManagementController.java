package com.edhec.commonedhecapps.web.foreignstudent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.edhec.commonedhecapps.model.foreignstudent.ForeignUniversity;
import com.edhec.commonedhecapps.service.ForeignStudentManagementService;
import com.edhec.commonedhecapps.web.AjaxUtils;
import com.edhec.commonedhecapps.web.foreignstudent.command.CorrespondantCommand;
import com.edhec.commonedhecapps.web.StringUTF8Editor;
import com.edhec.commonedhecapps.model.common.ComArtificialPerson;
import com.edhec.commonedhecapps.model.common.ComContact;
import com.edhec.commonedhecapps.model.common.ComJob;
import com.edhec.commonedhecapps.model.common.ComMail;
import com.edhec.commonedhecapps.model.common.ComPhone;

@Controller
@SessionAttributes({"correspondantCommand","university","school","scoYear","isErasmus"})
@RequestMapping("/foreignstudent/")
public class CorrespondantManagementController {

	@Autowired
	ForeignStudentManagementService foreignStudentManagementService;
	
 	@RequestMapping(value = "correspondantManagement", method = RequestMethod.GET)
	protected String index( Model model, HttpServletRequest request, SessionStatus sessionStatus) {
		
		ComContact university = null;
		String coordType = null;
		String school = (String) request.getSession().getAttribute("school");
		CorrespondantCommand correspondantCommand = new CorrespondantCommand();
		if(request.getSession().getAttribute("university") != null) {
			university = (ComContact)request.getSession().getAttribute("university");
			if (request.getSession().getAttribute("school")==null){
				school = ForeignUniversity.SHYID_EDHEC;
			}
			coordType = (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME))?ForeignUniversity.FUNCTION_COORD_ESPEME:ForeignUniversity.FUNCTION_COORD_EDHEC;
			//Affichage du code Erasmus de l'annee en cours
			Calendar cal = new GregorianCalendar();
			ForeignUniversity foreignUniversity = foreignStudentManagementService.findForeignUniversityByIdAndProgram(university.getId(),school,cal.get(Calendar.YEAR) + 1 + "");
			if (foreignStudentManagementService.findErasmusByLibang(foreignUniversity.getCountryName())){
				correspondantCommand.setCodeErasmus(foreignUniversity.getCodeErasmus());
				model.addAttribute("isErasmus","Y");
			}else{
				correspondantCommand.setCodeErasmus("N/A");
				model.addAttribute("isErasmus","N");
			}
			ComArtificialPerson comArtificialPerson = foreignStudentManagementService.findComArtificialPersonById(university.getId());
			if(comArtificialPerson != null) {
				for (ComJob comJob : comArtificialPerson.getComJobList()) {
					if (comJob.getDateTo()==null && comJob.getCrtIdFunction()!=null && comJob.getCrtIdFunction().equalsIgnoreCase(coordType))
					{
						correspondantCommand.setFirstName(comJob.getComNaturalPerson().getFirstName());
						correspondantCommand.setLastName(comJob.getComNaturalPerson().getComContact().getName());
						correspondantCommand.setSoftwares(comJob.getComNaturalPerson().getSoftwares());
						ComMail comMail = foreignStudentManagementService.findComContactEmail(comJob.getMalId());
						if (comMail!=null && comMail.getMail()!=null && !comMail.getMail().trim().equalsIgnoreCase("")){
							correspondantCommand.setEmail(comMail.getMail());
						}
						correspondantCommand.setTitle(comJob.getDescription());
						ComPhone comPhone = foreignStudentManagementService.findComContactPhone(comJob.getPhnId());
						if (comPhone!=null && comPhone.getPhone()!=null && !comPhone.getPhone().trim().equalsIgnoreCase("")){
							correspondantCommand.setPhone(comPhone.getPhone());
						}
					}
				}
			}
			correspondantCommand.setAddress(foreignUniversity.getAddress());
			correspondantCommand.setZipcode(foreignUniversity.getZipcode());
			correspondantCommand.setTown(foreignUniversity.getTown());
			correspondantCommand.setCountry(foreignUniversity.getCountryName());
			correspondantCommand.setOla(foreignUniversity.getOla());
		}else{
				return "/foreignstudent/notuniv";
		}
		model.addAttribute("correspondantCommand",correspondantCommand);
				
		if (coordType.equalsIgnoreCase(ForeignUniversity.FUNCTION_COORD_ESPEME)){
			return "/foreignstudent/correspondantmanagementBBA";
		}else{
			return "/foreignstudent/correspondantmanagement";
		}
	}
 	
	@RequestMapping(value = "addCorrespondant", method = RequestMethod.POST)
	protected String addCorrespondant(@RequestHeader(value="X-Requested-With", required=false) String requestedWith,@Valid CorrespondantCommand correspondantCommand, BindingResult result, Model model, HttpServletRequest request) {

		String school = (request.getSession().getAttribute("school") != null)?request.getSession().getAttribute("school").toString():"";
		
		if (result.hasErrors()) {
			model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(requestedWith));
			if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
				return "/foreignstudent/correspondantmanagementBBA";
			}else{
				return "/foreignstudent/correspondantmanagement";
			}
		}
		 
		if(request.getSession().getAttribute("university") != null) {
			ComContact university = (ComContact)request.getSession().getAttribute("university");
			String resultat = foreignStudentManagementService.createOrUpdateCorrespondantManagement(university.getId(), school, (CorrespondantCommand)correspondantCommand);
			if (resultat!="OK"){
				result.rejectValue("email", "foreignStd.usedemail");
				if (school.equalsIgnoreCase(ForeignUniversity.SHYID_ESPEME)){
					return "/foreignstudent/correspondantmanagementBBA";
				}else{
					return "/foreignstudent/correspondantmanagement";
				}
				
			}
		}

		request.getSession().setAttribute("correspondantDataSaved", "yes");
		
		return "redirect:/foreignstudent/index";
									
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
