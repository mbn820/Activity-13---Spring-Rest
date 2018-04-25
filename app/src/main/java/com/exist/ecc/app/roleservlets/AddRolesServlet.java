package com.exist.ecc.app.roleservlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.*;
import java.sql.Date;
import java.util.*;

public class AddRolesServlet extends HttpServlet {
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

		String roleInput = request.getParameter("role");

		out.println("<html>");
		out.println("<body>");
		out.println( "<h3>Add new Roles</h3>" );
		out.println( "<hr/>" );

		out.println( "<form action = 'AddRoles' method = 'GET'>");
		out.println( "Enter new Role <br/>" );
		out.println( "<input type = 'text' name = 'role'> <br/>");
		out.println( "<input type = 'submit' value = 'Add'> <br/>");
		out.println( "</form>" );
		out.println( "<hr/>" );

		if (roleInput != null) {
			try {
				Role role = new Role( roleInput );
				new RoleService().addRole( role );
				out.printf( "Role : %s has been added to the database!\n", role.getRoleName() );
			} catch ( Exception e ) {
				out.printf("Error: Role Already Exists!\n" );
			}

		}

		out.println("</body>");
		out.println("</html>");

	}

}
