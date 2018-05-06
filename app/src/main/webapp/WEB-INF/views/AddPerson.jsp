<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Add Person</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				function createElement(contactType) {
					var elemw =
					"<tr>" +
						"<td>" +
							contactType +
						"</td>" +
						"<td>" +
							"<input type = 'text' name = '" + contactType + "'/>" +
						"</td>" +
					"</tr>";

					return elemw;
				}

				$("#add-cellphone-button").click(function() {
					var type = createElement("Cellphone");
					$("#contactsWrapper").append( type );
				});

				$("#add-landline-button").click(function() {
					var type = createElement("Landline");
					$("#contactsWrapper").append( type );
				});

				$("#add-email-button").click(function() {
					var type = createElement("Email");
					$("#contactsWrapper").append( type );
				});


			});
		</script>
	</head>

	<body>
		<h3 align = "center">Add Person</h3>
		<hr/>

		<div class = "tableForm" align = "center">
			<form:form method = "POST" commandName = "person">
				<table border = "1">
					<tbody>
						<th colspan = "2"><h4>NAME</h4></th>
						<tr>
							<td>First Name</td>
							<td><form:input path = "name.firstName"/></td>
						</tr>
						<tr>
							<td>Middle Name</td>
							<td><form:input path = "name.middleName"/></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><form:input path = "name.lastName"/></td>
						</tr>
						<tr>
							<td>Suffix</td>
							<td><form:input path = "name.suffix"/></td>
						</tr>
						<tr>
							<td>Title</td>
							<td><form:input path = "name.title"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>ADDRESS</h4></th>
						<tr>
							<td>Street Number</td>
							<td><form:input path = "address.streetNumber"/></td>
						</tr>
						<tr>
							<td>Barangay</td>
							<td><form:input path = "address.barangay"/></td>
						</tr>
						<tr>
							<td>Municipality</td>
							<td><form:input path = "address.municipality"/></td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td><form:input path = "address.zipcode"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>BIRTHDATE</h4></th>
						<tr>
							<td>Birth Date</td>
							<td><input type = "date" name = "birthDateParam"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>DATE HIRED</h4></th>
						<tr>
							<td>Date Hired</td>
							<td><input type = "date" name = "dateHiredParam"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>EMPLOYMENT</h4></th>
						<tr>
							<td>Employed?</td>
							<td>
								<form:radiobutton path = "currentlyEmployed" value = "true"/>YES
								<form:radiobutton path = "currentlyEmployed" value = "true"/>NO
							</td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>GWA</h4></th>
						<tr>
							<td>Enter GWA</td>
							<td>
								<form:input path = "gwa"/>
							</td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>ROLES</h4></th>
						<tr>
							<td>Select Roles:</td>

							<td>
								<c:forEach items = "${existingRoles}" var = "role">
									<input type = "checkbox" name = "rolesParam" value = "${role.id}"/>
									${role.roleName} <br/>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<tbody id = "contactsWrapper">
							<th colspan = "2">
								<h4>CONTACTS</h4>
								<input type = "button" value = "Add Cellphone" id = "add-cellphone-button"/>
								<input type = "button" value = "Add Landline" id = "add-landline-button"/>
								<input type = "button" value = "Add Email" id = "add-email-button"/>
							</th>
						</tbody>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>
					</tbody>
				</table>
				<input type = "submit" value = "ADD"/>
			</form:form>
		</div>
	</body>

</html>
