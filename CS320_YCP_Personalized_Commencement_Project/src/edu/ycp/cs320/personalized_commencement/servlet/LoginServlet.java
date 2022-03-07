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
		
		// Creates advisor and student model
		AdvisorModel advisorModel = new AdvisorModel();
		StudentModel studentModel = new StudentModel();
		
		// Creates advisor and student controller
		AdvisorController advisorController = new AdvisorController();
		StudentController studentController = new StudentController();
		
		// sets model in controllers
		advisorController.setModel(advisorModel);
		studentController.setModel(studentModel);
		
		
		// Creates user to interact with controller
		UserModel user = new UserModel();
		
		studentModel.createTestStudent();
		advisorModel.createTestAdvisor();
		
		// get username and password from form
		try {
			// pull parameters from JSP
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			// set parameters in user to check in controller
			user.setEmail(email);
			user.setPassword(password);
			
			if(email == null || password == null) {
				errorMessage = "invalid Username or Password";
			}else {
				// Check if user is Student
				if(studentController.checkStudentLogin(user)) {
					
				}else if(advisorController.checkAdvisorLogin(user)) { // Check if user is advisor
					
				}
			}
		}catch(NullPointerException e) {
			System.out.println("Setting error");
			errorMessage = "invalid Username or Password";
		}
		
		
		
	}
}