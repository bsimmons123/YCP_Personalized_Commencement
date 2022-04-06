package edu.ycp.cs320.personalized_commencement.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;

@WebServlet(urlPatterns = "/qrcode.scan")
public class QRCodeScan extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("QRCode Scanned");
		
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
		ServletsController controller = new ServletsController();
		String student_id = req.getParameter("studentId");
		
		Student student = controller.getStudentById(Integer.parseInt(student_id));
		
		System.out.println("QR scanned for: " + student.getEmail());
		
		out.write(student.getFirst() + ": " + student.getEmail());
        out.write("<br>");
    }
}
