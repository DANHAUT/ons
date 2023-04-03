<%@ include file="header.jsp" %>

<div id="loadingicon" class="loadingicon"  style="display:none;"><img alt="" src="<c:url value="/img/ajax-loader.gif"/>"> Chargement ...</div>

<div id="availableList" style="margin-bottom:10px;"><!-- debut de Ligne -->
	<div class="style1_titre_deco"><h3><font style="text-transform: none;"><spring:message code="foreignStd.listForeignStdTitle1"/> <c:out value="${university.name}" /> <spring:message code="foreignStd.listForeignStdTitle2"/>	
	</font></h3>
	</div>
<!--
<c:if test="${ not empty otherSchool}">
					<div class="ligne">
						<input id="otherSchool" name="${otherSchool}" class="input_submit" size="50" value="Go <spring:message code="foreignStd.listForeignStdTitle2ESPEME"/>" />
					</div>
		 			<br/>
</c:if>
-->
	<br/>This online nomination system will be open from April 12th to <strong>April 30th, 2022</strong> for nominations regarding the <strong>Fall Semester and Full year</strong> exchange.<br/><br/>According to the exchange agreement between <c:out value="${university.name}" /> and EDHEC Business School you can nominate the following number of students for the academic year 2022/2023 :
	<!--
	<br/>This online nomination procedure will be open from September 15 to <strong>September 30, 2021</strong> for nominations regarding the <strong>Spring Semester</strong> exchange.<br/><br/>According to the exchange agreement between <c:out value="${university.name}" /> and EDHEC Business School you can nominate the following number of students for the Spring Semester 2021/2022 :
	-->
<br/><br/>
<table>
	<tr class="odd">
		<td colspan="2">
			<div class="ligne"><!-- debut de Ligne -->
				<c:out value="${foreignUniversity.totalSemester}" /> <spring:message code="foreignStd.semesterAvailable"/> ${nbSemester} / ${foreignUniversity.totalSemester} *
			</div>
			<div class="ligne"></div>
			
		</td>
	</tr>
</table>

<c:if test="${foreignUniversity.ddSemester != 0}">
<table>
	<tr class="odd">
		<td colspan="2">
			<div class="ligne"><!-- debut de Ligne -->
				<c:out value="${foreignUniversity.ddSemester}" /> <spring:message code="foreignStd.ddAvailable"/> ${nbDoubleDiploma} / ${foreignUniversity.ddSemester} *
			</div>
			<div class="ligne"></div>
			
		</td>
	</tr>
</table>
</c:if>
</div>

<!-- debut  de la div du contenu  -->
<br/>
<div class="style1">
<spring:message code="foreignStd.notabene"/>
</div>
<br/>
<table>
	<tr>
		<th colspan="3">
 			<spring:message code="foreignStd.editCorrespondantTitle"/>
		</th>
	</tr>
	<tr class="odd">
		<td colspan="1" width="400px">
		  <div id="coordinateurInfo" title="${coordinateurInfo}">
 			<b><spring:message code="foreignStd.firstName"/>: </b>${coordFirstname}
 			<br/>
 			<b><spring:message code="foreignStd.lastName"/>: </b>${coordLastname}
 			<br/>
 			<b><spring:message code="foreignStd.coordemail"/>: </b><a href="mailto:${coordEmail}">${coordEmail}</a>
 			<br/>
 			<b><spring:message code="foreignStd.coordTitle"/>: </b>${coordTitle}
 			<br/>
 			<b><spring:message code="foreignStd.telephone"/>: </b>${coordPhone}
 		  </div>
		</td>
		<td colspan="1" width="300px">
 			<b>${university.name}</b>
 			<br/>
 			<b><spring:message code="foreignStd.erasmus"/>: </b>${codeErasmus}
 			<br/>
 		</td>	
		<td colspan="1">
			${foreignUniversity.address}
			<br/>
			${foreignUniversity.zipcode}  ${foreignUniversity.town}
			<br/>
			${foreignUniversity.countryName}
			<br/><br/>
			<c:if test="${coordinateurEmailCopy=='Y'}">
				<b><spring:message code="foreignStd.softwaresinfo"/></b>
			</c:if>
			<br/>
			<c:if test="${olaUse=='OLA'}">
					<b>You use the Erasmus Dashboard to edit the Online Learning Agreement</b>
			</c:if>
		</td>
	</tr>
	<tr class="odd">
			<td colspan="2">
		 			<br/>
					<div class="ligne"><!-- debut de Ligne -->
						<input id="correspondantManagement" class="input_submit" size="40" value="<spring:message code="foreignStd.editCorrespondant"/>" />
					</div>
		 			<br/>
				<div class="ligne"></div>
			</td>
	</tr>
</table>


<table class="tan">
	<tr>
		<th colspan="6">
 			<spring:message code="foreignStd.helptableindex"/>
		</th>
	</tr>
	<tr>
		<th scope="col" class="date"><spring:message code="foreignStd.student"/></th>
		<th scope="col" class="societe">Student's study level for 2022/2023</th>
		<th scope="col" class="date"><spring:message code="foreignStd.studyPeriod"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.campus"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.helpHead"/></th>
		<th scope="col" class="date"><spring:message code="foreignStd.status"/></th>
		<c:if test="${forceadmin == 'Y'}">
		<th scope="col" class="date"><spring:message code="foreignStd.action"/></th>
		</c:if>
	</tr>
	<c:choose>
		<c:when test='${not empty foreignStudentList}'>
			<c:forEach  items='${foreignStudentList}' var='student'>
				<tr class="odd" >
					<td><c:out value="${student.firstName}" /> <c:out value="${student.lastName}" /> (<c:out value="${student.email}" />)</td>
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
					<td>
					<c:out value="${student.status}" />
					</td>
					<c:if test="${forceadmin == 'Y'}">
					<td>
							<a class="registerAccount" rel="#registered" href="registerforeignStudent?foreignStudentId=<c:out value="${student.id}" />&school=EDHEC"><img title="Register a student" src="<c:url value="/images/unlock.png"/>" /></a>&nbsp;&nbsp;
							<a class="confirmAccount" rel="#confirmed" href="confirmforeignStudent?foreignStudentId=<c:out value="${student.id}" />&school=EDHEC"><img title="Confirm a student" src="<c:url value="/images/lock.png"/>" /></a>&nbsp;&nbsp;
						    <a class="cancelAccount" rel="#canceled" href="cancelStudent?foreignStudentId=<c:out value="${student.id}" />&school=EDHEC"><img title="Cancel a student" src="<c:url value="/images/cancel.png"/>" /></a>
						    <a class="deleteAccount" rel="#delete" href="deleteforeignStudentAdmin?foreignStudentId=<c:out value="${student.id}" />"><img title="Delete a student" src="<c:url value="/images/trash.gif"/>" /></a>
					</td>
					</c:if>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr class="odd">
				<td colspan="5"><spring:message code="foreignStd.noStudent"/></td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr class="odd">
		<td colspan="2">
			<br/>
			<div class="ligne"><!-- debut de Ligne -->
				<input id="foreignStudentManagement" size="22" class="input_submit" value="<spring:message code="foreignStd.createStudent"/>" />
			</div>
 			<br/>
			<div class="ligne"></div>
		</td>
	</tr>	
</table>


<!-- Fin de Ligne -->

<script type="text/javascript" >

$(document).ready(function(){
	
	$("#otherSchool").click(function() { 
		$(this).blur();
		location.href = "../" + $(this).attr("name");
    }); 
	
	$("#correspondantManagement").click(function() { 
		$(this).blur();
		location.href = "./correspondantManagement";
    }); 
	
	$("#foreignStudentManagement").click(function() { 
		$(this).blur();
		if ($("#coordinateurInfo").attr("title")=="N"){
			alert("Please, fill in the home coordinator\'s information and OLA use");
			location.href = "./correspondantManagement";
		}else{
			if ($("#coordinateurInfo").attr("title")=="Y"){
				location.href = "./foreignStudentManagement";}
			else{
				location.href = "./disconnected";
			}
		}
    });

});

</script>

<%@ include file="footer.jsp" %>
