package com.exist.ecc.app;

import java.io.PrintWriter;
import java.util.List;
import java.util.Arrays;
import com.exist.ecc.core.service.PersonDto;
import com.exist.ecc.core.model.Role;

public class HtmlUtil {

	public void printPersonsTable(List<PersonDto> persons, PrintWriter out) {
		if(persons.isEmpty()) {
			out.println("<t3>No Record Found</t3>");
			return;
		}
		out.println("<table border = '1'>");
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>First Name</th>");
		out.println("<th>Middle Name</th>");
		out.println("<th>Last Name</th>");
		out.println("<th>Street Number</th>");
		out.println("<th>Barangay</th>");
		out.println("<th>Municipality</th>");
		out.println("<th>Zipcode</th>");
		out.println("<th>Birth Date</th>");
		out.println("<th>Date Hired</th>");
		out.println("<th>Currently Employed</th>");
		out.println("<th>GWA</th>");
		out.println("<th>Roles</th>");
		out.println("</tr>");

		for(PersonDto person : persons) {
			out.println("<tr>");
			out.printf( "<td>%s</td>\n", person.getId() );
			out.printf( "<td>%s</td>\n", person.getName().getFirstName() );
			out.printf( "<td>%s</td>\n", person.getName().getMiddleName() );
			out.printf( "<td>%s</td>\n", person.getName().getLastName() );
			out.printf( "<td>%s</td>\n", person.getAddress().getStreetNumber() );
			out.printf( "<td>%s</td>\n", person.getAddress().getBarangay() );
			out.printf( "<td>%s</td>\n", person.getAddress().getMunicipality() );
			out.printf( "<td>%s</td>\n", person.getAddress().getZipcode() );
			out.printf( "<td>%s</td>\n", person.getBirthDate() );
			out.printf( "<td>%s</td>\n", person.getDateHired() );
			out.printf( "<td>%s</td>\n", person.getCurrentlyEmployed() );
			out.printf( "<td>%s</td>\n", person.getGwa() );
			out.printf( "<td>%s</td>\n", person.getRoles() );
			out.println("</tr>");
		}
		out.println("</table>");
	}

	public void printPersonsTableWithDeleteColumn(List<PersonDto> persons, PrintWriter out, String action) {
		if(persons.isEmpty()) {
			out.println("<t3>No Record Found</t3>");
			return;
		}
		out.println("<table border = '1'>");
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>First Name</th>");
		out.println("<th>Middle Name</th>");
		out.println("<th>Last Name</th>");
		out.println("<th>Street Number</th>");
		out.println("<th>Barangay</th>");
		out.println("<th>Municipality</th>");
		out.println("<th>Zipcode</th>");
		out.println("<th>Birth Date</th>");
		out.println("<th>Date Hired</th>");
		out.println("<th>Currently Employed</th>");
		out.println("<th>GWA</th>");
		out.println("<th>Roles</th>");
		out.println("<th>Delete</th>");
		out.println("</tr>");

		out.printf("<form action = '%s' method = 'GET'>\n", action);
		for(PersonDto person : persons) {
			out.println("<tr>");
			out.printf( "<td>%s</td>\n", person.getId() );
			out.printf( "<td>%s</td>\n", person.getName().getFirstName() );
			out.printf( "<td>%s</td>\n", person.getName().getMiddleName() );
			out.printf( "<td>%s</td>\n", person.getName().getLastName() );
			out.printf( "<td>%s</td>\n", person.getAddress().getStreetNumber() );
			out.printf( "<td>%s</td>\n", person.getAddress().getBarangay() );
			out.printf( "<td>%s</td>\n", person.getAddress().getMunicipality() );
			out.printf( "<td>%s</td>\n", person.getAddress().getZipcode() );
			out.printf( "<td>%s</td>\n", person.getBirthDate() );
			out.printf( "<td>%s</td>\n", person.getDateHired() );
			out.printf( "<td>%s</td>\n", person.getCurrentlyEmployed() );
			out.printf( "<td>%s</td>\n", person.getGwa() );
			out.printf( "<td>%s</td>\n", person.getRoles() );
			out.printf( "<td><input type = 'checkbox' value = '%s' name = 'idToBeDeleted'></td>\n", person.getId());
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<input type = 'submit' value = 'DELETE'>");
		out.printf("</form>");
	}

	public void printRolesTable(List<Role> roles, PrintWriter out) {
		if ( roles.isEmpty() ) {
			out.println( "<h3>No record found</h3>" );
			return;
		}

		out.println( "<table border = '1'>" );
		out.println( "<tr>" );
		out.println( "<th>ID</th>" );
		out.println( "<th>Role</th>" );
		out.println( "<th>Person</th>" );
		out.println( "</tr>");

		for (Role role : roles) {
			out.println( "<tr>" );
			out.printf( "<td>%s</td>\n", role.getId() );
			out.printf( "<td>%s</td>\n", role.getRoleName() );
			out.printf( "<td>%s</td>\n", role.getPersons() );
			out.println( "</tr>");
		}
		out.println( "</table>" );
	}

	public void printRolesTableWithDeleteColumn(List<Role> roles, PrintWriter out, String action) {
		if ( roles.isEmpty() ) {
			out.println( "<h3>No record found</h3>" );
			return;
		}

		out.println( "<table border = '1'>" );
		out.println( "<tr>" );
		out.println( "<th>ID</th>" );
		out.println( "<th>Role</th>" );
		out.println( "<th>Person</th>" );
		out.println( "<th>Delete</th>" );
		out.println( "</tr>");

		out.printf("<form action = '%s' method = 'GET'>\n", action);
		for (Role role : roles) {
			out.println( "<tr>" );
			out.printf( "<td>%s</td>\n", role.getId() );
			out.printf( "<td>%s</td>\n", role.getRoleName() );
			out.printf( "<td>%s</td>\n", role.getPersons() );
			out.printf( "<td><input type = 'checkbox' value = '%s' name = 'idToBeDeleted'></td>\n", role.getId());
			out.println( "</tr>");
		}
		out.println( "</table>" );
		out.println("<input type = 'submit' value = 'DELETE'>");
		out.printf("</form>");

	}

	public void printRolesTable(Role role, PrintWriter out) {
		printRolesTable( Arrays.asList(role), out );
	}
}
