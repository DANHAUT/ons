<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1" prefix="str" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:if test="${!ajaxRequest}">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>
	<title>Online Application for Incoming Student</title>
	<meta http-equiv="Pragma" content="no-cache" />
	<link rel="icon" href="<c:url value="/img/favicon.ico" />" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="<c:url value="/styles/common.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/datatable.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/div.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/docpanel.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/fieldset.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/formulaire.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/jquery.loadmask.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/tooltips.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/tabmenu/tab-menu.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/nav.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/nav2.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/ui-darkness/jquery-ui-1.8.17.custom.css" />" type="text/css" media="screen, projection" />

<script type="text/javascript" src="https://getfirebug.com/firebug-lite.js"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery-1.4.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery-ui-1.8.17.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/ui.datepicker-en.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery.tools.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery.selectboxes.pack.js" />"></script>
	
</head>
<body>

</c:if>
	<div id="forms">
		<div id="wrapper_borders">
	
		<div id="header">
			<img title="<spring:message code="foreignStd.version"/>" src="<c:url value="/img/banniere_trans.png"/>" />
			<div id="TitleHeader">
			</div>
		</div>
	
		<div id='primary_nav'>
			<div id='primary_left'>
				<div id='primary_right'>		
					<ul>
						<li class='left active'><a href='#' title='<spring:message code="foreignStd.welcome"/>' ><img width="16px" height="16px" src="<c:url value="/img/default_thumb.png" />" /><spring:message code="foreignStd.welcome"/>, <security:authentication property="principal.firstName" /> <security:authentication property="principal.lastName" /></a></li>
						<li class="left " style="float:right;" ><a href='<c:url value="/logout"/>' title='Deconnexion'> <spring:message code="foreignStd.logout"/></a></li>
					</ul>
				</div>
			</div>
		</div>
	<div id="main-body">
	
		
		<div id="tabsForms">
			<div id="en-tete">
			    <ul>
			    	<li id="actif">
			    		<span><spring:message code="foreignStd.helptitle"/></span>
			    	</li>
			    </ul>
			    
 			 </div>
 			 <div id="tab-display">
