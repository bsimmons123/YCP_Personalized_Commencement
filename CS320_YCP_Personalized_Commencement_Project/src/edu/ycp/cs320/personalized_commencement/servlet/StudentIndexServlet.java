package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.controller.StudentInfoController;
import edu.ycp.cs320_personalized_commencement.model.StudentInfoModel;
//import edu.ycp.cs320.personalized_commencement.controller.StudentController;
import edu.ycp.cs320_personalized_commencement.model.StudentModel;

public class StudentIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Student Index Servlet: doGet");
		
		HttpSession session = req.getSession(false);
		StudentInfoModel stuInfo = new StudentInfoModel();
		stuInfo = (StudentInfoModel) session.getAttribute("sinfo");
		req.setAttribute("sinfo", stuInfo);

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
		StudentModel model = new StudentModel();
		req.setAttribute("errorMessage", errorMessage);
		System.out.println("\tPosting index");
		
		req.setAttribute("student", model);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
	}
}
