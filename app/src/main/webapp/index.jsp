<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<body>
		<c:set var = "selectedLang" value = "en"/>
		<div style = "text-align:center">
			<h2>Manage Persons</h2> <br/>
			<a href = "/addOrUpdatePerson.htm">Add Person</a> <br/>
			<a href = "/managePersons.htm">Manage Persons</a> <br/>
	    </div>

		<div style = "text-align:center">
			<h2>Manage Roles</h2> <br/>
			<a href = "/manageRoles.htm">Manage Roles</a> <br/>
		</div>

		<div style = "text-align:center">
			<h2>File Upload</h2> <br/>
			<a href = "/fileUpload.htm">Upload a File</a> <br/>
		</div>
	</body>
</html>
