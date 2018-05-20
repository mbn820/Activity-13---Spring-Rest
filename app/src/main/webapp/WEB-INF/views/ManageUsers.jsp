<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

		<div class="mainWrapper" align="center">
			<h3>Manage Users</h3>
			<hr/>
			<table border="1">
				<tr bgcolor="#ea8369">
					<th>ID</th>
					<th>USERNAME</th>
					<th>ROLE</th>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<th>UPDATE</th>
					<th>DELETE</th>
					</sec:authorize>
				</tr>

				<c:forEach items="${allUsers}" var="user">
					<tr>
						<td align="center">
							${user.id}
						</td>
						<td>${user.userName}</td>
						<td>${user.userRole}</td>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<td align="center">
								<a href="/deleteUser/${user.id}">Delete</a>
							</td>
							<td align="center">
								<a href="/updateUser/${user.id}">Update</a>
							</td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>
