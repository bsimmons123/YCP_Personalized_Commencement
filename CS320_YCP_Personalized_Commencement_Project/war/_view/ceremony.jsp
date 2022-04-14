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
		<%@ include file="ceremonynav.jsp" %>
		<!--Importing all the dependent classes-->
		<%@page import="java.util.ArrayList"%>
		<%@page import="java.util.Objects"%>
		<%@page import="java.util.List"%>
		<%@page import="java.util.stream.Collectors"%>
		<!-- bootsrap carousel scripts and html5QRCode script -->
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		<script src="https://unpkg.com/html5-qrcode" type="text/javascript"></script>
	</head>
	
	<!-- 
    Dear everybody,
    
    This scriptlet takes the ArrrayLists that were set as attributes in the servlet, converts each ArrayList to
    a List of type String, then creates an array of Strings that holds the result of converting each List to an array
    of Strings.
    
    It is disgusting and I had a terrible time figuring out how to do it, but I did it.  You're welcome.
    
    Love, Rob
    -->
    <%
    // pull list of all student ID's of each student as Strings from attribute
    ArrayList<String> IDs = (ArrayList<String>) request.getAttribute("studentIDs"); 
    List<String> studentIDs = new ArrayList<>(IDs.size());
    for (Object object : IDs) {
        studentIDs.add(Objects.toString(object, null));
    }
 	// array of student id's as strings that gets passed to the js QR function
    String[] stuIDs = new String[studentIDs.size()];
    for(int i = 0; i < studentIDs.size(); i++) {
    	stuIDs[i] = studentIDs.get(i);
    }
	
    // pulls list of all first names of each student from attribute
    ArrayList<String> fNames = (ArrayList<String>) request.getAttribute("studentFirstNames");
    List<String> studentFNames = new ArrayList<>(fNames.size());
    for (Object object : fNames) {
        studentFNames.add(Objects.toString(object, null));
    }
 	// array of first names as strings that gets passed to the js QR function
    String[] stuFirstNames = new String[studentFNames.size()];
    for(int i = 0; i < studentFNames.size(); i++) {
    	stuFirstNames[i] = studentFNames.get(i);
    }
		    
 	// pulls list of all last names of each student from attribute
    ArrayList<String> lNames = (ArrayList<String>) request.getAttribute("studentLastNames");
    List<String> studentLNames = new ArrayList<>(lNames.size());
    for (Object object : lNames) {
        studentLNames.add(Objects.toString(object, null));
    }
 	// array of last names as strings that gets passed to the js QR function
    String[] stuLastNames = new String[studentLNames.size()];
    for(int i = 0; i < studentLNames.size(); i++) {
    	stuLastNames[i] = studentLNames.get(i);
    }
		    
 	// pulls list of all majors of each student from attribute
    ArrayList<String> majors = (ArrayList<String>) request.getAttribute("studentMajors");
    List<String> studentMajors = new ArrayList<>(majors.size());
    for (Object object : majors) {
        studentMajors.add(Objects.toString(object, null));
    }
 	// array of majors as strings that gets passed to the js QR function
    String[] stuMajors = new String[studentMajors.size()];
    for(int i = 0; i < studentMajors.size(); i++) {
    	stuMajors[i] = studentMajors.get(i);
    }
		    
 	// pulls list of all minors of each student from attribute
    ArrayList<String> minors = (ArrayList<String>) request.getAttribute("studentMinors");
    List<String> studentMinors = new ArrayList<>(minors.size());
    for (Object object : minors) {
        studentMinors.add(Objects.toString(object, null));
    }
 	// array of minors as strings that gets passed to the js QR function
    String[] stuMinors = new String[studentMinors.size()];
    for(int i = 0; i < studentMinors.size(); i++) {
    	stuMinors[i] = studentMinors.get(i);
    }
    
 	// pulls list of all GPAs of each student from attribute
    ArrayList<String> GPAs = (ArrayList<String>) request.getAttribute("studentGPAs");
    List<String> studentGPAs = new ArrayList<>(GPAs.size());
    for (Object object : GPAs) {
        studentGPAs.add(Objects.toString(object, null));
    }
 	// array of GPA's as strings that gets passed to the js QR function
    String[] stuGPAs = new String[studentGPAs.size()];
    for(int i = 0; i < studentGPAs.size(); i++) {
    	stuGPAs[i] = studentGPAs.get(i);
    }
    
 	// pulls list of all extra curriculars of each student from attribute
    ArrayList<String> extras = (ArrayList<String>) request.getAttribute("studentExtras");
    List<String> studentExtras = new ArrayList<>(extras.size());
    for (Object object : extras) {
        studentExtras.add(Objects.toString(object, null));
    }
 	// array of ExtraCur's as strings that gets passed to the js QR function
    String[] stuExtras = new String[studentExtras.size()];
    for(int i = 0; i < studentExtras.size(); i++) {
    	stuExtras[i] = studentExtras.get(i);
    }

 	// pulls list of all awards of each student from attribute
    ArrayList<String> awards = (ArrayList<String>) request.getAttribute("studentAwards");
    List<String> studentAwards = new ArrayList<>(awards.size());
    for (Object object : awards) {
        studentAwards.add(Objects.toString(object, null));
    }
 	// array of awards as strings that gets passed to the js QR function
    String[] stuAwards = new String[studentAwards.size()];
    for(int i = 0; i < studentAwards.size(); i++) {
    	stuAwards[i] = studentAwards.get(i);
    }
    
 	// pulls list of all pictures of each student from attribute
    ArrayList<String> pictures = (ArrayList<String>) request.getAttribute("studentPictures");
    List<String> studentPictures = new ArrayList<>(pictures.size());
    for (Object object : pictures) {
        studentPictures.add(Objects.toString(object, null));
    }
 	// array of image names as strings that gets passed to the js QR function
    String[] stuPictures = new String[studentPictures.size()];
    for(int i = 0; i < studentPictures.size(); i++) {
    	stuPictures[i] = studentPictures.get(i);
    }
		    
 	// pulls list of all audios of each student from attribute
    ArrayList<String> audios = (ArrayList<String>) request.getAttribute("studentAudios");
    List<String> studentAudios = new ArrayList<>(audios.size());
    for (Object object : audios) {
        studentAudios.add(Objects.toString(object, null));
    }
    // array of audio names as strings that gets passed to the js QR function
    String[] stuAudios = new String[studentAudios.size()];
    for(int i = 0; i < studentAudios.size(); i++) {
    	stuAudios[i] = studentAudios.get(i);
    }
    %>
	
	<!-- body layout and styling -->
	<body style="background-color: rgb(0, 128, 0); height: 88%">
	<form action="${pageContext.servletContext.contextPath}/ceremony" method="post" style="height: 100%">
		<div id='audio' style="width: 0px; height: 0px;">
			<audio autoplay id='sound' src=''></audio>
		</div>
		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="10000" data-ride="carousel" style="float:left;width: 47.5%; height: 90%; margin-top: 0px;margin-left: 2.5%; margin-right:auto;border-left: 5px solid gray;border-top: 5px solid gray;border-bottom: 5px solid gray;">
		    <div id="left-inner" class="carousel-inner" style="height: 100%; width: 100%;">
    			<!-- First Carousel Slide -->
    			<div class="carousel-item active" id="left-slide" style="height: inherit;">
		    	  	<!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  	<img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/LiveStreamQR.png">
		      	</div>
		    </div>
	    </div>

		<!-- carousel window size and styling -->
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="10000" data-ride="carousel" style="float:left;width: 47.5%; height: 90%; margin-top: 0px;margin-left: auto; margin-right:2.5%;border-right: 5px solid gray;border-top: 5px solid gray;border-bottom: 5px solid gray;">
		    <div id="right-inner" class="carousel-inner" style="height: 100%; width: 100%;">
		    	<input type="hidden" id="Left-ID"/>
		  	  	<!-- First Carousel Slide -->
		      	<div class="carousel-item active" id="right-slide" style="height: inherit;">
		          	<!-- Image shown on first slide depending on if student submitted an image or not -->
		    	  	<img class="d-block w-100" style="width:100%; height: inherit" src="${pageContext.request.contextPath}/browser-images/LiveStreamQR.png">
		      	</div>
		    </div>
	    </div> 	
	</form>
	
	<div id="qr-reader" style="width: 20%; margin-top: 2px; margin-left: auto; margin-right: auto;"></div>
	
	<!-- Script for the in-browser QR scanner that updates html elements as each scan rolls in -->
    <script type="text/javascript">
    /**
    * Function that checks if scanner is ready to go and adds the event listener 
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
		// devices would be an array of objects of type: { id: "id", label: "label" }
		if (devices && devices.length) {
			docReady(function() {
				// create a const for the QR scanner (this will render each result later on)
				const html5QrcodeScanner = new Html5QrcodeScanner("qr-reader", { fps: 10, qrbox: 205 });
				
				// initalize the carousels and audio div to be used throughout the onSuccess method.
				let rightCarousel = document.getElementById("right-inner");
			    let leftCarousel= document.getElementById("left-inner");
			    let audioDiv = document.getElementById("audio");
			 	
			 	// initialize the lastResult variable and the count variable
			    let lastResult = 0;
			    let count = 0;
			    
				// instantiate the id variables
	  			let idString;
	        	let scannedNow;
	        	let scannedBefore;

			    
			    /**
			    * Method that converts the given array of strings to a readable js array.
			    */
			    <%!public static String getJSArray(String[] items){
			    	 String result = "[";
			         for(int i = 0; i < items.length; i++) {
			             result += "\"" + items[i] + "\"";
			             if(i < items.length - 1) {
			                 result += ", ";
			             }
			         }
			         result += "]";
			         return result;
			       }%>
			  
			    // create constant js list for every property containing each students value of that property
			    const IDs = <%=getJSArray(stuIDs)%>; 
			    const fNames = <%=getJSArray(stuFirstNames)%>;
			    const lNames = <%=getJSArray(stuLastNames)%>; 
			    const majors = <%=getJSArray(stuMajors)%>; 
			    const minors = <%=getJSArray(stuMinors)%>;
			    const GPAs = <%=getJSArray(stuGPAs)%>; 
			    const extras = <%=getJSArray(stuExtras)%>; 
			    const awards = <%=getJSArray(stuAwards)%>; 
			    const pictures = <%=getJSArray(stuPictures)%>; 
			    const audios = <%=getJSArray(stuAudios)%>; 
			    
			    /**
			    * On a successful QR scan, update the slides in the HTML document based off of the student ID read from the scanned QR code.
			    */
			    function onScanSuccess(decodedText, decodedResult) {
					// if the value pulled from new scan is not the same as the value pulled from the last scan
			        if (decodedText !== lastResult) {
			        	// Initialize old audio and slide outside of for loop to save time
			        	let oldAudio = audioDiv.innerHTML;
			        	let oldSlide = rightCarousel.innerHTML;
			        	
			        	// Instantiate new audio and slide variables to hold the innerhtml to replace the old versions of themselves
			    		let newAudio;
			    		let newSlide;
			    		
			    		// set id as string that was read
		    			idString = decodedText; 
		    			
		    			// set id scanned
		    			scannedNow = parseInt(idString);
			    		try {
			    			for (let i = 0; i < IDs.length; i++) { // for loop to iterate through the array of student ID's
			    				if (IDs[i].localeCompare(idString) == 0) { // if the studentID at i == the ID pulled from just scanned qr
			    					if (pictures[i] === '') { // if no picture for student
			    						if (audios[i] === '') { // if no audio for student
			    							newAudio = "<audio autoplay id='sound' src=''>";
			    							newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
			    						} else { // if student has audio
			    							newAudio = "<audio autoplay id='sound' src='${pageContext.servletContext.contextPath}/files/" + fNames[i] + "/" + audios[i] + "'>";
			    							newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
			    						}
			    					} else { // if student has picture
			    						if (audios[i] === '') { // if no audio for student
			    							newAudio = "<audio autoplay id='sound' src=''>";
			    							newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit;' src='${pageContext.request.contextPath}/files/" + fNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
			    						} else { // if student has audio
			    							newAudio = "<audio autoplay id='sound' src='${pageContext.servletContext.contextPath}/files/" + fNames[i] + "/" + audios[i] + "'>";
			    							newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit;' src='${pageContext.request.contextPath}/files/" + fNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
			    						}
			    					}
			    					// set the innerHTML of the audio and slides to replace what was just on the screen
			    					audioDiv.innerHTML = audioDiv.innerHTML.replace(oldAudio, newAudio)
	    							leftCarousel.innerHTML = leftCarousel.innerHTML.replace(leftCarousel.innerHTML, oldSlide);
			    					rightCarousel.innerHTML = rightCarousel.innerHTML.replace(oldSlide, newSlide);
			    				}
			    			}
			    		} catch {
			    			// cancel the scan if qr data can't be read (i.e. A smudged QR -- Not applicable in our case except for testing purposes)
			    			console.log("Can't read the QR data.");
			    			return;
			    		};
			    		// increase the counter, set last result to idString, and set old id to scanned id
			    		++count; 
			            lastResult = idString; 
			            scannedBefore = scannedNow; 
			        }
			    }
				// render the new innerHTML content of each slide on a successful scan
			    html5QrcodeScanner.render(onScanSuccess);
			});
		}
	}).catch(err => {
		// handle error if unable to get camera ID
		console.log("Couldn't get the camera ID");
	});
  	</script>
	</body>
</html>