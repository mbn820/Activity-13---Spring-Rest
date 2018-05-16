<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Login</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/resources/jqueryscript.js"></script>
		<link rel="stylesheet" type="text/css" href="/resources/login.css"/>
	</head>

	<body>
		<div align="center">
			<table>
				<th>Enter Username and Password</th>
				<tr>
					<td>USERNAME <br>
					    <input type="text"/></td>
				</tr>
				<tr>
					<td>PASSWORD <br>
					    <input type="password"/></td>
				</tr>
				<th><input type="submit" value="LOGIN"/></th>
			</table>
		</div>
	</body>
</html>
