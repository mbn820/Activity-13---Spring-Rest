<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
						$.post("/DeleteRoles", {idToBeDeleted : roleId}, function() {
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

	    <input type = "text" id = "newRole" placeholder = "Enter New Role"/>
		<input type = "button" name = "add-button" value = "Add"/>
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
						<input type = "button" name = "update-button" id = "${role.id}" value = "Update Role" data-origName = "${role.roleName}"/>
					</td>
					<td>
						<c:set var = "containsPerson" value = "${not empty role.persons ? 'true' : 'false'}"/>
						<input type = "button" name = "delete-button" id = "${role.id}" value = "Delete Role" data-contains-person = "${containsPerson}"/>
					</td>
				</tr>
			</c:forEach>
		</table>

		<hr/><a href = "/index.jsp">HOME</a>
	</body>
</html>
