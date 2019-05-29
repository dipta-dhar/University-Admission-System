<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="code.DATABASE_CONNECTION" %>
<%@ page import="java.io.PrintWriter" %>
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
    <title>Home</title>
    <style>
        body{
            background-image: url(image/canvas-texture-paper.jpg);
        }
        tr,td,th{
            text-align: center;
        }
        table {
            border-collapse: collapse;
            width: 50%;
        }

        th, td {
            text-align: left;
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
    <%!
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
            String unit = null,faculty=null;
    %>
    <form action="SubjectRegistrationValidation" method="post">
        <h1 class="text-center">Select<strong class="text-success"><%=" \""+"Units"+"\""%></strong> </h1><br>
        <table align="center" class="text-center">
            <%
                try {
                    con = DATABASE_CONNECTION.get_connection();
                    preparedStatement = con.prepareStatement("SELECT UNIT,FACULTY FROM UNIT_REQUIRMENT");
                    resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        unit = resultSet.getString(1);
                        faculty = resultSet.getString(2);
            %>

            <tr>
                <td><input type="checkbox" name="names" value=<%=unit%>><%=" "+unit+" unit"%></td>
                <td><%=faculty%></td>
            </tr>


            <%
                    }
                }catch (Exception e){
                    System.out.println("error in subreg");

                }
            %>
            <tr style="background-color: #2aabd2">
                <td><label>Phone No.</label></td>
                <td><input name="phone"></td>
            </tr>
        </table>


        <br><button class="text-danger">Confirm</button>
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