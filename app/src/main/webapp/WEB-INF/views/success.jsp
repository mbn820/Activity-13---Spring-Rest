<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>

<html>
	<body>
		${createdPerson.name} <br/>
		${createdPerson.address} <br/>
		${createdPerson.currentlyEmployed} <br/>
		${createdPerson.gwa} <br/>
		${createdPerson.roles} <br/>
		${createdPerson.contacts} <br/>

		<c:forEach items = "${createdPerson.roles}" var = "role">
			${role.id}
		</c:forEach>

	</body>
</html>
