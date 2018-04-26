<%@ page isELIgnored="false" %>
<td>
	<a href = "/ViewFullPersonDetails?personId=${person.id}">${person.id}</a>
</td>
<td>${person.name.firstName}</td>
<td>${person.name.middleName}</td>
<td>${person.name.lastName}</td>
<td>${person.address.streetNumber}</td>
<td>${person.address.barangay}</td>
<td>${person.address.municipality}</td>
<td>${person.address.zipcode}</td>
<td>${person.birthDate}</td>
<td>${person.dateHired}</td>
<td>${person.currentlyEmployed}</td>
<td>${person.gwa}</td>
<td>${person.roles}</td>
