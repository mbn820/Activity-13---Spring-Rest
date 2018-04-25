<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Find Persons</title>
	</head>
	<body>
		<h3>Find Person By Last Name</h3>
		<hr/>

		Enter person last name: <br/>
		<form action = 'FindPerson' method = 'GET'>
			<input type = 'text' name = 'lastName'/> <br/>
			<input type = 'submit' value = 'Find'/> <br/>
		</form>
		<hr/>

		<c:if test = "${not empty param.lastName}">
			<h3>Results</h3> <br/>
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
				</tr>
				<c:forEach items = "${requestScope.personList}" var = "person">
					<tr>
						<td><c:out value = "${person.id}"/></td>
						<td><c:out value = "${person.name.firstName}"/></td>
						<td><c:out value = "${person.name.middleName}"/></td>
						<td><c:out value = "${person.name.lastName}"/></td>
						<td><c:out value = "${person.address.streetNumber}"/></td>
						<td><c:out value = "${person.address.barangay}"/></td>
						<td><c:out value = "${person.address.municipality}"/></td>
						<td><c:out value = "${person.address.zipcode}"/></td>
						<td><c:out value = "${person.birthDate}"/></td>
						<td><c:out value = "${person.dateHired}"/></td>
						<td><c:out value = "${person.currentlyEmployed}"/></td>
						<td><c:out value = "${person.gwa}"/></td>
						<td><c:out value = "${person.roles}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>
</html>
