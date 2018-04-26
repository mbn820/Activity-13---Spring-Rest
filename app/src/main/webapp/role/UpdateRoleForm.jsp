<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Update Roles</title>
	</head>
	<body>
		<h3>Update Role</h3>
		<hr/>

		<form action = "UpdateRoles" method = "GET">
		<table border = "1">
			<tr bgcolor = "#85ce58">
				<th>ID</th>
				<th>Role</th>
			</tr>
			<c:forEach items = "${existingRoles}" var = "role">
				<tr>
					<td>
						<a href = "/UpdateRoles?roleId=${role.id}">${role.id}</a>
					</td>
					<td>${role.roleName}</td>
				</tr>
			</c:forEach>
		</table>
		</form>
		<hr/>
		<c:if test = "${not empty param.roleId}">
			<form action = "UpdateRoles" method = "POST">
				Enter new name for role ${roleToBeUpdated.roleName} <br/>
				<input type = "hidden" name = "idToBeUpdated" value = "${roleToBeUpdated.id}">
				<input type = "text" name = "newRoleName"> <br/>
				<input type = "submit" value = "UPDATE">
			</form>
		</c:if>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
