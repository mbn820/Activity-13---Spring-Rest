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

		<table border = "1">
			<tr bgcolor = '#77929b'>
				<c:import url = "/person/PersonTableHeader.jsp"/>
			</tr>
			<c:forEach items = "${requestScope.person}" var = "person">
				<tr>
					<c:set var = "person" value = "${person}" scope = "request"/>
					<c:import url = "PersonTableContents.jsp"/>
				</tr>
			</c:forEach>
		</table>
		<hr/>

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
