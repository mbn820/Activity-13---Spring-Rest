package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.service.PersonDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.app.HtmlTemplate;
import com.exist.ecc.core.model.*;
import java.sql.Date;
import java.util.*;

public class AddPersonServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch ( Exception e ) {

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String suffix = request.getParameter("suffix");
		String title = request.getParameter("title");
		Name name = new Name(firstName, middleName, lastName, suffix, title);

		int streetNumber = Integer.parseInt( request.getParameter("streetNumber") );
		String barangay = request.getParameter("barangay");
		String municipality = request.getParameter("municipality");
		String zipcode = request.getParameter("zipcode");
		Address address = new Address(streetNumber, barangay, municipality, zipcode);

		String bday = request.getParameter("birthDate");
		Date birthDate = Date.valueOf(bday);

		String dHired = request.getParameter("dateHired");
		Date dateHired = Date.valueOf(dHired);

		boolean currentlyEmployed = (request.getParameter("currentlyEmployed").equals("True")) ? true : false;
		double gwa = Double.parseDouble( request.getParameter("gwa") );

		String[] roleIds = request.getParameterValues("roles");

		PersonDto person = new PersonDto(name, address, birthDate, dateHired,
										 currentlyEmployed, gwa, new HashSet<Role>(), new HashSet<Contact>());

		for(String roleId : roleIds) {
			int id = Integer.parseInt(roleId);
			person.addRole( new RoleService().getRole(id) );
		}

		new PersonService().addPerson(person);

		new HtmlTemplate().setTitle("Add Person")
		                  .setBody("<h4>Person has been added!</h4>")
						  .print(out);

	}
}
