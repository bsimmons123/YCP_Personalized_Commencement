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
import edu.ycp.cs330.personalized_commencement.persist.DatabaseProvider;
import edu.ycp.cs330.personalized_commencement.persist.DerbyDatabase;
import edu.ycp.cs330.personalized_commencement.persist.IDatabase;

public class AdvisorIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Advisor Index Servlet: doGet");
		
		Advisor advisor = new Advisor();
		
		HttpSession session = req.getSession(false);
		
		advisor = (Advisor) session.getAttribute("advisor");
		
		// if no session then kick user to login page
		if(advisor == null) {
			LoginServlet login = new LoginServlet();
			login.doGet(req, resp);
		}
		
		// arraylist of adivsors students
		ArrayList<Student> students;
		
		// get all students for particular advisor
		students = getAdvisorsStudents(advisor.getEmail());
		
		// error message for JSP
		String errorMessage = null;
		
		req.setAttribute("errorMessage", errorMessage);
		
		req.setAttribute("advisor", advisor);
		req.setAttribute("stuList", students);


		req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("AdvisorIndex Servlet: doPost");
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
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
}