<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Update Person</title>
	</head>
	<body>
		<h3>Update Person</h3>
		<hr/>
		<form action = "UpdatePerson" method = "POST">
			<c:set var = "person" value = "${person}" scope = "request"/>
			<c:import url = "/person/PersonDetailsForm.jsp"/>
			<input type = "submit" value = "SAVE CHANGES">
		</form>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
