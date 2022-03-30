package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GradViewServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Login Servlet: doGet");
		// call JSP to generate empty form
		resp.sendRedirect(req.getContextPath() + "/_view/graduation_preview.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Login Servlet: doPost");
	}
}