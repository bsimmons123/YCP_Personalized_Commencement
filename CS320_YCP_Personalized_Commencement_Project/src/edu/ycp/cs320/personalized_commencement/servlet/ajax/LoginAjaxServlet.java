package edu.ycp.cs320.personalized_commencement.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.personalized_commencement.controller.AdvisorController;
import edu.ycp.cs320.personalized_commencement.controller.StudentController;
import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;
import edu.ycp.cs320_personalized_commencement.model.StudentModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;


public class LoginAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}

	private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set models
		StudentModel studentModel = new StudentModel();
		AdvisorModel advisorModel = new AdvisorModel();
		
		UserModel user = new UserModel();
		
		// Get parameters
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		user.setEmail(email);
		user.setPassword(password);
		
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		
		// Check whether parameters are valid
		if (email == null || password == null) {
			badRequest("Bad parameters", resp);
			return;
		}
		
		// Use a controller to process the request
		StudentController studentController = new StudentController();
		AdvisorController advisorController = new AdvisorController();
		
		// Send back a response
		resp.setContentType("text/plain");
	}


	private void badRequest(String message, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		resp.getWriter().println(message);
	}
}
