<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Full Person Details</title>
	</head>
	<body>
		<h3>Full Person Details</h3>
		<hr/>

		<h4>Basic Info</h4>
		<table border = "1">
			<tr bgcolor = '#77929b'>
				<th>ID</th>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Suffix</th>
				<th>Title</th>
				<th>Date Hired</th>
				<th>Birth Date</th>
				<th>GWA</th>
			</tr>
			<tr>
				<td>${person.id}</td>
				<td>${person.name.firstName}</td>
				<td>${person.name.middleName}</td>
				<td>${person.name.lastName}</td>
				<td>${person.name.suffix}</td>
				<td>${person.name.title}</td>
				<td>${person.dateHired}</td>
				<td>${person.birthDate}</td>
				<td>${person.gwa}</td>
			</tr>
		</table>

		<h4>Address</h4>
		<table border = "1">
			<tr bgcolor = '#efd945'>
				<th>Street Number</th>
				<th>Barangay</th>
				<th>Municipality</th>
				<th>Zipcode</th>
			</tr>
			<tr>
				<td>${person.address.streetNumber}</td>
				<td>${person.address.barangay}</td>
				<td>${person.address.municipality}</td>
				<td>${person.address.zipcode}</td>
			</tr>
		</table>

		<h4>Roles</h4>
		<table border = ".5">
			<tr bgcolor = '#80c471'>
				<th>ID</th>
				<th>Role</th>
			</tr>
			<c:forEach items = "${person.roles}" var = "role">
				<tr>
					<td>${role.id}</td>
					<td>${role.roleName}</td>
				</tr>
			</c:forEach>
		</table>

		<h4>Contacts</h4>

		<table border = "1">
			<tr bgcolor = "#dd8d46">
				<th>Type</th>
				<th>Details</th>
			</tr>
			<c:set var = "contacts" value = "${person.contacts}"/>
			<c:forEach items = "${contacts}" var = "contact">
				<tr>
					<td>${contact.type}</td>
					<td>${contact.detail}</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a href = "UpdatePerson?personId=${person.id}">UPDATE PERSON</a>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
