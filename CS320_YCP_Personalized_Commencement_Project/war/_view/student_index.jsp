<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Student Index View</title>
		<!-- Path to css styling for redirects -->
		<link href="${pageContext.request.contextPath}/css/StudentIndexSS.css" rel="stylesheet" type="text/css">
	</head>

	<!-- styling and layout of the body  -->
	<body>
		<!-- div for the title of the page -->
		<div id="pageheader"> 
			<h1 class="title">Information For Personalized Senior Commencement</h1>
		</div>
		<div id="instructions">
			<p>Instructions: </p>
			<ul>
			<li>Fill out the information in the text boxes and upload files to be displayed during the commencement ceremony. </li>
			<li>If there is nothing to add to any of the optional categories, simply leave them blank. </li>
			<li>File types for images can be .JPEG, .JPG, or .PNG files, and file types for videos can be .MP4 or .MOV files. </li>
			<li>When the form is completed, hit the save button.  If changes need to be made, click the edit button to do so. </li>
			</ul>
		</div>
		
		<!-- div for the contents of the page -->
		<div id="content">
			<!-- prompt and input type styling and positioning -->
			<div id="prompts">
				<label>First Name:</label>
				<br>
				<br>
				<label>Middle Initial:</label>
				<br>
				<br>
				<label>Last Name:</label>
				<br>
				<br>
				<label>Major(s):</label>
				<br>
				<br>
				<label>Minor(s) (optional):</label>
				<br>
				<br>
				<label>Sports, Clubs, or Organizations (optional):</label>
				<br>
				<br>
				<label>Image/Video to display (optional):</label>
				<br>
				<br>
				<label>Custom Audio for Commencement (optional):</label>
			</div>
			
			<div id="fields">
				<input type="text" name="firstname">
				<br>
				<br>
				<input type="text" name="middleinitial">
				<br>
				<br>
				<input type="text" name="lastname">
				<br>
				<br>
				<input type="text" name="majors">
				<br>
				<br>
				<input type="text" name="minors">
				<br>
				<br>
				<input type="text" name="sportsclubsactivities">
				<br>
				<br>
				<input type="file" id="myFile" name="imageorvideo">
				<br>
				<br>
				<input type="file" id="myAudio" name="customaudio">
			</div>
			<br>
			<br>
			<!--Button for adding new content-->
			<div id="buttons">
				<input type="button" value="Save">
				<input type="button" value="Edit Content">
			</div>
		</div>
		<div id="logoutDiv">
			<input class="logoutBut" type="button" onclick="window.location='http://localhost:8081/pcomm/login'" value="Logout">
		</div>
	</body>
</html>