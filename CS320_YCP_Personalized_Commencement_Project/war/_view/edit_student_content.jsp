<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Student Index View</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/StudentInSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
		<%@ include file="header.jsp" %>
	</head>
	
	<script>
	    $(function () {
	        $('#cancelModal').appendTo('body');
	    });
	    
	    $(function () {
	        $('#resetModal').appendTo('body');
	    });
	    
	    $(function () {
	        $('#saveModal').appendTo('body');
	    });
	</script>
	

	<!-- styling and layout of the body  -->
	<body>
		<!-- div for the title of the page -->
		<div id="pageheader"  style="padding-top: 100px;">
			<h1 class="title">Personalized Senior Commencement Form</h1>
		</div>
		<form action="${pageContext.servletContext.contextPath}/upload.do" method="post" enctype="multipart/form-data">
			<div id="instructions">
				<!-- Redirect student if not logged in -->
				<c:if test="${empty student }">
					<% response.sendRedirect(request.getContextPath() + "/_view/login.jsp"); %>
				</c:if>
			
				<c:if test="${! empty message}">
					<div id="uploadSM">${message}:
						<div id="uploadS">FileName: ${img}<br>FileSize: ${imgSize}</div>
						<div id="uploadS">FileName: ${audio}<br>FileSize: ${audioSize}</div>
					</div>
				</c:if>
	
				<c:if test="${! empty errorMessage}">
				    <div class="alert alert-success" role="alert">${errorMessage}</div>
					<div class="error">${errorMessage}</div>
				</c:if>
			
				<div class="alert alert-info" role="alert" style="color: black; text-align: center;">
						Fill out the information in the text boxes and upload files to be displayed during the commencement ceremony.
						If you do not want to upload specific content, simply leave that field empty.
						When the form is completed, hit the save button.  If you would like to delete any content, click the reset button.
				</div>
				<div class="alert alert-warning" role="alert" style="text-align: center;">
						Supported files extensions: .JPEG, .JPG, .PNG.  Audio: .MP3
				</div>
	
			    <div class="input-group mb-3">
			        <div class="input-group-prepend">
			          <span class="input-group-text">Sports, Clubs, or Organizations:</span>
			        </div>
			        <input type="text" class="form-control" name="sportsclubsactivities" aria-label="Amount (to the nearest dollar)" value="${student.extraCur}">
			        <div class="input-group-append">
			          <span class="input-group-text">Optional</span>
			        </div>
			      </div>
			
			      <div class="input-group mb-3">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="basic-addon1">Image</span>
			        </div>
			        <input type="file" name="imageorvideo" class="form-control" aria-describedby="basic-addon1">
			      </div>
			
			      <div class="input-group mb-3">
			        <div class="input-group-prepend">
			          <span class="input-group-text" id="basic-addon1">Audio</span>
			        </div>
			        <input type="file" name="customaudio" class="form-control" aria-describedby="basic-addon1">
			    </div>
	
				<!--Button for adding new content-->
				<div id="buttons" style="width: 100%; margin: auto; display: inline;">
				<!-- Button to Open the Modal -->
				<button type="button" style="margin-left: 0%;" class="btn btn-outline-secondary" data-toggle="modal" data-target="#cancelModal">
				 Cancel<span class="sr-only">(current)</span>
				</button>
				<!-- The Modal -->
				<div class="modal" id="cancelModal">
				  <div class="modal-dialog">
				    <div class="modal-content">
				
				      <!-- Modal Header -->
				      <div class="modal-header">
				        <h4 class="modal-title">WARNING</h4>
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				      </div>
				
				      <!-- Modal body -->
				      <div class="modal-body">
				        Changes made will not be saved if you leave 
				      </div>
				
				      <!-- Modal footer -->
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Go Back</button>
				        <button type="button" onclick="window.location='http://localhost:8081/pcomm/student_index'" class="btn btn-danger" data-dismiss="modal">Leave</button>
				      </div>
				    </div>
				  </div>
				</div>
			
				<button type="button" style="margin-left: 31.5%; margin-right: 34.4%;" class="btn btn-outline-danger" data-toggle="modal" data-target="#resetModal">
				 Reset<span class="sr-only">(current)</span>
				</button>
				<!-- The Modal -->
				<div class="modal" id="resetModal">
	  				<div class="modal-dialog">
	    				<div class="modal-content">
	
					      <!-- Modal Header -->
					      <div class="modal-header">
					        <h4 class="modal-title">WARNING</h4>
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					      </div>
	
					      <!-- Modal body -->
					      <div class="modal-body">
					        Are you sure that you want to reset your content?
					      </div>
	
					      <!-- Modal footer -->
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Go Back</button>
					        <button type="button" onclick="window.location='http://localhost:8081/pcomm/reset.do'" class="btn btn-danger" data-dismiss="modal">Reset</button>
					      </div>
	   					</div>
	  				</div>
				</div>
				<input type="submit" class="btn btn-outline-primary" data-dismiss="modal" value="Save">
			</div>
		</div>
    	</form>
	</body>
</html>
