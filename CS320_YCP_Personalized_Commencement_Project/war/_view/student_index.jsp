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
		<!-- Imports navbar -->
		<%@ include file="header.jsp" %>
		<!-- imports student model -->
		<%@page import="edu.ycp.cs320.personalized_commencement.model.Student"%>
		<!-- sets session's student model -->
		<% Student student = (Student) session.getAttribute("student"); %>
	</head>
	
	<script src="${pageContext.servletContext.contextPath}/javascript-files/confetti.js"></script>

	<!-- styling and layout of the body  -->
	<body>
	
		<!-- redirects to login page if -->
		<c:if test="${empty student }">
			<% response.sendRedirect(request.getContextPath() + "/_view/login.jsp"); %>
		</c:if>
		<!-- div for the title of the page -->
		<div id="pageheader"  style="padding-top: 100px;">
			<h1 class="title">Student Profile For Personalized Commencement</h1>
		</div>
		<form action="${pageContext.servletContext.contextPath}/upload.do" method="post" enctype="multipart/form-data">
			<div id="instructions">

				<!-- Welcome message -->
				<c:if test="${! empty student }">
					<div class="alert alert-info" role="alert" style="text-align: center;">
						Welcome to your student portal, ${student.first}!
						<p style="font-size: 13px; text-align: center;">
							This is your student profile.  The content that you can customize consists of your Sports, Clubs, or Organizations, your background image, 
							and the audio that will play.
							To add or change your content, click the "edit content" button.  To preview what your content will look like during the ceremony, 
							click the "preview content" button.
							When it is your turn to walk the stage at the ceremony, scan the QR code below in order to display your content on the screens that are running the ceremony.
						</p>
					</div>
				</c:if>
				
				

				<!-- Outputs approval status message depending on if the student has been approved or not -->
				<%
				if (student.getApproval() == 1) {
				%>
					<div class="alert alert-success" role="alert" style="text-align: center;">
						Your content has been approved for the commencement ceremony!
					</div>
					<script>
					// for starting the confetti 
					const start = () => {
						setTimeout(function() {
							confetti.start()
						}, 1000); // 1000 is time that after 1 second start the confetti ( 1000 = 1 sec)
					};

					// for stopping the confetti 
					const stop = () => {
						setTimeout(function() {
							confetti.stop()
						}, 5000); // 5000 is time that after 5 second stop the confetti ( 5000 = 5 sec)
					};

					// after this here we are calling both the function so it works
					start();
					stop();
					</script>
				<%
				} else{
				%>
					<div class="alert alert-danger" role="alert" style="text-align: center;">
						Your content has not yet been approved for the commencement ceremony.
					</div>
				<%
				}
				%>
				
				<!-- Outputs success message if files were uploaded -->
				<c:if test="${! empty message}">
					<div class="alert alert-success" role="alert" style="text-align: center;">
						${message}
					</div>
				</c:if>

				<!-- Outputs failure message if files were already uploaded or no files were uploaded -->
				<c:if test="${! empty errorMessage}">
					<div class="alert alert-warning" role="alert" style="text-align: center;">
						${errorMessage}
					</div>
				</c:if>

				

				<!-- Card for all of the student's information -->
				<div class="jumbotron" style="margin-bottom:2.5%; padding-top: 2%;padding-bottom: 2%;">
				  <h1 class="display-4">
				    ${student.first} ${student.last}
				  </h1>
				  <p class="lead">
				    <strong>Major(s):</strong>
				    ${student.major}
				  </p>
				  <p class="lead">
				    <strong>Minors:</strong>
				    ${student.minor}
				  </p>
				  <p class="lead">
				    <strong>GPA:</strong>
				    ${student.GPA}
				  </p>
				  <p class="lead">
				    <strong>Awards:</strong>
				    ${student.award}
				  </p>
					<c:choose>
						<c:when test="${student.extraCur != ''}">
							<c:if test="${student.checkExtCur == 1}">
							  <span class="badge badge-success">Approved!</span>
							  <p class="lead">
							    <strong>Sports, Clubs, or Organizations (optional):</strong>
							    <br>
							    ${student.extraCur}
							  </p>
						  </c:if>
						  <c:if test="${student.checkExtCur == 0}">
							  <span class="badge badge-secondary">Under Review</span>
							  <p class="lead">
							    <strong>Sports, Clubs, or Organizations (optional):</strong>
							    <br>
							    ${student.extraCur}
							  </p>
						  </c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${student.checkExtCur == 1}">
							  <span class="badge badge-success">Approved!</span>
							  <p class="lead">
							    <strong>Sports, Clubs, or Organizations (optional):</strong>
							    <br>  None Entered
							  </p>
						  </c:if>
						  <c:if test="${student.checkExtCur == 0}">
							  <span class="badge badge-secondary">Under Review</span>
							  <p class="lead">
							    <strong>Sports, Clubs, or Organizations (optional):</strong>
							    <br>  None Entered
							  </p>
						  </c:if>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${student.picture != ''}">
							<c:if test="${student.checkImg == 1}">
							  <span class="badge badge-success">Approved!</span>
							  <p class="lead">
							    <strong>Image/Video to display (optional):</strong>
							    <br>
							    <br>
							    <img style="display:block; margin-left:auto; margin-right:auto; width:60%;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.picture}" class="rounded img-fluid" >
							  </p>
						  </c:if>
						  <c:if test="${student.checkImg == 0}">
							  <span class="badge badge-secondary">Under Review</span>
							  <p class="lead">
							    <strong>Image/Video to display (optional):</strong>
							    <br>
							    <br>
							    <img style="display:block; margin-left:auto; margin-right:auto; width:60%;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.picture}" class="rounded img-fluid" >
							  </p>
						  </c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${student.checkImg == 1}">
							  <span class="badge badge-success">Approved!</span>
							  <p class="lead">
							    <strong>Image/Video to display (optional):</strong>
								<br>  None Uploaded
							  </p>
						  </c:if>
						  <c:if test="${student.checkImg == 0}">
							  <span class="badge badge-secondary">Under Review</span>
								<p class="lead">
							    <strong>Image/Video to display (optional):</strong>
								<br>  None Uploaded
							  </p>
						  </c:if>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${student.nameSound != ''}">
							<c:if test="${student.checkAudio == 1}">
							  <span class="badge badge-success" style="margin-top: 5%;">Approved!</span>
							  <p class="lead">
							    <strong>Custom Audio for Commencement (optional):</strong>
							    <br><br>
							    <audio controls style="display:block;width:60%;margin-left:auto;margin-right:auto;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.nameSound}"></audio>
							    <br>
							    <img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="pointer-events: none; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
							  </p>
						  </c:if>
						  <c:if test="${student.checkAudio == 0}">
							  <span class="badge badge-secondary" style="margin-top: 5%;">Under Review</span>
							  <p class="lead">
							    <strong>Custom Audio for Commencement (optional):</strong>
							    <br><br>
							    <audio controls style="display:block;width:60%;margin-left:auto;margin-right:auto;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.nameSound}"></audio>
							    <br>
							    <img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="pointer-events: none; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
							  </p>
						  </c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${student.checkAudio == 1}">
							  <span class="badge badge-success">Approved!</span>
							  <p class="lead">
							    <strong>Custom Audio for Commencement (optional):</strong>
								<br>  None Uploaded<br>
							    <img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="pointer-events: none; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
							  </p>
						  </c:if>
						  <c:if test="${student.checkAudio == 0}">
							  <span class="badge badge-secondary">Under Review</span>
								<p class="lead">
							    <strong>Custom Audio for Commencement (optional):</strong>
							    <br>  None Uploaded<br>
							    <img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="pointer-events: none; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
							  </p>
						  </c:if>
						</c:otherwise>
					</c:choose>
				</div>
				
				<!-- Comment section below student's info card -->
				<c:if test="${student.comment != ''}">
					<div class="card" style="margin-bottom:20px;">
					  <div class="card-header">
					    Advisor Comment
					  </div>
					  <div class="card-body">
					    <p class="card-text">${student.comment}</p>
					  </div>
					</div>
				</c:if>
				<!--Buttons for editing content and previewing content -->
				<div id="buttons" style="margin-left:0%; padding:0px; display: inline;">
					<input class="btn btn-info" style="width: 20%; margin-right: 51.2%;" type="button" onclick="window.location='http://localhost:8081/pcomm/_view/edit_student_content.jsp'" value="Edit Content">
					<input class="btn btn-success" style="width: 28%; margin-left:0%;" type="button" onclick="window.location='http://localhost:8081/pcomm/_view/graduation_preview.jsp'" value="Preview Content">
				</div>
			</div>
		</form>
	</body>
</html>
