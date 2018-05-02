package com.exist.ecc.app.roleservlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.service.RoleServiceImpl;
import com.exist.ecc.core.model.dto.RoleDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ManageRolesServlet extends HttpServlet {
	private ApplicationContext context = new ClassPathXmlApplicationContext( new String[] {"service-beans.xml", "servlet.xml", "dao-beans.xml"} );
	private RoleService roleService = (RoleService) context.getBean("roleService");

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch ( Exception e ) {
			response.getWriter().println( e.toString());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoleDto> roles = roleService.getAllRoles();

		request.setAttribute( "existingRoles", roles );

		RequestDispatcher rd = getServletContext().getRequestDispatcher( "/role/ManageRoles.jsp" );

		rd.forward( request, response );
	}

}
