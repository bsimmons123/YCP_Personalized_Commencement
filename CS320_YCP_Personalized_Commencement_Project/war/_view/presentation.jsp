<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>View</title>
	</head>
	<body>
	<form action="${pageContext.servletContext.contextPath}/presentation" method="get">
		<div>
			<p>${info.first} ${info.last}</p>	
		</div>
		<div>
			<p>${info.major}</p>	
		</div>
		<div>
			<p>${info.minor}</p>	
		</div>
		<div>
			<p>${info.extraCur}</p>	
		</div>
		<div>
			<p><img src="https://images-na.ssl-images-amazon.com/images/I/71Y6FwLwTfL.jpg" alt="Johnny Appleseed" width="460" height="345"></p>
		</div>
		<div>
			<p><embed src="${pageContext.request.contextPath}/Audio/johnny_appleseed.mp3" width="500" height="200" loop="false" autostart="true" hidden="true" /></p>
		</div>
	</form>
	</body>

</html>