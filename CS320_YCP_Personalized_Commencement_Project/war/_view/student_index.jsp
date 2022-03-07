<!DOCTYPE html>

<html>
	<head>
		<title>Student Index View</title>
	</head>
	<style>
		.button	{
			 margin-bottom: 20px;
		}
	</style>

	<body>
			<!--Button for adding/changing content-->
			<div class="button">
				<input type="button" onclick="window.location.href='update';" value="Update information" />
			</div>
			
			<!--Button for deleting content-->
			<div class="button">
				<input type="button" onclick="window.location.href='delete';" value="Delete Content" />
			</div>
			
			<!--Button for adding new content-->
			<div class="button">
				<input type="button" onclick="window.location.href='new';" value="Add Content" />
			</div>
	</body>
</html>