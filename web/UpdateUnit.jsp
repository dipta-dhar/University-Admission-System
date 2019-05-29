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
    <h1 class="text-center">All units. <strong class="text-success"><%=" \""+"Select"+"\""%></strong> units</h1><br>
    <form action="UpdateUnitValidation" method="post">
        <table align="center" class="text-center">
        <%!

            Long regId = null;
            Vector<String> units = new Vector<String>();
            Vector<String> allunits = new Vector<String>();
            Vector<String> paidUnit = new Vector<String>();
            Connection con = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
                String unit = null,faculty=null;
        %>
        <%
            //    units.clear();
//    allunits.clear();
            HttpSession httpSession = request.getSession();
            regId = (Long)httpSession.getAttribute("regid");
            System.out.println(regId);
            units = (Vector<String>) httpSession.getAttribute("units");
            //get all units
            try {
                con = DATABASE_CONNECTION.get_connection();
                PreparedStatement paidStatement = con.prepareStatement("SELECT UNIT FROM REGISTER_UNIT WHERE REGID=? and STATUS=?");
                paidStatement.setLong(1,regId);
                paidStatement.setString(2,"paid");
                ResultSet paidResult = paidStatement.executeQuery();
                paidUnit.clear();
                while(paidResult.next()){
                    //System.out.println("YAHHHHHH : "+paidResult.getString(1));
                    paidUnit.add(paidResult.getString(1));
                }
                paidUnit.add("OVI");
                httpSession.setAttribute("paidUnit",paidUnit);




                preparedStatement = con.prepareStatement("SELECT UNIT,FACULTY FROM UNIT_REQUIRMENT");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    %>
            <tr>
            <%
                    unit = resultSet.getString(1);
                    faculty = "  "+resultSet.getString(2);
                    if(units.contains(unit) && !paidUnit.contains(unit)){
        %>

                <td><input type="checkbox" name="names" checked="checked" value=<%=unit%>><%="  "+unit+" unit"%></td>
            <td><%=faculty%></td>
        <%
        }else if(!paidUnit.contains(unit)){
        %>
                <td><input type="checkbox" name="names" value=<%=unit%>><%="  "+unit+" unit"%></td>
            <td><%=faculty%></td>


        <%
                    }
                    else {
            %>
                <td><%="  "+unit+" unit"%></td>
                <td style="color: red"><%=faculty+" (Paid)"%></td>
                <%
                    }
            %>
            </tr>
            <%
                }

            }catch (Exception e){
                System.out.println("error in update profile "+e);;
            }finally {
                con.close();
            }
        %>
        </table><br>
        <button>Update</button>
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
