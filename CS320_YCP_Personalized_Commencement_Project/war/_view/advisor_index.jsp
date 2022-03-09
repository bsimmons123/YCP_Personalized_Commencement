<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Advisor Index View</title>
	</head>
	
	<!-- temporary hold location for css styling -->
	<style>
		body {
			background-color: rgb(240, 240, 235);
		}
		#pageHeader {
			width: 100%;
		}
		#instructions {
			width: 50%;
			margin: auto;
			font-size: 16px;
			text-align: center;
		}
		#outerdiv {
			width: 50%;
			margin: auto;
			padding: 10px;
			border: 3px solid green;
		}
		#innerdiv1{
			width: auto;
			float: left;
			font-size: 18px;
			margin-left: 20%;
			padding-top: 2px;
		}
		#innerdiv2{
			width: 15.5%;
			padding-top: 2px;
			margin-left: 63%;
		}
		#logout {
			width: auto;
			margin-top: 20px;
			margin-left: 1090px;
		}
		.title {
			color: green;
			font-size: 32px;
			text-align: center;
			border-bottom: 3px solid green;
		}
		.logoutButton {
			height:30px;
			width:65px;
		}
	</style>

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
		<div id="logout">
			<input class="logoutButton" type="button" onclick="window.location='login'" value="Logout">
		</div>
	</body>
</html>







