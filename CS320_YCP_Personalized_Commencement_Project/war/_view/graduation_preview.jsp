<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Graduation Preview</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<%@ include file="header.jsp" %>
		<%@ page import="edu.ycp.cs320.personalized_commencement.model.Student" %>
	</head>
	
	<!-- Bootstrap js script imports for carousel -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	 
	<!-- body layout and styling -->
	<body style="background-color: rgb(0, 128, 0);">
		<audio autoplay src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.nameSound}"></audio>
		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="10000" data-ride="carousel" style="width: 95%; height: 70%; margin-top: 100px; margin-left: 2.5%; margin-right: 2.5%; margin-bottom: 30px; border: 3px solid black;">
		    <div class="carousel-inner">
		  	  <!-- First Carousel Slide -->
		      <div class="carousel-item active">
		    	  <!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  <c:choose>
		    	  	<c:when test="${student.picture == ''}">
		    	  		<img class="d-block w-100" style="width:100%;height:550px;" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		    	  	</c:when>
		    	  	<c:otherwise>
		    	  		<img class="d-block w-100" style="width:100%;height:550px;" src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.picture}">
		    	  	</c:otherwise>
		    	  </c:choose>
			          
			      <!-- Student name majors and minors div -->
			      <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0, 128, 0, .7);">
			      	<!-- name -->
				    <h3 style="border-bottom: 2px solid white; width: 500px; margin-left: 26%;">${student.first} ${student.last}</h3>
				    <!-- Majors and minors -->
				    <p>
				    	<strong>Majors</strong>: ${student.major}<br>
				    	<strong>Minors</strong>: ${student.minor}<br>
				    	<strong>GPA</strong>: ${student.GPA}<br>
			    	</p>
				  </div>
		      </div>
		      <!-- Second Carousel Slide -->
		      <div class="carousel-item">
		        <!-- YCP image shown for second slide -->
		        <img class="d-block w-100" style="width:100%;height:550px;" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		        <!-- Student extracurricular div -->
		        <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0, 128, 0, .7);">
				    <!-- Sports clubs activities -->
				    <h3 style="border-bottom: 2px solid white; width: 500px; margin-left: 26%;">${student.first} ${student.last}</h3>
				    <!-- student extracurricular info -->
				    <p>
				    	<strong>Awards</strong>: ${student.award}<br>
				    	<strong>Extracurricular Activities</strong>: ${student.extraCur}<br>
				    </p>
			    </div>
		      </div>
		    </div>
		  		<!-- Slide controls for testing pt.2 (uncomment to use them) -->
		  
		    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		      <span class="carousel-control-next-icon" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a>
		  </div>
		
		  <div id="buttons" style="padding-left:93.5%; margin-top::0px; padding-bottom: 20px;display: inline; width: auto;">
			<input class="btn btn-secondary" type="button" onclick="window.location='http://localhost:8081/pcomm/student_index'" value="Back">
		  </div>
	</body>
</html>