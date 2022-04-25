package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.model.User;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ServletsController controller = new ServletsController();
	
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
		
		// Creates user to interact with controller
		User jspUser = new User();
		
		// get user name and password from form (USER)
		try {
			// pull parameters from JSP
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			// set parameters in user to check in controller
			jspUser.setEmail(email);
			jspUser.setPassword(password);
			
		} catch (NullPointerException e) {
			System.out.println("\tSetting error");
		}
		
		System.out.println("\tPosting Login results");
		
		req.setAttribute("user", jspUser);
		
		// check student login
		System.out.println("\tChecking user login");
		if (controller.checkStudentLogin(jspUser.getEmail(), jspUser.getPassword())) {
			System.out.println("\t\tStudent Logged in");
			HttpSession session = req.getSession(true);
			System.out.println("\t\tUser Session: " + session.getId());
			student = controller.getStudent(jspUser.getEmail(), jspUser.getPassword());
			session.setAttribute("student", student);
			StudentIndexServlet studentIndex = new StudentIndexServlet();
			studentIndex.doGet(req, resp);
			return;
		} else if(controller.checkAdvisorLogin(jspUser.getEmail(), jspUser.getPassword())) { // check advisor login
			System.out.println("\t\tAdvisor Logged in");
			HttpSession session = req.getSession(true); // create a new http session
			System.out.println("\t\tUser Session: " + session.getId()); // print out session id
			advisor = controller.getAdvisor(jspUser.getEmail(), jspUser.getPassword()); // get advisor's email to send to session
			session.setAttribute("advisor", advisor); // set session
			AdvisorIndexServlet advisorIndex = new AdvisorIndexServlet();
			advisorIndex.doGet(req, resp); // creates a guaranteed push to get in advisor index
			return; // break from code
		} else if(jspUser.getEmail().equals("Admin") && jspUser.getPassword().equals("YorkGrads2022")) {
			CeremonyServlet ceremonyServlet = new CeremonyServlet();
			ceremonyServlet.doGet(req, resp);
			return;
		}
		
		// redirects the user to the login page and shows invalid info error message
		req.setAttribute("errorMessage",  "Invalid Username/Password");
		System.out.println("Error message is: " + "Invalid Username/Password");
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
	}
}