<html>
   <head>
      <title>Uploading Form for file</title>
   </head>
  
   <body>
      <h3>File Upload:</h3>
      Select a file to upload: <br />
      <form action = "UploadForm" method = "post"
         enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "100" />
         <br />
         <input type = "submit" value = "Upload File" />
      </form>
   </body>
  
</html>