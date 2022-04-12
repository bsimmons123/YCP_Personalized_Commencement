<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US" style="height: 99%;">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<!-- title of the tab -->
		<title>Commencement Ceremony</title>
		<!-- import for the logo in the page's tab -->
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<!-- import for the navbar of each page -->
		<%@ include file="navbar.jsp" %>
		<!-- imports student model -->
		<%@page import="edu.ycp.cs320.personalized_commencement.model.Student"%>
		<%@page import="edu.ycp.cs320.personalized_commencement.controller.ServletsController"%>
	</head>
	
	<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
	<%@page import="java.util.Iterator"%>
	
	<!-- Bootstrap js script imports for carousel -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/html5-qrcode" type="text/javascript"></script>
	
 
	<!-- body layout and styling -->
	<body style="background-color: rgb(0, 128, 0); height: 88%">
	<form action="${pageContext.servletContext.contextPath}/ceremony" method="post" style="height: 100%">
		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="10000" data-ride="carousel" style="float:left;width: 47.5%; height: 90%; margin-top: 0px;margin-left: 2.5%; margin-right:auto;border-left: 5px solid gray;border-top: 5px solid gray;border-bottom: 5px solid gray;">
		    <div class="carousel-inner" style="height: 100%; width: 100%;">
		    <input type="hidden" id="hiddenField"/>
		  	  <!-- First Carousel Slide -->
		      <div class="carousel-item active" style="height: inherit;">
		    	  <!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  <img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		      </div>
		      
		      <!-- First Carousel Slide -->
		      <div class="carousel-item" style="height: inherit;">
		    	  <!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  <img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		      </div>
		      
		      <c:forEach var="student" items="${stuList}">
		        <div class="carousel-item" style="height: inherit;">
		    	  <!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  <c:choose>
		    	  	<c:when test="${student.picture == ''}">
		    	  		<img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		    	  	</c:when>
		    	  	<c:otherwise>
		    	  		<img class="d-block w-100" style="width:100%; height: inherit;" src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.picture}">
		    	  	</c:otherwise>
		    	  </c:choose>		      
			      <!-- Student name majors and minors div -->
			      <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;">
			      	<!-- name -->
				    <h5 style="border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;">${student.first} ${student.last}</h5>
				    <!-- Majors and minors -->
				    <p style="text-align: left; margin-left: 5%; font-size: 14px;">
				    	<strong>Majors</strong>: ${student.major}<br>
				    	<strong>Minors</strong>: ${student.minor}<br>
				    	<strong>GPA</strong>: ${student.GPA}<br>
				    	<strong>Awards</strong>: ${student.award}<br>
				    	<strong>Extracurricular Activities</strong>: ${student.extraCur}<br>
			    	</p>
				  </div>
		      	</div>
		      </c:forEach>
		    </div>
		    
		  		<!-- Slide controls for testing pt.2 (uncomment to use them) -->
		    <!-- <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		      <span class="carousel-control-next-icon" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a>-->
	    </div>

		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="10000" data-ride="carousel" style="float:left;width: 47.5%; height: 90%; margin-top: 0px;margin-left: auto; margin-right:2.5%;border-right: 5px solid gray;border-top: 5px solid gray;border-bottom: 5px solid gray;">
		    <div class="carousel-inner" style="height: 100%; width: 100%;">
		  	  <!-- First Carousel Slide -->
		      <div class="carousel-item active" style="height: inherit;">
		    	  <!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  <img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		      </div>
		  	  
		      <c:forEach var="student" items="${stuList}">
		        <div class="carousel-item" style="height: inherit;">
		        <audio autoplay id="audio" src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.nameSound}"></audio>
		    	  <!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  <c:choose>
		    	  	<c:when test="${student.picture == ''}">
		    	  		<img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/York.jpeg">
		    	  	</c:when>
		    	  	<c:otherwise>
		    	  		<img class="d-block w-100" style="width:100%; height: inherit;" src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.picture}">
		    	  	</c:otherwise>
		    	  </c:choose>
			      			      
			      <!-- Student name majors and minors div -->
			      <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;">
			      	<!-- name -->
				    <h5 style="border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;">${student.first} ${student.last}</h5>
				    <!-- Majors and minors -->
				    <p style="text-align: left; margin-left: 5%; font-size: 14px;">
				    	<strong>Majors</strong>: ${student.major}<br>
				    	<strong>Minors</strong>: ${student.minor}<br>
				    	<strong>GPA</strong>: ${student.GPA}<br>
				    	<strong>Awards</strong>: ${student.award}<br>
				    	<strong>Extracurricular Activities</strong>: ${student.extraCur}<br>
			    	</p>
				  </div>
		      	</div>
		      </c:forEach>
		    </div>
		    
		  		<!-- Slide controls for testing pt.2 (uncomment to use them) -->
		    <!-- <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		      <span class="carousel-control-next-icon" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a> -->
	    </div> 	
	</form>
	
	<div id="qr-reader" style="width: 40%; margin-left: auto; margin-right: auto;"></div>
	<div id="qr-reader-results" style="width: 40%; margin-left: auto; margin-right: auto;"></div>
	
	<!-- Script for the in-browser QR scanner -->
    <script type="text/javascript">
    /**
    * Function that checks if scanner is ready to go and adds event listener 
    */
    function docReady(fn) {
	     // see if DOM is already available
	     if (document.readyState === "complete" || document.readyState === "interactive") {
	         // call on next available tick
	         setTimeout(fn, 1);
	     } else {
	         document.addEventListener("DOMContentLoaded", fn);
	     }
	 } 
    
 	/**
 	* Triggers user permissions (but skips the approval) and activates docReady function if there is a camera.
 	*/
	Html5Qrcode.getCameras().then(devices => {
	   /**
	    * devices would be an array of objects of type:
	    * { id: "id", label: "label" }
	    */
		if (devices && devices.length) {
			
			docReady(function() {
			    
			    let resultContainer = document.getElementById('qr-reader-results');
			    
			    var lastResult = 0;
			    let count = 0;
			    
			    const html5QrcodeScanner = new Html5QrcodeScanner("qr-reader", { fps: 10, qrbox: 250 });
			    
			    function onScanSuccess(decodedText, decodedResult) {
			        if (decodedText !== lastResult) {
			        	// initialize the id
			    		let id;
			    		// let student;
			    		// let controller = new ServletsController();
			    		
			    		// try set the decodedText as the hidden field value, take the id from that elem
			    		try {
			    			// if the decoded text string contains an integer, parse the text to int and assign it to the id variable
			    			id = parseInt(decodedText);
			    			document.getElementById("hiddenField").value = decodedText;
			    			// id = Integer.parseInt(request.getParameter("hiddenField"));
			    			// student = controller.getStudentById(id);
			    		} catch {
			    			// cancel the scan if qr data can't be read
			    			console.log("Couldn't convert QR data to integer.");
			    			return;
			    		}
		
			    		++count;
			            lastResult = decodedText;
			            console.log("Student ID is: " + id);
			 
			            resultContainer.innerHTML += "<div style='color: white; width: 20%; margin-left: auto; margin-right: auto; padding-bottom: 30px; font-size: 18px;'>[#" + count + "] - ID Number " + id + "</div>";
			            
			            // Optional: To close the QR code scanning after the result is found.
			            // Not necessary as we'll want to leave it running so that the next student can scan and so on
			            //html5QrcodeScanner.clear();
			        }
			    }
				// render the qr scan on success
			    html5QrcodeScanner.render(onScanSuccess);
			});
		}
	}).catch(err => {
		// handle error if unable to get camera ID
		console.log(err);
	});
  	</script>
	</body>
</html>