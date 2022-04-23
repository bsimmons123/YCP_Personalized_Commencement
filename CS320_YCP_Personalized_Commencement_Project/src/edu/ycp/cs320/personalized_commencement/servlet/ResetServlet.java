package edu.ycp.cs320.personalized_commencement.servlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;

@WebServlet(urlPatterns = "/reset.do") // Link to reset user data
@MultipartConfig
public class ResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletsController controller = new ServletsController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// create controllers for info and student
		System.out.println("\tReset Servlet: doGet");


			HttpSession session = req.getSession(false); // get current session
			
        	// Get student from session
			Student student = new Student();

        	student = (Student) session.getAttribute("student");
        	
        	
        	System.out.println("\tresetting " + student.getFirst() + "'s content!");
        	controller.updateStudent(student.getEmail(), "", "", ""); // sets all user content to null
        	
        	student = controller.getStudentById(student.getStudentId()); // to  reinitilize the student's object from DB
        	
        	session.setAttribute("student", student); // reset the student to take changes into account
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
    }
}
