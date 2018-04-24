package com.exist.ecc.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.service.PersonDto;
import com.exist.ecc.core.service.PersonService;
import java.util.List;

public class ViewAllPersonsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (Exception e) {

		}
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String orderBy = request.getParameter("orderBy");

		if(orderBy == null) {
			orderBy = "id";
		}

		List<PersonDto> allPersons = new PersonService().getAllPerson(orderBy);

		out.println("<html>");
		out.println("<body>");
		out.println("<h3>List of all Persons:<h3>");
		out.println("<hr/>");
		//////////////////////////////////////
		out.printf("<h4>SORTED BY: %s</h4><br/>\n", orderBy.toUpperCase());
		new HtmlUtil().printPersonsTable(allPersons, out);

		//////////////////////////////////////
		out.println("<br/><br/>Sort by: ");
		out.println("<form action = 'ViewAllPersons' method = 'GET'>");
		out.println("<input type = 'radio' name = 'orderBy' value = 'name.lastName'/>Last Name<br/>");
		out.println("<input type = 'radio' name = 'orderBy' value = 'dateHired'/>Date Hired<br/>");
		out.println("<input type = 'radio' name = 'orderBy' value = 'gwa'/>GWA<br/>");
		out.println("<input type = 'submit' value = 'Sort'/><br/>");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");
	}

}
