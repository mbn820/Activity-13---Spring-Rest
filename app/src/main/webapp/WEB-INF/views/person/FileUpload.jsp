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
		<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
		<c:import url="../util/NavigationBar.jsp"/>
		<div class="mainWrapper">
		<h3>FILE UPLOAD</h3>
		<hr/>

		<form:form method="POST" commandName="fileUpload" action="/person/fileUpload" enctype="multipart/form-data">
			Select file to upload:
			<input type="file" name="multipartFile" id="multipartFile"/> <br>
			<input type="submit" value="Upload"/>
			<form:errors path="multipartFile" cssClass="error"/> <br>
		</form:form>
		</div>
	</body>
</html>
