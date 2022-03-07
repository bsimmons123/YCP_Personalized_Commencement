
<!--  this is just a holder login class until Andrew creates the real one  -->

<!DOCTYPE html>

<html>
	<head>
		<title>Login view</title>
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
			<!--Username box for login-->
			<div>
				<label>Username:</label>
			</div>
			<div class="text">
				<input type="text" />
				
			</div>
			
			<!--Password for login-->
			<div>
				<label>Password:</label>
			</div>
			<div class="text">
				<input type="text" />
			</div>
			<div>
				<div class="button">
				<input type="button" onclick="window.location.href='index';" value="Advisor Login" />
				</div>
			</div>
			
	</body>
</html>