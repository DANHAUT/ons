<%@ include file="headerBBA.jsp" %>

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
    width: 150px;
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
	<div class="style1_titre_deco"><h3><font style="text-transform: none;"><spring:message code="foreignStd.helptitleESPEME"/>
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
					<td class="societe" width="220"><span class="gamay"><spring:message code="foreignStd.lastName"/>* (no accents or special characters): </span></td>
					<td> 
						<form:input path='studentLastName' size="25"/><form:errors cssClass="error" path="studentLastName" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="220"><span class="gamay"><spring:message code="foreignStd.firstName"/>* (no accents or special characters): </span></td>
					<td> 
						<form:input path='studentFirstName' size="25"/><form:errors cssClass="error" path="studentFirstName" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="220"><span class="gamay"><spring:message code="foreignStd.title"/>*: </span></td>
					<td> 
						<form:radiobutton path="studentTitle" value="M"/><spring:message code="foreignStd.male"/>
						<form:radiobutton path="studentTitle" value="F"/><spring:message code="foreignStd.female"/>
						<form:errors cssClass="error" path="studentTitle" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="220"><span class="gamay"><spring:message code="foreignStd.birthdate"/>*: </span></td>
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
					<td class="societe" width="220"><span class="gamay"><spring:message code="foreignStd.email"/>*: </span></td>
					<td> 
						<form:input path='studentEmail' size="50"/><form:errors cssClass="error" path="studentEmail" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="220"><span class="gamay"><spring:message code="foreignStd.help"/>: </span></td>
					<td> 
					    <form:input path='help' size="50"/><form:errors cssClass="error" path="help" />
					</td>
				</tr>
				<tr class="odd">
					<td class="societe" width="220"><span class="gamay">Current year of study in 2022/2023*: </span></td>
					<td>
						<form:select cssClass="dropdown" size='1' path='currentyear' >
							<form:option id="currentyear1" value="1"><c:out value="1st year"/></form:option>
							<form:option id="currentyear2" value="2"><c:out value="2nd year"/></form:option>
							<form:option id="currentyear3" value="3"><c:out value="3rd year"/></form:option>
							<form:option id="currentyear4" value="4"><c:out value="4th year"/></form:option>
							<form:option id="currentyear5" value="5"><c:out value="5th year"/></form:option>
						</form:select><form:errors cssClass="error" path="currentyear" /> out of*
						<form:select cssClass="dropdown" size='1' path='currentyearoutof' >
							<form:option id="currentyearoutof3" value="3"><c:out value="3 years"/></form:option>
							<form:option id="currentyearoutof4" value="4"><c:out value="4 years"/></form:option>
							<form:option id="currentyearoutof5" value="5"><c:out value="5 years"/></form:option>
						</form:select><form:errors cssClass="error" path="currentyearoutof" />
					</td>
				</tr>
				<tr class="odd">	
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>			
				<tr class="odd">	
					<td colspan="2"><span class="gamay"><a target="blank" href="<c:url value="/pdf/BBA_EDHEC_FACT_SHEET.pdf"/>"><strong>Please click here to see the factsheet EDHEC BBA 2022-2023 (and access to the course offer)</strong></a></span></td>
				</tr>
				<tr class="odd" hidden="true">	
					<td colspan="2"><span class="gamay"><a target="blank" href="<c:url value="/pdf/BBA_EDHEC_ACADEMIC_STRUCTURE.pdf"/>"><spring:message code="foreignStd.ESPEMEstructureOverview"/></a></span></td>
				</tr>
				<tr class="odd">	
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr class="odd">	
					<td  colspan="2">Exchange students can take a maximum of 30 ECTS</td>
				</tr>			
				<tr class="odd">	
					<td><form:errors cssClass="error" path="studentLevel" /></td>
					<td>&nbsp;</td>
				</tr>	
</table>
			
<c:if test="${DDSem != 0}">
<table style="width:100%;"><tr><td class="auto-style1" bgcolor="#C9D5DF">Student&#39;s study period</td><td class="auto-style2" bgcolor="#C9D5DF" id="CAMPUS" style="display:none;">Campus - Language of instruction</td><td bgcolor="#C9D5DF" id="LEVEL" style="display:none;">&nbsp;Study program and level at EDHEC BBA*</td></tr>

<tr><td class="auto-style1" rowspan="10" bgcolor="LightSalmon"><input id="S1" class="input_select" value="Fall semester"></td>
  <td class="auto-style2" rowspan="2" bgcolor="LightSalmon" id="CS1LIEN" style="display:none;"><input id="S1LIEN" class="input_select" value="LILLE CAMPUS - English taught programmes"></td>
	<td bgcolor="LightSalmon" id="CS1LI2" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_S1_EB2ET"/>EDHEC International BBA 2nd year Business Fundamentals (English taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS1LI4" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_S1_EB4IBT"/>EDHEC International BBA 4th year International Business Track (English taught program)</td></tr>
  <tr><td class="auto-style2" rowspan="2" bgcolor="LightSalmon" id="CS1LIFR" style="display:none;"><input id="S1LIFR" class="input_select" value="LILLE CAMPUS - French taught programmes"></td>
    <td bgcolor="LightSalmon" id="CS1LI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_S1_EB2FT"/>EDHEC International BBA 2nd year Business Fundamentals (French taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS1LI3" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_S1_EB3"/>EDHEC International BBA 3rd year Academic Specializations Marketing or Finance (French taught program)</td></tr>
  <tr><td class="auto-style2" rowspan="3" bgcolor="LightSalmon" id="CS1NIEN" style="display:none;"><input id="S1NIEN" class="input_select" value="NICE CAMPUS - English taught programmes"></td>
    <td bgcolor="LightSalmon" id="CS1NI2" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S1_EB2ET"/>ZZZEDHEC International BBA 2nd year Business Fundamentals (English taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS1NI5" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S1_EB4HOSP"/>EDHEC International BBA 4th year Hospitality &amp; Event Management (English and French taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS1NI6" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S1_EB4IBT"/>EDHEC International BBA 4th year International Business Track (English taught program)</td></tr>
  <tr><td class="auto-style2" rowspan="3" bgcolor="LightSalmon" id="CS1NIFR" style="display:none;"><input id="S1NIFR" class="input_select" value="NICE CAMPUS - French taught programmes"></td>
    <td bgcolor="LightSalmon" id="CS1NI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S1_EB2FT"/>EDHEC International BBA 2nd year Business Fundamentals (French taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS1NI3" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S1_EB3"/>EDHEC International BBA 3rd year Academic Specializations Marketing or Finance (French taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS1NI4" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S1_EB4"/>EDHEC International BBA 4th year Sector Specializations (French taught program)</td></tr>

<tr><td class="auto-style1" rowspan="5" bgcolor="LightSteelBlue"><input id="S0" class="input_select" value="Full year"></td>
  <td class="auto-style2" rowspan="2" bgcolor="LightSteelBlue" id="CS0LIEN" style="display:none;"><input id="S0LIEN" class="input_select" value="LILLE CAMPUS - English taught programmes"></td>
	<td bgcolor="LightSteelBlue" id="CS0LI3" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_1AN_EB2_EB2ET"/>EDHEC International BBA 2nd year Business Fundamentals (English taught program)</td></tr>
	<tr><td bgcolor="LightSteelBlue" id="CS0LI2" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_1AN_EB4IBT_EB3IBT"/>EDHEC International BBA 4th year International Business Track (English taught program)</td></tr>
  <tr><td class="auto-style2" bgcolor="LightSteelBlue" id="CS0LIFR" style="display:none;"><input id="S0LIFR" class="input_select" value="LILLE CAMPUS - French taught programmes"></td>
    <td bgcolor="LightSteelBlue" id="CS0LI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_1AN_EB2_EB2FT"/>EDHEC International BBA 2nd year Business Fundamentals (French taught program)</td>
  <tr><td class="auto-style2" bgcolor="LightSteelBlue" id="CS0NIEN" style="display:none;"><input id="S0NIEN" class="input_select" value="NICE CAMPUS - English taught programmes"></td>
    <td bgcolor="LightSteelBlue" id="CS0NI2" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_1AN_EB4IBT_EB3IBT"/>EDHEC International BBA 4th year International Business Track (English taught program)</td></tr>
  <tr><td class="auto-style2" bgcolor="LightSteelBlue" id="CS0NIFR" style="display:none;"><input id="S0NIFR" class="input_select" value="NICE CAMPUS - French taught programmes"></td>
    <td bgcolor="LightSteelBlue" id="CS0NI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_1AN_EB2_EB2FT"/>EDHEC International BBA 2nd year Business Fundamentals (French taught program) </td></tr>

<c:if test="${foreignUniversity.ddSemester != 0}">
<tr><td class="auto-style1" rowspan="2" bgcolor="LightGreen"><input id="DD" class="input_select" value="Double diploma"></td>
  <td class="auto-style2" rowspan="1" bgcolor="LightGreen" id="CDDLI" style="display:none;"><input id="DDLI" class="input_select" value="LILLE CAMPUS - English taught programme only"></td>
    <td bgcolor="LightGreen" id="CDDLI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_1AN_DDEB4IBT_DDEB3IBT"/>EDHEC International BBA Double Degree International Business Track Full Year (English taught program)</td></tr>
  <tr><td class="auto-style2" rowspan="1" bgcolor="LightGreen" id="CDDNI" style="display:none;"><input id="DDNI" class="input_select" value="NICE CAMPUS - English taught programme only"></td>
    <td bgcolor="LightGreen" id="CDDNI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_1AN_DDEB4IBT_DDEB3IBT"/>EDHEC International BBA Double Degree International Business Track Full Year (English taught program)</td></tr>
</c:if>
</table>
</c:if>
    
<c:if test="${DDSem == 0}">
<table style="width:100%;"><tr><td class="auto-style1" bgcolor="#C9D5DF">Student&#39;s study period</td><td class="auto-style2" bgcolor="#C9D5DF" id="CAMPUS" style="display:none;">Campus</td><td bgcolor="#C9D5DF" id="LEVEL" style="display:none;">&nbsp;Study program and level at EDHEC International BBA*</td></tr>

<tr><td class="auto-style1" rowspan="5" bgcolor="LightSalmon"><input id="S2" class="input_select" value="Spring semester"></td>
  <td class="auto-style2" rowspan="3" bgcolor="LightSalmon" id="CS2LI" style="display:none;"><input id="S2LI" class="input_select" value="Lille"></td>
    <td bgcolor="LightSalmon" id="CS2LI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_S2_EB2FT"/>EDHEC International BBA 2nd year (French taught program)</td></tr>
	<tr><td bgcolor="LightSalmon" id="CS2LI3" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_S2_EB2ET"/>EDHEC International BBA 2nd year (English taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS2LI2" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_LI_S2_EB3IBT"/>EDHEC International BBA 3rd year International Business Track (English taught program)</td></tr>
  <tr><td class="auto-style2" rowspan="2" bgcolor="LightSalmon" id="CS2NI" style="display:none;"><input id="S2NI" class="input_select" value="Nice"></td>
    <td bgcolor="LightSalmon" id="CS2NI1" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S2_EB2FT"/>EDHEC International BBA 2nd year (French taught program)</td></tr>
    <tr><td bgcolor="LightSalmon" id="CS2NI2" style="display:none;"><form:radiobutton path="studentLevel" value="OFL_23_BBA_NI_S2_EB3IBT"/>EDHEC International BBA 3rd year International Business Track (English taught program)</td></tr>
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
		<th scope="col" class="date"><spring:message code="foreignStd.student"/></th>
		<th scope="col" ><spring:message code="foreignStd.studylevelabrg"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.studyPeriod"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.campus"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.helpHead"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.action"/></th>
	</tr>
	<c:choose>
		<c:when test='${not empty foreignStudentViewList}'>
			<c:forEach  items='${foreignStudentViewList}' var='student' varStatus="currentNumber">
				<tr id="<c:out value='${student.id}' />" class="odd" >
					<td><b><a href="javascript:modifyForeignStudent('<c:out value="${student.id}" />','<c:out value="${student.lastNameUnquoted}" />','<c:out value="${student.firstNameUnquoted}" />','<c:out value="${student.email}"/>','<c:out value="${student.gender}"/>','<c:out value="${student.birthdateFr}"/>','<c:out value="${student.nationality}"/>','<c:out value="${student.moduleCode}"/>','<c:out value="${student.helpUnquoted}"/>','<c:out value="${student.currentyear}"/>','<c:out value="${student.currentyearoutof}"/>');" title="Modify a student"><c:out value="${student.firstName}" /> <c:out value="${student.lastName}" /></a></b> (<a href="mailto:${student.email}"><c:out value="${student.email}" /></a>)</td>
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
					<td><str:capitalize><str:lowerCase><c:out value="${student.campus}" /></str:lowerCase></str:capitalize></td>
					<td>
						<c:out value="${student.help}" />
					</td>
					<td><a href="javascript:modifyForeignStudent('<c:out value="${student.id}" />','<c:out value="${student.lastNameUnquoted}" />','<c:out value="${student.firstNameUnquoted}" />','<c:out value="${student.email}"/>','<c:out value="${student.gender}"/>','<c:out value="${student.birthdateFr}"/>','<c:out value="${student.nationality}"/>','<c:out value="${student.moduleCode}"/>','<c:out value="${student.helpUnquoted}"/>','<c:out value="${student.currentyear}"/>','<c:out value="${student.currentyearoutof}"/>');" ><img title="Modify a student" src="<c:url value="/images/edit.png"/>" /></a>&nbsp;
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

function modifyForeignStudent(id, lastName, firstName, email, gender, birthdate, nationality, studentlevel, help, currentyear, currentyearoutof) {
	$("input[type='hidden'][name='foreignStudentId']").attr('value',id);
	$("input[name='studentLastName']").attr('value',lastName);
	$("input[name='studentFirstName']").attr('value',firstName);
	$("input[type='radio'][name='studentTitle']").filter('[value=' + gender +']').attr('checked', true);     
	$("input[name='birthDate']").attr('value',birthdate);
	document.getElementById('nationality' + nationality).selected = true;
	$('#nationality').val()==nationality;
	$("input[name='studentEmail']").attr('value',email);
	$("input[name='help']").attr('value',help);
	document.getElementById('currentyear' + currentyear).selected = true;
	$('#currentyear').val()==currentyear;
	document.getElementById('currentyearoutof' + currentyearoutof).selected = true;
	$('#currentyearoutof').val()==currentyearoutof;
	$("input[type='radio'][name='studentLevel']").filter('[value=' + studentlevel +']').attr('checked', true);
	showlevels();
	$('#studentLastName').focus();
}

function showlevels(){
	
	$('#CAMPUS').show();
	$('#LEVEL').show();

	$('#CS1LIEN').show();
	$('#CS1NIEN').show();

	$('#CS1LIFR').show();
	$('#CS1NIFR').show();

	$('#CS0LIEN').show();
	$('#CS0NIEN').show();

	$('#CS0LIFR').show();
	$('#CS0NIFR').show();

	$('#CS2LI').show();
	$('#CS2NI').show();

	$('#CDDLI').show();
	$('#CDDNI').show();

	$('#CS1LI1').show();
	$('#CS1LI2').show();
	$('#CS1LI3').show();
	$('#CS1LI4').show();
	
	$('#CS1NI1').show();
	$('#CS1NI2').hide();//ZZZ
	$('#CS1NI3').show();
	$('#CS1NI4').show();
	$('#CS1NI5').show();
	$('#CS1NI6').show();

	$('#CS0LI1').show();
	$('#CS0LI2').show();
	$('#CS0LI3').show();

	$('#CS0NI1').show();
	$('#CS0NI2').show();

	$('#CDDLI1').show();

	$('#CDDNI1').show();

	$('#CS2LI1').show();
	$('#CS2LI2').show();
	$('#CS2LI3').show();
	$('#CS2NI1').show();
	$('#CS2NI2').show();

}

function clearlevels(){
	
	$('#CS1LIEN').hide();
	$('#CS1NIEN').hide();
	$('#CS1LIFR').hide();
	$('#CS1NIFR').hide();

	$('#CS0LIEN').hide();
	$('#CS0NIEN').hide();
	$('#CS0LIFR').hide();
	$('#CS0NIFR').hide();

	$('#CDDNI').hide();
	$('#CDDLI').hide();

	$('#CS1LI1').hide();
	$('#CS1LI2').hide();
	$('#CS1LI3').hide();
	$('#CS1LI4').hide();
	
	$('#CS1NI1').hide();
	$('#CS1NI2').hide();
	$('#CS1NI3').hide();
	$('#CS1NI4').hide();
	$('#CS1NI5').hide();
	$('#CS1NI6').hide();

	$('#CS0LI1').hide();
	$('#CS0LI2').hide();
	$('#CS0LI3').hide();

	$('#CS0NI1').hide();
	$('#CS0NI2').hide();

	$('#CDDLI1').hide();

	$('#CDDNI1').hide();

	$('#CS2LI').hide();
	$('#CS2NI').hide();

	$('#CS2LI1').hide();
	$('#CS2LI2').hide();
	$('#CS2LI3').hide();
	$('#CS2NI1').hide();
	$('#CS2NI2').hide();

	$("input[type='radio'][name='studentLevel']").attr('checked',false);
}

function clearcampus(){
	
	$('#CAMPUS').hide();
	$('#LEVEL').hide();

	$('#CS1LIEN').hide();
	$('#CS1NIEN').hide();

	$('#CS1LIFR').hide();
	$('#CS1NIFR').hide();

	$('#CS0LIEN').hide();
	$('#CS0NIEN').hide();

	$('#CS0LIFR').hide();
	$('#CS0NIFR').hide();

	$('#CDDLI').hide();
	$('#CDDNI').hide();

	$('#CS2LI').hide();
	$('#CS2NI').hide();
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
		document.getElementById('currentyear1').selected = true;;
		document.getElementById('currentyearoutof3').selected = true;;
		$("input[type='radio'][name='studentLevel']").attr('checked',false);
		clearlevels();
		clearcampus();
		$('#studentLastName').focus();
	});
	
	$("#return").click(function() { 
		$(this).blur();
        location.href = "./index";       
    }); 
	
	$("#S1").click(function() { 
		$(this).blur();
		$('#CAMPUS').show();
		$('#LEVEL').hide();
		clearlevels();
		$('#CS1LIEN').show();
		$('#CS1NIEN').show();
		$('#CS1LIFR').show();
		$('#CS1NIFR').show();
		$('#CS0LIEN').hide();
		$('#CS0NIEN').hide();
		$('#CS0LIFR').hide();
		$('#CS0NIFR').hide();
		$('#CDDLI').hide();
		$('#CDDNI').hide();
    }); 

	$("#S0").click(function() { 
		$(this).blur();
		$('#CAMPUS').show();
		$('#LEVEL').hide();
		clearlevels();
		$('#CS0LIEN').show();
		$('#CS0NIEN').show();
		$('#CS0LIFR').show();
		$('#CS0NIFR').show();
		$('#CS1LIEN').hide();
		$('#CS1NIEN').hide();
		$('#CS1LIFR').hide();
		$('#CS1NIFR').hide();
		$('#CDDLI').hide();
		$('#CDDNI').hide();
    }); 

	$("#DD").click(function() { 
		$(this).blur();
		$('#CAMPUS').show();
		$('#LEVEL').hide();
		clearlevels();
		$('#CDDLI').show();
		$('#CDDNI').show();
		$('#CS1LIEN').hide();
		$('#CS1NIEN').hide();
		$('#CS1LIFR').hide();
		$('#CS1NIFR').hide();
		$('#CS0LIEN').hide();
		$('#CS0NIEN').hide();
		$('#CS0LIFR').hide();
		$('#CS0NIFR').hide();
    }); 

	$("#S2").click(function() { 
		$(this).blur();
		$('#CAMPUS').show();
		$('#LEVEL').hide();
		clearlevels();
		$('#CS2LI').show();
		$('#CS2NI').show();
    }); 
	
	$("#S1LIEN").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS1LIEN').show();
		$('#CS1LI2').show();
		$('#CS1LI4').show();
    }); 	
	
	$("#S1NIEN").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS1NIEN').show();
		$('#CS1NI2').hide();//ZZZ
		$('#CS1NI5').show();
		$('#CS1NI6').show();
    }); 	

	$("#S1LIFR").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS1LIFR').show();
		$('#CS1LI1').show();
		$('#CS1LI3').show();
    }); 	
	
	$("#S1NIFR").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS1NIFR').show();
		$('#CS1NI1').show();
		$('#CS1NI3').show();
		$('#CS1NI4').show();
    }); 	
	
	$("#S0LIEN").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS0LIEN').show();
		$('#CS0LI2').show();
		$('#CS0LI3').show();
    }); 	
	
	$("#S0NIEN").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS0NIEN').show();
		$('#CS0NI2').show();
    }); 	

	$("#S0LIFR").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS0LIFR').show();
		$('#CS0LI1').show();
    }); 	
	
	$("#S0NIFR").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS0NIFR').show();
		$('#CS0NI1').show();
    }); 	

	$("#DDLI").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CDDLI').show();
		$('#CDDLI1').show();
    }); 	
	
	$("#DDNI").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CDDNI').show();
		$('#CDDNI1').show();
    }); 	

	$("#S2LI").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS2LI').show();
		$('#CS2LI1').show();
		$('#CS2LI2').show();
		$('#CS2LI3').show();
    }); 	
	
	$("#S2NI").click(function() { 
		$(this).blur();
		$('#LEVEL').show();
		clearlevels();
		$('#CS2NI').show();
		$('#CS2NI1').show();
		$('#CS2NI2').show();
    }); 	
	
	$("#submit").click(function() { 
		$(this).blur();
		alert("Congratulations! You have successfully nominated the following students...\n\nFor security reasons, please Log Out and Exit your web browser when you are done.");
		location.href = "./sendemail";
    }); 
	
});
</script>

<%@ include file="footer.jsp" %>
