package edu.ycp.cs320.personalized_commencement.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;

@WebServlet(urlPatterns = "/search.do") // Web annotation for student search
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// create controllers for info and student
		System.out.println("\tSearch Servlet: doPost");
		
		String userInputEmail = req.getParameter("email");
		
		ServletsController controller = new ServletsController();
		
		System.out.println("\t\t" + userInputEmail);
		
		String[] studentFirstLast = null;
		
		// break email string into first and last name if user entered First and Last name
		for(int i = 0; i < 2; i++) {
			studentFirstLast = userInputEmail.split(" ");
		}

		if(userInputEmail != null && studentFirstLast.length < 2) { // check for Email in DB
			Student userStudent  = controller.getStudentByEmail(userInputEmail);
			if(userStudent != null) {
				req.setAttribute("student", userStudent);
				System.out.println("\t\tSearch for: " + userInputEmail + " complete");
				System.out.println("\t\tPosting Results");
				
				// Forward to view to render the result HTML document
				req.getRequestDispatcher("/_view/student_search.jsp").forward(req, resp);
				return;
			}else {
				req.setAttribute("errorMessage", "Student with Email: '" + userInputEmail + "' was not found");
			}
		}else if(studentFirstLast.length == 2){ // check for First and Last name in DB
			Student userStudent = controller.getStudentByFirstAndLast(studentFirstLast[0], studentFirstLast[1]);
			if(userStudent != null) {
				req.setAttribute("student", userStudent);
				System.out.println("\t\tSearch for: " + userInputEmail + " complete");
				System.out.println("\t\tPosting Results");
				
				// Forward to view to render the result HTML document
				req.getRequestDispatcher("/_view/student_search.jsp").forward(req, resp);
				return;
			}else {
				req.setAttribute("errorMessage", "Student: '"+ studentFirstLast[0] + " " + studentFirstLast[1] + "' was not found");
			}
		}

		userInputEmail.toLowerCase();
		if(confettiCannon("don hake", userInputEmail)) { // confetti trigger
			req.setAttribute("confetti", "CONFETTI!!!!");
			req.setAttribute("errorMessage", null);
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			return;
		}else if(confettiCannon("confetti", userInputEmail)) {
			req.setAttribute("confetti", "CONFETTI!!!!");
			req.setAttribute("errorMessage", null);
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			return;
		}
		
		
		// if all student is not found on search, push back to login
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
    }
	
	/**
	 * Check if user input equals condition
	 * @param trigger		Target phrase
	 * @param input			User input
	 * @return
	 */
	private boolean confettiCannon(String trigger, String input) {
		if(input.toLowerCase().equals(trigger)) 
			return true;
		return false;
	}
}
