<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Login view</title>
	</head>
	<style>
		.button	{
			 margin-bottom: 20px;
		}
		.error {
			color: red;
		}
	</style>

	<body>
		<c:if test="${! empty errorMessage}"> <!--if error message-->
			<div class="error">${errorMessage}</div>
		</c:if><!-- if no error message -->
			<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<!-- User Login Table-->
			<table>
				<!-- User Email -->
				<tr>
					<td>Email:</td>
					<td><input type="text" name="first" size="12" value="${user.email}" /></td>
				</tr>
				<!-- User Password -->
				<tr>
					<td>Password:</td>
					<td><input type="text" name="second" size="12" value="${user.password}" /></td>
				</tr>
			</table>
				<div class="button">
				<input type="Submit" name="login" value="Login">
				</div>
		</form>
	</body>
</html>