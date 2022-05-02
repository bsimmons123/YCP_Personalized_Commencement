package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.model.Student;

public class StudentIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Student Index Servlet: doGet");
		
		HttpSession session = req.getSession(false);
		
		// redirect if no session
		if(session == null) {
			LoginServlet loginServlet = new LoginServlet();
			loginServlet.doGet(req, resp);
			return;
		}
		
		Student stuInfo = new Student();
		stuInfo = (Student) session.getAttribute("student");
		
		if (!stuInfo.getComment().isEmpty()) {
			System.out.println("Student Comment: " + stuInfo.getComment());
		}
		
		req.setAttribute("student", stuInfo);
		
		req.setAttribute("gpa", stuInfo.getShowGPA());

		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("StudentIndex Servlet: doPost");
		
		// error message for JSP
		String errorMessage = null;
		
		// create new model
		Student model = new Student();
		req.setAttribute("errorMessage", errorMessage);
		System.out.println("\tPosting index");
		
		req.setAttribute("student", model);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
	}
}
