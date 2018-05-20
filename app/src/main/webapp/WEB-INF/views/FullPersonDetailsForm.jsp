<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Full Person Details</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/resources/jqueryscript.js"></script>
		<link rel="stylesheet" type="text/css" href="/resources/style.css"/>
	</head>
	<body>
		<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
		<c:import url="NavigationBar.jsp"/>
		<div class="mainWrapper">
		<h3>Full Person Details</h3>
		<hr/>

		<h4>Basic Info</h4>
		<table border="1">
			<tr bgcolor='#77929b'>
				<th>ID</th>
				<th><spring:message code="label.name.firstName"/></th>
				<th><spring:message code="label.name.middleName"/></th>
				<th><spring:message code="label.name.lastName"/></th>
				<th><spring:message code="label.name.suffix"/></th>
				<th><spring:message code="label.name.title"/></th>
				<th><spring:message code="label.dateHired"/></th>
				<th><spring:message code="label.birthDate"/></th>
				<th><spring:message code="label.gwa"/></th>
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
		<table border="1">
			<tr bgcolor='#efd945'>
				<th><spring:message code="label.address.streetNumber"/></th>
				<th><spring:message code="label.address.barangay"/></th>
				<th><spring:message code="label.address.municipality"/></th>
				<th><spring:message code="label.address.zipcode"/></th>
			</tr>
			<tr>
				<td>${person.address.streetNumber}</td>
				<td>${person.address.barangay}</td>
				<td>${person.address.municipality}</td>
				<td>${person.address.zipcode}</td>
			</tr>
		</table>

		<h4>Roles</h4>
		<table border=".5">
			<tr bgcolor='#80c471'>
				<th>ID</th>
				<th><spring:message code="label.roles"/></th>
			</tr>
			<c:forEach items="${person.roles}" var="role">
				<tr>
					<td>${role.id}</td>
					<td>${role.roleName}</td>
				</tr>
			</c:forEach>
		</table>

		<h4>Contacts</h4>

		<table border="1">
			<tr bgcolor="#dd8d46">
				<th>Type</th>
				<th>Details</th>
			</tr>
			<c:set var="contacts" value="${person.contacts}"/>
			<c:forEach items="${contacts}" var="contact">
				<tr>
					<td>${contact.type}</td>
					<td>${contact.detail}</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a href="/addOrUpdatePerson?personId=${person.id}">UPDATE PERSON</a>
		<hr>
		</div>
	</body>
</html>
