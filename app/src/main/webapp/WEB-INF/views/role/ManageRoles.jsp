<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Manage Roles</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/resources/jqueryscript.js"></script>
		<link rel="stylesheet" type="text/css" href="/resources/style.css"/>
	</head>

	<body>
		<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
		<c:import url="../util/NavigationBar.jsp"/>
		<div class="mainWrapper">
		<h3>Manage Roles</h3>
		<hr/>
		<c:import url="../util/LanguageSelect.jsp"/>

		<form:form method="POST" modelAttribute="role" action="/role/addRole">
			<form:input path="roleName" placeholder="Enter new Role"/>
			<input type="submit" value="Add"/>
			<form:errors path="roleName" cssClass="error"/>
		</form:form>
		<table border="1" width="100%">
			<tr bgcolor="#85ce58">
				<th>ID</th>
				<th><spring:message code="label.role"/></th>
				<th><spring:message code="label.persons"/></th>
				<th><spring:message code="label.update"/></th>
				<th><spring:message code="label.delete"/></th>
			</tr>
			<c:forEach items="${existingRoles}" var="role">
				<tr>
					<td align="center">
						${role.id}</td>
					<td>${role.roleName}</td>
					<td>${role.persons}</td>
					<td align="center">
						<input type="button" name="update-role-button" value="<spring:message code="label.update"/>" data-origName="${role.roleName}" data-roleId="${role.id}"/>
					</td>
					<td align="center">
						<input type="button" name="delete-role-button" value="<spring:message code="label.delete"/>" data-roleId="${role.id}"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>

	</body>
</html>
