<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
  </head>

  <body>
    <h2>JQuery AJAX Example</h2>
    Enter your name:
    <input type="text" id="name"/><br/>
    <button onclick="callJqueryAjax()">Submit</button>
    <br/><br/>
    <h3 id="result"></h3>
<script type="text/javascript">
    function callJqueryAjax(){
      var name = $('#name').val();
      $.ajax({
        url     : 'AjaxHandler',
        method     : 'POST',
        data     : {name : name},
        success    : function(resultText){
        $('#result').html(resultText);
        },
        error : function(jqXHR, exception){
          console.log('Error occured!!');
        }
      });
    }
    </script>
  </body>
</html>
