<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Add Role</title>
	</head>
	<body>
		<h3>Add Role</h3>
		<hr/>

		Enter new Role: <br/>
		<form action = 'AddRoles' method = 'GET'>
			<input type = 'text' name = 'role'/> <br/>
			<input type = 'submit' value = 'Add'/>
			<c:if test = "${roleExistsException != null}">
				Error: Role already exists!
			</c:if>
			<br/>
		</form>
		<hr/>

		<h3>Existing Roles</h3> <br/>
		<table border = "1">
			<tr bgcolor = "#85ce58">
				<th>Role</th>
				<th>Persons</th>
			</tr>
			<c:forEach items = "${existingRoles}" var = "role">
				<tr>
					<td>${role.roleName}</td>
					<td>${role.persons}</td>
				</tr>
			</c:forEach>
		</table>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
