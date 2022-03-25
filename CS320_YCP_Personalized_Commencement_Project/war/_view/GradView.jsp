<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Student Index View</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- CSS styling that takes the path of the site and imports the respective style sheet -->
		<link href="${pageContext.request.contextPath}/css/" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<%@ include file="header.jsp" %>
		<%@page import="edu.ycp.cs320.personalized_commencement.model.Student"%>
		<% Student student = (Student) session.getAttribute("student"); %>
	</head>
	
	<body>
		<div class="jumbotron" style="background-image: url("https://storage.googleapis.com/beat-buddy/sand.jpg");">
		  <h1 class="display-4">Hello, world!</h1>
		  <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
		  <hr class="my-4">
		  <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
		  <p class="lead">
		    <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
		  </p>
		</div>
	
	</body>
</html>