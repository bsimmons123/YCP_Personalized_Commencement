package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320_personalized_commencement.model.StudentInfoModel;

public class PresentationServlet_Advisor_View extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Presentation Servlet: doGet");
		
		// create new model
		StudentInfoModel info = new StudentInfoModel();

			
		//hardcode information for the advisor view
		info.setFirstName("John");
		info.setLastName("Appleseed");
		info.setMajor("Biology");
		info.setMinor("Philosophy");
		info.setMiddleInitial("A");
		info.setExtraCur("Gardening Club President");
		//picture will be hard coded into presentation jsp
				
				
		req.setAttribute("student", info);

		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/presentation.jsp").forward(req, resp);
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		
//		System.out.println("Presentation Servlet: doPost");
//		
//		
//		// create new model
//		StudentInfoModel model = new StudentInfoModel();
//
//	
//		//hardcode information for the advisor view
//		model.setFirstName("John");
//		model.setLastName("Appleseed");
//		model.setMajor("Biology");
//		model.setMinor("Philosophy");
//		model.setMiddleInitial("A");
//		model.setExtraCur("Gardening Club President");
//		//picture will be hard coded into presentation jsp
//		
//		
//		req.setAttribute("info", model);
//		
//		// Forward to view to render the result HTML document
//		req.getRequestDispatcher("/_view/presentation.jsp").forward(req, resp);
//	}
}
