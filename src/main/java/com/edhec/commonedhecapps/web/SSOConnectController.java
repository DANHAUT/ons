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

@Controller
@RequestMapping("/SSOConnect")
public class SSOConnectController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(SSOConnectController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) 
	{
		
		String urlReturn="/SSOcampusConnect";
		
		logger.info(" SSO redirected to "+ urlReturn);
		
		return urlReturn;
	}
}
