package edu.ycp.cs320.personalized_commencement.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout") // path for logout
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			System.out.println("Logout Servlet: doGet");
			System.out.println("Deleting current user session: " + session.getId());
			session.invalidate(); // delete the current user session
		} else {
			System.out.println("No session to logout from");
		}
		
		// Forward to view to render the result HTML document
		LoginServlet login = new LoginServlet();
		login.doGet(req, resp);
    }	
}
