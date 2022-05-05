package edu.ycp.cs320.personalized_commencement.ajaxservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.servlet.LoginServlet;

@WebServlet("/searchautofill.do")
public class StudentSearchAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentSearchAjaxServlet() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Search Ajax Servlet: doPost");
		
		ServletsController controller = new ServletsController();
		
		String searchQuery = req.getParameter("key");
		
		System.out.println(searchQuery);
		
		ArrayList<Student> students = controller.getAllStudents();
		
		for(Student stu : students) {
			if(stu.getFirst().contains(searchQuery)
					|| stu.getLast().contains(searchQuery) 
					|| stu.getEmail().contains(searchQuery)) {
				resp.getWriter().print("<tr>");
				resp.getWriter().print("<td>" + stu.getFirst() + "</td>");
				resp.getWriter().print("<td>" + stu.getLast() + "</td>");
				resp.getWriter().print("</tr>");
				System.out.println(stu.getFirst() + " " + stu.getLast());
			}
		}
		System.out.println("-----------------------------");
		
//        resp.getWriter().print("Hello "+ searchQuery + "!!");
//        resp.getWriter().print("Hello "+ searchQuery + "!!");

    }

}
