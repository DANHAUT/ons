
// Study management methods

// Getting course list after domain changing
function generateCourseList(domain, idacademicstudyabroad) {
	// Show the 'loading' message
	dwr.util.useLoadingMessage();
	//studentAbroadCourseManagementDWR.findByUniversityAndDomain(idacademicstudyabroad.value, domain.value, generateCourseListCallBack);
	
	studentAbroadCourseManagementDWR.findByUniversityAndDomain(idacademicstudyabroad.value, domain.value,{callback: function generateCourseListCallBack (courseUniversityAbroadViewList) {
		if(courseUniversityAbroadViewList.length == 0) {
			//$('addAutoCourse').fade();
			jQuery("#addAutoCourse:visible").hide();
			//$('addAutoCourse').style.visibility = 'hidden';
		}else{
			//$('addAutoCourse').appear();
			jQuery("#addAutoCourse:hidden").show();
			studentAbroadCourseManagementDWR.findCommentaryByAcademicStudyCourse(courseUniversityAbroadViewList[0].id, getCommentary);
			//$('addAutoCourse').style.visibility = 'visible';
		}
		
		dwr.util.removeAllOptions('id');
		dwr.util.addOptions('id', courseUniversityAbroadViewList, 'id', generateOptionText);
	}
		
	});
}

//Getting commentary after course list changing
function generateCourseCommentary(course) {
	// Show the 'loading' message
	dwr.util.useLoadingMessage();
	
	studentAbroadCourseManagementDWR.findCommentaryByAcademicStudyCourse(course.value, getCommentary);
	
	
	//dwr.util.setValue('commentary', course.value);
}

function getCommentary(commentary) {
	dwr.util.setValue('commentary', commentary);
}

//Generate the option text for course name
function generateOptionText (courseUniversityAbroadView) {
	return '(' + courseUniversityAbroadView.status + ' - ECTS : '+ ((courseUniversityAbroadView.ects==null)?'non connu a ce jour':courseUniversityAbroadView.ects) +') ' + courseUniversityAbroadView.nameCourse;
}

// Adding a course
function addCourseUniversityAbroad(idExchange, idCourse) {
		// Show the 'loading' message
		dwr.util.useLoadingMessage();
		
    	studentAbroadCourseManagementDWR.addCourseUniversityAbroad(idExchange,idCourse,addCourseCallBack);
}

// Adding a manual course
function addManualCourseUniversityAbroad(idExchange) {
	// Show the 'loading' message
	dwr.util.useLoadingMessage();
	
	validateAllForm('courseUniversityAbroadCommand',idExchange);	
}

// Delete a course
function deleteCourseUniversityAbroad(idCourseUniversityAbroad, idAcademicStudyAbroad) {
	// Show the 'loading' message
	dwr.util.useLoadingMessage();
	//studentAbroadCourseManagementDWR.deleteCourseUniversityAbroad(idCourseUniversityAbroad,idAcademicStudyAbroad);
	
	studentAbroadCourseManagementDWR.deleteCourseUniversityAbroad(idCourseUniversityAbroad,idAcademicStudyAbroad,{callback: function deleteCourseCallBack (academicCourseId) {
				Element.remove($(academicCourseId));
				if($("courseListTable").getElementsByTagName('tr').length == 1) {
					$('courseListTable').fade();
				}
				generateEcts();
			}	
		});	
}

//Confirm or Validate a course
function confirmCourseUniversityAbroad(idCourseUniversityAbroad, idAcademicStudyAbroad) {
	if (document.getElementById("select"+idCourseUniversityAbroad).checked){
		studentAbroadCourseManagementDWR.addAcademicStudyCourseStatus(idCourseUniversityAbroad,"C");
		document.getElementById("status" + idCourseUniversityAbroad).innerHTML = "Cours Echange Confirm&eacute";}else{
			studentAbroadCourseManagementDWR.addAcademicStudyCourseStatus(idCourseUniversityAbroad,"V");
			document.getElementById("status" + idCourseUniversityAbroad).innerHTML = "Cours Echange Valid&eacute;"}
}

//Delete logically a course after it has been printed
function deleteLogicallyCourseUniversityAbroad(idCourseUniversityAbroad, idAcademicStudyAbroad) {
	
		studentAbroadCourseManagementDWR.addAcademicStudyCourseStatus(idCourseUniversityAbroad,"D-",{callback: function updateCourseStatus (status) {
			document.getElementById("status" + idCourseUniversityAbroad).innerHTML = status;
			document.getElementById("delete" + idCourseUniversityAbroad).innerHTML = '';
		}	
	});	
		
		
}

//Print confirmed courses and disable checkbox
function printCourseUniversityAbroad(idAcademicStudyAbroad,mode) {

	var courseListRows = $('courseListTable').getElementsByTagName('tr');
    
	for(i = 0; i < courseListRows.length; i++) {
		if(i > 0) {
			var columns = courseListRows[i].getElementsByTagName('td');
				columns[0].innerHTML = " ";
		}
	}
		
	window.open ("/commonapps/pdfRecap.htm?id=" + idAcademicStudyAbroad+"&mode="+mode,"Impression_Learning_Agreement", "status=0, width=800, height=600"); 
}

// Call back method after adding a course, creating a new row in array
function addCourseCallBack (academicStudyCourse) {
	var newCourseLine = new Element('tr',{});
	Element.extend(newCourseLine);
	newCourseLine.id = academicStudyCourse.id;
	var newCourseColumn = new Element('td',{});
	newCourseColumn.align = "center";
	var etat = academicStudyCourse.academicStudyCourseViewList[0].status.toLowerCase();
//	if ( etat.match("cours valid") != null)
//	{
//	var newCourseSelect = new Element('input',{});
//	newCourseSelect.type = "checkbox";
//	newCourseSelect.id = "select" + academicStudyCourse.id;
//	newCourseSelect.setAttribute('onclick','confirmCourseUniversityAbroad("' + academicStudyCourse.id + '","' + academicStudyCourse.academicStudyAbroad.id + '");');
//	newCourseColumn.insert(newCourseSelect);
//	}
	newCourseLine.insert(newCourseColumn);
	newCourseColumn = new Element('td',{});
	newCourseColumn.id = "status" + academicStudyCourse.id;
	newCourseColumn.innerHTML = academicStudyCourse.academicStudyCourseViewList[0].status;
	newCourseLine.insert(newCourseColumn);
	newCourseColumn = new Element('td',{});
	newCourseColumn.innerHTML = academicStudyCourse.courseUniversityAbroad.nameCourse;
	newCourseLine.insert(newCourseColumn);
	newCourseColumn = new Element('td',{});
	if(academicStudyCourse.courseUniversityAbroad.ects == null || academicStudyCourse.courseUniversityAbroad.ects.length == 0) {
		newCourseColumn.innerHTML = '-';
	}else{
		newCourseColumn.innerHTML = academicStudyCourse.courseUniversityAbroad.ects;
	}
	
	newCourseLine.insert(newCourseColumn);
	newCourseColumn = new Element('td',{});
	newCourseAnchor = new Element('a',{});
	var courseLink = "javascript:deleteCourseUniversityAbroad('";
	courseLink += academicStudyCourse.id;
	courseLink += "',";
	courseLink += academicStudyCourse.academicStudyAbroad.id;
	courseLink += ")";
	newCourseAnchor.href = courseLink;
	newCourseAnchor.innerHTML = "<img src=\"images/trash.gif\"/></a>";
	newCourseColumn.insert(newCourseAnchor);
	newCourseLine.insert(newCourseColumn);
	var courseTabletbody = $("courseListTable").getElementsByTagName('tbody');
	$(courseTabletbody[0]).insert(newCourseLine);
	$('courseListTable').appear();
	new Effect.Highlight(newCourseLine, {duration:2, startcolor: '#CFE1E6', endcolor: '#ffffff',  queue: 'front'});
	new Effect.Highlight(newCourseLine, {duration:2, startcolor: '#CFE1E6', endcolor: '#ffffff',  queue: 'end'});
	generateEcts();
}


// Validation methods

// Validate one field (onchange)
function validateManualCourseField(element)
{
  var id = element.id;
  var value = element.value;
  courseUniversityAbroadCommandValidator.getInputFieldValidationMessage(id, value, {
          callback:function(dataFromServer) {
                  setInputFieldStatus(element.id, dataFromServer);
          }
  });
}

// Validate all the form (when clicking add)
function validateAllForm(formName,idExchange) {

	var formInputs = $(formName).getInputs();
	var formInputsArray = new Array(formInputs.length);
	formInputs.each(function(element,index) {
		formInputsArray[index] = new Array(2);
		formInputsArray[index][0] = element.id;
		formInputsArray[index][1] = element.value;
		});
	courseUniversityAbroadCommandValidator.getGeneralValidationMessage(formName, formInputsArray, {
        callback:function(dataFromServer) {
			if(dataFromServer.length > 0) {
				dataFromServer.each(function(element){
					setInputFieldStatus(element[0], element[1]);
					});
			}else{
				// No error adding course
				$('courseNameError').innerHTML = '';
				$('courseEctsError').innerHTML = '';
				$('courseNumberError').innerHTML = '';
				studentAbroadCourseManagementDWR.addManualCourseUniversityAbroad($('courseName').value, $('courseNumber').value, $('courseEcts').value, idExchange,addCourseCallBack);
			}
        }
	});
}

// Function adding the error in the correct error div
function setInputFieldStatus(elementId, message)
{
   var id = "" + elementId + "Error";
   $(id).innerHTML = message;
}

function calculateEcts() {
	var courseListRows = $('courseListTable').getElementsByTagName('tr');

	var ectsValue = 0;
	for(i = 0; i < courseListRows.length; i++) {
		if(i > 0) {
			columns = courseListRows[i].getElementsByTagName('td');
			if(columns[3] != null && columns[3].firstChild != null) {
				//alert(columns[2].firstChild.nodeValue);
				if(columns[3].firstChild.nodeValue != '-') {
					ectsValue += parseFloat(columns[3].firstChild.nodeValue);
				}
				
			}
		}
	}
	return ectsValue;

}

function generateEcts() {
	var totalEcts = calculateEcts();
	if(totalEcts == null || totalEcts == 0) {
		$('ectsValue').innerHTML = '';
		$('ects').fade();
	}else{
		$('ectsValue').innerHTML = totalEcts;
		$('ects').appear();
		
	}
}

function getMemoryTheme(thesisSubjectList,languageWriting,thesisDiscipline,userThesisId,isFirst) {
	dwr.util.useLoadingMessage();
	thesisManagementDWR.getThesisThemeList($(languageWriting).value, $(thesisDiscipline).value, userThesisId,{callback:function(thesisThemeList) {
		dwr.util.removeAllOptions('thesisSubjectList');
		dwr.util.addOptions('thesisSubjectList', thesisThemeList, 'id', 'libelle');
		thesisSwitchManualSubject(isFirst);
	}});
}

function thesisSwitchManualSubject(isFirst) {
	subjectList = $('thesisSubjectList');
	subjectText = $('thesisSubject');
	if($('thesisSubjectList').value == '') {	
		subjectText.readOnly = false;
		if($('thesisSubject').value == ''){
			$('thesisSubject').value = 'Entrez votre sujet ici';
		}
		
	}else {
		subjectText.readOnly = true;
		if(isFirst != '1' && $('thesisSubject').value != null && $('thesisSubject').value != '') {
			subjectText.value = dwr.util.getText($('thesisSubjectList'));
		}else{
			for(i = 0; i < $('thesisSubjectList').length; i++) {
				if($('thesisSubjectList')[i].text == $('thesisSubject').value) {
					$('thesisSubjectList')[i].selected = true;
				}
			}
			subjectText.value = dwr.util.getText($('thesisSubjectList'));
		}
	}
}
