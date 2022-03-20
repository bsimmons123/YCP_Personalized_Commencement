<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Student Index View</title>
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/StudentInSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">		
		<%@ include file="header.jsp" %>
	</head>

	<!-- styling and layout of the body  -->
	<body>
		<!-- div for the title of the page -->
		<div id="pageheader"> 
			<h1 class="title">Personalized Senior Commencement Form</h1>
		</div>
		<form action="${pageContext.servletContext.contextPath}/upload.do" method="post" enctype="multipart/form-data">
		<div id="instructions">
		<c:if test="${! empty student }">
			<div id="Welcome">
				<p> Welcome, ${student.email}! I hope you enjoy your stay XD</p>
			</div>
		</c:if>
		<c:if test="${! empty message}">
			<div id="uploadSM">${message}: 
				<div id="uploadS">FileName: ${img}<br>FileSize: ${imgSize}</div>
				<div id="uploadS">FileName: ${audio}<br>FileSize: ${audioSize}</div>
			</div>
		</c:if>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
			<p>
			Fill out the information in the text boxes and upload files to be displayed during the commencement ceremony.  
			If there is nothing to add to any of the optional categories, simply leave them blank.  
			When the form is completed, hit the save button.  If changes need to be made, click the edit button to do so.<br><br>
			File types for images can be .JPEG, .JPG, or .PNG files, and file types for videos can be .MP4 or .MOV files.<br>
			</p>
			<div class="jumbotron">
			  <h1 class="display-4">
			    ${sinfo.first} ${sinfo.last}
			  </h1>
			  <p class="lead">
			    <strong>Major(s):</strong>
			    ${sinfo.major}
			  </p>
			
			  <p class="lead">
			    <strong>Minor(s):</strong>
			    ${sinfo.minor}
			  </p>
			
			  <p class="lead">
			    <strong>Sports, Clubs, or Organizations (optional):</strong>
			    ${sinfo.extraCur}
			  </p>
			  
			  <p class="lead">
			    <strong>Image/Video to display (optional):</strong>
			    image
			  </p>
			  
			  <p class="lead">
			    <strong>Custom Audio for Commencement (optional):</strong>
			    audio
			  </p>
			  <hr class="my-4">
			</div>
		
		<!--Button for adding new content-->
		<div id="buttons">
			<input class="btn btn-secondary" type="button" href="login.jsp" value="Edit Content">
		</div>
		
		<div id="logoutDiv">
			<input class="btn btn-danger" type="button" onclick="window.location='http://localhost:8081/pcomm/login'" value="Logout">
		</div>
		
		<input name="studentModel" type="hidden" value="${student}" />
		</form>
	</body>
</html>