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
<meta http-equiv="refresh" content="10; URL=http://www.edhec.com">

<title>SSO MS Campus</title>

</head>
<body>

<%
 
//YOU SHOULD DYNAMICALLY CHANGE THE MAIL - ACCORDING TO LOGED IN STUDENT
String Mail = (String) pageContext.findAttribute("studentEmail");
%>

<p>
Your email <%=Mail%> is refused by Microsoft Campus
If your browser doesn't automatically go there within a few seconds, you may want to go to <a href="<% out.println("http://www.edhec.com");  %>"> EDHEC Campus </a>
</p>
</body>
</html>