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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.CountingOutputStream;

import edu.ycp.cs320.personalized_commencement.controller.StudentController;
import edu.ycp.cs320.personalized_commencement.controller.StudentInfoController;
import edu.ycp.cs320_personalized_commencement.model.StudentInfoModel;
import edu.ycp.cs320_personalized_commencement.model.StudentModel;

@WebServlet(urlPatterns = "/upload.do") // both used for uploading files
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "UserImages";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// create controllers for info and student
		StudentInfoController infoController = new StudentInfoController();
		StudentController stuController = new StudentController();
		
		// set info controller to stuController's arraylist of students
		infoController.setStudentArray(stuController.getStudents());
		if(ServletFileUpload.isMultipartContent(req)){
            try {
            	String fname = null;
            	String fsize = null;
            	String ftype = null;
            	List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
//            	System.out.println(multiparts.get(0));
//            	System.out.println(multiparts.get(1));
//            	System.out.println(multiparts.get(2));
//            	System.out.println(multiparts.get(3));
//            	System.out.println(multiparts.get(4));
//            	System.out.println(multiparts.get(5));
//            	System.out.println(multiparts.get(6));
//            	System.out.println(multiparts.get(7));
            	StudentInfoController StuInfo = new StudentInfoController();
            	String firstName = multiparts.get(0).getString();
            	String middleInitial = multiparts.get(1).getString();
            	String lastName = multiparts.get(2).getString();
            	String major = multiparts.get(3).getString();
            	String minor = multiparts.get(4).getString();
            	String extraCur = multiparts.get(5).getString();
            	String img = new File(multiparts.get(6).getName()).getName();
            	String audio = new File(multiparts.get(7).getName()).getName();
            	for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        fname = new File(item.getName()).getName();
                        fsize = new Long(item.getSize()).toString();
                        ftype = item.getContentType();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + fname));
                        System.out.println("\tFile: " + fname + " Uploaded Successfully");
                        req.setAttribute("name", fname);
                        req.setAttribute("size", fsize + "Bytes");
                    }
                }
            	StuInfo.setStudentInfo(firstName, middleInitial, lastName, major, minor, extraCur, img, audio);
               //File uploaded successfully
            } catch (Exception ex) {
            	System.out.println("\tFile Upload Failed due to " + ex);
            	req.setAttribute("errorMessage", "File Upload Failed due to" + ex);
            }          
         
        }else{
            req.setAttribute("errorMessage","Sorry this Servlet only handles file upload request");
        }
    
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
     
    }     
   }