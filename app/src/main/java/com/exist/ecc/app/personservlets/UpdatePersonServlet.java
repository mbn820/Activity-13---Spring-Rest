package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.*;
import com.exist.ecc.app.PersonBuilder;
import java.sql.Date;
import java.util.*;

public class UpdatePersonServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("personId");

		PersonDto personToBeUpdated = new PersonService().getPerson( Integer.parseInt(id) );
		List<RoleDto> existingRoles = new RoleService().getAllRoles();

		request.setAttribute( "person", personToBeUpdated );
		request.setAttribute( "existingRoles", existingRoles );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/person/UpdatePersonForm.jsp");

		rd.forward( request, response );
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonDto personToBeUpdated = new PersonDto();
		try {
			personToBeUpdated = new PersonBuilder(request).build();
		} catch(Exception e) {
			response.getWriter().println("<html><body><h3>Error: Invalid Input<h3></body></html>");
			return;
		}

		int idOfPerson = Integer.parseInt( request.getParameter("id") );

		personToBeUpdated.setId(idOfPerson);

		new PersonService().updatePerson(personToBeUpdated);

		response.sendRedirect("/ManagePersons");
	}
}
