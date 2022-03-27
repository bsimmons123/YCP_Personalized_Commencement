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
		<% Student student = (Student) session.getAttribute("student"); %>
	</head>
	
	<!-- Bootstrap js script imports for carousel -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	 
	<!-- body layout and styling -->
	<body style="background-color: rgb(0, 128, 0);">
		<audio autoplay src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.nameSound}"></audio>
		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="width: 95%; height: 70%; margin-top: 100px; margin-left: 2.5%; margin-right: 2.5%; margin-bottom: 30px; border: 3px solid black;">
		  		<!-- Slide controls for testing pt.1 (uncomment to use them) -->
		  <!--
		  <ol class="carousel-indicators">
		    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
		    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		  </ol>
		  -->
		  <div class="carousel-inner">
		  	<!-- First Carousel Slide -->
		    <div class="carousel-item active">
		    
	    	  		<!-- Need an if statement to tell if user has a picture submitted, if they don't: use a default YCP image -->
	    	  <!-- 
	    	  <c:if test="empty ${student.picture}">
	    	    <img class="d-block w-100" style="width:950px;height:620px;" src="${pageContext.request.contextPath}/files/carouselTests/York.png">
	    	  </c:if>
	    	  -->
	    	  
	    	  <!-- Image shown on slide -->
		      <img class="d-block w-100" style="width:100%;height:550px;" src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.picture}">
		      
		      <!-- Student name majors and minors div -->
		      <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0, 128, 0, .7);">
		      	<!-- name -->
			    <h3 style="border-bottom: 2px solid white; width: 500px; margin-left: 26%;">${student.first} ${student.last}</h3>
			    <!-- Majors and minors -->
			    <p>Majors: ${student.major}<br>Minors: ${student.minor}</p>
			  </div>
		    </div>
		    <!-- Second Carousel Slide -->
		    <div class="carousel-item">
		      <!-- YCP image shown for second slide -->
		      <img class="d-block w-100" style="width:100%;height:550px;" src="${pageContext.request.contextPath}/files/carouselTests/York.png">
		      <!-- Student extracurricular div -->
		      <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0, 128, 0, .7);">
			    <!-- Sports clubs activities -->
			    <h3 style="border-bottom: 2px solid white; width: 500px; margin-left: 26%;">Sports, Clubs, and Organizations</h3>
			    <!-- student extracurricular info -->
			    <p><br>${student.extraCur}<br></p>
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