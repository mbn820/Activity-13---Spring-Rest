<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Manage Roles</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                $("[name = update-button]").click(function() {
					var origName = $(this).attr("data-origName");
                    var newRoleName = window.prompt("Enter new role name", origName);
					var roleId = $(this).attr("id");

					if (newRoleName != null && newRoleName != "") {
						$.post("/UpdateRoles", {newRoleName : newRoleName, idToBeUpdated : roleId}, function() {
	                        location.reload();
	                    });
					}

                });

				$("[name = delete-button]").click(function() {
					var roleId = $(this).attr("id");
					var isAssignedToPerson = $(this).attr("data-contains-person");

					if (isAssignedToPerson == "true") {
						alert("Cannot Delete Role");
					} else {
						$.post("/deleteRole", {idToBeDeleted : roleId}, function() {
							location.reload();
						});
					}
                });

				$("[name = add-button]").click(function() {
					var newRole = $("#newRole").val();

					$.post("/AddRoles", {roleToBeAdded : newRole}, function() {
						location.reload();
					});
				});
            });
        </script>
	</head>

	<body>
		<h3>Manage Roles</h3>
		<hr/>

		<form:form method = "POST" commandName = "roleSamp">
			<form:input path = "roleName"/>
			<input type = "submit" value = "Add"/>
			<c:out value = "${param.addRoleErrorMessage}"/>
		</form:form>
		<table border = "1" width = "100%">
			<tr bgcolor = "#85ce58">
				<th>ID</th>
				<th>Role</th>
				<th>Persons</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<c:forEach items = "${existingRoles}" var = "role">
				<tr>
					<td>${role.id}</td>
					<td>${role.roleName}</td>
					<td>${role.persons}</td>
					<td>
						<a href = "google.com">Update</a>
					</td>
					<td>
						<a href = "/deleteRole?roleId=${role.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<t4><c:out value = "${param.deleteRoleErrorMessage}"/></t4>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
