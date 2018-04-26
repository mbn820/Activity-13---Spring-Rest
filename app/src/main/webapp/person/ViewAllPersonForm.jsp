<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>View All Person</title>
	</head>
	<body>
		<h3>List of all Persons</h3>
		<hr/>
		<h4>SORTED BY: <c:out value = "${requestScope.orderBy}"/></h4>
		<table border = "1">
			<tr bgcolor = '#77929b'>
				<c:import url = "/person/PersonTableHeader.jsp"/>
			</tr>
			<c:forEach items = "${personList}" var = "person">
				<tr>
					<c:set var = "person" value = "${person}" scope = "request"/>
					<c:import url = "PersonTableContents.jsp"/>
				</tr>
			</c:forEach>
		</table>

		<br/><br/>Sort by:
		<form action = 'ViewAllPersons' method = 'GET'>
			<input type = 'radio' name = 'orderBy' value = 'name.lastName'/>Last Name<br/>
			<input type = 'radio' name = 'orderBy' value = 'dateHired'/>Date Hired<br/>
			<input type = 'radio' name = 'orderBy' value = 'gwa'/>GWA<br/>
			<input type = 'submit' value = 'Sort'/><br/>
		</form>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
