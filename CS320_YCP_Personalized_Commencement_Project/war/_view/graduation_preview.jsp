<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US" style="height: 99%;">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Graduation Preview</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
	
		<%@ include file="header.jsp" %>
		<!-- imports student model -->
		<%@page import="edu.ycp.cs320.personalized_commencement.model.Student"%>
		<!-- sets session's student model -->
		<% Student student = (Student) session.getAttribute("student"); %>
	</head>
	
	<!-- Bootstrap js script imports for carousel -->
	<!-- Script below limits the students audio to 10 seconds -->
	<script type="text/javascript">
	  document.addEventListener("DOMContentLoaded", function() {
	  	setTime();
	  });
	  function setTime() {
	      var audio= document.getElementById("audio");
	      audio.currentTime=0;
	      audio.play();
	      console.log(audio.currentTime);
	      setInterval(function(){
	        if(audio.currentTime>10){
	          
	          audio.pause();
	        }
	      },1000);
	  }
  </script>
	 
	<!-- body layout and styling -->
	<body style="background-color: rgb(0, 128, 0); height: 90%">
		<c:choose>
    	  	<c:when test="${(student.nameSound == '') || (student.checkAudio == 0)}">
    	  		<audio autoplay id="audio" src="${pageContext.servletContext.contextPath}/files/"></audio>
    	  	</c:when>
    	  	<c:otherwise>
    	  		<audio autoplay id="audio" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.nameSound}"></audio>
    	  	</c:otherwise>
    	</c:choose>
		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="10000" data-ride="carousel" style="width: 40%; height: 90%; margin-top: 5%;margin-left: auto; margin-right:auto;border: 5px solid gray;">
		    <div class="carousel-inner" style="height: 100%; width: 100%;">
		  	  <!-- First Carousel Slide -->
		      <div class="carousel-item active" style="height: inherit;">
		    	  <!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  <c:choose>
		    	  	<c:when test="${(student.picture == '') || (student.checkImg == 0)}">
		    	  		<img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		    	  	</c:when>
		    	  	<c:otherwise>
		    	  		<img class="d-block w-100" style="width:100%; height: inherit;" src="${pageContext.servletContext.contextPath}/files/${student.first}${student.last}/${student.picture}">
		    	  	</c:otherwise>
		    	  </c:choose>
			          
			      <!-- Student name majors and minors div -->
			      <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0, 90, 0, .7); width: 60%; height: auto; margin-left: auto; margin-right: auto;">
			      	<!-- name -->
				    <h5 style="border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;">${student.first} ${student.last}</h5>
				    <!-- Majors and minors -->
				    <p style="text-align: left; margin-left: 5%; font-size: 14px;">
				    	<strong>Majors</strong>: ${student.major}<br>
				    	<strong>Minors</strong>: ${student.minor}<br>
				    	<strong>GPA</strong>: ${student.GPA}<br>
				    	<strong>Awards</strong>: ${student.award}<br>
				    	<c:choose>
				    	  	<c:when test="${(student.extraCur == '') || (student.checkExtCur == 0)}">
				    	  		<strong>Extracurricular Activities</strong>: None<br>
				    	  	</c:when>
				    	  	<c:otherwise>
				    	  		<strong>Extracurricular Activities</strong>: ${student.extraCur}<br>
				    	  	</c:otherwise>
				    	</c:choose>
			    	</p>
				  </div>
		      </div>
		    </div>
	    </div>
		
		  <div id="buttons" style="padding-left:93.5%; margin-top::0px; padding-bottom: 20px;display: inline; width: auto;">
			<input class="btn btn-secondary" type="button" onclick="window.location='http://localhost:8081/pcomm/student_index'" value="Back">
		  </div>
	</body>
</html>