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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Ceremony Servlet: doGet");
		
		// Create and initialize all of the student's properties
		ArrayList<Student> students;
		ArrayList<String> studentIDs = new ArrayList<String>();
		ArrayList<String> studentFirstNames = new ArrayList<String>();
		ArrayList<String> studentLastNames = new ArrayList<String>();
		ArrayList<String> studentMajors = new ArrayList<String>();
		ArrayList<String> studentMinors = new ArrayList<String>();
		ArrayList<String> studentGPAs = new ArrayList<String>();
		ArrayList<String> studentExtras = new ArrayList<String>();
		ArrayList<String> studentAwards = new ArrayList<String>();
		ArrayList<String> studentPictures = new ArrayList<String>();
		ArrayList<String> studentAudios = new ArrayList<String>();
		ArrayList<String> extraCurApprovals = new ArrayList<String>();
		ArrayList<String> imgApprovals = new ArrayList<String>();
		ArrayList<String> audioApprovals = new ArrayList<String>();
		ArrayList<String> GPAChoices = new ArrayList<String>();
		
		// add all students from database to student arrayList
		students = controller.getAllStudents(); // list is sorted by id number ascending
		
		// populate each property list
		for (Student stud : students) {
			studentIDs.add(String.valueOf(stud.getStudentId()));
			studentFirstNames.add(stud.getFirst());
			studentLastNames.add(stud.getLast());
			studentMajors.add(stud.getMajor());
			studentMinors.add(stud.getMinor());
			studentGPAs.add(String.valueOf(stud.getGPA()));
			studentExtras.add(stud.getExtraCur());
			studentAwards.add(stud.getAward());
			studentPictures.add(stud.getPicture());
			studentAudios.add(stud.getNameSound());
			extraCurApprovals.add(String.valueOf(stud.getCheckExtCur()));
			imgApprovals.add(String.valueOf(stud.getCheckImg()));
			audioApprovals.add(String.valueOf(stud.getCheckAudio()));
			GPAChoices.add(String.valueOf(stud.getShowGPA()));
		}
		
		// set all property lists as request attributes and send user to the page
		req.setAttribute("stuList", students);
		req.setAttribute("studentIDs", studentIDs);
		req.setAttribute("studentFirstNames", studentFirstNames);
		req.setAttribute("studentLastNames", studentLastNames);
		req.setAttribute("studentMajors", studentMajors);
		req.setAttribute("studentMinors", studentMinors);
		req.setAttribute("studentGPAs", studentGPAs);
		req.setAttribute("studentExtras", studentExtras);
		req.setAttribute("studentAwards", studentAwards);
		req.setAttribute("studentPictures", studentPictures);
		req.setAttribute("studentAudios", studentAudios);
		req.setAttribute("extraCurApprovals", extraCurApprovals);
		req.setAttribute("imgApprovals", imgApprovals);
		req.setAttribute("audioApprovals", audioApprovals);
		req.setAttribute("gpaChoices", GPAChoices);
		req.getRequestDispatcher("/_view/ceremony.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Ceremony Servlet: doPost");
	}
}