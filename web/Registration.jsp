</html>
<%@ page import="java.util.Vector" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="code.DATABASE_CONNECTION" %>
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
        table {
            border-collapse: collapse;
            width: 50%;
        }

        th, td,tr {
            text-align: center;
            padding: 8px;
        }

        tr:nth-child(odd){background-color: #f2f2f2}

        th {
            background-color: #4CAF50;
            color: white;
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
    <form action="StudentValidation" method="post" name="vform" onsubmit="return Validate()">
        <div>
            <label>SSC Roll</label>
            <input class="rounded" name="sscRoll"/>
            <label>SSC Board</label>
            <select class="rounded text-warning" name="sscBoard">
                <option value="Dhaka">Dhaka</option>
                <option value="Rajshahi">Rajshahi</option>
                <option value="Chittagong">Chittagong</option>
                <option value="Khulna">Khulna</option>
            </select>
            <label>SSC Year</label>
            <input class="rounded" name="sscYear"/>
            <div id="nameErroro" class="valError"></div>
        </div>

        </br>

        <div>
            <label>HSC Roll</label>
            <input class="rounded" name="hscRoll"/>
            <label>HSC Board</label>
            <select class="rounded text-warning" name="hscBoard">
                <option value="Dhaka">Dhaka</option>
                <option value="Rajshahi">Rajshahi</option>
                <option value="Chittagong">Chittagong</option>
                <option value="Khulna">Khulna</option>
            </select>
            <label>HSC Year</label>
            <input class="rounded" name="hscYear"/>
            <div id="hscRollError" class="valError"></div>
        </div>

        </br>
        <button name="checkValidity">Check Validity</button>&nbsp;<a href="RegistrationAnyway.jsp">Registration Anyway</a>
    </form>
</div>

<footer class="clearfix " style=" height:50vh; background: url(image/@BSMRSTU.jpg) no-repeat bottom;
background-size: 100%;"></footer>

<script src="js/jquery.slim.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
