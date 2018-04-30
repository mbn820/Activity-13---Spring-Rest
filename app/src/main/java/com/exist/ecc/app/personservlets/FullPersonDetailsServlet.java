package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.service.PersonService;
import java.util.List;
import java.util.Arrays;

public class FullPersonDetailsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			response.getWriter().println( e.toString() );
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (Exception e) {

		}
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter( "personId" );

		PersonDto person = new PersonService().getPerson( Integer.parseInt(idParam) );

		request.setAttribute( "person", person );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/person/FullPersonDetailsForm.jsp");

		rd.forward( request, response );
	}

}
