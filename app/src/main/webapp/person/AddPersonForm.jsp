<%@ page import="java.util.List" %>
<%@ page import="com.exist.ecc.core.model.Role" %>
<%@ page import="com.exist.ecc.core.service.RoleService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Add Person</title>
	</head>

	<body>
		<h3>Add Person</h3>

		<hr/>

		<form action = "/AddPerson" method = "POST">
			<c:import url = "PersonDetailsForm.jsp"/>
			<hr/>
			<br/><br/> <input type = "submit" value = "SUBMIT"/>
		</form>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>

</html>
