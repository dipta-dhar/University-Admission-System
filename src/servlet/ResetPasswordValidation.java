package servlet;

import checkdata.CheckData;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ResetPasswordValidation")
public class ResetPasswordValidation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sscRoll=null,sscReg=null,hscRoll=null,hscReg=null,phone=null,sscBoard=null,hscBoard=null,newPassword=null,encText=null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HttpSession httpSession = request.getSession();
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();





        //System.out.println(sscRoll+" "+sscReg+" "+sscBoard+" "+hscRoll+" "+hscReg+" "+hscBoard+" "+phone);
        try{
            sscRoll = request.getParameter("sscRoll");
            hscRoll = request.getParameter("hscRoll");
            sscReg = request.getParameter("sscReg");
            hscReg = request.getParameter("hscReg");
            sscBoard = request.getParameter("sscBoard");
            hscBoard = request.getParameter("hscBoard");
            phone = request.getParameter("phone");
            //check data
            Boolean bSscRoll = CheckData.checkNumber(sscRoll);
            Boolean bHscRoll = CheckData.checkNumber(hscRoll);
            Boolean bSscReg = CheckData.checkNumber(sscReg);
            Boolean bHscReg = CheckData.checkNumber(hscReg);
            Boolean bPhone = CheckData.checkNumber(phone);
            if(bHscReg && bHscRoll && bPhone && bSscReg && bSscRoll){
                con = DATABASE_CONNECTION.get_connection();
                preparedStatement = con.prepareStatement("select * from REGISTER_STUDENT where SSCROLL=? AND HSCROLL=? AND SSCREG=? AND HSCREG=? AND SSCBOARD=? AND HSCBOARD=?");
                preparedStatement.setString(1,sscRoll);
                preparedStatement.setString(2,hscRoll);
                preparedStatement.setString(3,sscReg);
                preparedStatement.setString(4,hscReg);
                preparedStatement.setString(5,sscBoard);
                preparedStatement.setString(6,hscBoard);
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    newPassword = ID_Maker.getPassword();
                    //encrypt
                    encText = Encrypt.EncryptText(newPassword);
                    preparedStatement = con.prepareStatement("UPDATE REGISTER_STUDENT SET PASSWORD=? where SSCROLL=? AND HSCROLL=? AND SSCREG=? AND HSCREG=? AND SSCBOARD=? AND HSCBOARD=?");
                    preparedStatement.setString(1,encText);
                    preparedStatement.setString(2,sscRoll);
                    preparedStatement.setString(3,hscRoll);
                    preparedStatement.setString(4,sscReg);
                    preparedStatement.setString(5,hscReg);
                    preparedStatement.setString(6,sscBoard);
                    preparedStatement.setString(7,hscBoard);
                    int data = preparedStatement.executeUpdate();
//                if(data==1){
//                    System.out.println("done");
//                }else {
//                    System.out.println("not done");
//                }
                    //System.out.println(true);
                    //System.out.println(newPassword);
                    //printWriter.println("<h1>Nor Registered Yet or Wrong Information</h1>");
                    httpSession.setAttribute("newPassword",newPassword);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("NewPassword.jsp");
                    requestDispatcher.forward(request,response);
                }else{
                    //System.out.println(false);
                    printWriter.println("<h1>Nor Registered Yet or Wrong Information</h1>");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                    requestDispatcher.include(request,response);
                }
            }else{
                printWriter.println("<h1>Nor Registered Yet or Wrong Information</h1>");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                requestDispatcher.include(request,response);
            }




        }catch (Exception e){
            System.out.println("Error in reset Password validation "+e);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
