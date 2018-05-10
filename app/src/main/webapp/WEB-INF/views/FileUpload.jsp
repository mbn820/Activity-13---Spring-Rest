<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>File Upload</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/resources/jqueryscript.js"></script>
		<link rel="stylesheet" type="text/css" href="/resources/style.css"/>
	</head>

	<body>
		<h3>FILE UPLOAD</h3>
		<hr/>

		<form:form method="POST" commandName="fileUpload" action="/fileUpload.htm" enctype="multipart/form-data">
			Select file to upload:
			<input type="file" name="multipartFile" id="multipartFile"/> <br>
			<input type="submit" value="Upload"/>
			<form:errors path="multipartFile" cssClass="error"/> <br>
		</form:form>

		<hr/>
		<a href="/addOrUpdatePerson.htm">Add Person</a> <br>
		<a href="/managePersons.htm">Manage Persons</a> <br>
		<a href="/manageRoles.htm">Manage Roles</a> <br>
		<a href="/index.jsp">HOME</a>
	</body>
</html>
