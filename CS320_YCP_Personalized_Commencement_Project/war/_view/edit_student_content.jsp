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
          <span class="input-group-text" id="basic-addon1">First Name</span>
        </div>
        <input type="text" class="form-control" placeholder="John" name="firstname" aria-describedby="basic-addon1" value="${student.first}">
      </div>

      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon1">Last Name</span>
        </div>
        <input type="text" class="form-control" placeholder="Smith" name="lastname" aria-describedby="basic-addon1" value="${student.last}">
      </div>

      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon1">Major(s)</span>
        </div>
        <input type="text" class="form-control" placeholder="Computer Science, etc." name="majors" aria-describedby="basic-addon1" value="${student.major}">
      </div>

      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon1">Minor(s)</span>
        </div>
        <input type="text" class="form-control" placeholder="Math, etc." name="minors" aria-describedby="basic-addon1" value="${student.minor}">
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
		<div id="buttons">
			<input class="btn btn-primary" type="Submit" name="login" value="Save">
		</div>
    </form>

		<div id="logoutDiv">
			<input class="btn btn-danger" type="button" onclick="window.location='http://localhost:8081/pcomm/login'" value="Logout">
		</div>

	</body>
</html>
