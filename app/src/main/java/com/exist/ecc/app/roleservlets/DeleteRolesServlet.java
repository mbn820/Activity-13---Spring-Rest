package com.exist.ecc.app.roleservlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import com.exist.ecc.app.HtmlUtil;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.*;
import java.sql.Date;
import java.util.*;

public class DeleteRolesServlet extends HttpServlet {
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

		String[] ids = request.getParameterValues( "idToBeDeleted" );

		if ( ids != null ) {
			for (String id : ids) {
				try {
					new RoleService().deleteRole( Integer.parseInt(id) );
				} catch (Exception e) {
					response.sendRedirect("/DeleteRoles");
				}
			}

		}

		List<Role> roles = new RoleService().getAllRoles();

		out.println("<html>");
		out.println("<body>");
		out.println( "<h3>Delete Roles</h3>" );
		out.println( "<hr/>" );
		new HtmlUtil().printRolesTableWithDeleteColumn(roles, out, "DeleteRoles");
		out.println("</body>");
		out.println("</html>");

	}

}
