<%@ page import="java.util.List" %>
<%@ page import="com.exist.ecc.core.model.Role" %>
<%@ page import="com.exist.ecc.core.service.RoleService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Add Person</title>
	</head>

	<body>
		<h3>Add Person</h3>

		<hr/>

		<form action = "AddPerson" method = "GET">
			<h4>ID</h4>
			PERSON ID                                    <br/>
		    <input type = "text" name = "id" readonly>   <br/>

			<hr/>

			<h4>NAME</h4>
			First Name                                   <br/>
			<input type = "text" name = "firstName">     <br/>
			Middle Name                                  <br/>
			<input type = "text" name = "middleName">    <br/>
			Last Name                                    <br/>
			<input type = "text" name = "lastName">      <br/>
			Suffix                                       <br/>
			<input type = "text" name = "suffix">        <br/>
			Title                                        <br/>
			<input type = "text" name = "title">         <br/>

			<hr/>

			<h4>ADDRESS</h4>
			Street Number                                <br/>
			<input type = "text" name = "streetNumber">  <br/>
			Barangay                                     <br/>
			<input type = "text" name = "barangay">      <br/>
			Municipality                                 <br/>
			<input type = "text" name = "municipality">  <br/>
			Zipcode                                      <br/>
			<input type = "text" name = "zipcode">       <br/>

			<hr/>

			<h4>BIRTH DATE</h4>
			Date <br/>
			<input type = "date" name = "birthDate"/>

			<hr/>

			<h4>DATE HIRED</h4>
			Date <br/>
			<input type = "date" name = "dateHired"/>

			<hr/>

			<h4>EMPLOYMENT</h4>
			Employed? <br/>
			<input type = "radio" name = "currentlyEmployed" value = "True"/> Yes <br/>
			<input type = "radio" name = "currentlyEmployed" value = "False"/> No <br/>

			<hr/>

			<h4>GWA</h4>
			Enter gwa (1.0 - 5.0) <br/>
			<input type = "text" name = "gwa"/> <br/>

			<hr/>

			<h4>ROLES</h4>
			Select Roles <br/>
			<select name = "roles" multiple>
				<%
					List<Role> roles = new RoleService().getAllRoles();
					for(Role role : roles) {
						out.println( "<option value = '" + role.getId() + "'>" + role.getRoleName() + "</option>" );
					}
				%>
 			</select> <br/>
			<i>(Ctrl + click to choose multiple roles)</i> <br/>

			<hr/>

			<h4>CONTACTS</h4>
			Cellphone Number <br/>
			<input type = "text" name = "contacts_phone"> <br/>
			Landline <br/>
			<input type = "text" name = "contacts_landline"> <br/>
			Email Address <br/>
			<input type = "text" name = "contacts_email"> <br/>


			<!-- BUTTON -->
			<hr/>
			<br/><br/> <input type = "submit" value = "SUBMIT"/>
		</form>
	</body>

</html>
