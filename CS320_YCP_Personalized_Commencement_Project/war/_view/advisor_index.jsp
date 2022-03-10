<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Advisor Index View</title>
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/AdvisorIndexSS.css" rel="stylesheet" type="text/css">
	</head>

	<!-- styling and layout of the body  -->
	<body>
		<!-- Header of the page -->
		<div id="pageheader"> 
			<h1 class="title">List of Submissions For Review</h1>
		</div>
		<!-- List of instructions -->
		<div id="instructions"> 
			<p>
			Here you can find a list of submissions from your students that need to be reviewed.<br>
			To view a student's submission, simply click the "view" button next to their name.<br>
			Once you are ready to approve or deny a student's submission, click the respective button on their submission.
			</p>
		</div>
		
		<!-- Idea is for students to be shown automatically with view buttons next to each student -->
		<!-- Holds student names-->
		<div id="outerdiv">
			<div id="innerdiv1">
				<label>John Appleseed</label><br><br>
				<label>Gwegowy Thunderballz</label><br><br>
				<label>Ethan RosesN'Berries</label><br><br>
				<label>Andrew "Expostulate" Mott</label><br><br>
				<label>Michael Jackson</label><br><br>
				<label>Ben(Brandon) Simmons</label><br><br>
				<label>Bobert Forest </label><br><br>
				<label>Cassidy Patchel </label><br><br>
				<label>Grant MacDonald </label><br><br>
				<label>Spider Man </label><br><br>
				<label>Bologna Boy </label><br><br>
				<label>Ricky Berwick </label><br><br>
			</div>
			<!-- Holds buttons for each student -->
			<div id="innerdiv2">
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
				<input type="button" name="view" value="View Submission" style="height:23px;width:117px;"><br><br>
			</div>
		</div>	
		<div id="logoutDiv">
			<input class="logoutBut" type="button" onclick="window.location='http://localhost:8081/pcomm/login'" value="Logout">
		</div>
	</body>
</html>







