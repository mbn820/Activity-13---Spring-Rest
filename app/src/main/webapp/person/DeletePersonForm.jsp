<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Delete Persons</title>
	</head>
	<body>
		<h3>Delete Persons</h3>
		<hr/>

		<form action = "DeletePerson" method = "GET">
		<table border = "1">
			<tr bgcolor = '#77929b'>
				<c:import url = "/person/PersonTableHeader.jsp"/>
				<th>
					<input type = "submit" value = "DELETE">
				</th>
			</tr>
			<c:forEach items = "${requestScope.personList}" var = "person">
				<tr>
					<c:set var = "person" value = "${person}" scope = "request"/>
					<c:import url = "/person/PersonTableContents.jsp"/>
					<td><input type = 'checkbox' value = "${person.id}" name = 'idToBeDeleted'/></td>
				</tr>
			</c:forEach>
		</table>
		</form>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
