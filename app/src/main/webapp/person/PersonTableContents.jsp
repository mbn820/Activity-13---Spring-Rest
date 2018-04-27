<%@ page isELIgnored="false" %>
<td>
	<a href = "/ViewFullPersonDetails?personId=${person.id}">${person.id}</a>
</td>
<td>${person.name.firstName}</td>
<td>${person.name.middleName}</td>
<td>${person.name.lastName}</td>
<td>${person.dateHired}</td>
<td>${person.gwa}</td>
<td>${person.roles}</td>
<td>
	<a href = "UpdatePerson?personId=${person.id}">Update Person</a>
</td>
