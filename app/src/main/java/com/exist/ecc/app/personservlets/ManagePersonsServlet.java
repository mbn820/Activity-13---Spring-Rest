package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.PersonServiceImpl;
import com.exist.ecc.core.service.RoleServiceImpl;
import com.exist.ecc.core.model.dto.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ManagePersonsServlet extends HttpServlet {
	private ApplicationContext context = new ClassPathXmlApplicationContext( new String[] {"service-beans.xml", "servlet.xml"} );
	private PersonService personService = (PersonService) context.getBean("personService");

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
        String orderBy = request.getParameter("orderBy");
        String orderType = request.getParameter("orderType");

        if (lastNameFilter == null) { lastNameFilter = ""; }
        if (orderBy == null) { orderBy = "id"; }
        if (orderType == null) { orderType = "asc"; }

		List<PersonDto> personList = personService.getPersonsByLastName(lastNameFilter, orderBy, orderType);

		request.setAttribute( "personList", personList );

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/person/ManagePersons.jsp");

		rd.forward( request, response );
	}
}
