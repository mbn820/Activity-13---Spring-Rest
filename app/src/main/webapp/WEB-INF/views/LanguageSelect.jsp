<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<spring:message code="label.language"/>
<select id="language">
	<option value="en" ${param.lang == 'en' ? 'selected' : ''}>English</option>
	<option value="tg" ${param.lang == 'tg' ? 'selected' : ''}>Tagalog</option>
</select>
