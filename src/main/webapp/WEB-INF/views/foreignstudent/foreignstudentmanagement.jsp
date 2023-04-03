<%@ include file="header.jsp" %>

<div id="loadingicon" class="loadingicon"  style="display:none;"><img alt="" src="<c:url value="/img/ajax-loader.gif"/>"></div>

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
.auto-style1 {
    width: 200px;
    text-align: center;
}
.auto-style2 {
    width: 50px;
    text-align: center;
}
</style>

<div class="ligne"><!-- debut de Ligne -->
	<input id="return" class="input_submit" value="<spring:message code="foreignStd.cancel"/>" />
</div>
<div class="style1"><!-- debut de Ligne -->
	<div class="style1_titre_deco"><h3><font style="text-transform: none;"><spring:message code="foreignStd.helptitle"/>
	</font></h3>
	</div>

<spring:message code="foreignStd.mandatoryfields"/>
</div>

<!-- 
<div id="divTabInfo"><spring:message code="foreignStd.noSemeseterAvailable"/></div>
<c:if test="${createLodforeignStudentError != null}">
	<div id="divTabInfo"><spring:message code="${createLodforeignStudentError}"/></div>
</c:if>
 -->

<form:form modelAttribute='foreignStudentCommand' action="addStudent" method="post">
	<form:hidden path="foreignStudentId" />
	<form:hidden path="semesterUsed"  />
	<br/>
			<table id="tan" >
				
				<tr class="odd">
					<td class="societe" width="200"><span class="gamay"><spring:message code="foreignStd.lastName"/>* (no accents or special characters): </span></td>
					<td> 
						<form:input path='studentLastName' size="25"/><form:errors cssClass="error" path="studentLastName" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="200"><span class="gamay"><spring:message code="foreignStd.firstName"/>* (no accents or special characters): </span></td>
					<td> 
						<form:input path='studentFirstName' size="25"/><form:errors cssClass="error" path="studentFirstName" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="200"><span class="gamay"><spring:message code="foreignStd.title"/>*: </span></td>
					<td> 
						<form:radiobutton path="studentTitle" value="M"/><spring:message code="foreignStd.male"/>
						<form:radiobutton path="studentTitle" value="F"/><spring:message code="foreignStd.female"/>
						<form:errors cssClass="error" path="studentTitle" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="200"><span class="gamay"><spring:message code="foreignStd.birthdate"/>*: </span></td>
					<td> 
						<form:input path='birthDate' size="11"/><form:errors cssClass="error" path="birthDate" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="220"><span class="gamay"><spring:message code="foreignStd.nationality"/>*: </span></td>
					<td>
						<form:select cssClass="dropdown" size='1' path='nationality' >
							<form:option id="nationalitynull" value="${null}"><c:out value="-----------------------------"/></form:option>
							<c:forEach items="${comCriteriaNationalityViewList}" var="criteriaNationalityView" >
								<form:option id="nationality${criteriaNationalityView.crtId}" value="${criteriaNationalityView.crtId}"><c:out value="${criteriaNationalityView.name}"/></form:option>
							</c:forEach>
						</form:select>
						<form:errors cssClass="error" path="nationality" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="200"><span class="gamay"><spring:message code="foreignStd.email"/>*: </span></td>
					<td> 
						<form:input path='studentEmail' size="50"/><form:errors cssClass="error" path="studentEmail" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="200"><span class="gamay"><spring:message code="foreignStd.help"/>: </span></td>
					<td> 
					    <form:input path='help' size="50"/><form:errors cssClass="error" path="help" />
					</td>
				</tr>
				<tr class="odd" style="display:none;">
					<td class="societe"><span class="gamay"><spring:message code="foreignStd.currentyear"/> / <span class="gamay"><spring:message code="foreignStd.currentyearoutof"/>*: </span></td>
					<td> 
					    <form:input path='currentyear' size="1" value="1"/><form:errors cssClass="error" path="currentyear" /> / 
					    <form:input path='currentyearoutof' size="1" value="1"/><form:errors cssClass="error" path="currentyearoutof" />
					</td>
				</tr>
				<tr class="odd">	
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr class="odd" >
					<td colspan="2"><span class="gamay"><a target="blank" href="<c:url value="/pdf/EDHEC_FACT_SHEET.pdf"/>">(for further information please consult our factsheet)</a></span></td>
				</tr>
				<tr class="odd">
					<td><form:errors cssClass="error" path="studentLevel" /></td>
					<td>&nbsp;</td>
				</tr>	
				
				</table>		

<c:if test="${DDSem != 0}">
<table style="width:100%;"><tr><td class="auto-style1" bgcolor="#C9D5DF"><span class="gamay">Campus and specialization</span></td><td class="auto-style1" bgcolor="#C9D5DF" id="PERIODE" style="display:none;">Student&#39;s study period</td><td bgcolor="#C9D5DF" id="LEVEL" style="display:none;">&nbsp;Student's study level for 2022/2023*</td></tr>
<tr><td class="auto-style1" rowspan="10" bgcolor="LightSalmon"><textarea id="LI" class="textarea_selectw" rows="2" readonly="readonly">Lille&#13;&#10; Business Management Track</textarea></td>
  <td class="auto-style1" rowspan="5" bgcolor="LightSalmon" id="CLIS0" style="display:none;"><input id="LIS0" class="input_select" value="Full year"></td>
    <td bgcolor="LightSalmon" id="CLIS01" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_1AN_PMFT"/>Undergraduate (Bachelor program) Classic track</td></tr>
	<tr><td bgcolor="LightSalmon" id="CLIS05" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_1AN_PMET"/>Undergraduate (Bachelor program) English track</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS02" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_1AN_M1"/>Upper-undergraduate (Master 1 program) English taught program</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS03" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_1AN_M2"/>Graduate (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS04" style="display:none;"><c:if test="${foreignUniversity.ddSemester != 0}"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_1AN_M2DD"/>Graduate Double Degree (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</c:if></td></tr>
  <tr><td class="auto-style1" rowspan="5" bgcolor="LightSalmon" id="CLIS1" style="display:none;"><input id="LIS1" class="input_select" value="Fall semester"></td>
    <td bgcolor="LightSalmon" id="CLIS11" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S1_PMFT"/>Undergraduate (Bachelor program) Classic track</td></tr>
	<tr><td bgcolor="LightSalmon" id="CLIS15" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S1_PMET"/>Undergraduate (Bachelor program) English track</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS12" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S1_M1"/>Upper-undergraduate (Master 1 program) English taught program</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS13" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S1_M2"/>Graduate (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS14" style="display:none;"><c:if test="${(foreignUniversity.ddSemester != 0 && foreignUniversity.universityNumber==5117)||foreignUniversity.universityNumber==''}"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S1_M2DD"/>Graduate Double Degree (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</c:if></td></tr>
<tr><td class="auto-style1" rowspan="10" bgcolor="LightSteelBlue"><textarea id="NI" class="textarea_selectw" rows="2" readonly="readonly">Nice&#13;&#10; Financial Economic Track</textarea></td>
  <td class="auto-style1" rowspan="4" bgcolor="LightSteelBlue" id="CNIS0" style="display:none;"><input id="NIS0" class="input_select" value="Full year"></td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS01" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_1AN_M1"/>Upper-undergraduate (Master 1 program) English taught program</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS02" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_1AN_M2"/>Graduate (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS03" style="display:none;"><c:if test="${foreignUniversity.ddSemester != 0}"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_1AN_M2DD"/>Graduate Double Degree (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</c:if></td></tr>
  <tr><td class="auto-style1" rowspan="6" bgcolor="LightSteelBlue" id="CNIS1" style="display:none;"><input id="NIS1" class="input_select" value="Fall semester"></td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS11" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S1_M1"/>Upper-undergraduate (Master 1 program) English taught program</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS12" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S1_M2"/>Graduate (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS13" style="display:none;"><c:if test="${(foreignUniversity.ddSemester != 0 && foreignUniversity.universityNumber==5117)||foreignUniversity.universityNumber==''}"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S1_M2DD"/>Graduate Double Degree (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</c:if></td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS14" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S1_QTEM1"/>QTEM network (Master 1 program)</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS15" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S1_QTEM2"/>QTEM network (Master 2 program)</td></tr>
</table>
</c:if>

<c:if test="${DDSem == 0}">
<table style="width:100%;"><tr><td class="auto-style1" bgcolor="#C9D5DF"><span class="gamay">Campus and specialization</span></td><td class="auto-style1" bgcolor="#C9D5DF">Student&#39;s study period</td><td bgcolor="#C9D5DF">Student's study level for 2022/2023*&nbsp;</td></tr>
<tr><td class="auto-style1" rowspan="5" bgcolor="LightSalmon"><textarea id="LI" class="textarea_selectw" rows="2" readonly="readonly">Lille&#13;&#10; Business Management Track</textarea></td>
  <td class="auto-style1" rowspan="5" bgcolor="LightSalmon" id="CLIS2" style="display:none;">Spring semester</td>
    <td bgcolor="LightSalmon" id="CLIS21" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S2_PMFT"/>Undergraduate (Bachelor program) Classic track</td></tr>
	<tr><td bgcolor="LightSalmon" id="CLIS26" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S2_PMET"/>Undergraduate (Bachelor program) English track</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS22" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S2_M1"/>Upper-undergraduate (Master 1 program) English taught program</td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS23" style="display:none;"><c:if test="${foreignUniversity.ddSemester != 0}"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S2_M1DD"/>Upper-undergraduate Double Degree (Master 1 program) English taught program</c:if></td></tr>
    <tr><td bgcolor="LightSalmon" id="CLIS24" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S2_M2"/>Graduate (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</td></tr>
    <!-- <tr><td bgcolor="LightSalmon" id="CLIS25" style="display:none;"><c:if test="${foreignUniversity.ddSemester != 0}"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_LI_S2_M2DD"/>Graduate Double Degree (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</c:if></td></tr> -->
<tr><td class="auto-style1" rowspan="5" bgcolor="LightSteelBlue"><textarea id="NI" class="textarea_selectw" rows="2" readonly="readonly">Nice&#13;&#10; Financial Economic Track</textarea></td>
  <td class="auto-style1" rowspan="5" bgcolor="LightSteelBlue" id="CNIS2" style="display:none;">Spring semester</td>
    <td bgcolor="LightSteelBlue" id="CNIS21" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S2_M1"/>Upper-undergraduate (Master 1 program) English taught program</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS22" style="display:none;"><c:if test="${foreignUniversity.ddSemester != 0}"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S2_M1DD"/>Upper-undergraduate Double Degree (Master 1 program) English taught program</c:if></td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS23" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S2_M2"/>Graduate (Master 2 program) English taught program - Bachelor diploma awarded, min 180 ECTS validated</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS24" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S2_QTEM1"/>QTEM network (Master 1 program)</td></tr>
    <tr><td bgcolor="LightSteelBlue" id="CNIS25" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_EDHEC_NI_S2_QTEM2"/>QTEM network (Master 2 program)</td></tr>
</table>
</c:if>

<table>			
				<tr class="odd">	
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>			
				<tr class="odd">
					<td class="societe" width="200">
						<div id="formButtons" class="ligne"><!-- debut de Ligne -->
						<input id="validate" class="input_submit" value="<spring:message code="foreignStd.register"/>" />
						</div>
					</td>
					<td class="societe">
						<div id="formButtons" class="ligne"><!-- debut de Ligne -->
						<input id="clear" class="input_submit" value="<spring:message code="foreignStd.clearForm"/>" />
						</div>
					</td>
				</tr>
				<tr class="odd">	
					<td>&nbsp;</td>
					<td><br/><form:errors cssClass="error" path="semesterUsed" /><br/></td>
				</tr>			
			</table>	
</form:form>

<div id="studentList">
<table class="tan">
	<tr>
		<th colspan="6">
 			<spring:message code="foreignStd.helptableindex"/>
		</th>
	</tr>
	<tr>
		<th scope="col" class="date">Edit Student</th>
		<th scope="col" ><spring:message code="foreignStd.studylevelabrg"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.studyPeriod"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.campus"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.helpHead"/></th>
		<th scope="col" class="date">Delete</th>
	</tr>
	<c:choose>
		<c:when test='${not empty foreignStudentViewList}'>
			<c:forEach  items='${foreignStudentViewList}' var='student' varStatus="currentNumber">
				<tr id="<c:out value='${student.id}' />" class="odd" >
					<td><b><a href="javascript:modifyForeignStudent('<c:out value="${student.id}" />','<c:out value="${student.lastNameUnquoted}" />','<c:out value="${student.firstNameUnquoted}" />','<c:out value="${student.email}"/>','<c:out value="${student.gender}"/>','<c:out value="${student.birthdateFr}"/>','<c:out value="${student.nationality}"/>','<c:out value="${student.moduleCode}"/>','<c:out value="${student.helpUnquoted}"/>');" title="Modify a student"><c:out value="${student.firstName}" /> <c:out value="${student.lastName}" /></a></b> (<a href="mailto:${student.email}"><c:out value="${student.email}" /></a>)</td>
					<td><c:out value="${student.moduleLib}" /></td>
					<td><str:capitalize><str:lowerCase>
						<c:if test="${student.periodeCode == 'S1'}">
							<spring:message code="foreignStd.fallSemesterabrg"/>
						</c:if>
						<c:if test="${student.periodeCode == 'S2'}">
							<spring:message code="foreignStd.springSemesterabrg"/>
						</c:if>
						<c:if test="${student.periodeCode == 'S0'}">
							<spring:message code="foreignStd.fullYearabrg"/>
						</c:if>
						<c:if test="${student.doubleDiploma == 'Y'}">
							<spring:message code="foreignStd.doubleDiplomabrg"/>
						</c:if>
						</str:lowerCase></str:capitalize>
					</td>
					<td><c:choose>
						<c:when test='${student.campus=="LILLE"}'><spring:message code="foreignStd.campusLilleBM"/></c:when>
						<c:otherwise><spring:message code="foreignStd.campusNiceFE"/></c:otherwise>
					</c:choose></td>
					<td>
						<c:out value="${student.help}" />
					</td>
					<td><a href="javascript:modifyForeignStudent('<c:out value="${student.id}" />','<c:out value="${student.lastNameUnquoted}" />','<c:out value="${student.firstNameUnquoted}" />','<c:out value="${student.email}"/>','<c:out value="${student.gender}"/>','<c:out value="${student.birthdateFr}"/>','<c:out value="${student.nationality}"/>','<c:out value="${student.moduleCode}"/>','<c:out value="${student.helpUnquoted}"/>');" >&nbsp;</a>&nbsp;&nbsp;
					<a class="deleteAccount" rel="#delete" href="deleteforeignStudent?foreignStudentId=<c:out value="${student.id}" />"><img title="Delete a student" src="<c:url value="/images/trash.gif"/>" /></a>&nbsp;&nbsp;</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr class="odd">
				<td colspan="4"><spring:message code="foreignStd.noStudent"/></td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr class="odd">
		<td colspan="5" align="right">
			<br/>
			<div class="ligne"><!-- debut de Ligne -->
				<input id="submit" size="22" class="input_submit" value="<spring:message code="foreignStd.submit"/>" />
			</div>
 			<br/>
			<div class="ligne"></div>
		</td>
	</tr>	
	</table>
</div>
<br/><br/>

<script>

function modifyForeignStudent(id, lastName, firstName, email, gender, birthdate, nationality, studentlevel, help) {
	$("input[type='hidden'][name='foreignStudentId']").attr('value',id);
	$("input[name='studentLastName']").attr('value',lastName);
	$("input[name='studentFirstName']").attr('value',firstName);
	$("input[type='radio'][name='studentTitle']").filter('[value=' + gender +']').attr('checked', true);     
	$("input[name='birthDate']").attr('value',birthdate);
	document.getElementById('nationality' + nationality).selected = true;
	$("input[name='studentEmail']").attr('value',email);
	$("input[name='help']").attr('value',help);
	$("input[type='radio'][name='studentLevel']").filter('[value=' + studentlevel +']').attr('checked', true);
	showlevels();
	$('#studentLastName').focus();
}

function showlevels(){
	
	$('#PERIODE').show();
	$('#LEVEL').show();

	$('#CLIS0').show();
	$('#CLIS1').show();
	$('#CLIS2').show();

	$('#CNIS0').show();
	$('#CNIS1').show();
	$('#CNIS2').show();

	$('#CLIS01').show();
	$('#CLIS02').show();
	$('#CLIS03').show();
	$('#CLIS04').show();
	$('#CLIS05').show();

	$('#CLIS11').show();
	$('#CLIS12').show();
	$('#CLIS13').show();
	$('#CLIS14').show();
	$('#CLIS15').show();

	$('#CNIS01').show();
	$('#CNIS02').show();
	$('#CNIS03').show();

	$('#CNIS11').show();
	$('#CNIS12').show();
	$('#CNIS13').show();
	$('#CNIS14').show();
	$('#CNIS15').show();

	$('#CLIS21').show();
	$('#CLIS22').show();
	$('#CLIS23').show();
	$('#CLIS24').show();
	//$('#CLIS25').show();
	$('#CLIS26').show();

	$('#CNIS21').show();
	$('#CNIS22').show();
	$('#CNIS23').show();
	$('#CNIS24').show();
	$('#CNIS25').show();
}

function clearlevels(){

	$('#CLIS0').hide();
	$('#CLIS1').hide();
	$('#CNIS1').hide();
	$('#CNIS0').hide();
	$('#CLIS2').hide();
	$('#CNIS2').hide();

	$('#CLIS01').hide();
	$('#CLIS02').hide();
	$('#CLIS03').hide();
	$('#CLIS04').hide();
	$('#CLIS05').hide();

	$('#CLIS11').hide();
	$('#CLIS12').hide();
	$('#CLIS13').hide();
	$('#CLIS14').hide();
	$('#CLIS15').hide();

	$('#CNIS01').hide();
	$('#CNIS02').hide();
	$('#CNIS03').hide();

	$('#CNIS11').hide();
	$('#CNIS12').hide();
	$('#CNIS13').hide();
	$('#CNIS14').hide();
	$('#CNIS15').hide();

	$('#CLIS21').hide();
	$('#CLIS22').hide();
	$('#CLIS23').hide();
	$('#CLIS24').hide();
//	$('#CLIS25').hide();
	$('#CLIS26').hide();

	$('#CNIS21').hide();
	$('#CNIS22').hide();
	$('#CNIS23').hide();
	$('#CNIS24').hide();
	$('#CNIS25').hide();

	$("input[type='radio'][name='studentLevel']").attr('checked',false);
}

function clearperiodes(){
	$('#PERIODE').hide();
	$('#LEVEL').hide();
	
	$('#CLIS0').hide();
	$('#CLIS1').hide();
	$('#CLIS2').hide();
	
	$('#CNIS0').hide();
	$('#CNIS1').hide();
	$('#CNIS2').hide();
}

$(document).ready(function() {
	
	var currentTime = new Date();
	var year = currentTime.getFullYear();
	var month = currentTime.getMonth();
	var day = currentTime.getDate();
	
	$('#birthDate').datepicker({minDate: new Date(year-40, month, day), maxDate: new Date(year-16,month,day) });

	$("#validate").click(function() { 
		$('#loadingicon').show();
		$(this).blur();
       location.href = "javascript:document.forms['foreignStudentCommand'].submit();";
		$('#loadingicon').hide();
    }); 
	
	$("#clear").click(function() { 
		$("input[type='hidden'][name='foreignStudentId']").attr('value',"");
		$("input[name='studentLastName']").attr('value',"");
		$("input[name='studentFirstName']").attr('value',"");
		$("input[type='radio'][name='studentTitle']").attr('checked',false);     
		$("input[name='birthDate']").attr('value',"");
		document.getElementById('nationalitynull').selected = true;
		$("input[name='studentEmail']").attr('value',"");
		$("input[name='help']").attr('value',"");
		$("input[type='radio'][name='studentLevel']").attr('checked',false);
		clearlevels();
		clearperiodes();
		$('#studentLastName').focus();
	});
	
	
	$("#return").click(function() { 
		$(this).blur();
        location.href = "./index";       
    }); 
	
	$("#LI").click(function() { 
		$(this).blur();
		$('#PERIODE').show();
		$('#LEVEL').hide();
		clearlevels();
		$('#CLIS0').show();
		$('#CLIS1').show();
		$('#CNIS0').hide();
		$('#CNIS1').hide();
		$('#CLIS2').show();
		$('#CNIS2').hide();
		$('#CLIS21').show();
		$('#CLIS22').show();
		$('#CLIS23').show();
		$('#CLIS24').show();
//		$('#CLIS25').show();
		$('#CLIS26').show();
		$('#CNIS21').hide();
		$('#CNIS22').hide();
		$('#CNIS23').hide();
		$('#CNIS24').hide();
		$('#CNIS25').hide();
    }); 

	$("#NI").click(function() { 
		$(this).blur();
		$('#PERIODE').show();
		$('#LEVEL').hide();
		clearlevels();
		$('#CNIS0').show();
		$('#CNIS1').show();
		$('#CLIS0').hide();
		$('#CLIS1').hide();
		$('#CNIS2').show();
		$('#CLIS2').hide();
		$('#CNIS21').show();
		$('#CNIS22').show();
		$('#CNIS23').show();
		$('#CNIS24').show();
		$('#CNIS25').show();
		$('#CLIS21').hide();
		$('#CLIS22').hide();
		$('#CLIS23').hide();
		$('#CLIS24').hide();
//		$('#CLIS25').hide();
		$('#CLIS26').hide();
    }); 
	
	$("#LIS0").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CLIS0').show();
		$('#CLIS01').show();
		$('#CLIS02').show();
		$('#CLIS03').show();
		$('#CLIS04').show();
		$('#CLIS05').show();
    }); 	
	
	$("#LIS1").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CLIS1').show();
		$('#CLIS11').show();
		$('#CLIS12').show();
		$('#CLIS13').show();
		$('#CLIS14').show();
		$('#CLIS15').show();
    }); 	
	
	$("#NIS0").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CNIS0').show();
		$('#CNIS01').show();
		$('#CNIS02').show();
		$('#CNIS03').show();
    }); 	
	
	$("#NIS1").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CNIS1').show();
		$('#CNIS11').show();
		$('#CNIS12').show();
		$('#CNIS13').show();
		$('#CNIS14').show();
		$('#CNIS15').show();
    }); 	
	
	$("#submit").click(function() { 
		$(this).blur();
		alert("Congratulations! You have successfully nominated the following students...\n\nFor security reasons, please Log Out and Exit your web browser when you are done.");
		location.href = "./sendemail";
    }); 

});
</script>

<%@ include file="footer.jsp" %>
