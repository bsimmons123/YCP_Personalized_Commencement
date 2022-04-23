package edu.ycp.cs320.personalized_commencement.servlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import edu.ycp.cs320.personalized_commencement.controller.ServletsController;
import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.persist.GenerateQRCode;

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
		String data;
    	String charset = "UTF-8";  
    	String path;
    	Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

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
	        	for(FileItem item : multiparts) {
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
	                    } else {
	                    	// don't output a "no files selected" message as it can be assumed and sometimes outputs even though one type of file was uploaded
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
            } finally {
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
            	
            	// path where the students QR codes get stored
            	path = "war/QRCodes/" + student.getFirst() + student.getLast() + student.getStudentId() + "QR.png"; 
            	
        		// for scanning on mobile replace this IP address with the address of the PC running the project, we'll use this
        		// method for testing until it is polished and ready to go live.
            	data = Integer.toString(student.getStudentId());
            	
            	try {
            		// generates QR code with Low level(L) error correction capability  
    				GenerateQRCode.generateQRcode(data, path, charset, hashMap, 100, 100);
    			} catch (WriterException e) {
    				// catch block
    				e.printStackTrace();
    			}
            }
        }
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
    }
}
