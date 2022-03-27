<!-- Overall navbar styling -->
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: green; position: fixed; width: 100%; top: 0px; z-index: 1000;">
  <!-- Project Name as a link that takes the user to the login page -->
  <a class="navbar-brand mr-auto" style="pointer-events: none;font-size: 24px;"><img src="${pageContext.request.contextPath}/browser-images/YCP Logo.png" style="width: 45px; height: 50px;"> Personal Commencement Portal</a>
  <div id="navbarNavAltMarkup ml-auto">
    <div class="navbar-nav">
    <form action="${pageContext.servletContext.contextPath}/logout" method="post">
      <a class="nav-link active" href="http://localhost:8081/pcomm/logout" 
      style="text-align: center;width: 100px; border-radius: 5px; color: green; background-color: white; font-size: 20px;">
      Logout<span class="sr-only">(current)</span>
      </a>
    </form>
    </div>
  </div>
</nav>

