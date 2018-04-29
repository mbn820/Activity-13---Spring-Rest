package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.model.dto.*;
import java.util.List;

public class ViewAllPersonsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			response.getWriter().println(e.toString());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (Exception e) {

		}
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderBy = request.getParameter("orderBy");

		if(orderBy == null) {
			orderBy = "id";
		}

		List<PersonDto> allPersons = new PersonService().getAllPerson(orderBy);

		request.setAttribute( "personList", allPersons );
		request.setAttribute( "orderBy", orderBy.toUpperCase() );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/person/Dashboard.jsp");

		rd.forward( request, response );
	}

}
