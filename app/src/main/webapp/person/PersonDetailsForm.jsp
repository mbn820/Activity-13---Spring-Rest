<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Person Details</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                $("#add-button").click(function() {
                    $(".contacts").append( $(".elem").html() );
                });
            });
        </script>
	</head>

	<body>
		<h4>ID</h4>
		PERSON ID <br/>
		<input type = "text" name = "id" readonly value = "${person.id}"> <br/>

		<hr/>

		<div>
			<h4>NAME</h4>
			First Name <br/>
			<input type = "text" name = "firstName" value = "${person.name.firstName}"> <br/>
			Middle Name <br/>
			<input type = "text" name = "middleName" value = "${person.name.middleName}"> <br/>
			Last Name <br/>
			<input type = "text" name = "lastName" value = "${person.name.lastName}"> <br/>
			Suffix <br/>
			<input type = "text" name = "suffix" value = "${person.name.suffix}"> <br/>
			Title <br/>
			<input type = "text" name = "title" value = "${person.name.title}"> <br/>
		</div>

		<!-- <div style = "text-align:center">
			<h4>NAME</h4>
			<input type = "text" name = "firstName" value = "${person.name.firstName}" placeholder = "First Name"> <br/>
			<input type = "text" name = "middleName" value = "${person.name.middleName}" placeholder = "Middle Name"> <br/>
			<input type = "text" name = "lastName" value = "${person.name.lastName}" placeholder = "Last Name"> <br/>
			<input type = "text" name = "suffix" value = "${person.name.suffix}" placeholder = "Suffix"> <br/>
			<input type = "text" name = "title" value = "${person.name.title}" placeholder = "Title"> <br/>
		</div> -->

		<hr/>

		<h4>ADDRESS</h4>
		Street Number <br/>
		<input type = "text" name = "streetNumber" value = "${person.address.streetNumber}"> <br/>
		Barangay <br/>
		<input type = "text" name = "barangay" value = "${person.address.barangay}"> <br/>
		Municipality <br/>
		<input type = "text" name = "municipality" value = "${person.address.municipality}"> <br/>
		Zipcode <br/>
		<input type = "text" name = "zipcode" value = "${person.address.zipcode}"> <br/>

		<hr/>

		<h4>BIRTH DATE</h4>
		Date <br/>
		<input type = "date" name = "birthDate" value = "${person.birthDate}"/>

		<hr/>

		<h4>DATE HIRED</h4>
		Date <br/>
		<input type = "date" name = "dateHired" value = "${person.dateHired}"/>

		<hr/>

		<h4>EMPLOYMENT</h4>
		Employed? <br/>
		<c:choose>
			<c:when test = "${person.currentlyEmployed}">
				<c:set var = "trueChecked" value = "checked"/>
				<c:set var = "falseChecked" value = ""/>
			</c:when>
			<c:otherwise>
				<c:set var = "trueChecked" value = ""/>
				<c:set var = "falseChecked" value = "checked"/>
			</c:otherwise>
		</c:choose>

		<input type = "radio" name = "currentlyEmployed" value = "True" ${trueChecked}/> Yes <br/>
		<input type = "radio" name = "currentlyEmployed" value = "False" ${falseChecked}/> No <br/>

		<hr/>

		<h4>GWA</h4>
		Enter gwa (1.0 - 5.0) <br/>
		<input type = "text" name = "gwa" value = "${person.gwa}"/> <br/>

		<hr/>

		<h4>ROLES</h4>
		Select Roles <br/>
		<c:forEach items = "${existingRoles}" var = "role">
			<c:set var = "checked" value = "${person.roles.contains(role) ? 'checked' : ''}"/>
			<input type = "checkbox" value = "${role.id}" name = "roles" ${checked}> ${role.roleName} <br/>
		</c:forEach>

		<hr/>

		<h4>CONTACTS</h4>
		Cellphone Number <br/>
		<c:forEach items = "${person.contacts}" var = "contact">
			<c:if test = "${contact.type == 'phone'}">
				<c:set var = "phone" value = "${contact.detail}"/>
			</c:if>
			<c:if test = "${contact.type == 'landline'}">
				<c:set var = "landline" value = "${contact.detail}"/>
			</c:if>
			<c:if test = "${contact.type == 'email'}">
				<c:set var = "email" value = "${contact.detail}"/>
			</c:if>
		</c:forEach>
		<input type = "text" name = "contacts_phone" value = "${phone}"> <br/>
		Landline <br/>
		<input type = "text" name = "contacts_landline" value = "${landline}"> <br/>
		Email Address <br/>
		<input type = "text" name = "contacts_email" value = "${email}"> <br/>

		<!-- <h4>CONTACTS</h4>
        <input type = "button" value = "ADD" id="add-button">
        <br/>
        <div class = "contacts">
            <div class = "elem">
                <select name = "type">
                    <option value = "cellphone">CellPhone</option>
                    <option value = "landline">Landline</option>
                    <option value = "email">Email</option>
                </select>
                <input type = "text" name = "detail">
                <br/>
            </div>
        </div> -->


	</body>
</html>
