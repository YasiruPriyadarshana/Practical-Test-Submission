<%@page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Hospitals.js"></script>

</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Hospital Management</h1>
				
				<form id="formHospital" name="formHospital">
					Hospital Address : <input id="Address" name="Address" type="text"
						class="form-control form-control-sm"> <br> 
					Hospital City :	<input id="City" name="City" type="text"
						class="form-control form-control-sm"> <br> 
					Hospital Phone : <input id="Phone" name="Phone" type="text"
						class="form-control form-control-sm"> <br>
					Hospital Name : <input id="Name" name="Name" type="text"
						class="form-control form-control-sm"> <br> 
					Hospital Rooms : <input id="Rooms" name="Rooms" type="text"
						class="form-control form-control-sm"> <br> 
						<input	id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> 
						<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				
				<div id="divHospitalGrid">
					<%
					Hospital hospitalObj = new Hospital();
					out.print(hospitalObj.readHospitals());
					%>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>