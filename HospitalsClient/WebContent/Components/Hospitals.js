$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateHospitalForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidHospitalIDSave").val() == "") ? "PUT" : "POST";
	
	$.ajax(
	{
		url : "HospitalAPI",
		type : type,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onHospitalSaveComplete(response.responseText, status);
		}
	});
	
});



//remove----
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "HospitalAPI",
		type : "DELETE",
		data : "HospitalID=" + $(this).data("HospitalID"),
		dataType : "text",
		complete : function(response, status) {
			oneHospitalDeleteComplete(response.responseText, status);
		}
	});
});




function onHospitalSaveComplete(response, status) {
	if (status == "success") {
		
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divHospitalGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") 
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidHospitalIDSave").val("");
	$("#formHospital")[0].reset();
}




function oneHospitalDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divHospitalGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hideHospitalIDSave").val(
					$(this).closest("tr").find('#hidHospitalIDUpdate').val());
			$("#Address").val($(this).closest("tr").find('td:eq(0)').text());
			$("#City").val($(this).closest("tr").find('td:eq(1)').text());
			$("#Phone").val($(this).closest("tr").find('td:eq(2)').text());
			$("#Name").val($(this).closest("tr").find('td:eq(3)').text());
			$("#Rooms").val($(this).closest("tr").find('td:eq(4)').text());
});



//CLIENTMODEL=========================================================================
function validateHospitalForm() {
	// CODE
	if ($("#Address").val().trim() == "") {
		return "Insert Hospital Address.";
	}
	// NAME
	if ($("#City").val().trim() == "") {
		return "Insert Hospital City.";
	}
	
	// PRICE-------------------------------
	if ($("#Phone").val().trim() == "") {
		return "Insert Hospital Phone.";
	}
	
	// is numerical value
	var tmpPrice = $("#Phone").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for Phone Number.";
	}
	// convert to decimal price
	
	$("#Phone").val(parseInt(tmpPrice));
	// DESCRIPTION------------------------
	if ($("#Name").val().trim() == "") {
		return "Insert Hospital Name.";
	}
	//Rooms 
	if ($("#Rooms").val().trim() == "") {
		return "Insert Hospital Rooms.";
	}
	return true;
}