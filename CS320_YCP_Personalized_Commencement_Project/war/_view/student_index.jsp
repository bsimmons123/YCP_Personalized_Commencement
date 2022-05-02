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
		<%-- import for AJAX --%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>

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
	<div id="instructions">

				<!-- Welcome message -->
				<c:if test="${! empty student }">
					<div class="alert alert-info" role="alert" style="color: black; text-align: center;">
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
						}, 4000); // 4000 is time that after 4 second stop the confetti ( 4000 = 4 sec)
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
					<div class="custom-control custom-switch">
					  <input type="checkbox" onclick="callJqueryAjax()" class="custom-control-input" id="customSwitch1">
					  <label class="custom-control-label" for="customSwitch1">Toggle this switch element</label>
					</div>

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
							    <strong>Image to display (optional):</strong>
							    <br>
							    <br>
							    <img style="display:block; margin-left:auto; margin-right:auto; width:60%;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.picture}" class="rounded img-fluid" >
							  </p>
						  </c:if>
						  <c:if test="${student.checkImg == 0}">
							  <span class="badge badge-secondary">Under Review</span>
							  <p class="lead">
							    <strong>Image to display (optional):</strong>
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
							    <strong>Image to display (optional):</strong>
								<br>  None Uploaded
							  </p>
						  </c:if>
						  <c:if test="${student.checkImg == 0}">
							  <span class="badge badge-secondary">Under Review</span>
								<p class="lead">
							    <strong>Image to display (optional):</strong>
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
							    <strong>Custom Audio for (optional):</strong>
							    <br><br>
							    <audio controls style="display:block;width:60%;margin-left:auto;margin-right:auto;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.nameSound}"></audio>
							    <br><br>
								<div style="margin-left:auto; margin-right:auto; width:100%;">
									<h5 style="text-align: center; margin: auto; color: green;">QR For Ceremony</h5>
									<img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="border: 3px solid green; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
								</div>
								</p>
						  </c:if>
						  <c:if test="${student.checkAudio == 0}">
							  <span class="badge badge-secondary" style="margin-top: 5%;">Under Review</span>
							  <p class="lead">
							    <strong>Custom Audio for (optional):</strong>
							    <br><br>
							    <audio controls style="display:block;width:60%;margin-left:auto;margin-right:auto;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.nameSound}"></audio>
							    <br><br>
								<div style="margin-left:auto; margin-right:auto; width:100%;">
									<h5 style="text-align: center; margin: auto; color: green;">QR For Ceremony</h5>
									<img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="border: 3px solid green; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
								</div>
								</p>
						  </c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${student.checkAudio == 1}">
							  <span class="badge badge-success">Approved!</span>
							  <p class="lead">
							    <strong>Custom Audio for (optional):</strong>
								<br>  None Uploaded<br><br>
								<div style="margin-left:auto; margin-right:auto; width:100%;">
									<h5 style="text-align: center; margin: auto; color: green;">QR For Ceremony</h5>
									<img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="border: 3px solid green; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
								</div>
							  </p>
						  </c:if>
						  <c:if test="${student.checkAudio == 0}">
							  <span class="badge badge-secondary">Under Review</span>
								<p class="lead">
							    <strong>Custom Audio (optional):</strong>
							    <br>  None Uploaded<br><br>
								<div style="margin-left:auto; margin-right:auto; width:100%;">
									<h5 style="text-align: center; margin: auto; color: green;">QR For Ceremony</h5>
									<img src="${pageContext.servletContext.contextPath}/QRCodes/${student.first}${student.last}${student.studentId}QR.png" style="border: 3px solid green; display:block; margin-left:auto; margin-right:auto; width:40%; height: 40%;">
								</div>
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
			<input name="studentid" id="studentid" type="hidden" value="${student.studentId}" />


	<script type="text/javascript">
			function callJqueryAjax(){
				console.log('Sending selection');
				var gpa = $('#customSwitch1').val();
				var id = $('#studentid').val();
				console.log(id);
				console.log(gpa);
				$.ajax({
					url     : 'GPAAjaxServlet',
					method     : 'POST',
					data     : {studentid: id, gpa: gpa},
					success    : function(resultText){
					$('#result').html(resultText);
					},
					error : function(jqXHR, exception){
						console.log('Error occured!!');
					}
				});
			}
		</script>


	<script type="text/javascript">
	function callAjax() {
		httpRequest = new XMLHttpRequest();

		if (!httpRequest) {
		console.log('Unable to create XMLHTTP instance');
		return false;
		}
		httpRequest.open('POST', 'GPAAjaxServlet');
		httpRequest.send();
	}
	</script>

	</body>
</html>
