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
                $("[name=update-role-button]").click(function() {
					var origName=$(this).attr("data-origName");
                    var newRoleName=window.prompt("Enter new role name", origName);
					var roleId=$(this).attr("data-roleId");

					if (newRoleName != null && newRoleName != "") {
						$.post("/updateRole.htm", {newRoleName : newRoleName, idToBeUpdated : roleId}, function() {
	                        location.reload();
	                    });
					}

                });

				$("[name=delete-role-button]").click(function() {
					var roleId=$(this).attr("data-roleId");

					$.post("/deleteRole.htm", {idToBeDeleted : roleId}, function() {
						location.reload();
					}).error(function() {
						alert("Cannot Delete Role!!");
					});

                });
            });
        </script>
		<style>
			.error {
				color: #ff0000;
				font-size: 15px;
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
		<h3>Manage Roles</h3>
		<hr/>

		<form:form method="POST" commandName="role">
			<form:input path="roleName"/>
			<input type="submit" value="Add"/>
			<form:errors path="roleName" cssClass="error"/>
		</form:form>
		<table border="1" width="100%">
			<tr bgcolor="#85ce58">
				<th>ID</th>
				<th>Role</th>
				<th>Persons</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${existingRoles}" var="role">
				<tr>
					<td>${role.id}</td>
					<td>${role.roleName}</td>
					<td>${role.persons}</td>
					<td align="center">
						<input type="button" name="update-role-button" value="Update" data-origName="${role.roleName}" data-roleId="${role.id}"/>
					</td>
					<td align="center">
						<!-- <form action="/deleteRole.htm" method="GET">
							<input type="hidden" name="idToBeDeleted" value="${role.id}"/>
							<input type="submit" value="Delete"/>
						</form> -->
						<input type="button" name="delete-role-button" value="Delete" data-roleId="${role.id}"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		<t4><c:out value="${param.deleteRoleErrorMessage}"/></t4>

		<hr/>
		<a href="/managePersons.htm">Manage Persons</a> <br>
		<a href="/index.jsp">HOME</a> <br>

	</body>
</html>
