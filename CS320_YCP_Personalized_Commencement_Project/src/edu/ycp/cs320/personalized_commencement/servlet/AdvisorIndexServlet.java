package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.persist.DatabaseProvider;
import edu.ycp.cs320.personalized_commencement.persist.DerbyDatabase;
import edu.ycp.cs320.personalized_commencement.persist.IDatabase;

public class AdvisorIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Advisor Index Servlet: doGet");
		
		Advisor advisor = new Advisor();
		
		// get current session
		HttpSession session = req.getSession(false);
		
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
		students = getAdvisorsStudents(advisor.getEmail());
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
		
		int student_id = getInteger(req, "student");
		
		Student student = getStudentById(student_id);
		
		System.out.println("\tAdvisor Viewing: " + student.getFirst() + student.getLast());
		
		HttpSession session = req.getSession(false);
		
		session.setAttribute("studentInfo", student);
	
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/presentation.jsp").forward(req, resp);
	}
	
	// gets an Integer from the Posted form data, for the given attribute name
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}

	public ArrayList<Student> getAdvisorsStudents(String AdvisorEmail) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Student> studentAdvisorList = db.findStudentsByAdvisor(AdvisorEmail);
		
		// check if anything was returned and output the list
		if (studentAdvisorList.isEmpty()) {
			System.out.println("\tNo students found for <" + AdvisorEmail + ">");
			return null;
		}
		else {
			return studentAdvisorList;
		}
	}
	
	private Student getStudentById(int id) {
		// Create the default IDatabase instance
				DatabaseProvider.setInstance(new DerbyDatabase());
				
				// get the DB instance and execute transaction
				IDatabase db = DatabaseProvider.getInstance();
				Student student = db.findStudentsById(id);
				
				// check if anything was returned and output the list
				if (student == null) {
					System.out.println("\tNo students found for ID <" + id + ">");
					return null;
				}
				else {
					return student;
				}
	}
}