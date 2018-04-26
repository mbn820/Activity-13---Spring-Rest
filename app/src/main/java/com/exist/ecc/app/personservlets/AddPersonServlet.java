package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.core.service.PersonDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

public class AddPersonServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch ( Exception e ) {
			response.getWriter().println(e.toString());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createPersonDtoObject(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Role> existingRoles = new RoleService().getAllRoles();

		request.setAttribute( "existingRoles", existingRoles );

		RequestDispatcher rd = getServletContext().getRequestDispatcher( "/person/AddPersonForm.jsp" );

		rd.forward( request, response );
	}

	public void createPersonDtoObject(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

		String contactPhone = request.getParameter("contacts_phone");
		Contact phone = new Contact("phone", contactPhone);
		String contactLandline = request.getParameter("contacts_landline");
		Contact landline = new Contact("landline", contactLandline);
		String contactEmail = request.getParameter("contacts_email");
		Contact email = new Contact("email", contactEmail);
		Set<Contact> contacts = new HashSet<Contact>( Arrays.asList(phone, landline, email) );


		PersonDto person = new PersonDto(name, address, birthDate, dateHired,
										 currentlyEmployed, gwa, new HashSet<Role>(), contacts);

		if ( roleIds != null ) {
			for(String roleId : roleIds) {
				int id = Integer.parseInt(roleId);
				person.addRole( new RoleService().getRole(id) );
			}
		}

		new PersonService().addPerson(person);

		out.println("<html><body><h3>Person Has Been Added to the Database!<h3></body></html>");
	}
}
