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
		<link href="${pageContext.request.contextPath}/css/AdvisorInSS.css" rel="stylesheet" type="text/css">
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
		<form action="${pageContext.servletContext.contextPath}/advisor_index" method="get" enctype="multipart/form-data">
		<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
		<%@page import="java.util.Iterator"%>
		<%@page import="edu.ycp.cs320.personalized_commencement.model.Student"%>

		<% ArrayList<Student> studentList = (ArrayList) request.getAttribute("stuList"); %> <%--Assigning ArrayList object containing Student data to the local object --%>		

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
		<div id="instructions">
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
				<%
				// Iterating through subjectList

				if(request.getAttribute("stuList") != null)  // Null check for the object
				{
					for(int i = 0; i < studentList.size(); i++){
						Student student = studentList.get(i);
						if(student.getApproval()){
						%>
						<div class="card" style="padding:20px;background-color:rgb(240,240,240);float:left;width: 18rem;">
						  <div class="card-body">
						    <h5 class="card-title"><%=student.getFirst()%> <%=student.getLast()%> <img src="${pageContext.request.contextPath}/browser-images/Check.png" style="width: 45px; height: 50px;"></h5>
						    </div>
						  <ul class="list-group list-group-flush">
						    <p class="card-text">Major: <%=student.getMajor() %></p>
						    <p class="card-text">Minor: <%=student.getMinor() %></p>
						    <p class="card-text">Extracurricular: <%=student.getExtraCur() %></p>
						  </ul>
						  <div class="card-body">
					  		<!-- <button type="button" class="btn btn-lg btn-secondary" data-toggle="popover" title="Popover title" 
					  			data-content="And here's some amazing content. It's very engaging. Right?">Image</button> -->
				  			<img class="imgzm" src="${pageContext.servletContext.contextPath}/files/<%=student.getFirst()%>/<%=student.getPicture()%>" title="" height="130" width="100">
							<img class="imgzm" src="${pageContext.servletContext.contextPath}/files/<%=student.getFirst()%>/<%=student.getNameSound()%>" title="" height="130" width="100">
						 	<img src="${pageContext.request.contextPath}/browser-images/Approved.png" style="width: 35px; height: 35px; margin-left: 80%; margin-top: 20px;">
						  </div>
						</div>
						<%
						} else {
						%>
						<div class="card" style="padding:20px;background-color:rgb(240,240,240);float:left;width: 18rem;">
						  <div class="card-body">
						    <h5 class="card-title"><%=student.getFirst()%> <%=student.getLast()%></h5>
						    </div>
						  <ul class="list-group list-group-flush">
						    <p class="card-text">Major: <%=student.getMajor() %></p>
						    <p class="card-text">Minor: <%=student.getMinor() %></p>
						    <p class="card-text">Extracurricular: <%=student.getExtraCur() %></p>
						  </ul>
						  <div class="card-body">
					  		<!-- <button type="button" class="btn btn-lg btn-secondary" data-toggle="popover" title="Popover title" 
					  			data-content="And here's some amazing content. It's very engaging. Right?">Image</button> -->
				  			<img class="imgzm" src="${pageContext.servletContext.contextPath}/files/<%=student.getFirst()%>/<%=student.getPicture()%>" title="" height="130" width="100">
							<img class="imgzm" src="${pageContext.servletContext.contextPath}/files/<%=student.getFirst()%>/<%=student.getNameSound()%>" title="" height="130" width="100">
							<img src="${pageContext.request.contextPath}/browser-images/Denied.png" style="width: 35px; height: 35px; margin-left: 80%; margin-top: 20px;">
						  </div>
						</div>
						<%
						}
					}
				}
			  %>
			  <!-- Logout button -->
			<div id="logoutDiv" style="margin-left: 80%; width: auto;">
				<input class="btn btn-danger" type="button" onclick="window.location='http://localhost:8081/pcomm/logout'" value="Logout">
			</div>
			</div>
	</form>
	</body>
</html>
