<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Delete Persons</title>
	</head>
	<body>
		<h3>Delete Persons</h3>
		<hr/>

		<form action = "DeletePerson" method = "GET">
		<table border = "1">
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Street Number</th>
				<th>Barangay</th>
				<th>Municipality</th>
				<th>Zipcode</th>
				<th>Birth Date</th>
				<th>Date Hired</th>
				<th>Currently Employed</th>
				<th>GWA</th>
				<th>Roles</th>
				<th>Delete</th>
			</tr>
			<c:forEach items = "${requestScope.personList}" var = "person">
				<tr>
					<!-- <td><c:out value = "${person.id}"/></td> -->
					<td>${person.id}</td>
					<td>${person.name.firstName}</td>
					<td>${person.name.middleName}</td>
					<td>${person.name.lastName}</td>
					<td>${person.address.streetNumber}</td>
					<td>${person.address.barangay}</td>
					<td>${person.address.municipality}</td>
					<td>${person.address.zipcode}</td>
					<td>${person.birthDate}</td>
					<td>${person.dateHired}</td>
					<td>${person.currentlyEmployed}</td>
					<td>${person.gwa}</td>
					<td>${person.roles}</td>
					<td><input type = 'checkbox' value = "${person.id}" name = 'idToBeDeleted'/></td>
				</tr>
			</c:forEach>
		</table>
		<input type = "submit" value = "DELETE">
		</form>
	</body>
</html>
