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
		<div id="pageheader"  style="padding-top: 100px;">
			<h1 class="title">Personalized Senior Commencement Form</h1>
		</div>
		<form action="${pageContext.servletContext.contextPath}/upload.do" method="post" enctype="multipart/form-data">
		<div id="instructions">
		
		<!-- Welcome message -->
		<c:if test="${! empty student }">
			<div class="alert alert-info" role="alert" style="text-align: center;">
				Welcome, ${student.email}!
			</div>
		</c:if>
		
		<% 
			if(student.getApproval() == 1) {
			%>
			<div class="alert alert-success" role="alert" style="text-align: center;">
				You are approved for graduation!
			</div>
			<%
			}else{
			%>
			<div class="alert alert-danger" role="alert" style="text-align: center;">
				You have not been approved for graduation
			</div>
			<%
			} 
			%>
		
		<!-- redirects to login page if -->
		<c:if test="${empty student }">
			<% response.sendRedirect(request.getContextPath() + "/_view/login.jsp"); %>
		</c:if>
		
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
		
			<div class="jumbotron" style="margin-bottom:2.5%; padding-top: 2%;padding-bottom: 2%;">
			  <h1 class="display-4">
			    ${student.first} ${student.last}
			  </h1>
			  <c:if test="${student.checkMajor == 1}">
			  <span class="badge badge-success">Good!</span>
				  <p class="lead">
				    <strong>Major(s):</strong>
				    ${student.major}
				  </p>
			  </c:if>
			  <c:if test="${student.checkMajor == 0}">
			  <span class="badge badge-warning">Please Fix</span>
			  <p class="lead">
				    <strong>Major(s):</strong>
				    ${student.major}
				  </p>
			  </c:if>
			  <c:if test="${student.checkMinor == 1}">
			  <span class="badge badge-success">Good!</span>
			  <p class="lead">
			    <strong>Minors (optional):</strong>
			    ${student.minor}
			  </p>
			  </c:if>
			  <c:if test="${student.checkMinor == 0}">
			  <span class="badge badge-warning">Please Fix</span>
			  <p class="lead">
			    <strong>Minors (optional):</strong>
			    ${student.minor}
			  </p>
			  </c:if>
			  <c:if test="${student.checkExtCur == 1}">
			  <span class="badge badge-success">Good!</span>
			  <p class="lead">
			    <strong>Sports, Clubs, or Organizations (optional):</strong>
			    ${student.extraCur}
			  </p>
			  </c:if>
			  <c:if test="${student.checkExtCur == 0}">
			  <span class="badge badge-warning">Please Fix</span>
			  <p class="lead">
			    <strong>Sports, Clubs, or Organizations (optional):</strong>
			    ${student.extraCur}
			  </p>
			  </c:if>
			  <c:if test="${student.checkImg == 1}">
			  <span class="badge badge-success">Good!</span>
			  <p class="lead">
			    <strong>Image/Video to display (optional):</strong>
			    <img src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.picture}" class="rounded float-right img-fluid" >
			  </p>
			  </c:if>
			  <c:if test="${student.checkImg == 0}">
			  <span class="badge badge-warning">Please Fix</span>
			  <p class="lead">
			    <strong>Image/Video to display (optional):</strong>
			    <img src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.picture}" class="rounded float-right img-fluid" >
			  </p>
			  </c:if>
			  <c:if test="${student.checkAudio == 1}">
			  <span class="badge badge-success">Good!</span>
			  <p class="lead">
			    <strong>Custom Audio for Commencement (optional):</strong>
			    <audio controls src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.nameSound}"></audio>
			  </p>
			  </c:if>
			  <c:if test="${student.checkAudio == 0}">
			  <span class="badge badge-warning">Please Fix</span>
			  <p class="lead">
			    <strong>Custom Audio for Commencement (optional):</strong>
			    <audio controls src="${pageContext.servletContext.contextPath}/files/${student.first}/${student.nameSound}"></audio>
			  </p>
			  </c:if>
			</div>
			
			<c:if test="${student.comment != ''}">
			<div class="card" style="margin-bottom:20px;">
			  <div class="card-header">
			    Advisor Comment
			  </div>
			  <div class="card-body">
			    <p class="card-text">${student.comment}</p>
			  </div>
			</div>
			</c:if>

			<!--Buttons for editing content, previewing content, and logging out -->
			<div id="buttons" style="margin-left:0%; padding:0px; display: inline;">
				<input class="btn btn-primary" style="width: 20%; margin-right: 51.2%;" type="button" onclick="window.location='http://localhost:8081/pcomm/_view/edit_student_content.jsp'" value="Edit Content">
				<input class="btn btn-success" style="width: 28%; margin-left:0%;" type="button" onclick="window.location='http://localhost:8081/pcomm/_view/graduation_preview.jsp'" value="Preview Content">
			</div>
		</div>
		</form>
	</body>
</html>
