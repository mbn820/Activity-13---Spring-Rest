package com.exist.ecc.app;

import javax.servlet.ServletException;
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

		out.println("<html>");
		out.println("<body>");

		out.println("<h3>Find Person</h3>");
		out.println("<hr/>");

		out.println("Enter person last name: <br/>");
		out.println("<form action = 'FindPerson' method = 'GET'>");
		out.println("<input type = 'text' name = 'lastName'/> <br/>");
		out.println("<input type = 'submit' value = 'Find'/> <br/>");
		out.println("</form>");
		out.println("<hr/>");

		if(lastName != null) {
			out.println("<h3>Results</h3><br/>");
			List<PersonDto> persons = new PersonService().getPersonsByLastName(lastName);
			new HtmlUtil().printPersonsTable(persons, out);
		}

		out.println("</html>");
		out.println("</body>");
	}

}
