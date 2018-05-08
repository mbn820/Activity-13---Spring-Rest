<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>File Upload</title>
		<style>
			.error {
				color: #ff0000;
				font-size: 15px;
			}

			.errorblock {
				color: #000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
		</style>
	</head>

	<body>
		<h3>FILE UPLOAD</h3>
		<hr/>

		<form:form method = "POST" commandName = "fileUpload" action = "/fileUpload.htm" enctype = "multipart/form-data">
			Select file to upload:
			<input type = "file" name = "multipartFile" id = "multipartFile"/> <br/>
			<input type = "submit" value = "Upload"/>
			<form:errors path = "multipartFile" cssClass = "error"/>
		</form:form>
	</body>
</html>
