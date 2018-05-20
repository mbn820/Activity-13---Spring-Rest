<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/resources/login.css"/>
	</head>
	<body>
		<div align="center"><h1 class="welcome">WELCOME</h1></div>

		<br><br><br><br><br><br><br><br>
		<div align="center">
		<form action="j_spring_security_check" method="POST">
			<table>
				<tr>
					<th colspan="2">LOGIN WITH USERNAME AND PASSWORD</th>
				</tr>
				<tr>
					<td>Username</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="LOGIN"/>
					</th>
				</tr>
			</table>
		</form>

		<c:if test="${not empty errorMsg}">
			<div class="error">${errorMsg}</div>
		</c:if>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	    </div>
	</body>
</html>
