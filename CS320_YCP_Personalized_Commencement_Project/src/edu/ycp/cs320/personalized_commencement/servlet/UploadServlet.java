package edu.ycp.cs320.personalized_commencement.servlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.jasper.Constants;

@WebServlet(urlPatterns = "/upload.do") // both used for uploading files
//@MultipartConfig
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "UserImages";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = req.getServletContext().getRealPath("");
        System.out.println(appPath + File.separator + UPLOAD_DIRECTORY);
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
         
        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
        }
        req.setAttribute("message", "Upload has been done successfully!");
//		 Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
	}
	
	private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	    }
	    return "";
	}
//		if(ServletFileUpload.isMultipartContent(req)){
//            try {
//               String fname = null;
//               String fsize = null;
//               String ftype = null;
//                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
//                for(FileItem item : multiparts){
//                    if(!item.isFormField()){
//                        fname = new File(item.getName()).getName();
//                        fsize = new Long(item.getSize()).toString();
//                        ftype = item.getContentType();
//                        item.write( new File(UPLOAD_DIRECTORY + File.separator + fname));
//                        System.out.println("\tFile Uploaded Successfully");
//                        req.setAttribute("message", "File Uploaded Successfully");
//                        req.setAttribute("name", fname);
//                        req.setAttribute("size", fsize + "Bytes");
//                    }
//                }
//               //File uploaded successfully
//            } catch (Exception ex) {
//            	System.out.println("\tFile Upload Failed due to " + ex);
//               req.setAttribute("errorMessage", "Error Uploading File");
//            }          
//         
//        }else{
//            req.setAttribute("errorMessage","Sorry this Servlet only handles file upload request");
//        }
//    
//		// Forward to view to render the result HTML document
//		req.getRequestDispatcher("/_view/student_index.jsp").forward(req, resp);
//     
//    }     
   }