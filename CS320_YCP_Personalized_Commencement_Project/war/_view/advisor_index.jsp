<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%! int i; %>
<html lang="en-US">

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Advisor Index View</title>
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/AdvisorInSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<%@ include file="header.jsp" %>
	</head>

	<!-- styling and layout of the body  -->
	<body>
		<form action="${pageContext.servletContext.contextPath}/advisor_index" method="get" enctype="multipart/form-data">
		<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
		<%@page import="java.util.Iterator"%>
		<%@page import="edu.ycp.cs320.personalized_commencement.model.Student"%>

		<% ArrayList<Student> studentList = (ArrayList) request.getAttribute("stuList"); %> <%--Assigning ArrayList object containing Student data to the local object --%>
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
				<!-- List of instructions -->
				<p>
				Here you can find a list of submissions from your students that need to be reviewed.<br>
				To view a student's submission, simply click the "view" button next to their name.<br>
				</p>
			</c:if>
	
			<table class="table table-secondary table-striped table-hover">
			  <thead>
			    <tr>
			      <th>Name</th>
			      <th>Submission</th>
			    </tr>
			  </thead>
			<tbody>
				<%
				// Iterating through subjectList

				if(request.getAttribute("stuList") != null)  // Null check for the object
				{
					for(int i = 0; i < studentList.size(); i++){
						Student name = studentList.get(i);

					%>
					<tr>
						<td><%=name.getFirst()%> <%=name.getLast()%></td>
						<td><input class="btn btn-secondary" type="button" name="view" onclick="window.location='http://localhost:8081/pcomm/presentation'" value="View"></td>
					</tr>
					<%
					}
				}
			  %>
			</tbody>
			</table>

		</div>
		<!-- Logout button -->
		<div id="logoutDiv">
			<input class="btn btn-danger" type="button" onclick="window.location='http://localhost:8081/pcomm/login'" value="Logout">
		</div>
	</form>
	</body>
</html>
