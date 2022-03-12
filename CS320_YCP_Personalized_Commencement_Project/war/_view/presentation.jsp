<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Submission - ${student.last}, ${student.first}</title>
	</head>
	<body>
	<form action="${pageContext.servletContext.contextPath}/presentation" method="get">
		<table>
			<tr>
				<td>First Name:</td>
				<td>${student.first}</td>
			</tr>
			<tr>
				<td>Middle Initial:</td>
				<td>${student.middle}</td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td>${student.last}</td>
			</tr>
			<tr>
				<td>Minor(s):</td>
				<td>${student.major}</td>
			</tr>
			<tr>
				<td>Major(s):</td>
				<td>${student.minor}</td>
			</tr>
			<tr>
				<td>Extracurricular Activities:</td>
				<td>${student.extraCur}</td>
			</tr>
			<tr>
				<td>Uploaded Image/Video:</td>
				<td>Will work on later</td>
			</tr>
			<tr>
				<td>Uploaded Audio for Walk:</td>
				<td>Will work on later</td>
			</tr>
		</table>
	</form>
	</body>

</html>