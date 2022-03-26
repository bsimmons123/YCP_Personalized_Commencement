<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%! int i; %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Advisor Index View</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/AdvisorIndex.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<!-- Script for popups -->
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		<%@ include file="header.jsp" %>
	</head>

	<!-- styling and layout of the body  -->
	<body>
		
		<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
		<%@page import="java.util.Iterator"%>
		<!-- Enable popups -->
		<script>             
			$(function() {
			 $('.imgzm').popover({
			  html: true,
			  trigger: 'hover',
			  content: function () {
			    return '<img src="'+$(this).attr('src') + '" width="500" height="500" />';
			  }
			 });
			})
		</script>
		
		<!-- Header of the page -->
		<div id="pageheader">
			<h1 class="title">List of Submissions For Review</h1>
		</div>
		<div id="instructions" class="clearfix">
			<c:if test="${! empty advisor }">
				<!-- Welcome message -->
				<div class="alert alert-success" role="alert">
					Welcome, ${advisor.email}!
				</div>

			</c:if>
			<c:if test="${empty advisor }">
				<% response.sendRedirect(request.getContextPath() + "/_view/login.jsp"); %>
			</c:if>
			<!-- List of instructions -->
			<p>
			Here you can find a list of submissions from your students that need to be reviewed.<br>
			To view a student's submission, simply click the "view" button next to their name.<br>
			</p>
						<c:forEach var="student" items="${stuList}">
						<form action="${pageContext.servletContext.contextPath}/advisor_index" method="post">
						<div class="card" style="margin-left: 3.1%; margin-top: 20px; margin-right: 3.1%;padding:10px;background-color:#69a95d;float:left;width: 18rem; min-height: 18rem;">
						  <div class="card-body">
						    <h5 class="card-title" style="padding-top: 10px;"><c:out value="${student.first}"/> <c:out value="${student.last}"/></h5>
						    </div>
						  <ul class="list-group list-group-flush" style="margin-top: 0px;">
						    <p class="card-text">Major: <c:out value="${student.major}"/></p>
						    <p class="card-text">Minor: <c:out value="${student.minor}"/></p>
						    <p class="card-text">Extracurricular: <c:out value="${student.extraCur}"/></p>
						    <input name="student" type="hidden" value="${student.studentId}" />
						    <input type="submit" class="btn btn-secondary" value="View Student">
						  </ul>
						  </div>
						  </form>
						  </c:forEach>
		<!-- Logout button -->
		<div id="logoutDiv" style="width: auto;">
			<input class="btn btn-danger" type="button" onclick="window.location='http://localhost:8081/pcomm/logout'" value="Logout">
		</div>
	</body>
</html>
