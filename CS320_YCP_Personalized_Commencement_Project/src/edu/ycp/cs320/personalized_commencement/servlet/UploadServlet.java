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

@WebServlet(urlPatterns = "/upload.do") // both used for uploading files
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "UserImages";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(req)){
            try {
            	String fname = null;
            	String fsize = null;
            	String ftype = null;
            	List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            	for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        fname = new File(item.getName()).getName();
                        fsize = new Long(item.getSize()).toString();
                        ftype = item.getContentType();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + fname));
                        System.out.println("\tFile: " + fname + " Uploaded Successfully");
                        req.setAttribute("message", "File Uploaded Successfully");
                        req.setAttribute("name", fname);
                        req.setAttribute("size", fsize + "Bytes");
                    }else {
                    	String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        System.out.println(fieldName + ": " + fieldValue);
                    }
                }
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