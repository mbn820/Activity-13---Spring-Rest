<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<c:set var="requestType" value="Add User"/>
	<c:set var="action" value="/addUserSubmit"/>
	<c:if test="${user.id != 0}">
		<c:set var="requestType" value="Update User"/>
		<c:set var="action" value="/updateUserSubmit"/>
	</c:if>
	<head>
		<title>${requestType}</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/resources/jqueryscript.js"></script>
		<link rel="stylesheet" type="text/css" href="/resources/login.css"/>
	</head>

	<body>
		<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
		<c:import url="NavigationBar.jsp"/>
		<div class="mainWrapper" align="center">
		<h3>${requestType}</h3>
		<hr/>

		<form:form method="POST" modelAttribute="user" action="${action}">
			<table>
				<form:hidden path="id"/>
				<th colspan="2">
					Create new User Account
				</th>
				<tr>
					<td>Username</td>
					<td><form:input path="userName"/> <br>
						<form:errors path="userName" cssClass="error"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input type="password" path="password" placeholder="Minimum length(6)"/> <br>
						<form:errors path="password" cssClass="error"/></td>
				</tr>
				<tr>
					<td>User Role</td>
					<td><form:radiobuttons items="${userRoles}" path="userRole" delimiter="<br>"/></td>
				</tr>
				<th colspan="2"><input type="submit" value="${requestType}"/></th>
			</table>
		</form:form>
		</div>
	</body>
</html>
