<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>
<meta http-equiv="refresh" content="2; URL=http://app.edhec.edu/ons/SSOConnect?q=doConnect">

<title>SSO MS Campus</title>

</head>
<body>

<%
 
//YOU SHOULD DYNAMICALLY CHANGE THE MAIL - ACCORDING TO LOGED IN STUDENT
//String Mail ="test@microsoft.com";
String Mail = (String) pageContext.findAttribute("studentEmail");
//Replace this with your own confidential School Key
String Key = (String) pageContext.findAttribute("SSOkey");
// MAKE SURE YOU INDICATE THE CORRECT REFERER
//String Referer =  "http://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
String Referer =  "http://"+request.getServerName()+"/ons/SSOConnect";
%>

<%
    String doConnectt ;
	doConnectt = request.getParameter("q");
	request.setAttribute("doConnectt", doConnectt);
	if(doConnectt!=null){
		String reponse; 
		out.println(reponse = authenticateUser(Mail,Key,Referer));		
		out.println( "<br> we do need to connect=" +doConnectt);
		if (reponse.startsWith("http")) {
			response.sendRedirect(reponse);
		}
	}
	 %>	
      
<%!	 
	 public String authenticateUser (String mail, String key, String referer )
	 {
	String outputString ="";
	String sb = "https://campus.etudiants.ms/secure/AuthenticateUser.aspx";
	//Add queries
	sb +="?email="+mail+"&key="+key+"&referrer="+referer;
	
	URL u ;
	String charset = "UTF-8";

	try {
		u= new URL(sb);
		
		try {
		URLConnection uc = u.openConnection();
		uc.setRequestProperty("Accept-Charset", charset);
		//uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

		//InputStream response = uc.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				String res; 
				StringBuilder sbldr = new StringBuilder();
		    	while ((res = in.readLine()) != null) {
		    		sbldr.append(res);
		    	} 
				in.close();

		outputString = sbldr.toString(); ; //"success";
		}
		catch (IOException myIOExc) {
			//error connecting to URL  // IO ERROR
			outputString = myIOExc.getMessage(); //"IO ERRROR";
		}
		
	}


catch (MalformedURLException exc){
		//the url is not right
		outputString = "mlalformed URL";
	}

		 return outputString;
	 }
%>
<p>
If your browser doesn't automatically go there within a few seconds, you may want to go to <a href="<% out.println(Referer+"?q=doConnect");  %>"> Microsoft Campus </a>
</p>
</body>
</html>