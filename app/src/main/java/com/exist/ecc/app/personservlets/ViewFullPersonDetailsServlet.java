package com.exist.ecc.app.personservlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.app.HtmlUtil;
import com.exist.ecc.core.service.PersonDto;
import com.exist.ecc.core.service.PersonService;
import java.util.List;

public class ViewFullPersonDetailsServlet extends HttpServlet {

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

		String idParam = request.getParameter( "roleId" );

		PersonDto person = new PersonService().getPerson( Integer.parseInt(idParam) );

		out.println("<html>");
		out.println("<body>");

		out.println("<h3>Full Person Details</h3>");
		out.println("<hr/>");

		new HtmlUtil().printPersonsTable(person, out);

		out.println("<hr/>");

		out.println("<h4>Contacts</h4>");
		new HtmlUtil().printPersonContacts(person, out);

		out.println("</html>");
		out.println("</body>");
	}

}
