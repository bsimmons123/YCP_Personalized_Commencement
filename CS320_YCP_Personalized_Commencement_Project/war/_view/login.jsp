<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Student/Advisor Login</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/browser-images/YCP Tab Logo.png">
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/LoginSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">		<%@ include file="navbar.jsp" %>
	</head>
	
	<!-- Body styling and layout -->
	<body>
		<!-- if no error message (User enters correct credentials) -->
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<!-- Header of the page -->
			<div id="pageheader"> 
				<h1 class="title">Student/Advisor Login</h1>
			</div>
			<!-- List of instructions -->
			<div id="instructions"> 
				<p>
				Please log in using YCP credentials (student/advisor email and password).<br>
				Once logged in, you will be taken to your respective home page.
				</p>
				<!-- if error message (User enters invalid credentials) -->
				<c:if test="${! empty errorMessage}">
					<div class="alert alert-danger" role="alert">
						${errorMessage}
					</div>
				</c:if>	    
				<!-- Login Table-->
				<table class="table">
					<tbody>
						<!-- User Email -->
					    <tr>
					      <th scope="row"></th>
					      <td class="tdPrompt">Email:</td>
					      <td class="tdPrompt"><input type="text" name="email" size="20" value="${user.email}"></td>
					    </tr>
					    <!-- User Password -->
					    <tr>
					      <th scope="row"></th>
					      <td class="tdPrompt">Password:</td>
						  <td class="tdPrompt"><input type="password" name="password" size="20" value="${user.password}"></td>
					    </tr>
					    <!-- Login Button -->
					    <tr>
					      <th scope="row"></th>
					      <td></td>
						  <td style="padding-left:24%;"><input class="btn btn-success" type="Submit" name="login" value="Login"></td>
					    </tr>
			    	</tbody>
				</table>
			</div>
			<input name="studentModel" type="hidden" value="${sinfo}" />
		</form>
		<br>
		<br>
		<div id="pageheader"> 
			<h1 class="title" style="font-size: 20px;">Watch The Ceremony Here: <input class="btn btn-outline-success" style="margin-left: 2%;" type="button" onclick="window.location='http://localhost:8081/pcomm/ceremony'" value="Watch Ceremony"></h1>
		</div>
	</body>
</html>