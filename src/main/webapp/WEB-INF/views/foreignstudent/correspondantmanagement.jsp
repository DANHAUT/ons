<%@ include file="header.jsp" %>

<div id="loadingicon" class="loadingicon"  style="display:none;"><img alt="" src="<c:url value="/img/ajax-loader.gif"/>"> Chargement ...</div>

<style type="text/css">
div#divTabInfoOK{
	text-align: center;
    border:2px solid #009900;
    color:#FFFFFF ;
    background-color:#00BB00;
    padding:5px;
    margin: 0px 5px 5px 5px; 
	margin-left: auto;
    margin-right: auto;
    width: 250px;
}
div#divTabInfoNOK{
	text-align: center;
    border:2px solid #990000;
    color:#FFFFFF ;
    background-color:#BB0000;
    padding:5px;
    margin: 0px 5px 5px 5px; 
	margin-left: auto;
    margin-right: auto;
    width: 250px;
}
</style>

<div class="style1"><!-- debut de Ligne -->
	<div class="style1_titre_deco"><h3><spring:message code="foreignStd.editCorrespondantTitle"/></h3></div>
</div>
<div class="style1"><!-- debut de Ligne -->
<spring:message code="foreignStd.mandatoryfields"/>
</div>
<!-- debut  de la div du contenu  -->
<br/>

<form:form modelAttribute="correspondantCommand" action="addCorrespondant" method="post">
	
	<!--  BLOC CORRESPONDANT PERSONAL INFO -->
		<div class="conteneursousparagraphe2">
			<table id="tan" >
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.firstName"/>*: </span></td>
					<td> 
						<form:input path='firstName' /><form:errors cssClass="error" path="firstName" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.lastName"/>*: </span></td>
					<td> 
						<form:input path='lastName' /><form:errors cssClass="error" path="lastName" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.coordemail"/>*: </span></td>
					<td>
						<form:input path='email' size="50"/><form:errors cssClass="error" path="email" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.coordTitle"/>*: </span></td>
					<td>
						<form:input path='title' size="50"/><form:errors cssClass="error" path="title" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.telephone"/>: </span></td>
					<td>
						<form:input path='phone'/><form:errors cssClass="error" path="phone" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><b><span class="gamay"><spring:message code="foreignStd.univinfo"/></span></b></td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.erasmus"/>: </span></td>
					<td>
						<c:if test="${isErasmus == 'Y'}">
							<form:input path='codeErasmus' /><form:errors cssClass="error" path="codeErasmus" />
						</c:if>
						<c:if test="${isErasmus == 'N'}">
							<form:input path='codeErasmus' readonly="TRUE"/><form:errors cssClass="error" path="codeErasmus" />
						</c:if>
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.address"/>*: </span></td>
					<td>
						<form:input path='address' size="50"/><form:errors cssClass="error" path="address" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.zipcode"/>*: </span></td>
					<td>
						<form:input path='zipcode' /><form:errors cssClass="error" path="zipcode" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.city"/>*: </span></td>
					<td>
						<form:input path='town' /><form:errors cssClass="error" path="town" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.country"/>*: </span></td>
					<td>
						<form:input path='country' readonly="TRUE"/><form:errors cssClass="error" path="country" />
					</td>
				</tr>
				<tr class="odd">
					<td width="200"><span class="gamay"><spring:message code="foreignStd.softwares"/>: </span></td>
					<td>
						<form:checkbox path='softwares' value="EMAILCOPY"/><form:errors cssClass="error" path="softwares" />
					</td>
				</tr>
				<c:if test="${isErasmus == 'Y'}">
					<tr class="odd">
						<td width="200"><span class="gamay">Tick the box if you use the Erasmus Dashboard to edit the Online Learning Agreement</span><br/>
						</td>
						<td>
							<form:checkbox path='ola' value="OLA"/><form:errors cssClass="error" path="ola" />
						</td>
					</tr>
					<tr class="odd">
						<td colspan="2"><b><span class="gamay">Either way please insert Virginie Ghesquiere (virginie.ghesquiere@edhec.edu) as contact for Lille campus  and Zuzana SEDLACKOVA  (zuzana.sedlackova@edhec.edu ) as contact for Nice campus</span></b></td>
					</tr>
				</c:if>
			</table>
		</div>
		<!-- FIN BLOC CORRESPONDANT PERSONAL INFO -->
		
		<table>
			<tr class="odd">
				<td colspan="2">
						<div class="ligne"><!-- debut de Ligne -->
							 <input id="validate" class="input_submit" type="submit" value="<spring:message code="foreignStd.create"/>" />
							 <input id="return" class="input_submit" value="<spring:message code="foreignStd.cancel"/>" />
						</div>
					<div class="ligne"></div>
				</td>
				
			</tr>
		</table>
		
</form:form>
<script language="JavaScript" type="text/javascript"> 

$(document).ready(function(){
		
	$("#validate").click(function() { 
		$(this).blur();
        location.href = "javascript:document.forms['correspondantCommand'].submit();";
    }); 

	$("#return").click(function() { 
		$(this).blur();
        location.href = "./index";
    }); 
});
</script>
<%@ include file="footer.jsp" %>
