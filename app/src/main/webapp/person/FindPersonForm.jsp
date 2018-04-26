<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Find Persons</title>
	</head>
	<body>
		<h3>Find Person By Last Name</h3>
		<hr/>

		Enter person last name <br/>
		<form action = '/FindPerson' method = 'GET'>
			<input type = 'text' name = 'lastName'/> <br/>
			<input type = 'submit' value = 'Find'/> <br/>
		</form>
		<hr/>

		<c:if test = "${not empty param.lastName}">
			<h3>Results</h3> <br/>
			<table border = "1">
				<tr bgcolor = '#77929b'>
					<c:import url = "PersonTableHeader.jsp"/>
				</tr>
				<c:forEach items = "${requestScope.personList}" var = "person">
					<tr>
						<c:set var = "person" value = "${person}" scope = "request"/>
						<c:import url = "PersonTableContents.jsp"/>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
