<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Student Index View</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/StudentInSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<%@ include file="header.jsp" %>
		<%@page import="edu.ycp.cs320.personalized_commencement.model.Student"%>
		<% Student student = (Student) session.getAttribute("student"); %>
	</head>

	<!-- styling and layout of the body  -->
	<body>
		<!-- div for the title of the page -->
		<div id="pageheader">
			<h1 class="title">Personalized Senior Commencement Form</h1>
		</div>
		<form action="${pageContext.servletContext.contextPath}/upload.do" method="post" enctype="multipart/form-data">
		<div id="instructions">
		
		<!-- Welcome message -->
		<c:if test="${! empty student }">
			<div class="alert alert-success" role="alert">
				Welcome, ${student.email}!
			</div>
		</c:if>
		
		<c:if test="${empty student }">
			<% response.sendRedirect(request.getContextPath() + "/_view/login.jsp"); %>
		</c:if>
			<% 
			if(student.getApproval() == 1) {
			%>
			<div class="alert alert-primary" role="alert">
				You are approved for graduation!
			</div>
			<%
			}else{
			%>
			<div class="alert alert-danger" role="alert">
				You have not been approved for graduation
			</div>
			<%
			} 
			%>
		
		
		
		<c:if test="${! empty message}">
			<div class="alert alert-success" role="alert">
				${message}
			</div>
		</c:if>
		
		<c:if test="${! empty errorMessage}">
			<div class="alert alert-warning" role="alert">
				${errorMessage}
			</div>
		</c:if>

			<div class="jumbotron">
			  <h1 class="display-4">
			    ${student.first} ${student.last}
			  </h1>
			  <p class="lead">
			    <strong>Major(s):</strong>
			    ${student.major}
			  </p>

			  <p class="lead">
			    <strong>Minor(s):</strong>
			    ${student.minor}
			  </p>

			  <p class="lead">
			    <strong>Sports, Clubs, or Organizations (optional):</strong>
			    ${student.extraCur}
			  </p>

			  <p class="lead">
			    <strong>Image/Video to display (optional):</strong>
			    <img src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.picture}" class="rounded float-right img-fluid" >
			  </p>

			  <p class="lead">
			    <strong>Custom Audio for Commencement (optional):</strong>
			    <audio controls src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.nameSound}"></audio>
			  </p>
			  <hr class="my-4">
			</div>


		<!--Button for adding new content-->
		<div id="buttons">
			<input class="btn btn-secondary" type="button"onclick="window.location='http://localhost:8081/pcomm/_view/edit_student_content.jsp'" value="Edit Content">
		</div>

		<div id="logoutDiv">
			<input class="btn btn-danger" type="button" onclick="window.location='http://localhost:8081/pcomm/logout'" value="Logout">
		</div>
		</div>
		</form>
	</body>
</html>
