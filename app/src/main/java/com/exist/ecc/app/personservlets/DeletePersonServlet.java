package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.service.PersonDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.model.*;
import java.sql.Date;
import java.util.*;

public class DeletePersonServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch ( Exception e ) {
			response.getWriter().println(e.toString());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("idToBeDeleted");

		if(ids != null) {
			for(String id : ids) {
				try {
					new PersonService().deletePerson( Integer.parseInt(id) );
				} catch(Exception e) {
					response.sendRedirect("/DeletePerson");
				}
			}
		}

		List<PersonDto> allPersons = new PersonService().getAllPerson("id");

		request.setAttribute( "personList", allPersons );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/person/DeletePersonForm.jsp");

		rd.forward( request, response );
	}

}
