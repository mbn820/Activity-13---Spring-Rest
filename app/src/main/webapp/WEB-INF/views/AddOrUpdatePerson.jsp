<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Add Person</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				var incrementor=0;

				function createElement(contactType) {
					var autoId="link" + incrementor;
					var elemw =
					"<tr id=" + autoId + ">" +
						"<td>" +
							contactType +
						"</td>" +
						"<td>" +
							"<input type='text' name='" + contactType + "'/>" +
							"<input type='button' id='remove-button' value='X' name=" + autoId + ">" +
						"</td>" +
					"</tr>";

					incrementor++;

					return elemw;
				}

				$("#add-cellphone-button").click(function() {
					var type=createElement("Cellphone");
					$("#contactsSection").append( type );
				});

				$("#add-landline-button").click(function() {
					var type=createElement("Landline");
					$("#contactsSection").append( type );
				});

				$("#add-email-button").click(function() {
					var type=createElement("Email");
					$("#contactsSection").append( type );
				});

				$(document).on('click', '#remove-button', function() {
					var elemToBeRemoved=$(this).attr("name");
					$("#" + elemToBeRemoved).remove();
				});

				$("#language").change(function () {
			        var selectedOption=$(this).val();
			        if (selectedOption != ''){
			            window.location.replace('?lang=' + selectedOption);
			        }
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

			th {
				align-items: center;
			}

			table, th {
				border: 1px solid black;
				border-collapse: collapse;
			}
		</style>
	</head>

	<body>
		<h3 align="center">${requestType}</h3>
		<hr/>

		<div class="tableForm" align="center">
			<spring:message code="label.language"/>
			<select id="language">
				<option value="en" ${param.lang == 'en' ? 'selected' : ''}>English</option>
				<option value="tg" ${param.lang == 'tg' ? 'selected' : ''}>Tagalog</option>
			</select>
			<form:form method="POST" commandName="person">
				<form:hidden path="id"/>

				<table border="0">
					<tbody>

						<%-- =============================================================================== --%>

						<th colspan="2"><h4><spring:message code="header.label.name"/></h4></th>
						<tr>
							<td><spring:message code="label.name.firstName"/></td>
							<td><form:input path="name.firstName"/> <br>
								<form:errors path="name.firstName" cssClass="error"/></td>
						</tr>
						<tr>
							<td><spring:message code="label.name.middleName"/></td>
							<td><form:input path="name.middleName"/> <br>
								<form:errors path="name.middleName" cssClass="error"/></td>
						</tr>
						<tr>
							<td><spring:message code="label.name.lastName"/></td>
							<td><form:input path="name.lastName"/> <br>
								<form:errors path="name.lastName" cssClass="error"/></td>
						</tr>
						<tr>
							<td><spring:message code="label.name.suffix"/></td>
							<td><form:input path="name.suffix"/></td>
						</tr>
						<tr>
							<td><spring:message code="label.name.title"/></td>
							<td><form:input path="name.title"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan="2"><h4><spring:message code="header.label.address"/></h4></th>
						<tr>
							<td><spring:message code="label.address.streetNumber"/></td>
							<td><form:input path="address.streetNumber"/> <br>
								<form:errors path="address.streetNumber" cssClass="error"/></td>
						</tr>
						<tr>
							<td><spring:message code="label.address.barangay"/></td>
							<td><form:input path="address.barangay"/> <br>
								<form:errors path="address.barangay" cssClass="error"/></td>
						</tr>
						<tr>
							<td><spring:message code="label.address.municipality"/></td>
							<td><form:input path="address.municipality"/> <br>
								<form:errors path="address.municipality" cssClass="error"/></td>
						</tr>
						<tr>
							<td><spring:message code="label.address.zipcode"/></td>
							<td><form:input path="address.zipcode"/> <br>
								<form:errors path="address.zipcode" cssClass="error"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan="2"><h4><spring:message code="header.label.birthDate"/></h4></th>
						<tr>
							<td><spring:message code="label.birthDate"/></td>
							<td><form:input path="birthDate"/> <br>
								<form:errors path="birthDate" cssClass="error"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan="2"><h4><spring:message code="header.label.dateHired"/></h4></th>
						<tr>
							<td><spring:message code="label.dateHired"/></td>
							<td><form:input path="dateHired"/> <br>
								<form:errors path="dateHired" cssClass="error"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan="2"><h4><spring:message code="header.label.employment"/></h4></th>
						<tr>
							<td><spring:message code="label.employment"/></td>
							<td><form:radiobutton path="currentlyEmployed" value="true"/>YES
								<form:radiobutton path="currentlyEmployed" value="false"/>NO</td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan="2"><h4><spring:message code="header.label.gwa"/></h4></th>
						<tr>
							<td><spring:message code="label.gwa"/></td>
							<td><form:input path="gwa"/> <br>
								<form:errors path="gwa" cssClass="error"/></td>
						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<th colspan="2"><h4><spring:message code="header.label.roles"/></h4></th>
						<tr>
							<td><spring:message code="label.roles"/></td>

							<td>
								<c:forEach items="${existingRoles}" var="role">
									<c:set var="checked" value="${person.roles.contains(role) ? 'checked' : ''}"/>
									<input type="checkbox" name="rolesParam" value="${role.id}" ${checked}/>
									${role.roleName} <br>
								</c:forEach>
							</td>

						</tr>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>

						<%-- =============================================================================== --%>

						<tbody id="contactsSection">
							<th colspan="2">
								<h4><spring:message code="header.label.contacts"/></h4>
								<input type="button" value="Add Cellphone" id="add-cellphone-button"/>
								<input type="button" value="Add Landline" id="add-landline-button"/>
								<input type="button" value="Add Email" id="add-email-button"/> <br>
								<form:errors path="contacts" cssClass="error"/>
							</th>
							<c:forEach items="${person.contacts}" var="contact">
								<tr id="link${contact.id}">
									<td>${contact.type}</td>
									<td>
										<input type="text" name="${contact.type}" value="${contact.detail}"/>
										<input type="button" id="remove-button" value="X" name="link${contact.id}"/>
										<form:errors path="contacts" cssClass="error"/>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tr>
							<td>&nbsp</td>
							<td>&nbsp</td>
						</tr>
						<th colspan="2"><input type="submit" value="${submitLabel}"/></th>
					</tbody>
				</table>
			</form:form>
			<hr/>
			<a href = "/fileUpload.htm">Upload a File</a> <br>
            <a href="/manageRoles.htm">Manage Roles</a> <br>
            <a href="/index.jsp">HOME</a>
		</div>
	</body>

</html>
