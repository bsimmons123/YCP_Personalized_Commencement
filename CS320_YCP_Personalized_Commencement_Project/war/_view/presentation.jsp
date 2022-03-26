<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Submission - ${student.last}, ${student.first}</title>
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
		
		<form action="${pageContext.servletContext.contextPath}/presentation" method="get">
			<!-- Header of the page -->
			<div id="pageheader">
				<h1 class="title">Submission Preview for ${student.first} ${student.last}</h1>
			</div>

			<!-- Instructions for advisor -->
			<div id="instructions">
				<p>
				Please use the check boxes to approve the following student's information.<br>
				If you disapprove of any of the information, leave the check box for it blank.<br>
				When you are finished, simply click the submit button to confirm your decision.
				</p>
			<div class="jumbotron">
				<h1 class="display-4">
					${studentInfo.first} ${studentInfo.last}
				</h1>
				<p class="lead">
					<strong>Major(s):</strong>
					${studentInfo.major}
					<input type="checkbox" name="major_approval">
				</p>

				<p class="lead">
					<strong>Minor(s):</strong>
					${studentInfo.minor}
					<input type="checkbox" name="minor_approval">
				</p>

				<p class="lead">
					<strong>Sports, Clubs, or Organizations (optional):</strong>
					${student.extraCur}
					<input type="checkbox" name="extracur_approval">
				</p>

				<p class="lead">
					<strong>Image/Video to display (optional):</strong>
			    	<img src="${pageContext.servletContext.contextPath}/files/${studentInfo.first}/${studentInfo.picture}" class="rounded float-right img-fluid" >
					<%-- <img src="${pageContext.servletContext.contextPath}/img/Edge.jpg" alt="User Image" width="460" height="345"> --%>
					<input type="checkbox" name="image_approval">
				</p>

				<p class="lead">
					<strong>Custom Audio for Commencement (optional):</strong>
					<audio controls src="${pageContext.servletContext.contextPath}/Audio/johnny_appleseed.mp3"></audio>
					<input type="checkbox" name="audio_approval">
				</p>
				<hr class="my-4">
			</div>

			<!-- Submit button and Back Button -->
			<div id="buttons" style="margin-left:0%; padding:0px; display: inline;">
				<input class="btn btn-secondary" type="button" onclick="window.location='http://localhost:8081/pcomm/advisor_index'" value="Back">
				<input class="btn btn-success" type="Submit" name="Status" value="Submit">
			</div>
			</div>
		</form>
	</body>
</html>
