package com.edhec.commonedhecapps.web;

import com.edhec.commonedhecapps.model.User;
import com.edhec.commonedhecapps.model.common.ComContact;
import com.edhec.commonedhecapps.model.foreignstudent.ForeignUniversity;
import com.edhec.commonedhecapps.service.CommonStudentAbroadService;
import com.edhec.commonedhecapps.service.ForeignStudentManagementService;
import com.edhec.commonedhecapps.service.UserService;
import com.edhec.commonedhecapps.service.impl.ForeignStudentManagementServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

@Controller
@RequestMapping({"/"})
public class GlobalController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalController.class);

    @Autowired
    UserService userService;

    @Autowired
    ForeignStudentManagementService foreignStudentManagementService;

    @Autowired
    CommonStudentAbroadService commonStudentAbroadService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        logger.info("Acces Anonyme");
        return "anonyme";
    }

    @RequestMapping(value = "/edhec", method = RequestMethod.GET)
    public String home_edhec(Model model, HttpServletRequest request) {
        logger.info("Acces Anonyme");
        String urlReturn = "anonyme";
        if (request.getParameter("open") != null && request.getParameter("open").toString().equalsIgnoreCase("Y")) {
            request.getSession().setAttribute("open", "Y");
            urlReturn = "anonymeopen";
            logger.info("Acces Anonyme EDHEC open=Y");
        }
        if (request.getParameter("admin") != null && request.getParameter("admin").toString().equalsIgnoreCase("Y")) {
            request.getSession().setAttribute("open", "Y");
            request.getSession().setAttribute("admin", "Y");
            urlReturn = "anonymeopen";
            logger.info("Acces Anonyme EDHEC admin=Y");
        }

        return urlReturn;
    }

    @RequestMapping(value = "/bba", method = RequestMethod.GET)
    public String home_bba(Model model, HttpServletRequest request) {
        logger.info("Acces Anonyme");
        String urlReturn = "anonymeBBA";
        if (request.getParameter("open") != null && request.getParameter("open").toString().equalsIgnoreCase("Y")) {
            request.getSession().setAttribute("open", "Y");
            urlReturn = "anonymeBBAopen";
            logger.info("Acces Anonyme BBA open=Y");
        }
        if (request.getParameter("admin") != null && request.getParameter("admin").toString().equalsIgnoreCase("Y")) {
            request.getSession().setAttribute("open", "Y");
            request.getSession().setAttribute("admin", "Y");
            urlReturn = "anonymeBBAopen";
            logger.info("Acces Anonyme BBA admin=Y");
        }
        return urlReturn;
    }

    @GetMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication currentUser = ctx.getAuthentication();
            //UserDetails userDetails = (UserDetails) currentUser.getPrincipal();
            if (currentUser.getPrincipal() == null || currentUser.getPrincipal().toString().contentEquals("anonymousUser")) {
                logger.info("Authentification Anonyme EDHEC");
                return "anonyme";
            }
            User user = (User) currentUser.getPrincipal();
            if (user==null){
                logger.info("User disconnected");
                return "/foreignstudent/disconnected";
            }

            //User user = (User) userService.loadUserByUsername(userDetails.getUsername());
            String urlReturn = "notuniv";
            ResourceBundle bundle = ResourceBundle.getBundle("messages");
            ComContact contact = commonStudentAbroadService.findContactById(user.getCntId());

            if (contact == null) {
                logger.info("userName : " + user.getUsername() + " (" + user.getCntId() + ") est inconnu dans com_contact");
                urlReturn = "notuniv";
            }
            String open = "";

            ForeignUniversity foreignUniversity = foreignStudentManagementService.findForeignUniversityById(contact.getId());
            if (foreignUniversity != null) {
                if (user.getUsername().contentEquals("univ0")) {
                    request.getSession().setAttribute("open", "Y");
                    open = "&open=Y";
                }
                if (request.getParameter("open") != null && request.getParameter("open").toString().equalsIgnoreCase("Y")) {
                    request.getSession().setAttribute("open", "Y");
                    open = "&open=Y";
                }
                if (request.getParameter("admin") != null && request.getParameter("admin").toString().equalsIgnoreCase("Y")) {
                    request.getSession().setAttribute("open", "Y");
                    request.getSession().setAttribute("admin", "Y");
                    open = "&admin=Y";
                }
                urlReturn = "redirect:/foreignstudent/index?school=edhec";
            }

            logger.info("userName : " + user.getUsername() + " redirected to " + urlReturn);
            return urlReturn;
    }

    @GetMapping(value = "/loginBBA")
    public String loginBBA(Model model, HttpServletRequest request) {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication currentUser = ctx.getAuthentication();
            //UserDetails userDetails = (UserDetails) currentUser.getPrincipal();
            if (currentUser.getPrincipal() == null || currentUser.getPrincipal().toString().contentEquals("anonymousUser")) {
                logger.info("Authentification Anonyme BBA");
                return "anonymeBBA";
            }
            User user = (User) currentUser.getPrincipal();
            if (user==null){
                logger.info("User disconnected");
                return "/foreignstudent/disconnected";
            }

            //User user = (User) userService.loadUserByUsername(userDetails.getUsername());
            String urlReturn = "notuniv";
            ResourceBundle bundle = ResourceBundle.getBundle("messages");
            ComContact contact = commonStudentAbroadService.findContactById(user.getCntId());

            if (contact == null) {
                logger.info("userName : " + user.getUsername() + " (" + user.getCntId() + ") est inconnu dans com_contact");
                urlReturn = "notuniv";
            }
            String open = "";

            ForeignUniversity foreignUniversity = foreignStudentManagementService.findForeignUniversityById(contact.getId());
            if (foreignUniversity != null) {
                if (user.getUsername().contentEquals("univ0")) {
                    request.getSession().setAttribute("open", "Y");
                    open = "&open=Y";
                }
                if (request.getParameter("open") != null && request.getParameter("open").toString().equalsIgnoreCase("Y")) {
                    request.getSession().setAttribute("open", "Y");
                    open = "&open=Y";
                }
                if (request.getParameter("admin") != null && request.getParameter("admin").toString().equalsIgnoreCase("Y")) {
                    request.getSession().setAttribute("open", "Y");
                    request.getSession().setAttribute("admin", "Y");
                    open = "&admin=Y";
                }
                urlReturn = "redirect:/foreignstudent/index?school=bba";
            }

            logger.info("userName : " + user.getUsername() + " redirected to " + urlReturn);
            return urlReturn;
    }

    @GetMapping(value = "/summer")
    public String summer(Model model, HttpServletRequest request) {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication currentUser = ctx.getAuthentication();
            //UserDetails userDetails = (UserDetails) currentUser.getPrincipal();
            if (currentUser.getPrincipal() == null || currentUser.getPrincipal().toString().contentEquals("anonymousUser")) {
                logger.info("Authentification Anonyme Summer");
                return "anonymeSummer";
            }else{
                User user = (User) currentUser.getPrincipal();
                if (user==null){
                    logger.info("User disconnected");
                    return "/foreignstudent/disconnected";
                }
                //User user = (User) userService.loadUserByUsernam e(userDetails.getUsername());
                String urlReturn = "notuniv";
                ResourceBundle bundle = ResourceBundle.getBundle("messages");
                ComContact contact = commonStudentAbroadService.findContactById(user.getCntId());

                if (contact == null) {
                    logger.info("userName : " + user.getUsername() + " (" + user.getCntId() + ") est inconnu dans com_contact");
                    urlReturn = "notuniv";
                }

                ForeignUniversity foreignUniversity = foreignStudentManagementService.findForeignUniversityById(contact.getId());
                if (foreignUniversity != null) {
                    if (user.getUsername().contentEquals("univ0")) {
                        request.getSession().setAttribute("open", "Y");
                    }
                    if (request.getParameter("open") != null && request.getParameter("open").toString().equalsIgnoreCase("Y")) {
                        request.getSession().setAttribute("open", "Y");
                    }
                    if (request.getParameter("admin") != null && request.getParameter("admin").toString().equalsIgnoreCase("Y")) {
                        request.getSession().setAttribute("open", "Y");
                        request.getSession().setAttribute("admin", "Y");
                    }
                    urlReturn = "redirect:/foreignstudent/index?school=bba";
                }
                logger.info("userName : " + user.getUsername() + " redirected to " + urlReturn);
                return urlReturn;
            }

    }

}
