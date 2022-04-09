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
	</head>
	
	<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
	<%@page import="java.util.Iterator"%>
	
	<!-- Bootstrap js script imports for carousel -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/html5-qrcode" type="text/javascript"/>
	<script src="https://raw.githubusercontent.com/mebjas/html5-qrcode/master/minified/html5-qrcode.min.js"></script>
 
	<!-- body layout and styling -->
	<body style="background-color: rgb(0, 128, 0); height: 88%">
	<form action="${pageContext.servletContext.contextPath}/ceremony" method="post" style="height: 100%">
		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="10000" data-ride="carousel" style="float:left;width: 47.5%; height: 90%; margin-top: 0px;margin-left: 2.5%; margin-right:auto;border-left: 5px solid gray;border-top: 5px solid gray;border-bottom: 5px solid gray;">
		    <div class="carousel-inner" style="height: 100%; width: 100%;">
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
	
	<div id="qr-window" style="width: 40%; margin-left: auto; margin-right: auto;"></div>
	<div id="qr-reader" style="width: 40%; margin-left: auto; margin-right: auto;"></div>
	<div id="qr-reader-results" style="width: 40%; margin-left: auto; margin-right: auto;"></div>
    <script type="text/javascript">
	// This method will trigger user permissions
	Html5Qrcode.getCameras().then(devices => {
		  /**
		   * devices would be an array of objects of type:
		   * { id: "id", label: "label" }
		   */
		  if (devices && devices.length) {
		    var cameraId = devices[0].id;
		    // .. use this to start scanning.
		  }
		}).catch(err => {
		  // handle err
	});
	 
	function onScanSuccess(decodedText, decodedResult) {
		// handle the scanned code as you like, for example:
	 	console.log(`Code matched = ${decodedText}`, decodedResult);
	}
	
	function onScanFailure(error) {
	  	// handle scan failure, usually better to ignore and keep scanning.
		// for example:
		console.warn(`Code scan error = ${error}`);
	}
	 
	let html5QrcodeScanner = new Html5QrcodeScanner(
		"qr-window",
		{ fps: 10, qrbox: {width: 600, height: 100} },
		/* verbose= */ false);
		html5QrcodeScanner.render(onScanSuccess, onScanFailure);
	 
	// This method will trigger user permissions
	Html5Qrcode.getCameras().then(devices => {
	   /**
	    * devices would be an array of objects of type:
	    * { id: "id", label: "label" }
	    */
		if (devices && devices.length) {
		    const cameraId = devices[0].id;
		    // .. use this to start scanning.
		  	// Create instance of the object. The only argument is the "id" of HTML element created above.
		 	const html5QrCode = new Html5Qrcode("qr-window");
				html5QrCode.start(
					cameraId,     // retreived in the previous step.
					{
					  fps: 10,    // sets the framerate to 10 frame per second
					  qrbox: 350  // sets only 250 X 250 region of viewfinder to
					              // scannable, rest shaded.
					},
					qrCodeMessage => {
					  // do something when code is read. For example:
					  console.log(`QR Code detected: ${qrCodeMessage}`);
					},
					errorMessage => {
					  // parse error, ideally ignore it. For example:
					})
				.catch(err => {
					// Start failed, handle it. For example,
					console.log(`Unable to start scanning, error: ${err}`);
				});
			}
		}).catch(err => {
		// handle err
		});
	
	function docReady(fn) {
	     // see if DOM is already available
	     if (document.readyState === "complete" || document.readyState === "interactive") {
	         // call on next available tick
	         setTimeout(fn, 1);
	     } else {
	         document.addEventListener("DOMContentLoaded", fn);
	     }
	 } 
	
	 docReady(function() {
	     var resultContainer = document.getElementById('qr-reader-results');
	     
	     var lastResult, countResults = 0;
	     
	     var html5QrcodeScanner = new Html5QrcodeScanner(
	         "qr-window", { fps: 10, qrbox: 250 });
	     
	     function onScanSuccess(decodedText, decodedResult) {
	         if (decodedText !== lastResult) {
	             ++countResults;
	             lastResult = decodedText;
	             console.log(`Scan result = ${decodedText}`, decodedResult);
	  
	             resultContainer.innerHTML += `<div>[${countResults}] - ${decodedText}</div>`;
	             
	             // Optional: To close the QR code scannign after the result is found
	             html5QrcodeScanner.clear();
	         }
	     }
	     
	     // Optional callback for error, can be ignored.
	     function onScanError(qrCodeError) {
	         // This callback would be called in case of qr code scan error or setup error.
	         // You can avoid this callback completely, as it can be very verbose in nature.
	     }
	     
	     html5QrcodeScanner.render(onScanSuccess, onScanError);
	 });
  	</script>
	</body>
</html>