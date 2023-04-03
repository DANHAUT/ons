var semesterUsedValues;
var doubleDiplomaValues;

function initEvents() {
	
	//Elements that opens an overlay
	var deleteAccounts = $("a.deleteAccount").overlay({
		// some expose tweaks suitable for modal dialogs
		expose: {
			color: '#333',
			loadSpeed: 200,
			opacity: 0.9
		},
		api: true,
		top: 200,
		closeOnClick: false,
		onClose: function(event) { 
	        if(deleteAccountsValue) {
	        	$(location).attr('href',this.getTrigger().attr('href'));
	        }   
	        
	    } 
	});

	var deleteAccountsValue;

	var buttons = $("#delete button").click(function(e) {
		// get user input
		deleteAccountsValue = buttons.index(this) === 0;
	});

	var createAccounts = $("a.createAccount").overlay({
		// some expose tweaks suitable for modal dialogs
		expose: {
			
			color: '#333',
			loadSpeed: 200,
			opacity: 0.9
		},
		closeOnClick: false,
		api: true,
		top: 200,
		onClose: function(event) { 
	        if(createAccountsValue) {
	        	$(location).attr('href',this.getTrigger().attr('href'));
	        }   
	        
	    } 
	});

	var createAccountsValue;

	var buttons2 = $("#createAccount button").click(function(e) {
		var test = createAccounts;
		// get user input
		createAccountsValue = buttons2.index(this) === 0;
	});
	
	
	$("input[type='radio'][name='studentLevel']").change(function() {
		if($(this).val() == 'UG') {
			$("input[type='radio'][name='fall'][value='000']").removeAttr("checked");
			$("input[type='radio'][name='fall'][value='000']").attr("disabled","disabled");
			$("input[type='radio'][name='fall'][value='111']").attr("checked", "checked");
		}else{
			$("input[type='radio'][name='fall'][value='000']").removeAttr("disabled");	
		}
		setAvailableForNbLeft();
	});

	setAvailableForNbLeft();
	
	$("#nbSemesterUsed").html(semesterUsedValues[0]);
	$("#nbDoubleDiploma").html(doubleDiplomaValues[0]);
	
}

function setAvailableForNbLeft() {
	
	semesterUsedValues = $("#semesterUsed").val().split("-");
	doubleDiplomaValues = $("#doubleDiploma").val().split("-");
	semesterOver = false;
	doubleDiplomaOver = false;

	if(parseInt(semesterUsedValues[0]) >= parseInt(semesterUsedValues[1])) {
		semesterOver = true;
	}
	if(parseInt(doubleDiplomaValues[0]) >= parseInt(doubleDiplomaValues[1])) {
		doubleDiplomaOver = true;
	}
	
	if(semesterOver && doubleDiplomaOver && ($("#foreignStudentId").val() == null || $("#foreignStudentId").val() == "")) { // cannot add students
		$('#formDiv').hide();
		$('#divTabInfo').show();
	}else if(semesterOver && !doubleDiplomaOver) {
		$('#divTabInfo').hide();
		$("input[type='radio'][name='fall'][value='110']").removeAttr("checked");
		$("input[type='radio'][name='fall'][value='110']").attr("disabled","disabled");
		$("input[type='radio'][name='fall'][value='011']").removeAttr("checked");
		$("input[type='radio'][name='fall'][value='011']").attr("disabled","disabled");
		$("input[type='radio'][name='fall'][value='111']").removeAttr("checked");
		$("input[type='radio'][name='fall'][value='111']").attr("disabled","disabled");
	}else if(!semesterOver && doubleDiplomaOver) {
		$('#divTabInfo').hide();
		$("input[type='radio'][name='fall'][value='000']").removeAttr("checked");
		$("input[type='radio'][name='fall'][value='000']").attr("disabled","disabled");
	}else{
		$('#divTabInfo').hide();
	}
	
}


function editForeignStudent(id, lastName, firstName, email, gender, fall, winter, spring, studentlevel, campus) {

	$('#formDiv').show();
	$('#divTabInfo').hide();
	$("input[type='hidden'][name='foreignStudentId']").attr('value',id);
	$("input[name='studentLastName']").attr('value',lastName);
	$("input[name='studentFirstName']").attr('value',firstName);
	$("input[name='studentEmail']").attr('value',email);

	$("input[type='radio'][name='studentTitle']").each(
			function(i) {
				if($(this).attr('value').toLowerCase() == gender.toLowerCase()){
					$(this).attr('checked',true);
				}
			}
	);
	
	$("input[type='radio'][name='studentLevel']").each(
			function(i) {
				if($(this).attr('value').toLowerCase() == studentlevel.toLowerCase()){
					$(this).attr('checked',true);
					if($(this).val() == 'UG') {
					 $("input[type='radio'][name='fall'][value='000']").attr("disabled", "disabled");
					}
				}
			}
	);
	
	var period = fall + winter + spring;
	
	$("input[type='radio'][name='fall']").each(
			function(i) {
				if($(this).attr('value').toLowerCase() == period.toLowerCase()){
					$(this).attr('checked',true);
				}
			}
	);
	
	$("input[type='radio'][name='studentCampus']").each(
			function(i) {
				if($(this).attr('value').toLowerCase() == campus.toLowerCase()){
					$(this).attr('checked',true);
				}
			}
	);
	
}

function clearForeignStudentForm() {
	
	$("input[type='hidden'][name='foreignStudentId']").attr('value',"");
	$("input[name='studentLastName']").attr('value',"");
	$("input[name='studentFirstName']").attr('value',"");
	$("input[name='studentEmail']").attr('value',"");
	
	$("input[type='radio'][name='studentCampus']").attr('checked',false);
	$("input[type='radio'][name='fall']").attr('checked',false);
	$("input[type='radio'][name='studentLevel']").attr('checked',false);
	$("input[type='radio'][name='studentTitle']").attr('checked',false);
	
}