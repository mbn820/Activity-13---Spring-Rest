<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/resources/navbar.css"/>
	<head>

	<body>
		<div class="navbody">
			<ul>
				<li><a class="${currentPage.equals('/home') ? 'active' : ''}"              href="/home">HOME</a></li>
				<li><a class="${currentPage.equals('/addOrUpdatePerson') ? 'active' : ''}" href="/addOrUpdatePerson">Add Person</a></li>
				<li><a class="${currentPage.equals('/addUser') ? 'active' : ''}"           href="/addUser">Add User</a></li>
				<li><a class="${currentPage.equals('/managePersons') ? 'active' : ''}"     href="/managePersons">Manage Persons</a></li>
				<li><a class="${currentPage.equals('/manageRoles') ? 'active' : ''}"       href="/manageRoles">Manage Roles</a></li>
				<li><a class="${currentPage.equals('/manageUsers') ? 'active' : ''}"       href="/manageUsers">Manage Users</a></li>
				<li><a class="${currentPage.equals('/fileUploadForm') ? 'active' : ''}"    href="/fileUploadForm">Upload a File</a></li>
				<div style="float:right">
				<li><a class="userTab" href="#">CURRENT USER: ${pageContext.request.userPrincipal.name}</a></li>
				<li><a class="logout" href="j_spring_security_logout"> Logout</a></li>
				</div>
			<ul>
		</div>
	</body>
</html>
