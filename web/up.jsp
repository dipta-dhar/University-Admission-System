<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "FileUploadServlet" method = "post" enctype = "multipart/form-data">
    <input type="text" name="name" size="20"/>
    <br/>
    <input type = "file" name = "photo" size = "50" accept="image/*"/>
    <br />
    <input type = "submit" value = "Upload File" />
</form>
</body>
</html>
