<%@ page import="Data.Register_Student" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="code.DATABASE_CONNECTION" %>
<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: OVI
  Date: 14-Oct-17
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/animate.css">
    <meta http-equiv='cache-control' content='no-cache'>
    <meta http-equiv='cache-control' content='no-store'>
    <meta http-equiv='pragma' content='no-cache'>
    <meta http-equiv='expires' content='0'>
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
    Connection con  =null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Vector<String> units = new Vector<String>();
    Register_Student regStu=null;
        String name = null,username=null,phone=null,regdate=null,status="unpaid";
    Long regid=null;
    HttpSession httpSession = null;
    Vector<String> paidUnit = new Vector<String>();
    Vector<String> unpaidUnit = new Vector<String>();
    String imgSrc = "regImage/201720172017.jpg";
%>
<div class="container-fluid text-center" >
    <%
        httpSession = request.getSession();
        regStu = (Register_Student) httpSession.getAttribute("regStu");
        String regID = regStu.getRegid().toString();
        System.out.println(regID);
        File folder = new File("C:\\Users\\OVI\\Dropbox\\Admission\\web\\regImage");
        File[] files = folder.listFiles();
        for(File file : files){
            String name = file.getName();
            if(name.contains(regID)){
                imgSrc = "regImage/"+file.getName();
                System.out.println(imgSrc);
            }
        }
    %>
    <label><%=imgSrc%></label>
    <h1 class="text-center">Welcome<strong class="text-success"><%=" \""+regStu.getName()+"\""%></strong> </h1>
    <img class="img-fluid rounded clearfix" src="<%=imgSrc%>" height="300" width="250" alt=<%=regStu.getName()%>/>
</div>
<div class="container-fluid">

    <%
        name = regStu.getName();
        regid = regStu.getRegid();
        username = regStu.getUsername();
        phone = regStu.getPhone();
        regdate = regStu.getRegdate();
//    Vector<String> units = (Vector<String>) httpSession.getAttribute("units");
//    for(String unit : units){
//        System.out.println(unit);
//    }
        try{
            con = DATABASE_CONNECTION.get_connection();
            preparedStatement = con.prepareStatement("SELECT * FROM REGISTER_UNIT WHERE REGID=?");
            preparedStatement.setLong(1,regStu.getRegid());
            resultSet = preparedStatement.executeQuery();
            units.clear();
            paidUnit.clear();
            unpaidUnit.clear();
            while(resultSet.next()){
                String unit = resultSet.getString(2);
                units.add(unit);
                if(resultSet.getString(3).equals("unpaid")){
                    unpaidUnit.add(resultSet.getString(2));
                }else{
                    paidUnit.add(resultSet.getString(2));
                }
                //System.out.println(unit);
            }
        }catch (Exception e){
            System.out.println("Error in profile "+e);
        }finally {
            con.close();
            //httpSession.invalidate();
        }
    %>
    <%
        httpSession = request.getSession();
        httpSession.setAttribute("regid",regid);
        httpSession.setAttribute("units",units);
    %>
    <h1></h1>
    <table align="center" class="rounded">
        <tr>
            <td>Name</td>
            <td><%=name%></td>
        </tr>
        <tr>
            <td>Registration ID</td>
            <td><%=regid%></td>
        </tr>
        <tr>
            <td>Username</td>
            <td><%=username%></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><%=phone%></td>
        </tr>
        <tr>
            <td>Registered Units</td>
            <td class="text-success" style="font-family: 'Century'">
                <%
                    for(String unit:units){
                %>
                <%=unit+" "%>
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <td>Registration Date</td>
            <td><%=regdate%></td>
        </tr>
        <tr>
            <td>Paid Units</td>
            <td style="color:blue;">
                <%
                    for(String unit: paidUnit){
                %>
                <%=unit+" "%>
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <td>Unpaid Units</td>
            <td style="color:red;">
                <%
                    for(String unit:unpaidUnit){
                %>
                <%=unit+" "%>
                <%
                    }
                %>
            </td>
        </tr>
        <tr >
            <td ></td>
            <td><form action="UpdateUnit.jsp" method="post"><button class="text-warning" style="background:#FFFFFF;">Add or remove units</button></form></td>
        </tr>
    </table>
    <%
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("regId",regid);
    %>
    <a href="imageUpload.jsp">Upload Image</a>
</div>
<footer class="clearfix " style=" height:50vh; background: url(image/@BSMRSTU.jpg) no-repeat bottom;
background-size: 100%;"></footer>

<script src="js/jquery.slim.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
<%
    imgSrc = "regImage/201720172017.jpg";
%>
</body>
</html>
