<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>

<html>
	<body>
		<form:form method = "POST" commandName = "employee">
			First Name: <form:input path = "name.first"/> <br/>
			Last Name: <form:input path = "name.last"/> <br/>
			Address: <form:input path = "address"/> <br/>
			Age: <form:input path = "age"/> <br/>
			<input type = "submit" value = "Pass"/>
		</form:form>

		<h4>Result</h4>
		${employee.name} <br/>
		${employee.address} <br/>
		${employee.age} <br/>

	</body>
</html>
