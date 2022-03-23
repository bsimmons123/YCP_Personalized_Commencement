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
		Advisor advisor = null;
//		// Creates user to interact with controller
		User jspUser = new User();
		
		// get username and password from form (USER)
		try {
			// pull parameters from JSP
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			// set parameters in user to check in controller
			jspUser.setEmail(email);
			jspUser.setPassword(password);
		}catch(NullPointerException e) {
			System.out.println("\tSetting error");
		}
		
		
		System.out.println("\tPosting Login results");
		
		req.setAttribute("user", jspUser);
		
		// check student login
		System.out.println("\tChecking user login");
		if(checkStudentLogin(jspUser.getEmail(), jspUser.getPassword())) {
			System.out.println("\t\tStudent Logged in");
			HttpSession session = req.getSession(true);
			System.out.println("\t\tUser Session: " + session.getId());
			student = getStudent(jspUser.getEmail(), jspUser.getPassword());
			session.setAttribute("sinfo", student);
			StudentIndexServlet studentIndex = new StudentIndexServlet();
			studentIndex.doGet(req, resp);
			return;
		}else if(checkAdvisorLogin(jspUser.getEmail(), jspUser.getPassword())) { // check advisor login
			System.out.println("\t\tAdvisor Logged in");
			HttpSession session = req.getSession(true); // create a new http session
			System.out.println("\t\tUser Session: " + session.getId()); // print out session id
			advisor = getAdvisor(jspUser.getEmail(), jspUser.getPassword()); // get advisor's email to send to session
			session.setAttribute("advisor", advisor); // set session
			AdvisorIndexServlet advisorIndex = new AdvisorIndexServlet();
			advisorIndex.doGet(req, resp); // creates a guarenteed push to get in advisor index
			return; // break from code
		}
			// redirects the user to the login page and shows invalid info error message
			req.setAttribute("errorMessage",  "Invalid Username/Password");
			System.out.println("Error message is: " + "Invalid Username/Password");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
	}
	
	/**
	 * get advisor account
	 * @param email		advisors email
	 * @return			advisor account
	 */
	public Advisor getAdvisor(String email, String password) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Advisor advisor = db.getAdvisor(email, password);
		
		// check if anything was returned and output the list
		if (advisor != null) {
				return advisor;
			}
		return null;
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
	 * boolean if advisor exists in db
	 * @param email		email to check records with
	 * @return			true if user exists
	 */
	public boolean checkAdvisorLogin(String email, String password) {
		Advisor advisor = new Advisor();
		
		advisor = getAdvisor(email, password);
		
		// check if anything was returned and output the list
		if (advisor != null) {
				return true;
			}
		return false;
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