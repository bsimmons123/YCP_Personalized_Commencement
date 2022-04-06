package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;

public class CeremonyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ServletsController controller = new ServletsController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Ceremony Servlet: doGet");
		
		// arraylist of adivsors students
		ArrayList<Student> students;
		ArrayList<Integer> studentIDs = new ArrayList<Integer>();
		
		// get all students for particular advisor
		students = controller.getAllStudents();
		
		for(Student student: students) {
			if(student.getStudentId() != 0) {
				studentIDs.add(student.getStudentId());
			}
			else {
				continue;
			}
		}
		
		// list of all students
		req.setAttribute("stuList", students);
		req.setAttribute("stuIDList", studentIDs);
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/ceremony.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Ceremony Servlet: doPost");
		// arraylist of adivsors students
				ArrayList<Student> students;
				ArrayList<Integer> studentIDs = new ArrayList<Integer>();
				
				// get all students for particular advisor
				students = controller.getAllStudents();
				
				for(Student student: students) {
					if(student.getStudentId() != 0) {
						studentIDs.add(student.getStudentId());
					}
					else {
						continue;
					}
				}
				
				// list of all students
				req.setAttribute("stuList", students);
				req.setAttribute("stuIDList", studentIDs);
				req.getRequestDispatcher("/_view/ceremony.jsp").forward(req, resp);
	}
}