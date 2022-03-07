package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320_personalized_commencement.model.Advisor;
import edu.ycp.cs320_personalized_commencement.model.Student;
import edu.ycp.cs320_personalized_commencement.model.User;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");

		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		// Creates advisor and student objects
		Advisor advisor = new Advisor();
		Student student = new Student();
		// Creates user to interact with controller
		User user = new User();
		
		student.createTestStudent();
		advisor.createTestAdvisor();
		
		// get username and password from form
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
		}catch(NullPointerException e) {
			System.out.println("Setting error");
			errorMessage = "invalid Username or Password";
		}
		
		
	}
}