package com.exist.ecc.app.roleservlets;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
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
		String roleInput = request.getParameter("role");

		if (roleInput != null) {
			try {
				Role role = new Role( roleInput );
				new RoleService().addRole( role );
			} catch (Exception e) {
				request.setAttribute( "roleExistsException", e );
			}
		}

		List<Role> existingRoles = new RoleService().getAllRoles();

		request.setAttribute( "existingRoles", existingRoles );

		RequestDispatcher rd = getServletContext().getRequestDispatcher( "/role/AddRoleForm.jsp" );

		rd.forward( request, response );
	}

}
