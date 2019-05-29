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
    <title>Bootstrap</title>
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
    <a class=" nav-item nav-link active" href="index.jsp">Home</a>
    <a class="nav-item nav-link" href="Login.jsp">Profile</a>
    <a class="nav-item nav-link" href="#">Circular</a>
    <a class="nav-item nav-link" href="#">Seat Plan</a>
    <a class="nav-item nav-link" href="About.jsp">About</a>
</nav>
<div>&nbsp;&nbsp;&nbsp;</div><div>&nbsp;&nbsp;&nbsp;</div>

<%!
   String name=null,username=null,password=null;
       Long regid=null;
%>
<%
    try{
        HttpSession httpSession = request.getSession();
        name = (String)httpSession.getAttribute("name");
        username = (String)httpSession.getAttribute("username");
        password = (String)httpSession.getAttribute("password");
        regid = (Long) httpSession.getAttribute("regid");
        System.out.println(name);
    }catch (Exception e){
        System.out.println(e);
    }

%>
<div class="container-fluid text-center" style="font-size: 25px">
    <h1 class="text-center">Welcome <strong class="text-success"><%="\""+name+"\""%></strong></h1><br>
    <label class="text-success">Username : </label><label class="text-danger font-weight-bold"><%="  "+username%></label><br>
    <label class="text-success">Password : </label><label class="text-danger font-weight-bold"><%="  "+password%></label><br>
    <label class="text-success">Registration Id : </label><label class="text-danger font-weight-bold"><%="  "+regid%></label><br>
    <a class="text-danger" href="index.jsp">Home</a>&nbsp;&nbsp;<a class="text-danger" href="Login.jsp">Login</a>
</div>
<footer class="clearfix " style=" height:50vh; background: url(image/@BSMRSTU.jpg) no-repeat bottom;
background-size: 100%;"></footer>

<script src="js/jquery.slim.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>
