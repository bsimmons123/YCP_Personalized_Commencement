package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import edu.ycp.cs320.personalized_commencement.controller.AdvisorController;
import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;

public class AdvisorIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Advisor Index Servlet: doGet");
		
		ArrayList<String> stuList = new ArrayList<String>();
		stuList.add("Brandon Simmons");
		stuList.add("Rob Wood");
		stuList.add("Ethan Rosenberry");
		stuList.add("Andrew Mott");
		stuList.add("John Appleseed");
		stuList.add("Gwegowy Thunderballz");
		stuList.add("Ethan RosesN'Berries");
		stuList.add("Andrew \"Expostulate\" Mott");
		stuList.add("Michael Jackson");
		stuList.add("Ben(Brandon) Simmons");
		stuList.add("Bobert Forest");
		stuList.add("Cassidy Patchel");
		stuList.add("Grant MacDonald");
		stuList.add("Ricky Berwick");
		stuList.add("Spider Man");
		stuList.add("Bologna Boy");
		
		// error message for JSP
		String errorMessage = null;
		
//		AdvisorController controller = new AdvisorController();
//		controller.setModel(model);
		
		req.setAttribute("errorMessage", errorMessage);
		System.out.println("\tPosting index");
		
		HttpSession session = req.getSession(false);
		
		AdvisorModel model = (AdvisorModel) session.getAttribute("advisor");
		
		req.setAttribute("advisor", model);
		req.setAttribute("stuList", stuList);


		req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("AdvisorIndex Servlet: doPost");
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/advisor_index.jsp").forward(req, resp);
	}

}