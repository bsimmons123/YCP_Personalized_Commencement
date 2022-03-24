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
	private final String UPLOAD_DIRECTORY = "war/img";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// create controllers for info and student

		if(ServletFileUpload.isMultipartContent(req)){

			// field names for file uploads
        	String fname = null;
        	String fsize = null;
        	String ftype = null;

        	// field names for form params
        	String firstName = null;
        	String middleInitial = null;
        	String lastName = null;
        	String major = null;
        	String minor = null;
        	String extraCur = null;
        	String img = null;
        	String audio = null;
        	
        	HttpSession session = req.getSession(false);
        	
        	Student student = new Student();
        	
        	student = (Student) session.getAttribute("sinfo");
//        	
//        	String first = req.getParameter("firstname");
//        	
//        	System.out.println(first);
//        	
//        	System.out.println(student.getEmail() + "First Name: " + student.getFirst());
        	
        	// try-catch for file upload
        	try {
        		// retrieves all form fields
        		List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
//        		System.out.println(multiparts.size());
//        		System.out.println(multiparts.get(0));
//        		System.out.println(multiparts.get(1));
//        		System.out.println(multiparts.get(2));
//        		System.out.println(multiparts.get(3));
//        		System.out.println(multiparts.get(4));
//        		System.out.println(multiparts.get(5));
//        		System.out.println(multiparts.get(6));
        		
        		// iterates through all form fields
        	for(FileItem item : multiparts){
        		// checks if the item is a field param and not a file
                if(!item.isFormField()){
                	// stores the field name of the file
                	String field = item.getFieldName();

                	// retrieves the file name and file to send to jsp
                    fname = new File(item.getName()).getName();
                    fsize = new Long(item.getSize()).toString();
                    ftype = item.getContentType();
                    System.out.println(fsize);
                    if(!fsize.equals("0")) {
                    // writes file to project folder
                    item.write( new File(UPLOAD_DIRECTORY + File.separator + fname));

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
            	req.setAttribute("errorMessage", "File Upload Failed due to " + ex);
            }finally {
            	
            	System.out.println(img + " | " + audio);
            	if(updateStudent(student.getEmail(), student.getAdvisorId(), 
            			student.getEmail(), student.getPassword(), firstName, 
            			lastName, major, minor, extraCur, img, audio)) {
            		student = getStudent(student.getEmail(), student.getPassword());
            		session.setAttribute("sinfo", student);
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
