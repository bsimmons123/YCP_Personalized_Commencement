package edu.ycp.cs320.personalized_commencement.ajaxservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.servlet.AdvisorIndexServlet;
import edu.ycp.cs320.personalized_commencement.servlet.LoginServlet;

@WebServlet("/GPAAjaxServlet")
public class StudentGPAAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentGPAAjaxServlet() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GPA AJAX Servlet: doPost");
		
		ServletsController controller = new ServletsController();
		
		// get current session
		HttpSession session = req.getSession(false);
		
		// redirect if no session
		if(session == null) {
			LoginServlet loginServlet = new LoginServlet();
			loginServlet.doGet(req, resp);
			return;
		}
		
		try {
		Student student = controller.getStudentById(controller.getInteger(req, "studentid"));
		
		int showGPA = controller.getInteger(req, "gpa");	
		if(student.getShowGPA() != showGPA) { // to null out first click
			controller.updateStudentShowGPA(student.getStudentId() , showGPA);
			if(showGPA == 1){ // if user wants info to be shown
				   System.out.println("Student: " + student.getFirst() + " wants to show their GPA!");
		    }else if(showGPA == 0){ // if user does not want info to be shown
		    	System.out.println("Student: " + student.getFirst() + " DOES NOT want to show their GPA!");
		    }
		} else {
			System.out.println("\tStudent has not made any changes");
		}
		}catch(NumberFormatException e){
			System.out.println(e);
		}
		
	    

    }

}
