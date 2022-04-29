<script>
    $(function () {
        $('#myModal').appendTo('body');
    });

</script>
<!-- This is to let the modal work properly -->

<!-- Overall navbar styling -->
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: green; position: fixed; width: 100%; top: 0px; z-index: 1000;">
  <!-- Project Name as a link that takes the user to the login page -->
  <a class="navbar-brand mr-auto" style="pointer-events: none;font-size: 24px;"><img src="${pageContext.request.contextPath}/browser-images/YCP Logo.png" style="width: 45px; height: 50px;"> Personal Commencement Portal</a>
  <div id="navbarNavAltMarkup ml-auto">
    <div class="navbar-nav">
      
    
	   	<!-- Button to Open the Modal -->
		<button type="button" 
		style="text-align: center;width: 100px; border-radius: 5px; color: green; background-color: white; font-size: 20px;" 
		class="btn" data-toggle="modal" data-target="#myModal">
		 Logout<span class="sr-only">(current)</span>
		</button>
		<!-- The Modal -->
		<div class="modal" id="myModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">NOTICE</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
		        Are you sure you want to log out? There's nothing else you want to do? 
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">No, go back</button>
		        <button type="button" onclick="window.location='http://localhost:8081/pcomm/logout'" class="btn btn-danger" data-dismiss="modal">Yes, Logout</button>
		      </div>
		
		    </div>
		  </div>
		</div>
  	</div>
  </div>
</nav>

