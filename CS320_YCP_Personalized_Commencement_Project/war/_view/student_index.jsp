<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Student Index View</title>
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/StudentInSS.css" rel="stylesheet" type="text/css">
	</head>

	<!-- styling and layout of the body  -->
	<body>
		<!-- div for the title of the page -->
		<div id="pageheader"> 
			<h1 class="title">Personalized Senior Commencement Form</h1>
		</div>
		<div id="instructions">
			<p>
			Fill out the information in the text boxes and upload files to be displayed during the commencement ceremony.  
			If there is nothing to add to any of the optional categories, simply leave them blank.  
			When the form is completed, hit the save button.  If changes need to be made, click the edit button to do so.<br><br>
			File types for images can be .JPEG, .JPG, or .PNG files, and file types for videos can be .MP4 or .MOV files.<br>
			</p>
		</div>
		
		<table>
			<tr class="row">
				<td class="prompt">First Name:</td>
				<td class="textBoxes"><input type="text" name="firstname"></td>
			</tr>
			<tr class="row">
				<td class="prompt">Middle Initial:</td>
				<td class="textBoxes"><input type="text" name="middleinitial"></td>
			</tr>
			<tr class="row">
				<td class="prompt">Last Name:</td>
				<td class="textBoxes"><input type="text" name="lastname"></td>
			</tr>
			<tr class="row">
				<td class="prompt">Major(s):</td>
				<td class="textBoxes"><input type="text" name="majors"></td>
			</tr>
			<tr class="row">
				<td class="prompt">Minor(s) (optional):</td>
				<td class="textBoxes"><input type="text" name="minors"></td>
			</tr>
			<tr class="row">
				<td class="prompt">Sports, Clubs, or Organizations (optional):</td>
				<td class="textBoxes"><input type="text" name="sportsclubsactivities"></td>
			</tr>
			<tr class="row">
				<td class="prompt">Image/Video to display (optional):</td>
				<td class="uploads"><input type="file" id="myFile" name="imageorvideo"></td>
			</tr>
			<tr>
				<td class="prompt">Custom Audio for Commencement (optional):</td>
				<td class="uploads"><input type="file" id="myAudio" name="customaudio"></td>
			</tr>
		</table>
		
		<!--Button for adding new content-->
		<div id="buttons">
			<input type="button" value="Save">
			<input type="button" value="Edit Content">
		</div>
		
		<div id="logoutDiv">
			<input class="logoutBut" type="button" onclick="window.location='http://localhost:8081/pcomm/login'" value="Logout">
		</div>
	</body>
</html>