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
		
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
		
		
		<%@ include file="header.jsp" %>
	</head>

	<script type="text/javascript">
	$(function () {
        $('#backButtonModal').appendTo('body');
    });
	</script>

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
				<div class="alert alert-info" role="alert" style="text-align: center; color: black;">
					<p style="text-align: center;">
						Please use the check boxes to approve the following student's information. <br>
						  If you disapprove of any of the information, leave the check box for it blank and add a comment regarding why it was denied.  When you are finished, click the submit button to confirm your decision.
					</p>
				</div>
				
				
				<!-- Card holding student info -->
				<div class="jumbotron">
					<!-- Student name -->
					<h1 class="display-4">
						${studentInfo.first} ${studentInfo.last}
					</h1>
					<!-- Student extracurricular activities -->
					<p class="lead">
						<strong>Sports, Clubs, or Organizations (optional):</strong>
						<input type="checkbox" name="extracur_approval">
						<br>${studentInfo.extraCur}
					</p>
					<c:choose> 
						<c:when test="${studentInfo.picture != ''}">
							<p class="lead">
								<strong>Image to display (optional):</strong>
								<input type="checkbox" name="image_approval">
								<img style="margin-left: auto; margin-right: auto; width: 50%; display: block;"src="${pageContext.servletContext.contextPath}/files/${studentInfo.first}${studentInfo.last}/${studentInfo.picture}" class="rounded img-fluid" >
							</p>
						</c:when>
						<c:otherwise>
						  	<p class="lead">
								<strong>Image to display (optional):</strong>
								<input type="checkbox" name="image_approval">
								<br>  No Image Uploaded
							</p>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${studentInfo.nameSound != ''}">
							<p class="lead">
								<strong>Custom Audio (optional):</strong>
								<input type="checkbox" name="audio_approval">
								<audio controls src="${pageContext.servletContext.contextPath}/files/${studentInfo.first}${studentInfo.last}/${studentInfo.nameSound}"></audio>
							</p>
						</c:when>
						<c:otherwise>
						  <p class="lead">
								<strong>Custom Audio (optional):</strong>
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
					  <textarea class="form-control" maxlength="495" name="submissioncomment" aria-label="With textarea"></textarea>
					</div>
					
					
					
					<!-- Submit button and Back Button -->
					<div id="buttons" style="width: 100%; position: relative;">
						<button type="button" style="left: 0%; position: absolute" class="btn btn-outline-secondary" data-toggle="modal" data-target="#backButtonModal">
							Back <span class="sr-only">(current)</span>
						</button>
						<!-- The Modal -->
						<div class="modal" id="backButtonModal">
			  				<div class="modal-dialog">
			    				<div class="modal-content">
							      <!-- Modal Header -->
							      <div class="modal-header">
							        <h4 class="modal-title">WARNING</h4>
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							      </div>
			
							      <!-- Modal body -->
							      <div class="modal-body">
							        Are you sure that you go back?
							      </div>
			
							      <!-- Modal footer -->
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							        <button type="button" onclick="window.location='${pageContext.request.contextPath}/advisor_index'" class="btn btn-danger" data-dismiss="modal">Back</button>
							      </div>
			   					</div>
			  				</div>
						</div>
						<input class="btn btn-outline-success" style="position: absolute; right: 0%;" type="Submit" name="Status" value="Submit">
					</div>
				</div>
				<!-- history of comments -->
				<ul class="list-group float-right" style="margin-top: -2%; margin-bottom: 10%; width: 100%; word-wrap: break-word; z-index: 1000;">
					<li class="list-group-item list-group-item-success" style="background-color: rgb(0,128,0);"><h5 style="color: white; width: 30%; margin: auto; border-bottom: 1px solid white;">Past Comments</h5></li>
				<c:forEach var="comment" items="${comments}">
					<li class="list-group-item" style="background-color: rgb(233,236,239); text-align: left;">${comment}</li>
				</c:forEach>
				</ul>
			</div>
		</form>
	</body>
</html>
