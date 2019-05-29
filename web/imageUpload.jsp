<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Uploading Form</title>
</head>

<body>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
%>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action = "imageUploadValidation.jsp" method = "post"
      enctype = "multipart/form-data">
    <input type = "file" name = "file" size = "50" accept="image/*"/>
    <br />
    <input type = "submit" value = "Upload File" />
</form>
</body>

</html>