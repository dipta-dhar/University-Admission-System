package servlet;

import Data.Register_Student;
import code.DATABASE_CONNECTION;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "loginValidation")
public class loginValidation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null,password= null,name=null,phone=null,regdate=null,encText=null;
        Long regid=null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HttpSession httpSession = request.getSession();
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        username = request.getParameter("username");
        password = request.getParameter("password");
        //password encryption
        encText = Encrypt.EncryptText(password);
        try{
            con = DATABASE_CONNECTION.get_connection();
            preparedStatement = con.prepareStatement("select * from REGISTER_STUDENT where USERNAME=? AND PASSWORD=?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,encText);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //get data from register_student
                name = resultSet.getString(1);
                regid = resultSet.getLong(8);
                phone = resultSet.getString(11);
                regdate = resultSet.getString(12);
                Register_Student regStu = new Register_Student(name,regid,username,phone,regdate);
                httpSession.setAttribute("regStu",regStu);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Profile.jsp");
                requestDispatcher.forward(request,response);
            }else{

                printWriter.println("<h1>Wrong username or password</h1>");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                requestDispatcher.include(request,response);
            }
        }catch (Exception e){
            System.out.println("Login Validation "+e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
