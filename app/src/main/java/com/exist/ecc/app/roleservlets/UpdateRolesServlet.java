package com.exist.ecc.app.roleservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.service.*;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.*;
import java.sql.Date;
import java.util.*;

public class UpdateRolesServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch ( Exception e ) {
			response.getWriter().println( e.toString() );
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println( "Test" );

		List<PersonDto> allPersons = new PersonService().getAllPerson("id");

		request.setAttribute( "personList", allPersons );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewAllPersonForm.jsp");

		rd.forward( request, response );

	}

}
