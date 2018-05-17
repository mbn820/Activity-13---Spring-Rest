
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/resources/navbar.css"/>
	<head>

	<body>
		<div class="navbody">
			<ul>
				<li><a class="${currentPage.equals('/home.htm') ? 'active' : ''}"              href="/home.htm">HOME</a></li>
				<li><a class="${currentPage.equals('/addOrUpdatePerson.htm') ? 'active' : ''}" href="/addOrUpdatePerson.htm">Add Person</a></li>
				<li><a class="${currentPage.equals('/addUser.htm') ? 'active' : ''}"           href="/addUser.htm">Add User</a></li>
				<li><a class="${currentPage.equals('/managePersons.htm') ? 'active' : ''}"     href="/managePersons.htm">Manage Persons</a></li>
				<li><a class="${currentPage.equals('/manageRoles.htm') ? 'active' : ''}"       href="/manageRoles.htm">Manage Roles</a></li>
				<li><a class="${currentPage.equals('/manageUsers.htm') ? 'active' : ''}"       href="/manageUsers.htm">Manage Users</a></li>
				<li><a class="${currentPage.equals('/fileUploadForm.htm') ? 'active' : ''}"    href="/fileUploadForm.htm">Upload a File</a></li>
				<div style="float:right">
				<li><a class="userTab" href="#">CURRENT USER: ${pageContext.request.userPrincipal.name}</a></li>
				<li><a class="logout" href="j_spring_security_logout"> Logout</a></li>
				</div>
			<ul>
		</div>
	</body>
</html>
