<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<head>
		<meta charset="UTF-8">
		<!-- CSS styling that takes the path of the site and imports the respective style sheet -->
		<link href="${pageContext.request.contextPath}/css/PresentationSS.css" rel="stylesheet" type="text/css">
		<title>Submission - ${student.last}, ${student.first}</title>	
	</head>

	<!-- Body layout and formatting -->
	<body>
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
			</div>
			
			<!-- Fields and their values -->
			<table>
				<tr class="row">
					<td class="valueName">First Name:</td>
					<td class="value">${student.first}</td>
					<td><input type="checkbox" name="first_approval"></td>
				</tr>
				<tr class="row">
					<td class="valueName">Middle Initial:</td>
					<td class="value">${student.middle}</td>
					<td><input type="checkbox" name="middle_approval"></td>
				</tr>
				<tr class="row">
					<td class="valueName">Last Name:</td>
					<td class="value">${student.last}</td>
					<td><input type="checkbox" name="last_approval"></td>
				</tr>
				<tr class="row">
					<td class="valueName">Major(s):</td>
					<td class="value">${student.major}</td>
					<td><input type="checkbox" name="major_approval"></td>
				</tr>
				<tr class="row">
					<td class="valueName">Minor(s):</td>
					<td class="value">${student.minor}</td>
					<td><input type="checkbox" name="minor_approval"></td>
				</tr>
				<tr class="row">
					<td class="valueName">Extracurricular Activities:</td>
					<td class="value">${student.extraCur}</td>
					<td><input type="checkbox" name="extracur_approval"></td>
				</tr>
				<tr class="row">
					<td class="valueName">Uploaded Image/Video:</td>
					<td><img src="https://images-na.ssl-images-amazon.com/images/I/71Y6FwLwTfL.jpg" alt="Johnny Appleseed" width="460" height="345"></td>
					<td><input type="checkbox" name="image_approval"></td>
				</tr>
				<tr>
					<td class="valueName">Uploaded Audio for Walk:</td>
					<td><audio controls src="${pageContext.servletContext.contextPath}/Audio/johnny_appleseed.mp3"></audio></td>
					<td><input type="checkbox" name="audio_approval"></td>
				</tr>
			</table>
			
			<!-- Submit button -->
			<div class="button"> 
				<input class="bSize" type="Submit" name="Status" value="Submit"> 
			</div>
		</form>
	</body>
</html>