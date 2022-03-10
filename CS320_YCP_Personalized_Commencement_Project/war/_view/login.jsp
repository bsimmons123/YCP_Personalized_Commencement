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
			</div>
			<!--if error message-->
			<c:if test="${! empty errorMessage}">
				<div id="error">
					${errorMessage}
				</div>
			</c:if>
			<!-- User Login Table-->
			<table class="table">
				<!-- User Email -->
				<tr>
					<td class="tdPrompt">Email:</td>
					<td><input type="text" name="email" size="20" value="${user.email}"></td>
				</tr>
				<!-- User Password -->
				<tr>
					<td class="tdPrompt">Password:</td>
					<td><input type="password" name="password" size="20" value="${user.password}"></td>
				</tr>
				<tr>
					<td>
						<div class="button"> 
							<input type="Submit" name="login" value="Login">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>