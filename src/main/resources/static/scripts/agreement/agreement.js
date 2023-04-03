//Docpanel : affichage des documents

function viewHideDocs(divId) {
	
	$('#'+divId+':visible').hide("blind",1000,function() { $('#'+divId+'Panelbarimg').attr('src','/commonapps/resources/img/Plus01.gif')});
	$('#'+divId+':hidden').show("blind",1000,function() { $('#'+divId+'Panelbarimg').attr('src','/commonapps/resources/img/Moins01.gif') });

	return false;
}

function initializefields(command,status) {
	
	if(status != 'INIT') {
		$("#"+command+" :input").attr("disabled", true);		
	}
	
}

function hideDiv() {
	$("#hideshow").fadeOut();
}
	
function showDiv() {
	$("#hideshow").fadeIn();
} 

function changePage(number) {
	
	if(number == '1') {
		$('#page2').fadeOut('fast', function() {
			$('#page1').fadeIn();
		});
	}else if(number == '2') {
		$('#page1').fadeOut('fast', function() {
			$('#page2').fadeIn();
		});
	}

}



function headOfficeIsTrainingPlace() {
	
	if($('#isTrainingPlace1:checked').val() == 'true') {
		
		$('#trainingPlaceName').val($('#headOfficeName').val());
		$('#trainingPlaceName').attr('readonly',true);
		
		$('#trainingPlaceAddress1').val($('#headOfficeAddress1').val());
		$('#trainingPlaceAddress1').attr('readonly',true);

		$('#trainingPlaceAddress2').val($('#headOfficeAddress2').val());
		$('#trainingPlaceAddress2').attr('readonly',true);
		
		$('#trainingPlaceAddress3').val($('#headOfficeAddress3').val());
		$('#trainingPlaceAddress3').attr('readonly',true);
		
		$('#trainingPlaceZipCode').val($('#headOfficeZipCode').val());
		$('#trainingPlaceZipCode').attr('readonly',true);
		
		$('#trainingPlaceCity').val($('#headOfficeCity').val());
		$('#trainingPlaceCity').attr('readonly',true);
		
		$('#trainingPlaceCountryId').val($('#headOfficeCountryId').val());
		$('#trainingPlaceCountryId').attr('disabled',true);
		

	}else{
		
		$('#trainingPlaceName').attr('readonly','');
		$('#trainingPlaceAddress1').attr('readonly','');
		$('#trainingPlaceAddress2').attr('readonly','');
		$('#trainingPlaceAddress3').attr('readonly','');
		$('#trainingPlaceZipCode').attr('readonly','');
		$('#trainingPlaceCity').attr('readonly','');
		$('#trainingPlaceCountryId').attr('disabled','');

	}
}




function signatoryIsDirector() {
	if($('#isDirector1:checked').val() == 'true') {
		
		$('#directorFadId').val($('#signatoryFadId').val());
		$('#directorFadId').attr('readonly',true);
		
		$('#directorFunction').val($('#signatoryFunction').val());
		$('#directorFunction').attr('readonly',true);
		
		$('#directorLastName').val($('#signatoryLastName').val());
		$('#directorLastName').attr('readonly',true);
		
		$('#directorFirstName').val($('#signatoryFirstName').val());
		$('#directorFirstName').attr('readonly',true);
		
		$('#directorEmail').val($('#signatoryEmail').val());
		$('#directorEmail').attr('readonly',true);
		
		$('#directorPhone').val($('#signatoryPhone').val());
		$('#directorPhone').attr('readonly',true);
		
		$('#directorFax').val($('#signatoryFax').val());
		$('#directorFax').attr('readonly',true);

		
	}else{
		
		$('#directorFadId').attr('readonly','');
		$('#directorFunction').attr('readonly','');
		$('#directorLastName').attr('readonly','');
		$('#directorFirstName').attr('readonly','');
		$('#directorEmail').attr('readonly','');
		$('#directorPhone').attr('readonly','');
		$('#directorFax').attr('readonly','');
	}
}



function updateSignatoryLinkedfield(field) {
	
	if($('#isDirector1:checked').val() == 'true') {
		$('#director' + field.id.substring(9,field.id.length)).val(field.value);
	}
}

function updateHeadOfficeLinkedfield(field) {
	
	if($('#isTrainingPlace1:checked').val() == 'true') {
		$('#trainingPlace' + field.id.substring(10,field.id.length)).val(field.value);
	}
}

function displaySubmit() {
	
	if($('#atteste:checked').val() == 'on') {
		$('#submitButton').fadeIn('fast');
	}else{
		$('#submitButton').fadeOut('fast');
	}
}
