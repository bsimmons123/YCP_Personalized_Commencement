<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<!-- head elements and imports -->
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<!-- CSS styling that takes the path of the site and imports the respective stylesheet -->
		<link href="${pageContext.request.contextPath}/css/LoginSS.css" rel="stylesheet" type="text/css">
		<!-- Styling with bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
		<%@ include file="header.jsp" %>
	</head>

	<!-- Body styling and layout -->
	<body>
		<!-- if no error message -->
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
			
			<!--if error message-->
			<c:if test="${! empty errorMessage}">
				<div id="error">${errorMessage}</div>
			</c:if>
			<!-- User Login Table-->
			<table class="table">
				<!-- User Email -->
				<tr>
					<td class="tdPrompt">Email:</td>
					<td class="tdPrompt"><input type="text" name="email" size="20" value="${user.email}"></td>
				</tr>
				<!-- User Password -->
				<tr>
					<td class="tdPrompt">Password:</td>
					<td class="tdPrompt"><input type="password" name="password" size="20" value="${user.password}"></td>
				</tr>
				<tr>
					<td>
						<div class="button"> 
							<input class="btn btn-success" type="Submit" name="login" value="Login">
						</div>
					</td>
				</tr>
			</table>
			</div>
			<input name="studentModel" type="hidden" value="${sinfo}" />
		</form>
	</body>
</html>