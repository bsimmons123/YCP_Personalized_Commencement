package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.personalized_commencement.controller.AdvisorController;
import edu.ycp.cs320.personalized_commencement.controller.StudentController;
import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;
import edu.ycp.cs320_personalized_commencement.model.StudentModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

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
		
		// Checks if student or advisor has entered the correct information
		boolean student = false;
		boolean advisor = false;
		
		// Creates advisor and student model
		AdvisorModel advisorModel = new AdvisorModel();
		StudentModel studentModel = new StudentModel();
		
		// Creates advisor and student controller
		AdvisorController advisorController = new AdvisorController();
		StudentController studentController = new StudentController();
	
		// Creates user to interact with controller
		UserModel jspUser = new UserModel();
		
		// sets model in controllers
		advisorController.setModel(advisorModel);
		studentController.setModel(studentModel);
		
		studentController.createTestStudent("bsimmons1@ycp.edu", "test");
		advisorController.createTestAdvisor();
		
		// get username and password from form
		try {
			// pull parameters from JSP
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			// set parameters in user to check in controller
			jspUser.setEmail(email);
			jspUser.setPassword(password);
			if(email == null || password == null) {
				System.out.println("\tInvalid Username/Password");
				errorMessage = "Invalid Username/Password";
			}else {
				// Check if user is Student
				System.out.println("\tChecking user login");
				if(studentController.checkStudentLogin(jspUser)) {
					student = true;
					studentController.setLogin();
				}else if(advisorController.checkAdvisorLogin(jspUser)) { // Check if user is advisor
					advisor = true;
				}else {
					System.out.println("\tInvalid Username/Password");
					errorMessage = "Invalid Username/Password";
				}
			}
		}catch(NullPointerException e) {
			System.out.println("Setting error");
			errorMessage = "Invalid Username/Password";
		}
		
		req.setAttribute("errorMessage", errorMessage);
		System.out.println("\tLogin results");
		
		req.setAttribute("user", jspUser);
		
		// determines where to send the user
		if(student) {
			student = false; // set to false incase user logs out and tries to log in as a new user
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
		}else if(advisor) {
			advisor = false; // set to false incase user logs out and tries to log in as a new user
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
		}else {
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		
	}
}