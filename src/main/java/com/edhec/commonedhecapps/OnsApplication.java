package com.edhec.commonedhecapps;

import com.edhec.commonedhecapps.service.UserService;
//import net.unicon.cas.client.configuration.EnableCasClient;
//import org.springframework.stereotype.Controller;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.servlet.http.HttpSessionEvent;
import javax.sql.DataSource;


//@Controller
//@EnableCasClient
@SpringBootApplication
public class OnsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OnsApplication.class, args);
    }

    @Value("${sec.cas.server}")
    private String casServer;

    @Value("${sec.app.server}")
    private String appServer;

    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService("http://"+appServer+"/ons/login/cas");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    @Primary
    public AuthenticationEntryPoint authenticationEntryPoint(ServiceProperties sP) {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl("https://"+casServer+"/cas/login");
        entryPoint.setServiceProperties(sP);
        return entryPoint;
    }

    @Bean
    public TicketValidator ticketValidator() {
        return new Cas20ServiceTicketValidator("https://"+casServer+"/cas");
    }
    // Last version = Cas30ServiceTicketValidator

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(ticketValidator());
        //provider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
        //   provider.setUserDetailsService(
        //      s -> new User("casuser", "Mellon", true, true, true, true,
        //        AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
        provider.setUserDetailsService(userService);
        provider.setKey("CAS_PROVIDER_LOCALHOST_8080");
        return provider;
    }


    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(
                "https://"+casServer+"/cas/logout", securityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout"); // change the default (/logout) URL -
        return logoutFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix("https://"+casServer+"/cas");
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }


    @Bean
    public JdbcDaoImpl jdbcUserService() {
        JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
        jdbcDaoImpl.setEnableGroups(true);
        jdbcDaoImpl.setEnableAuthorities(true);
        jdbcDaoImpl.setDataSource(dataSource);
        return jdbcDaoImpl;
    }

    @Bean
    public UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken> authenticationUserDetailsService() {
        UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken> userDetailsByName = new UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken>();
        userDetailsByName.setUserDetailsService(jdbcUserService());
        return userDetailsByName;
    }

    @EventListener
    public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(HttpSessionEvent event) {
        return new SingleSignOutHttpSessionListener();
    }


    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages", "classpath:application");
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    //Use to read custom validation messages ... to work with message= on annotations for example
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

}
