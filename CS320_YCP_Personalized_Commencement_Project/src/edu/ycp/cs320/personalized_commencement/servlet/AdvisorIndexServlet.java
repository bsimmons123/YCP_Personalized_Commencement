package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;

public class AdvisorIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletsController controller = new ServletsController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Advisor Index Servlet: doGet");
		
		Advisor advisor = new Advisor();
		
		// get current session
		HttpSession session = req.getSession(false);
		
		// redirect if no session
		if(session == null) {
			LoginServlet loginServlet = new LoginServlet();
			loginServlet.doGet(req, resp);
			return;
		}
		
		// get the current advisor logged in
		advisor = (Advisor) session.getAttribute("advisor");
		
		// set student info to null so info does not overlap
		session.setAttribute("studentInfo", null);
		
		// if no session then kick user to login page
		if(advisor == null) {
			LoginServlet login = new LoginServlet();
			login.doGet(req, resp);
		}
		
		// arraylist of adivsors students
		ArrayList<Student> students;
		
		// arraylist of approved students
		ArrayList<Student> approvedStudents = new ArrayList<Student>();
		
		// arraylist of pending approval students
		ArrayList<Student> pendingStudents = new ArrayList<Student>();
		
		// get all students for particular advisor
		students = controller.getAdvisorsStudents(advisor.getEmail());
		
		for(Student student: students) {
			if(student.getApproval() == 1) {
				approvedStudents.add(student);
			}
			else {
				pendingStudents.add(student);
			}
		}
		
		// error message for JSP
		String errorMessage = null;
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("advisor", advisor);
		
		// list of approved students
		req.setAttribute("stuList", approvedStudents);
		
		// list of students pending approval
		req.setAttribute("pendingStuList", pendingStudents);
		
		req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("AdvisorIndex Servlet: doPost");
		
		int student_id = controller.getInteger(req, "student");
		
		Student student = controller.getStudentById(student_id);
		
		System.out.println("\tAdvisor Viewing: " + student.getFirst() + " " +  student.getLast());
		
		HttpSession session = req.getSession(false);
		
		ServletsController DBController = new ServletsController();
		
		ArrayList<String> studentComments = DBController.getStudentsComments(student.getStudentId());
		
		// redirect if no session
		if(session == null) {
			LoginServlet loginServlet = new LoginServlet();
			loginServlet.doGet(req, resp);
			return;
		}
		
		session.setAttribute("studentInfo", student);
		
		req.setAttribute("comments", studentComments);
	
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/presentation.jsp").forward(req, resp);
	}
}