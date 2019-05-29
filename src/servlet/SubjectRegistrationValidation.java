package servlet;

import Data.REGISTRATION_VALIDATION;
import Data.Student;
import code.DATABASE_CONNECTION;
import code.ID_Maker;
import security.Encrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "SubjectRegistrationValidation")
public class SubjectRegistrationValidation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        String[] names = null;
        String phone= null,username=null,password,encText=null;
        Student student = null;
        Long regid = 2017000200l;
        Boolean flag=true;
        String status = "unpaid";

        try{
            response.setContentType("text/html");
            HttpSession httpSession = request.getSession();
            names = request.getParameterValues("names");
            for(String e : names){
                //System.out.println(e);
            }
            phone = request.getParameter("phone");
            username = ID_Maker.getRandomUsername();
            username = regid+"";
            password = ID_Maker.getPassword();
            httpSession.setAttribute("password",password);
            httpSession.setAttribute("username",username);
            //password encryption
            encText = Encrypt.EncryptText(password);
            student = (Student)httpSession.getAttribute("student");
            httpSession.setAttribute("name",student.getName());
            //System.out.println(username);
            //System.out.println(password);
            flag = REGISTRATION_VALIDATION.checkValidation(student);
            if(!flag){
                try {
                    con = DATABASE_CONNECTION.get_connection();
                    preparedStatement = con.prepareStatement("INSERT INTO REGISTER_STUDENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setString(1,student.getName());
                    preparedStatement.setString(2,student.getSscRoll());
                    preparedStatement.setString(3,student.getSscReg());
                    preparedStatement.setString(4,student.getSscBoard());
                    preparedStatement.setString(5,student.getHscRoll());
                    preparedStatement.setString(6,student.getHscReg());
                    preparedStatement.setString(7,student.getHscBoard());
                    preparedStatement.setLong(8,regid);
                    preparedStatement.setString(9,username);
                    preparedStatement.setString(10,encText);
                    preparedStatement.setString(11,phone);
                    Date date = new Date();
                    preparedStatement.setString(12,date.toString());
                    preparedStatement.executeQuery();

                }catch (Exception e){
                    System.out.println("error in success "+ e);
                }finally {
                    con.close();
                }
                try{
                    con = DATABASE_CONNECTION.get_connection();
                    for(String unit : names){
                        preparedStatement2 = con.prepareStatement("INSERT INTO REGISTER_UNIT VALUES(?,?,?)");
                        preparedStatement2.setLong(1,regid);
                        preparedStatement2.setString(2,unit);
                        preparedStatement2.setString(3,status);
                        preparedStatement2.executeQuery();
                    }

                }catch (Exception e){
                    System.out.println("error in success "+ e);
                }finally {
                    con.close();
                }
                httpSession.setAttribute("regid",regid);
                regid++;
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Welcome.jsp");
                requestDispatcher.forward(request,response);
            }
            else {

                PrintWriter printWriter = response.getWriter();
                printWriter.println("<h1>You already Registered Please Login for Registration</h1>");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                requestDispatcher.include(request,response);
            }
        }catch (Exception e){
            System.out.println("Error in SubjectRegistrationValidation "+e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
