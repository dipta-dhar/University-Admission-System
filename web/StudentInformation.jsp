<%@ page import="java.sql.Connection" %>
<%@ page import="code.DATABASE_CONNECTION" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Data.Student" %>
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
        table, th, td {
            border: 0.5px solid black;
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
<div class="container-fluid" style="font-size: 30px";>
    <%!
        Connection con = null;
            PreparedStatement sscPreparedStatement=null,hscPreparedStatement=null;
            ResultSet sscResultSet=null,hscResultSet=null;
            Double sscPoint,hscPoint;
            String sscRoll=null,hscRoll=null,name=null,fatherName=null,motherName=null,address=null,sscGrade=null,
                    hscGrade=null,dateOfBirth=null,sscBoard=null,hscBoard=null,sscGroup=null,hscGroup=null,
                    sscRegistrationNo=null,hscRegistrationNo=null;
            Long sscPassingYear,hscPassingYear;
        Student student = null;
    %>
    <%
        //get session data
        sscRoll = request.getParameter("sscRoll");
        hscRoll = request.getParameter("hscRoll");
    %>
    <%
        try{
            con = DATABASE_CONNECTION.get_connection();
            //get ssc information
            sscPreparedStatement = con.prepareStatement("SELECT * FROM SSC_RESULT WHERE ROLL_NO=?");
            sscPreparedStatement.setString(1,sscRoll);
            sscResultSet = sscPreparedStatement.executeQuery();
            while(sscResultSet.next()){
                sscRegistrationNo = sscResultSet.getString(2);
                name = sscResultSet.getString(3);
                fatherName = sscResultSet.getString(4);
                motherName = sscResultSet.getString(5);
                address = sscResultSet.getString(6);
                dateOfBirth = sscResultSet.getString(7);
                sscBoard = sscResultSet.getString(8);
                sscGroup = sscResultSet.getString(9);
                sscGrade = sscResultSet.getString(10);
                sscPoint = sscResultSet.getDouble(11);
                sscPassingYear = sscResultSet.getLong(12);
            }
            //get hsc information
            hscPreparedStatement = con.prepareStatement("SELECT * FROM HSC_RESULT where ROLL_NO=?");
            hscPreparedStatement.setString(1,hscRoll);
            hscResultSet = hscPreparedStatement.executeQuery();
            while(hscResultSet.next()){
                hscRegistrationNo = hscResultSet.getString(2);
                hscBoard = hscResultSet.getString(8);
                hscGroup = hscResultSet.getString(9);
                hscGrade = hscResultSet.getString(10);
                hscPoint = hscResultSet.getDouble(11);
                hscPassingYear = hscResultSet.getLong(12);
            }

        }catch (Exception e){
            System.out.println("CheckValidity.jsp : "+e);
        }
        student = new Student(name,sscRoll,sscRegistrationNo,sscBoard,sscPassingYear,hscRoll,hscRegistrationNo,hscBoard,hscPassingYear);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("student",student);
    %>
    <h1 class="text-center">Welcome<strong class="text-success"><%=" \""+name+"\""%></strong> </h1><br>
    <table align="center">
        <tr>
            <th class="text-center" colspan="3">Student Information</th>
        </tr>
        <tr>
            <td>Name</td>
            <td colspan="2"><%=name%></td>
        </tr>
        <tr>
            <td>Father Name</td>
            <td colspan="2"><%=fatherName%></td>
        </tr>
        <tr>
            <td>Mother Name</td>
            <td colspan="2"><%=motherName%></td>
        </tr>
        <tr>
            <td>Address</td>
            <td colspan="2"><%=address%></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td colspan="2"><%=dateOfBirth%></td>
        </tr>
        <tr>
            <th class="text-center" colspan="3">Board Result</th>
        </tr>
        <tr>
            <th>Result</th>
            <th>SSC</th>
            <th>HSC</th>
        </tr>
        <tr>
            <td>Roll No</td>
            <td><%=sscRoll%></td>
            <td><%=hscRoll%></td>
        </tr>
        <tr>
            <td>Registration No</td>
            <td><%=sscRegistrationNo%></td>
            <td><%=hscRegistrationNo%></td>
        </tr>
        <tr>
            <td>Group</td>
            <td><%=sscGroup%></td>
            <td><%=hscGroup%></td>
        </tr>
        <tr>
            <td>Board</td>
            <td><%=sscBoard%></td>
            <td><%=hscBoard%></td>
        </tr>
        <tr>
            <td>Grade</td>
            <td><%=sscGrade%></td>
            <td><%=hscGrade%></td>
        </tr>
        <tr>
            <td>Point</td>
            <td><%=sscPoint%></td>
            <td><%=hscPoint%></td>
        </tr>
        <tr>
            <td>Passing Year</td>
            <td><%=sscPassingYear%></td>
            <td><%=hscPassingYear%></td>
        </tr>
    </table>
    <div align="center">
        <p><a href="Registration.jsp">Previous</a> <strong>or</strong> <a href="SubjectRegistration.jsp">Next</a> </p>
    </div>
</div>

<footer class="clearfix " style=" height:50vh; background: url(image/@BSMRSTU.jpg) no-repeat bottom;
background-size: 100%;"></footer>

<script src="js/jquery.slim.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>
