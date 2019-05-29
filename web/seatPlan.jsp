<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/animate.css">
    <title>Seat Plan</title>
    <style>
        body{
            background-image: url(image/canvas-texture-paper.jpg);
        }
    </style>
</head>
<body>
<header class="clearfix" style="margin-bottom: 5px">
    <img class="img-fluid" src="image/BSMRSTU_COVER.jpg">
</header>
<nav class="nav nav-pills justify-content-center flex-column flex-sm-row">
    <a class="nav-item nav-link" href="index.jsp">Home</a>
    <a class="nav-item nav-link" href="Login.jsp">Profile</a>
    <a class="nav-item nav-link" href="#">Circular</a>
    <a class="nav-item nav-link active" href="#">Seat Plan</a>
    <a class="nav-item nav-link" href="About.jsp">About</a>
</nav>

<div>&nbsp;&nbsp;</div>
<div class="container-fluid text-center">
    <h2>Seat Plan</h2>
</div>
<div class="container-fluid text-center">
    <%
        try{
            String src = "C:\\Users\\OVI\\Dropbox\\Admission\\web\\Seat Plan\\";
            String fileName = null;
            String filePath = null;
            File file = new File(src);
            File[] files = file.listFiles();
            for(File temp : files){
                fileName = temp.getName();
                filePath = temp.getAbsolutePath();
                if(fileName.contains("pdf")){
                    System.out.println(fileName);
                    System.out.println(filePath);
                    %>
    <a href="/Seat%20Plan/<%=fileName%>" target="_blank"><%=fileName.replace(".pdf"," Unit")%></a><br>
    <%
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    %>
</div>
<div>&nbsp;&nbsp;</div>
<div class="container-fluid text-center">
    <h2>Total Student</h2>
</div>
<div class="container-fluid text-center">
    <%
        try{
            String src = "C:\\Users\\OVI\\Dropbox\\Admission\\web\\Seat Plan\\Total\\";
            String fileName = null;
            String filePath = null;
            File file = new File(src);
            File[] files = file.listFiles();
            for(File temp : files){
                fileName = temp.getName();
                filePath = temp.getAbsolutePath();
                if(fileName.contains("pdf")){
                    System.out.println(fileName);
                    System.out.println(filePath);
    %>
    <a href="/Seat%20Plan/Total/<%=fileName%>" target="_blank"><%=fileName.replace(".pdf"," Unit")%></a><br>
    <%
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    %>
</div>



<footer class="clearfix " style=" height:50vh; background: url(image/@BSMRSTU.jpg) no-repeat bottom;
background-size: 100%;"></footer>
<script src="js/jquery.slim.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>
