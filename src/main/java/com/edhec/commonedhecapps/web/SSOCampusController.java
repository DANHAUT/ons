package com.edhec.commonedhecapps.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"studentEmail","SSOkey"})
@RequestMapping("/SSOCampus")
public class SSOCampusController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(SSOCampusController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) 
	{
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication currentUser = ctx.getAuthentication();
		UserDetails userDetails = (UserDetails) currentUser.getPrincipal();		
			
		String studentEmail = userDetails.getUsername();
		model.addAttribute("studentEmail",studentEmail);
		
		String urlReturn="SSOrefused";
		String SSOkey="b78867a5-41e9-4a95-899e-3b63a890af12";
		
		if (studentEmail.endsWith("@edhec.com")||studentEmail.endsWith("@espeme.com")||studentEmail.endsWith("@edhec.edu"))
		{
			model.addAttribute("SSOkey",SSOkey);
			urlReturn="/SSOcampusConnect";
			logger.info("userName : "+ studentEmail + " SSO doConnectt "+ urlReturn);
		}else{
			logger.info("userName : "+ studentEmail + " refused.");
		}
		
		return urlReturn;
	}
}
