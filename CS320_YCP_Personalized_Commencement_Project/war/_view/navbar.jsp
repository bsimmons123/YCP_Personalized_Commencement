<nav class="navbar navbar-expand-lg navbar-dark " style="background-color: green; margin-bottom:20px;">
	<a class="navbar-brand" style="margin-left: 0%; pointer-events: none; font-size: 24px;"><img src="${pageContext.request.contextPath}/browser-images/YCP Logo.png" style="width: 45px; height: 50px;"> Personal Commencement Portal</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    </ul>
    <form class="form-inline my-2 my-lg-0" action="${pageContext.servletContext.contextPath}/search.do" method="post">
      <input class="form-control mr-sm-2" name="email" type="search" placeholder="Enter Student's Email or First and Last Name" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
