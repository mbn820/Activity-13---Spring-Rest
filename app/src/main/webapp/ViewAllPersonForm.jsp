<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>View All Person</title>
	</head>
	<body>
		<h3>List of all Persons</h3>
		<hr/>
		<h4>SORTED BY: <c:out value = "${requestScope.orderBy}"/></h4>
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

		<br/><br/>Sort by:
		<form action = 'ViewAllPersons' method = 'GET'>
			<input type = 'radio' name = 'orderBy' value = 'name.lastName'/>Last Name<br/>
			<input type = 'radio' name = 'orderBy' value = 'dateHired'/>Date Hired<br/>
			<input type = 'radio' name = 'orderBy' value = 'gwa'/>GWA<br/>
			<input type = 'submit' value = 'Sort'/><br/>
		</form>
	</body>
</html>
