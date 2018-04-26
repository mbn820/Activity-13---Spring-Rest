<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Delete Roles</title>
	</head>
	<body>
		<h3>Delete Roles</h3>
		<hr/>

		<form action = "DeleteRoles" method = "GET">
		<table border = "1">
			<tr bgcolor = "#85ce58">
				<th>Role</th>
				<th>Persons</th>
				<th>
					<input type = "submit" value = "DELETE">
				</th>
			</tr>
			<c:forEach items = "${existingRoles}" var = "role">
			<c:set var = "state" value = "${empty role.persons ? '' : 'disabled'}"/>
				<tr>
					<td>${role.roleName}</td>
					<td>${role.persons}</td>
					<td>
						<input type = 'checkbox' value = "${role.id}" name = 'idToBeDeleted' ${state}>
					</td>
				</tr>
			</c:forEach>
		</table>
		</form>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
