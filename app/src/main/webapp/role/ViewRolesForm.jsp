<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>View Roles</title>
	</head>
	<body>
		<h3>View Roles</h3>
		<hr/>

		<h4>Existing Roles</h4> <br/>
		<table border = "1">
			<tr bgcolor = "#85ce58">
				<th>ID</th>
				<th>Role</th>
				<th>Persons</th>
			</tr>
			<c:forEach items = "${existingRoles}" var = "role">
				<tr>
					<td>${role.id}</td>
					<td>${role.roleName}</td>
					<td>${role.persons}</td>
				</tr>
			</c:forEach>
		</table>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
