package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.model.Student;

public class PresentationServlet_Advisor_View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Presentation Servlet: doPost");
		
		String major = req.getParameter("major_approval");
		String minor = req.getParameter("minor_approval");
		String extraCur = req.getParameter("extracur_approval");
		String image = req.getParameter("image_approval");
		String audio = req.getParameter("audio_approval");
		
		System.out.println("\tMajor: " + major + " | Minor: " + minor +
				" | ExtraCur: " + extraCur + " | Image: " + image + 
				" | Audio: " + audio);
		
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/presentation.jsp").forward(req, resp);
	}
}
