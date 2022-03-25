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

import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.persist.DatabaseProvider;
import edu.ycp.cs320.personalized_commencement.persist.DerbyDatabase;
import edu.ycp.cs320.personalized_commencement.persist.IDatabase;

@WebServlet(urlPatterns = "/upload.do") // both used for uploading files
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "war/files";

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
        	String firstName = null;
        	String lastName = null;
        	String major = null;
        	String minor = null;
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
                    System.out.println("\tFile Size (bits): " + fsize);
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
                firstName = multiparts.get(0).getString();
            	lastName = multiparts.get(1).getString();
            	major = multiparts.get(2).getString();
            	minor = multiparts.get(3).getString();
            	extraCur = multiparts.get(4).getString();
            	img = new File(multiparts.get(5).getName()).getName();
            	audio = new File(multiparts.get(6).getName()).getName();
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
            		if(updateStudent(student.getEmail(), student.getAdvisorId(), 
	            			student.getEmail(), student.getPassword(), firstName, 
	            			lastName, major, minor, extraCur, student.getPicture(), student.getNameSound())) {
	            		student = getStudent(student.getEmail(), student.getPassword());
	            		session.setAttribute("student", student);
	            	}
            	}else if(audio.isEmpty()) {
            		if(updateStudent(student.getEmail(), student.getAdvisorId(), 
	            			student.getEmail(), student.getPassword(), firstName, 
	            			lastName, major, minor, extraCur, img, student.getNameSound())) {
	            		student = getStudent(student.getEmail(), student.getPassword());
	            		session.setAttribute("student", student);
            		}
            	}else if (img.isEmpty()){
            		if(updateStudent(student.getEmail(), student.getAdvisorId(), 
	            			student.getEmail(), student.getPassword(), firstName, 
	            			lastName, major, minor, extraCur, student.getPicture(), student.getNameSound())) {
	            		student = getStudent(student.getEmail(), student.getPassword());
	            		session.setAttribute("student", student);
            		}
            	}else {
            		if(updateStudent(student.getEmail(), student.getAdvisorId(), 
	            			student.getEmail(), student.getPassword(), firstName, 
	            			lastName, major, minor, extraCur, img, audio)) {
	            		student = getStudent(student.getEmail(), student.getPassword());
	            		session.setAttribute("student", student);
            		}
            	}
            }
        }
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
    }
	
	/**
	 * get student account
	 * @param email		students email
	 * @return			students account
	 */
	public Student getStudent(String email, String password) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Student student = db.getStudent(email, password);
		
		// check if anything was returned and output the list
		if (student != null) {
				return student;
			}
		return null;
	}
	
	public Boolean updateStudent(String userEmail, int advisorId, String email, String password, 
			String first, String last, String major, String minor, String extraCur, 
			String picture, String sound) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Boolean update = db.updateStudents(userEmail, first, last, 
				major, minor, extraCur, picture, sound);
		
		return update;
			
	}
		
}
