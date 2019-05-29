<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">
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

<div class="container">
    <section class="container">
        <div class="row container" style="background-color: silver">
            <div class="container-fluid text-center" style="margin-top: 10%">
                <form method="post" action="loginValidation" name="vform" onsubmit="return Validate()">
                    <div>
                        <i class="fa fa-user" style="font-size: 30px">&nbsp;Username : </i>
                        <input type="text" name="username" class="boxinput rounded">
                        <div id="nameError" class="valError"></div></br>
                    </div>
                    <div>
                        <i class="fa fa-key" style="font-size: 30px">&nbsp;Password : </i>
                        <input type="password" name="password" class="boxinput rounded">
                        <div id="passwordError" class="valError"></div></br>
                    </div>
                    <i style="font-size: 20px;"><button class="rounded text-success">Login</button></i>&nbsp;&nbsp;<a class="fa-lock text-danger" style="color: red" href="ResetPassword.jsp">Reset Password</a>
                    <div>
                        &nbsp;
                        &nbsp;
                    </div>
                </form>
            </div>
            <div>

            </div>
           <!-- <div class=" bg-success col-sm-4 col-md-4 col-lg-4">
                <article style="margin-top: 10%">
                    <p class="display-4">Notic Board</p>
                </article>
            </div>
            <div class=" bg-danger col-sm-8 col-md-8 col-lg-8">

            </div>-->
        </div>
    </section>

</div><!-- content container -->

<footer class="clearfix " style=" height:50vh; background: url(image/@BSMRSTU.jpg) no-repeat bottom;
background-size: 100%;"></footer>

<script src="js/jquery.slim.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
<script src="js/myscript.js"></script>
</body>
</html>