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
		resp.sendRedirect(req.getContextPath() + "/_view/login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		
		// Checks if student or advisor has entered the correct information
		// Used later
		boolean student = false;
		boolean advisor = false;

		// Creates advisor and student controller
		AdvisorController advisorController = new AdvisorController();
		StudentController studentController = new StudentController();

		// Add students to arraylist
		studentController.addStudent("teststudent@ycp.edu", "test"); 			// index 0
		studentController.addStudent("bsimmons1@ycp.edu", "test");		// index 1
		studentController.addStudent("rwood7@ycp.edu", "test");		// index 2
		studentController.addStudent("erosenberry", "test"); 	// index 3
		studentController.addStudent("amott@ycp.edu", "test");		// index 4
		
		//Add advisors to arraylist
		advisorController.addAdvisor("testadvisor@ycp.edu", "test");				// index 0
		advisorController.addAdvisor("jmoscola@ycp.edu", "test");					// index 1
		
		// Creates user to interact with controller
		UserModel jspUser = new UserModel();
		
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
			}else {
				// Check if user is Student
				System.out.println("\tChecking user login");
				// for each loop that iterates over each user
				for(StudentModel studentIter: studentController.getStudents()) {
					// if user is a student
					if(studentController.checkStudentLogin(studentIter, jspUser)) {
						System.out.println("\t\t" + studentIter.getEmail() + ": Logged in");
						student = true;
						studentController.setLogin(studentIter);
						req.setAttribute("student", studentIter);
					}
				}
				for(AdvisorModel advisorIter: advisorController.getAdvisors()) {
					// user is advisor
					if(advisorController.checkAdvisorLogin(advisorIter, jspUser)) {
						System.out.println("\t\t" + advisorIter.getEmail() + ": Logged in");
						advisor = true;
						advisorController.setLogin(advisorIter);
						req.setAttribute("advisor", advisorIter);
					}
				}
			}
		}catch(NullPointerException e) {
			System.out.println("Setting error");
		}
		
		
		System.out.println("\tPosting Login results");
		
		req.setAttribute("user", jspUser);
		
		// determines where to send the user
		if(student) {
			student = false; // set to false in case user logs out and tries to log in as a new user
			// redirects the student to their index page
			req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
		}else if(advisor) {
			advisor = false; // set to false in case user logs out and tries to log in as a new user
			// redirects the advisor to their index page
			req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
		}else {
			// redirects the user to the login page and shows invalid info error message
			req.setAttribute("errorMessage",  "Invalid Username/Password");
			System.out.println("Error message is: " + "Invalid Username/Password");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}
}