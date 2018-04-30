package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.*;
import com.exist.ecc.app.PersonBuilder;
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
		PersonDto personToBeAdded = new PersonDto();
		try {
			personToBeAdded = new PersonBuilder(request).build();
		} catch(Exception e) {
			response.getWriter().println("<html><body><h3>Error: Invalid Input<h3></body></html>");
			return;
		}

		new PersonService().addPerson(personToBeAdded);

		response.sendRedirect("/ManagePersons");
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoleDto> existingRoles = new RoleService().getAllRoles();

		request.setAttribute( "existingRoles", existingRoles );

		RequestDispatcher rd = getServletContext().getRequestDispatcher( "/person/AddPersonForm.jsp" );

		rd.forward( request, response );
	}
}
