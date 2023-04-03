<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>
	<title>Online Application for Incoming Student</title>
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="icon" href="<c:url value="/img/favicon.ico" />" />
	<link rel="stylesheet" href="<c:url value="/styles/common.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/datatable.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/div.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/docpanel.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/fieldset.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/formulaire.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/jquery.loadmask.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/tooltips.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/smoothness/jquery-ui-1.8.9.custom.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/tabmenu/tab-menu.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/nav.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/nav2.css" />" type="text/css" media="screen, projection" />

<script type="text/javascript" src="<c:url value="/scripts/jquery-1.4.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery-ui-1.8.9.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery.tools.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/jquery.selectboxes.pack.js" />"></script>
	
</head>
<body>

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
						<li class='left active'><a href='login' title='<spring:message code="foreignStd.connect"/>'><img width="16px" height="16px" src="<c:url value="/img/default_thumb.png" />" /><spring:message code="foreignStd.connect"/> EDHEC</a></li>
						<li class="left " style="float:right;"><a href="<c:url value="./?locale=fr"/>"><img width="25" height="15" src="<c:url value="/img/FR.png" />" alt="France" id="FLG_FR" class="FLG_around"/></a></li>
						<li class="left " style="float:right;"><a href="<c:url value="./?locale=en"/>"><img width="25" height="15" src="<c:url value="/img/EN.png" />" alt="English" id="FLG_EN" class="FLG_around"/></a></li>
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
 			 
<div id="availableList" style="margin-bottom:10px;"><!-- debut de Ligne -->
	<div class="style1_titre_deco"><h3><spring:message code="foreignStd.helpconnect"/></h3>
	</div>
</div>

			</div> <!-- End tabDisplay -->
		</div> <!-- End tabsForms -->

</div> <!-- End main-body -->

</div> <!-- End wrapper_borders -->

</div> <!-- End forms -->

</body>
</html>
