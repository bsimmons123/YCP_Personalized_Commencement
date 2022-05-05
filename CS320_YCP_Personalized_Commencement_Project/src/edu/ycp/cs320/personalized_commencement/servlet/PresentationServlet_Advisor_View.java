package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;

public class PresentationServlet_Advisor_View extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletsController controller = new ServletsController();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Presentation Servlet: doPost");
		
		// get current session
		HttpSession session = req.getSession(false); 
		
		// redirect if no session
		if(session == null) {
			LoginServlet loginServlet = new LoginServlet();
			loginServlet.doGet(req, resp);
			return;
		}
		
		Student student = (Student) session.getAttribute("studentInfo");
		
		// pull attributes from page
		String major = req.getParameter("major_approval");
		String minor = req.getParameter("minor_approval");
		String extraCur = req.getParameter("extracur_approval");
		String image = req.getParameter("image_approval");
		String audio = req.getParameter("audio_approval");
		String comment = req.getParameter("submissioncomment");
		
		System.out.println("\tMajor: " + major + " | Minor: " + minor +
				" | ExtraCur: " + extraCur + " | Image: " + image + 
				" | Audio: " + audio);
		
		System.out.println("\tSubmission Comment: " + comment);
		
		// cast them to integers for database
		int checkExtCur = controller.toInt(extraCur);
		int checkImg = controller.toInt(image);
		int checkAudio = controller.toInt(audio);
		
		// update student content
		if(controller.updateStudentContent(student.getStudentId(), checkExtCur, checkImg, checkAudio)) {
			System.out.println("\tAdvisor's checkBoxes saved");
		}
		
		if(controller.updateStudentComment(student.getStudentId(), comment)){
			System.out.println("\tAdvisor's Comment saved");
		}
		
		// Forward to view to render the result HTML document
		AdvisorIndexServlet advisorServlet = new AdvisorIndexServlet();
		advisorServlet.doGet(req, resp);
	}
}
