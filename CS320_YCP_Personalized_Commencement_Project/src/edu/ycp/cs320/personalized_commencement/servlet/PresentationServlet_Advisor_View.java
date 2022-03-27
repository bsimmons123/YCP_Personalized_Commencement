package edu.ycp.cs320.personalized_commencement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.persist.DatabaseProvider;
import edu.ycp.cs320.personalized_commencement.persist.DerbyDatabase;
import edu.ycp.cs320.personalized_commencement.persist.IDatabase;

public class PresentationServlet_Advisor_View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Presentation Servlet: doPost");
		
		// get current session
		HttpSession session = req.getSession(false); 
		
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
		
		// cast them to ints for database
		int checkMajor = toInt(major);
		int checkMinor = toInt(minor);
		int checkExtCur = toInt(extraCur);
		int checkImg = toInt(image);
		int checkAudio = toInt(audio);
		
		// update student content
		if(updateStudentContent(student.getStudentId(), checkMajor, checkMinor, checkExtCur, checkImg, checkAudio)) {
			System.out.println("\tAdvisor's checkBoxes saved");
		}
		
		if(updateStudentComment(student.getEmail(), comment)){
			System.out.println("\tAdvisor's Comment saved");
		}
		
		// Forward to view to render the result HTML document
		AdvisorIndexServlet advisorServlet = new AdvisorIndexServlet();
		advisorServlet.doGet(req, resp);
	}
	
	private boolean updateStudentComment(String email, String comment) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Boolean student = db.updateAdvisorComment(email, comment);
		
		// check if anything was returned and output the list
		if (student == null) {
			System.out.println("\tNo students found for email <" + email + ">");
			return false;
		}
		else {
			return student;
		}
	}

	private int toInt(String param) {
		if(param == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	private Boolean updateStudentContent(int id, int major, int minor, int extcur, int img, int audio) {
		// Create the default IDatabase instance
				DatabaseProvider.setInstance(new DerbyDatabase());
				
				// get the DB instance and execute transaction
				IDatabase db = DatabaseProvider.getInstance();
				Boolean student = db.updateStudentContentSubmissions(id, major, minor, extcur, img, audio);
				
				// check if anything was returned and output the list
				if (student == null) {
					System.out.println("\tNo students found for ID <" + id + ">");
					return null;
				}
				else {
					return student;
				}
	}
}
