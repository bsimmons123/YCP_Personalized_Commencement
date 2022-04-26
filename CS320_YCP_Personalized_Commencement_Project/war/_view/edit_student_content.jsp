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
		<%@ include file="header.jsp" %>
	</head>

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
			<p>
			Fill out the information in the text boxes and upload files to be displayed during the commencement ceremony.
			If there is nothing to add to any of the optional categories, simply leave them blank.
			When the form is completed, hit the save button.  If changes need to be made, click the edit button to do so.<br><br>
			File types for images can be .JPEG, .JPG, or .PNG files, and file types for videos can be .MP4 or .MOV files.<br>
			</p>

      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">Sports, Clubs, or Organizations:</span>
        </div>
        <input type="text" class="form-control" maxlength="39" name="sportsclubsactivities" aria-label="Amount (to the nearest dollar)" value="${student.extraCur}">
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
			<input class="btn btn-secondary" style="margin-left: 0%;" type="button" onclick="window.location='http://localhost:8081/pcomm/student_index'" value="Cancel">
			<input class="btn btn-danger" style="margin-left: 31.5%; margin-right: 34.4%;" type="button" onclick="window.location='http://localhost:8081/pcomm/reset.do'" name="login" value="Reset">
			<input class="btn btn-primary" style="margin-right: 0%; ;" type="Submit" name="login" value="Save">
		</div>
    </form>

	</body>
</html>
