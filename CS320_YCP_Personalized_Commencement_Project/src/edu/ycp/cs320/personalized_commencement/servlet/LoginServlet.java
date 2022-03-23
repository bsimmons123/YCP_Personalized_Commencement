package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.controller.AdvisorController;
import edu.ycp.cs320.personalized_commencement.controller.StudentController;
import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.model.User;
import edu.ycp.cs330.personalized_commencement.persist.DatabaseProvider;
import edu.ycp.cs330.personalized_commencement.persist.DerbyDatabase;
import edu.ycp.cs330.personalized_commencement.persist.IDatabase;

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
		Student student = null;
		boolean advisor = false;

//		// Creates advisor and student controller
//		AdvisorController advisorController = new AdvisorController();
//		StudentController studentController = new StudentController();
//
//		// Add students to arraylist
//		studentController.addStudent("teststudent@ycp.edu", "test"); 			// index 0
//		studentController.addStudent("bsimmons1@ycp.edu", "test");				// index 1
//		studentController.addStudent("rwood7@ycp.edu", "test");					// index 2
//		studentController.addStudent("erosenberry@ycp.edu", "test"); 					// index 3
//		studentController.addStudent("amott@ycp.edu", "test");					// index 4
//		
//		//Add advisors to arraylist
//		advisorController.addAdvisor("testadvisor@ycp.edu", "test");			// index 0
//		advisorController.addAdvisor("jmoscola@ycp.edu", "test");				// index 1
//		// index 0
//		studentController.setStudentInfo(studentController.getStudent(1),"Brandon", "P", "Simmons", "Computer Science", "Philosophy - Math", "RockClimbing Club", "Faceshot.png", "Null");
//		// index 1
//		studentController.setStudentInfo(studentController.getStudent(2),"Robert", "P", "Wood", "Computer Science", "Cocks", "Face Palming", "Faceshot.png", "Null");
//		// index 2
//		studentController.setStudentInfo(studentController.getStudent(3),"Ethan", "P", "Rosenberry", "Electrical Engineering", "Sleep", "N/A", "Faceshot.png", "Null");
//		// index 3
//		studentController.setStudentInfo(studentController.getStudent(4),"Andrew", "P", "Mott", "Electrical Engineering", "Math", "Twack und felid", "Faceshot.png", "Null");
//		
//		
//		// Creates user to interact with controller
		User jspUser = new User();
		
//		// get username and password from form
//		try {
//			// pull parameters from JSP
//			String email = req.getParameter("email");
//			String password = req.getParameter("password");
//			
//			// set parameters in user to check in controller
//			jspUser.setEmail(email);
//			jspUser.setPassword(password);
//			if(email == null || password == null) {
//				System.out.println("\tInvalid Username/Password");
//			}else {
//				// Check if user is Student
//				System.out.println("\tChecking user login");
//				// for each loop that iterates over each user
//				for(Student studentIter: studentController.getStudents()) {
//					// if user is a student
//					if(studentController.checkStudentLogin(studentIter, jspUser)) {
//						System.out.println("\t\t" + studentIter.getEmail() + ": Logged in");
//						student = true;
//						studentController.setLogin(studentIter);
//						req.setAttribute("student", studentIter);
////						req.setAttribute("sinfo", studentController.getStudent(1).getStudentInfo());
//					}
//				}
//				for(Advisor advisorIter: advisorController.getAdvisors()) {
//					// user is advisor
//					if(advisorController.checkAdvisorLogin(advisorIter, jspUser)) {
//						System.out.println("\t\t" + advisorIter.getEmail() + ": Logged in");
//						advisor = true;
//						advisorController.setLogin(advisorIter);
//						req.setAttribute("advisor", advisorIter);
//					}
//				}
//			}
//		}catch(NullPointerException e) {
//			System.out.println("Setting error");
//		}
		
		
		System.out.println("\tPosting Login results");
		
		req.setAttribute("user", jspUser);
		
		// check student login
		if(checkStudentLogin(jspUser.getEmail(), jspUser.getPassword())) {
			System.out.println("\t\tStudent Logged in");
			HttpSession session = req.getSession(true);
			System.out.println("\t\tUser Session: " + session.getId());
			student = getStudent(jspUser.getEmail(), jspUser.getPassword());
			session.setAttribute("sinfo", student);
			StudentIndexServlet studentIndex = new StudentIndexServlet();
			studentIndex.doGet(req, resp);
			return;
		}
			// redirects the user to the login page and shows invalid info error message
			req.setAttribute("errorMessage",  "Invalid Username/Password");
			System.out.println("Error message is: " + "Invalid Username/Password");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
	}
	
	/**
	 * get student account
	 * @param email		students email
	 * @return			students account
	 */
	public Student getStudent(String email, String password) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Student student = db.getStudent(email, password);
		
		// check if anything was returned and output the list
		if (student != null) {
				return student;
			}
		return null;
	}
	
	/**
	 * boolean if student exists in db
	 * @param email		email to check records with
	 * @return			true if user exists
	 */
	public boolean checkStudentLogin(String email, String password) {
		Student student = new Student();
		
		student = getStudent(email, password);
		
		// check if anything was returned and output the list
		if (student != null) {
				return true;
			}
		return false;
		}
}