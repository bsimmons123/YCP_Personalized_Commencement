<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<head>
		<meta charset="UTF-8">
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/PresentationSS.css" rel="stylesheet" type="text/css">
		<title>Submission - ${student.last}, ${student.first}</title>
		
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/presentation" method="get">
			<div id="instructions">
				<p>
				Please use the check boxes to approve the following student's information.
				Leave check boxes blank for disapproval, when done click submit.
				</p>
			</div>
			<table>
				<tr>
					<td class="subheading">First Name:</td>
					<td class="info">${student.first}</td>
					<td><input type="checkbox" name="first_approval"></td>
				</tr>
				<tr>
					<td class="subheading">Middle Initial:</td>
					<td class="info">${student.middle}</td>
					<td><input type="checkbox" name="middle_approval"></td>
				</tr>
				<tr>
					<td class="subheading">Last Name:</td>
					<td class="info">${student.last}</td>
					<td><input type="checkbox" name="last_approval"></td>
				</tr>
				<tr>
					<td class="subheading">Major(s):</td>
					<td class="info">${student.major}</td>
					<td><input type="checkbox" name="major_approval"></td>
				</tr>
				<tr>
					<td class="subheading">Minor(s):</td>
					<td class="info">${student.minor}</td>
					<td><input type="checkbox" name="minor_approval"></td>
				</tr>
				<tr>
					<td class="subheading">Extracurricular Activities:</td>
					<td class="info">${student.extraCur}</td>
					<td><input type="checkbox" name="extracur_approval"></td>
				</tr>
				<tr>
					<td class="subheading">Uploaded Image/Video:</td>
					<td><img src="https://images-na.ssl-images-amazon.com/images/I/71Y6FwLwTfL.jpg" alt="Johnny Appleseed" width="460" height="345"></td>
					<td><input type="checkbox" name="image_approval"></td>
				</tr>
				<tr>
					<td class="subheading">Uploaded Audio for Walk:</td>
					<td><audio controls src="${pageContext.servletContext.contextPath}/Audio/johnny_appleseed.mp3"></audio></td>
					<td><input type="checkbox" name="audio_approval"></td>
				</tr>
				<tr>
					<td><input type="Submit" name="Approval" value="Submit"></td>
				</tr>
			</table>
		</form>
	</body>
</html>