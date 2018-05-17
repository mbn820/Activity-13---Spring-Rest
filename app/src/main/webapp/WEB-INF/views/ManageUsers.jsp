<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Manage Users</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/resources/jqueryscript.js"></script>
		<link rel="stylesheet" type="text/css" href="/resources/style.css"/>
	</head>

	<body>
		<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
		<c:import url="NavigationBar.jsp"/>
		<h3>Manage Users</h3>
		<hr/>

		<div align="center">
		<table border="1">
			<tr bgcolor="#ea8369">
				<th>ID</th>
				<th>USERNAME</th>
				<th>ROLE</th>
				<th>UPDATE</th>
				<th>DELETE</th>
			</tr>

			<c:forEach items="${allUsers}" var="user">
				<tr>
					<td align="center">
						${user.id}
					</td>
					<td>${user.userName}</td>
					<td>${user.userRole}</td>
					<td align="center">
						<a href="/deleteUser/${user.id}.htm">Delete</a>
					</td>
					<td align="center">
						<a href="/updateUser/${user.id}.htm">Update</a>
					</td>
				</tr>
			</c:forEach>

		</table>
		</div>

		<hr/>

	</body>
</html>
