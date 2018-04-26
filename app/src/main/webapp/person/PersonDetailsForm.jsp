<%@ page import="java.util.List" %>
<%@ page import="com.exist.ecc.core.model.Role" %>
<%@ page import="com.exist.ecc.core.service.RoleService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Person Details</title>
	</head>

	<body>
		<h4>ID</h4>
		PERSON ID                                    <br/>
		<input type = "text" name = "id" readonly value = "${person.id}">   <br/>

		<hr/>

		<h4>NAME</h4>
		First Name                                   <br/>
		<input type = "text" name = "firstName" value = "${person.name.firstName}">     <br/>
		Middle Name                                  <br/>
		<input type = "text" name = "middleName" value = "${person.name.middleName}">    <br/>
		Last Name                                    <br/>
		<input type = "text" name = "lastName" value = "${person.name.lastName}">      <br/>
		Suffix                                       <br/>
		<input type = "text" name = "suffix" value = "${person.name.suffix}">        <br/>
		Title                                        <br/>
		<input type = "text" name = "title" value = "${person.name.title}">         <br/>

		<hr/>

		<h4>ADDRESS</h4>
		Street Number                                <br/>
		<input type = "text" name = "streetNumber" value = "${person.address.streetNumber}">  <br/>
		Barangay                                     <br/>
		<input type = "text" name = "barangay" value = "${person.address.barangay}">      <br/>
		Municipality                                 <br/>
		<input type = "text" name = "municipality" value = "${person.address.municipality}">  <br/>
		Zipcode                                      <br/>
		<input type = "text" name = "zipcode" value = "${person.address.zipcode}">       <br/>

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
		<input type = "text" name = "contacts_phone" value = "${person.contacts}"> <br/>
		Landline <br/>
		<input type = "text" name = "contacts_landline"> <br/>
		Email Address <br/>
		<input type = "text" name = "contacts_email"> <br/>
		<hr/>

		<!-- <form action = "/StoreContacts" method = "GET">
			<h4>CONTACTS</h4>
			<select name = "type">
				<option value = "cellphone">CellPhone</option>
				<option value = "landline">Landline</option>
				<option value = "email">Email</option>
			</select>
			<input type = "text" name = "detail">
			<input type = "submit" value = "ADD">
		</form> -->

	</body>

</html>
