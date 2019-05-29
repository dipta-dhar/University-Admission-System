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
    <title>Registration</title>
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
<div class="container-fluid text-center">
    <h1 class="text-center">Provide the<strong class="text-success"><%=" "+"information"+" "%></strong>for registration</h1><br>
    <form action="RegistrationAnywayValidation" method="post">
        <label class="text-success">Name (<strong>By Certificate</strong>)</label>
        <input class="rounded" name="name"><br><br>
        <label class="text-success">SSC Roll</label>
        <input class="rounded" name="sscRoll"/>
        <label class="text-success">SSC Reg</label>
        <input class="rounded" name="sscReg"/>
        <label class="text-success">SSC Year</label>
        <input class="rounded" name="sscYear"/>
        <label class="text-success">SSC Board</label>
        <select class="rounded text-warning" name="sscBoard">
            <option value="Dhaka">Dhaka</option>
            <option value="Rajshahi">Rajshahi</option>
            <option value="Chittagong">Chittagong</option>
            <option value="Khulna">Khulna</option>
        </select>
        </br></br>
        <label class="text-success">HSC Roll</label>
        <input class="rounded" name="hscRoll"/>
        <label class="text-success">HSC Reg</label>
        <input class="rounded" name="hscReg"/>
        <label class="text-success">HSC Year</label>
        <input class="rounded" name="hscYear"/>
        <label class="text-success">HSC Board</label>
        <select class="rounded text-warning"name="hscBoard">
            <option value="Dhaka">Dhaka</option>
            <option value="Rajshahi">Rajshahi</option>
            <option value="Chittagong">Chittagong</option>
            <option value="Khulna">Khulna</option>
        </select>
        </br></br>
        <label class="text-success">Phone No</label>
        <input class="rounded" type="text" name="phone"></br></br>
        <button class="text-success text-info">Send</button>
    </form>
</div>

<footer class="clearfix " style=" height:50vh; background: url(image/@BSMRSTU.jpg) no-repeat bottom;
background-size: 100%;"></footer>

<script src="js/jquery.slim.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>