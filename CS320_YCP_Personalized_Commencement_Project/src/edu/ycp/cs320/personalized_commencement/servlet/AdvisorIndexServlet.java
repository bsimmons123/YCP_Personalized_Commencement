package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.personalized_commencement.controller.AdvisorController;
import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;

public class AdvisorIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Advisor Index Servlet: doGet");

		// call JSP to generate empty form
		resp.sendRedirect(req.getContextPath() + "/_view/login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("AdvisorIndex Servlet: doPost");
		
		// error message for JSP
		String errorMessage = null;
		
		// create new model
		AdvisorModel model = new AdvisorModel();
		AdvisorController controller = new AdvisorController();
		//controller.setModel(model);
		
		req.setAttribute("errorMessage", errorMessage);
		System.out.println("\tPosting index");
		
		req.setAttribute("advisor", model);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
	}

}