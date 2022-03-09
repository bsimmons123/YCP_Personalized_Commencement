<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Login</title>
	</head>
	<style>
		body {
			background-color: rgb(240, 240, 235);
		}
		table {
			padding-top: 10px;
			border: 3px solid green;
			width: 300px;
			margin: auto;
		}
		#pageHeader {
			width: auto;
		}
		#instructions {
			margin: auto;
			width: 50%;
			text-align: center;
			font-size: 16px;
		}
		#error {
			margin: auto;
			width: 50%;
			padding-bottom: 20px;
			text-align: center;
			font-size: 18px;
			font-weight: bold;
			color: red;
		}
		.title {
			color: green;
			font-size: 32px;
			text-align: center;
			border-bottom: 3px solid green;
		}
		.tdPrompt {
			padding-left: 4px;
			font-size: 20px;
		}
		.button	{
			padding-left: 135px;
		}
	</style>

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