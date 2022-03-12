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
			<p>${info.first}</p>	
		</div>
		<div>
			<p>${info.last}</p>	
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
		
	</form>
	</body>

</html>