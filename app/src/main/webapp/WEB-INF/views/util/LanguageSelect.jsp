<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<c:set var="theLanguage">
	<spring:message code="label.language"/>
</c:set>
${theLanguage}:
<select id="language">
	<option value="en" ${theLanguage == 'Language' ? 'selected' : ''}>English</option>
	<option value="tg" ${theLanguage == 'Wika' ? 'selected' : ''}>Tagalog</option>
</select>
