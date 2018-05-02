package com.exist.ecc.app.personservlets;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.core.service.PersonServiceImpl;

public class DeletePersonServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			new PersonServiceImpl().deletePerson( Integer.parseInt(request.getParameter("id")) );
		} catch( Exception e ) {
			response.getWriter().println(e.toString());
		}
	}

}
