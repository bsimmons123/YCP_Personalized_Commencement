package edu.ycp.cs320.personalized_commencement.servlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;

@WebServlet(urlPatterns = "/upload.do") // both used for uploading files
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "war/files";
	private ServletsController controller = new ServletsController();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// create controllers for info and student
		System.out.println("\tUpload Servlet: doPost");

		if(ServletFileUpload.isMultipartContent(req)){

			// field names for file uploads
        	String fname = null;
        	String fsize = null;

        	// field names for form params
        	String extraCur = null;
        	String img = null;
        	String audio = null;

        	HttpSession session = req.getSession(false);

        	Student student = new Student();

        	student = (Student) session.getAttribute("student");
        	// try-catch for file upload
        	try {
        		// retrieves all form fields
        		List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
        		// iterates through all form fields
        	for(FileItem item : multiparts){
        		// checks if the item is a field param and not a file
                if(!item.isFormField()){

                	// retrieves the file name and file to send to jsp
                    fname = new File(item.getName()).getName();
                    fsize = new Long(item.getSize()).toString();
                    if(!fsize.equals("0")) {
                    // writes file to project folder
                    item.write( new File(UPLOAD_DIRECTORY + File.separator + student.getFirst() + File.separator + fname));

                    // successfull upload message
                    System.out.println("\tFile: " + fname + " Uploaded Successfully");
                    // checks if the file is image or audio

                    // sets message that files uploaded
                    req.setAttribute("message", "Files Uploaded Successfully!");
                    }else {
                    	req.setAttribute("errorMessage", "No Files Selected");
                    	System.out.println("\tFile not uploaded, File is Null");
                    }
                }
                // retrieves info from form field
            	extraCur = multiparts.get(0).getString();
            	img = new File(multiparts.get(1).getName()).getName();
            	audio = new File(multiparts.get(2).getName()).getName();
            }
               //File uploaded successfully
            } catch (Exception ex) {
            	// files not uploaded
            	System.out.println("\tFile Upload Failed due to " + ex);
            	req.setAttribute("errorMessage", "File already uploaded!");
            }finally {
            	System.out.println("\tImage: " + img + " | Audio: " + audio);
            	// Chekc if user is uploading a file or not
            	if(img.isEmpty() && audio.isEmpty()) {
            		controller.updateStudent(student.getEmail(), extraCur, student.getPicture(), student.getNameSound());
            	}else if(audio.isEmpty()) {
            		controller.updateStudent(student.getEmail(), extraCur, img, student.getNameSound());
            	}else if (img.isEmpty()){
            		controller.updateStudent(student.getEmail(), extraCur, student.getPicture(), student.getNameSound());
            	}else {
            		controller.updateStudent(student.getEmail(), extraCur, img, audio);
            	}
            	if(controller.updateStudentApproval(student.getStudentId(), 0)) {
            		System.out.println("\t\tStudent's approval has been set to false");
            		controller.updateStudentContent(student.getStudentId(), 0, 0, 0);
            		if(!student.getComment().isEmpty())
	            		if(!student.getComment().subSequence(0, 4).equals("Was:"))
	            			controller.updateStudentComment(student.getEmail(), "Was: " + student.getComment());
            		student = controller.getStudent(student.getEmail(), student.getPassword());
            		session.setAttribute("student", student);
            	}
            }
        }
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
    }

}
