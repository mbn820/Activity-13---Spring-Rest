package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.app.HtmlUtil;
import com.exist.ecc.core.service.PersonDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.model.*;
import java.sql.Date;
import java.util.*;

public class FindPersonServlet extends HttpServlet {
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

		String lastName = request.getParameter("lastName");

		List<PersonDto> persons = new PersonService().getPersonsByLastName(lastName);

		request.setAttribute( "personList", persons );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/FindPersonForm.jsp");

		rd.forward( request, response );
	}

}
