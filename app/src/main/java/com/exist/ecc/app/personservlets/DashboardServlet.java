package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

public class DashboardServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch ( Exception e ) {
			response.getWriter().println("error " + e.toString());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastNameFilter = request.getParameter("lastNameFilter");
        String sortBy = request.getParameter("sortBy");
        String orderBy = request.getParameter("orderBy");

        if (lastNameFilter == null) { lastNameFilter = ""; }
        if (sortBy == null) { sortBy = "id"; }
        if (orderBy == null) { orderBy = "asc"; }

		List<PersonDto> personList = new PersonService().getPersonsByLastName(lastNameFilter);

		request.setAttribute( "personList", personList );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/person/Dashboard.jsp");

		rd.forward( request, response );
	}
}
