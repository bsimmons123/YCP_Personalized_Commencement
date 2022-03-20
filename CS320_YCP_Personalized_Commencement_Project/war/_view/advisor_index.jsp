<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Advisor Index View</title>
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/AdvisorInSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">		<%@ include file="header.jsp" %>
		<%@ include file="header.jsp" %>
	</head>
	
	<style>
		
	</style>

	<!-- styling and layout of the body  -->
	<body>
		<!-- Header of the page -->
		<div id="pageheader"> 
			<h1 class="title">List of Submissions For Review</h1>
		</div>
		<div id="instructions"> 
			<c:if test="${! empty advisor }">
				<div id="Welcome">
					<p> Welcome, ${advisor.email}! I hope you enjoy your stay XD</p>
				</div>
			</c:if>
		<!-- List of instructions -->
		
			<p>
			Here you can find a list of submissions from your students that need to be reviewed.<br>
			To view a student's submission, simply click the "view" button next to their name.<br>
			</p>
		</div>
		
		<!-- Idea is for students to be shown automatically with view buttons next to each student -->
		<!-- First student is example, will add on-clicks later in the project -->
		<table class="table">
			<tr class="row">
				<td class="student">John Appleseed</td>
				<td class="view">
					<input class="viewBut" type="button" name="view" onclick="window.location='http://localhost:8081/pcomm/presentation'" value="View Submission">
				</td>
			</tr>
			<tr class="row">
				<td class="student">Gwegowy Thunderballz</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Ethan RosesN'Berries</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Andrew "Expostulate" Mott</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Michael Jackson</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Ben(Brandon) Simmons</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Bobert Forest</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Cassidy Patchel</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Grant MacDonald</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Ricky Berwick</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr class="row">
				<td class="student">Spider Man</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
			<tr>
				<td class="student">Bologna Boy</td>
				<td class="view"> <input class="viewBut" type="button" name="view" value="View Submission"> </td>
			</tr>
		</table>
	
		<!-- Logout button -->
		<div id="logoutDiv">
			<input class="logoutBut" type="button" onclick="window.location='http://localhost:8081/pcomm/login'" value="Logout">
		</div>
	</body>
</html>







