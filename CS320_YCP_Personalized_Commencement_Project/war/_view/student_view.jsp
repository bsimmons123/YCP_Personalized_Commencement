<!DOCTYPE html>

<html>
	<head>
		<title>Student view</title>
	</head>
	<style>
		.dropmenu {
		
		}
		.textbox {
		
		}
		.button	{
			 margin-bottom: 20px;
			 float: left;
		}
	</style>

	<body>
		<!-- Edit content button -->
			<div class="button">
				<input type="button" onclick="window.location.href='update';" value="Edit" />
			</div>
			
			<div class="textbox">
				<input type="text" name="fName">
				<br>
				<input type="SUBMIT" name="Submit">
				<% out.println(request.getParameter("text1")); %>
			</div>
			
			
			
			
			
			<!--Save button-->
			<div class="button">
				<input type="button" onclick="window.location.href='new';" value="Save" />
			</div>
			
			
	</body>
</html>