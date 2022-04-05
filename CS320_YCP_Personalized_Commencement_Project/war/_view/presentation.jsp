<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Submission - ${studentInfo.last}, ${studentInfo.first}</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- CSS styling that takes the path of the site and imports the respective style sheet -->
		<link href="${pageContext.request.contextPath}/css/PresentationSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<%@ include file="header.jsp" %>
	</head>

	<!-- Body layout and formatting -->
	<body>
		<!-- Redirect advisor if not logged in -->
		<c:if test="${empty advisor }">
			<% response.sendRedirect(request.getContextPath() + "/_view/login.jsp"); %>
		</c:if>
		<!-- Header of the page -->
		<div id="pageheader" style="padding-top: 100px;">
			<h1 class="title">Submission Preview for ${studentInfo.first} ${studentInfo.last}</h1>
		</div>
		<form action="${pageContext.servletContext.contextPath}/presentation" method="post">
			<!-- Instructions for advisor -->
			<div id="instructions">
				<p>
				Please use the check boxes to approve the following student's information.<br>
				If you disapprove of any of the information, leave the check box for it blank.<br>
				When you are finished, simply click the submit button to confirm your decision.
				</p>
				<!-- Card holding student info -->
				<div class="jumbotron">
					<!-- Student name -->
					<h1 class="display-4">
						${studentInfo.first} ${studentInfo.last}
					</h1>
					<!-- Student major -->
					<p class="lead">
						<strong>Major(s):</strong>
						${studentInfo.major}
					</p>
					<!-- Student minor -->
					<p class="lead">
						<strong>Minor(s):</strong>
						${studentInfo.minor}
					</p>
					<!-- Student GPA -->
					<p class="lead">
						<strong>GPA:</strong>
						${studentInfo.GPA}
					</p>
					<!-- Student Awards -->
					<p class="lead">
						<strong>Awards:</strong>
						${studentInfo.award}
					</p>
					<!-- Student extracurricular activities -->
					<p class="lead">
						<strong>Sports, Clubs, or Organizations (optional):</strong>
						<input type="checkbox" name="extracur_approval">
						<br>${studentInfo.extraCur}
					</p>
					<c:choose> 
						<c:when test="${studentInfo.picture != ''}">
							<p class="lead">
								<strong>Image/Video to display (optional):</strong>
								<input type="checkbox" name="image_approval">
								<img style="margin-left: auto; margin-right: auto; width: 50%; display: block;"src="${pageContext.servletContext.contextPath}/files/${studentInfo.first}/${studentInfo.picture}" class="rounded img-fluid" >
							</p>
						</c:when>
						<c:otherwise>
						  	<p class="lead">
								<strong>Image/Video to display (optional):</strong>
								<input type="checkbox" name="image_approval">
								<br>  No Image Uploaded
							</p>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${studentInfo.nameSound != ''}">
							<p class="lead">
								<strong>Custom Audio for Commencement (optional):</strong>
								<input type="checkbox" name="audio_approval">
								<audio controls src="${pageContext.servletContext.contextPath}/files/${studentInfo.first}/${studentInfo.nameSound}"></audio>
							</p>
						</c:when>
						<c:otherwise>
						  <p class="lead">
								<strong>Custom Audio for Commencement (optional):</strong>
								<input type="checkbox" name="audio_approval">
								<br> No Audio Uploaded
							</p>
						</c:otherwise>
					</c:choose>
					<hr class="my-4"> <!-- Horizontal rule to end info -->
					<!-- Advisor comment section -->
					<div class="input-group" style="margin-bottom: 20px;">
					  <div class="input-group-prepend">
					    <span class="input-group-text">Submission Comment</span>
					  </div>
					  <textarea class="form-control" maxlength="500" name="submissioncomment" aria-label="With textarea"></textarea>
					</div>
					<!-- Submit button and Back Button -->
					<div id="buttons" style="margin-left:0%; padding:0px; display: inline;">
						<input class="btn btn-secondary" type="button" onclick="window.location='http://localhost:8081/pcomm/advisor_index'" value="Back">
						<input class="btn btn-success" type="Submit" name="Status" value="Submit">
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
