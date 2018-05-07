<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Add Person</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				var incrementor = 0;

				function createElement(contactType) {
					var autoId = "link" + incrementor;
					var elemw =
					"<tr id = " + autoId + ">" +
						"<td>" +
							contactType +
						"</td>" +
						"<td>" +
							"<input type = 'text' name = '" + contactType + "'/>" +
							"<input type = 'button' id = 'remove-button' value = 'X' name = " + autoId + ">" +
						"</td>" +
					"</tr>";

					incrementor++;

					return elemw;
				}

				$("#add-cellphone-button").click(function() {
					var type = createElement("Cellphone");
					$("#contactsSection").append( type );
				});

				$("#add-landline-button").click(function() {
					var type = createElement("Landline");
					$("#contactsSection").append( type );
				});

				$("#add-email-button").click(function() {
					var type = createElement("Email");
					$("#contactsSection").append( type );
				});

				$(document).on('click', '#remove-button', function() {
					var elemToBeRemoved = $(this).attr("name");
					$("#" + elemToBeRemoved).remove();
				});

			});
		</script>
		<style>
			.error {
				color: #ff0000;
				font-size: 10px;
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
		<h3 align = "center">${requestType}</h3>
		<hr/>

		<div class = "tableForm" align = "center">
			<form:form method = "POST" commandName = "person">
				<form:hidden path = "id"/>
				<table border = "0">
					<tbody>
						<th colspan = "2"><h4>NAME</h4></th>
						<tr>
							<td>First Name</td>
							<td>
								<form:input path = "name.firstName"/> <br/>
								<form:errors path = "name.firstName" cssClass = "error"/>
							</td>
						</tr>
						<tr>
							<td>Middle Name</td>
							<td>
								<form:input path = "name.middleName"/> <br/>
								<form:errors path = "name.middleName" cssClass = "error"/>
							</td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td>
								<form:input path = "name.lastName"/> <br/>
								<form:errors path = "name.lastName" cssClass = "error"/>
							</td>
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
							<td>
								<form:input path = "address.streetNumber"/> <br/>
								<form:errors path = "address.streetNumber" cssClass = "error"/>
							</td>
						</tr>
						<tr>
							<td>Barangay</td>
							<td>
								<form:input path = "address.barangay"/> <br/>
								<form:errors path = "address.barangay" cssClass = "error"/>
							</td>
						</tr>
						<tr>
							<td>Municipality</td>
							<td>
								<form:input path = "address.municipality"/> <br/>
								<form:errors path = "address.municipality" cssClass = "error"/>
							</td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td>
								<form:input path = "address.zipcode"/> <br/>
								<form:errors path = "address.zipcode" cssClass = "error"/>
							</td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>BIRTHDATE</h4></th>
						<%-- <tr>
							<td>Birth Date</td>
							<td><input type = "date" name = "birthDateParam"/></td>
						</tr> --%>
						<tr>
							<td>Birth Date</td>
							<td>
								<form:input path = "birthDate"/> <br/>
								<form:errors path = "birthDate" cssClass = "error"/>
							</td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan = "2"><h4>DATE HIRED</h4></th>
						<tr>
							<td>Date Hired</td>
							<td>
								<form:input path = "dateHired"/> <br/>
								<form:errors path = "dateHired" cssClass = "error"/>
							</td>
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
									<c:set var = "checked" value = "${person.roles.contains(role) ? 'checked' : ''}"/>
									<input type = "checkbox" name = "rolesParam" value = "${role.id}" ${checked}/>
									${role.roleName} <br/>
								</c:forEach>
							</td>

						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<tbody id = "contactsSection">
							<th colspan = "2">
								<h4>CONTACTS</h4>
								<input type = "button" value = "Add Cellphone" id = "add-cellphone-button"/>
								<input type = "button" value = "Add Landline" id = "add-landline-button"/>
								<input type = "button" value = "Add Email" id = "add-email-button"/>
							</th>
							<c:forEach items = "${person.contacts}" var = "contact">
								<tr id = "link${contact.id}">
									<td>${contact.type}</td>
									<td>
										<input type = "text" name = "${contact.type}" value = "${contact.detail}"/>
										<input type = "button" id = "remove-button" value = "X" name = "link${contact.id}"/>
										<form:errors path = "contacts" cssClass = "error"/>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>
					</tbody>
				</table>
				<input type = "submit" value = "${submitLabel}"/>
			</form:form>
		</div>
	</body>

</html>
