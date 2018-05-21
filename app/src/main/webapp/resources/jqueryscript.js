$(document).ready(function() {

	function createElement(contactType) {
		var elemw =
		"<tr>" +
			"<td>" + contactType + "</td>" +
			"<td>" + "<input type='text' name='" + contactType + "'/>" +
					 "<input type='button' id='remove-button' value='X'>" + "</td>" +
		"</tr>";

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
		$(this).closest("tr").remove();
	});

	$("#language").change(function () {
		var selectedOption=$(this).val();
		if (selectedOption != '') {
			window.location.replace('?lang=' + selectedOption);
		}
	});

	$("[name=update-role-button]").click(function() {
		var origName=$(this).attr("data-origName");
		var newRoleName=window.prompt("Enter new role name", origName);
		var roleId=$(this).attr("data-roleId");

		if (newRoleName != origName && newRoleName != null && newRoleName != "") {
			$.post("/role/updateRole", {newRoleName : newRoleName, idToBeUpdated : roleId}, function() {
				location.reload();
			}).fail(function() {
				alert("Role Already Exists!");
			});
		}

	});

	$("[name=delete-role-button]").click(function() {
		var roleId=$(this).attr("data-roleId");

		$.post("/role/deleteRole", {idToBeDeleted : roleId}, function() {
			location.reload();
		}).fail(function() {
			alert("Cannot Delete Role!!");
		});

	});

	$(".userTab").click(function() {
		var proceed = confirm("Update Password?");
		if (proceed) {
			var newPassword = window.prompt("Enter new Password:");

			if (newPassword.length < 6) {
				alert("Password too short!");
				return;
			}
			
			if (newPassword != null && newPassword != "") {
				$.post("/user/updatePassword", {newPassword : newPassword}, function() {
					alert("Password Updated!");
				});
			}
		}
	});

});
