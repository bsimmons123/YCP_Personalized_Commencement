<!DOCTYPE html>

<html>
	<head>
		<title>Student Login view</title>
	</head>
	<style>
		.button	{
			 margin-bottom: 20px;
		}
		.text {
			 margin-bottom: 20px;
		}
	</style>
	
	<body>
			<!--Username box for Student login-->
			<div>
				<label>Username:</label>
			</div>
			<div class="text">
				<input type="text" />
				
			</div>
			
			<!--Password for Student login-->
			<div>
				<label>Password:</label>
			</div>
			<div class="text">
				<input type="text" />
			</div>
			<div>
				<div class="button">
				<input type="button" onclick="window.location.href='student_index';" value="Student Login" />
				</div>
			</div>
			
	</body>
</html>