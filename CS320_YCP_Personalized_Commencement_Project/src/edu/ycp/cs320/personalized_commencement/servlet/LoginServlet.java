package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
		
		// initialize students array
		ArrayList<StudentModel> students = new ArrayList<StudentModel>();
		
		// Creates advisor and student model
		AdvisorModel advisorModel = new AdvisorModel();
		StudentModel studentModel = new StudentModel();

		// Creates advisor and student controller
		AdvisorController advisorController = new AdvisorController();
		StudentController studentController = new StudentController();
		
		// Set student arraylist in controller
		studentController.setStudents(students);
		
		// sets model in controllers
		advisorController.setModel(advisorModel);
		
		// Creates student models for testing 
		StudentModel amottStudentModel = new StudentModel();
		StudentModel erosenberryStudentModel = new StudentModel();
		StudentModel rwoodStudentModel = new StudentModel();
		StudentModel bsimmonsStudentModel = new StudentModel();
		
		studentController.addStudent(studentModel); 			// index 0
		studentController.addStudent(bsimmonsStudentModel);		// index 1
		studentController.addStudent(rwoodStudentModel);		// index 2
		studentController.addStudent(erosenberryStudentModel); 	// index 3
		studentController.addStudent(amottStudentModel);		// index 4
	
		// Creates user to interact with controller
		UserModel jspUser = new UserModel();
		
		advisorController.createTestAdvisor("testadvisor@ycp.edu", "test");
		
		// Creates accounts for test users
		studentController.createTestStudent(studentController.getStudent(0),"teststudent@ycp.edu", "test");
		studentController.createTestStudent(studentController.getStudent(1), "bsimmons1@ycp.edu", "test");
		studentController.createTestStudent(studentController.getStudent(2), "rwood7@ycp.edu", "test");
		studentController.createTestStudent(studentController.getStudent(3), "erosenberry", "test");
		studentController.createTestStudent(studentController.getStudent(4), "amott@ycp.edu", "test");
		
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
				for(StudentModel studentIter: studentController.getStudents()) {
					if(studentController.checkStudentLogin(studentIter, jspUser)) {
						System.out.println("\t\t" + studentIter.getEmail() + ": Logged in");
						student = true;
						studentController.setLogin(studentIter);
					}else if(advisorController.checkAdvisorLogin(jspUser)) { // Check if user is advisor
						advisor = true;
					}else {
						errorMessage = "Invalid Username/Password";
					}
				}
		
			}
		}catch(NullPointerException e) {
			System.out.println("Setting error");
			errorMessage = "Invalid Username/Password";
		}
		
		req.setAttribute("errorMessage", errorMessage);
		System.out.println("\tPosting Login results");
		
		req.setAttribute("user", jspUser);
		
		// determines where to send the user
		if(student) {
			student = false; // set to false incase user logs out and tries to log in as a new user
			// Redirect to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/_view/student_index.jsp");
		}else if(advisor) {
			advisor = false; // set to false incase user logs out and tries to log in as a new user
			// Redirect to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/_view/advisor_index.jsp");
		}else {
			// Redirect to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/_view/login.jsp");
		}
		
	}
}