package servlet;

import code.DATABASE_CONNECTION;
import unit.UPDATE_UNIT;

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
import java.util.Vector;

@WebServlet(name = "UpdateUnitValidation")
public class UpdateUnitValidation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] units = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long regId = null;
        Vector<String> preReg = new Vector<String>();
        preReg.clear();
        HttpSession httpSession = request.getSession();
        units = request.getParameterValues("names");
        regId = (Long)httpSession.getAttribute("regid");
        Vector<String> paidUnit = (Vector<String>) httpSession.getAttribute("paidUnit");
        try{
            con = DATABASE_CONNECTION.get_connection();

            for (String ss : paidUnit){
                System.out.println("loooooo " +ss);
            }

            preparedStatement = con.prepareStatement("SELECT UNIT FROM REGISTER_UNIT WHERE REGID=?");
            preparedStatement.setLong(1,regId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                preReg.add(resultSet.getString(1));
            }
        }catch (Exception e){
            System.out.println("error in updateprofilevalidation "+e);
        }
        if(units!=null){
            for (String unit : units){
                if(preReg.contains(unit) && !paidUnit.contains(unit)){
                    //System.out.println("already "+unit);
                    //remain already registered unit in database
                    preReg.remove(unit);
                }else{
                    //System.out.println("add "+unit);
                    //insert function
                    UPDATE_UNIT.add(regId,unit);
                }
            }
        }

        for (String unit : preReg){
            //System.out.println("del" +unit);
            //delete function
            if(!paidUnit.contains(unit))
                UPDATE_UNIT.delete(regId,unit);
        }
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<h1>Profile Updated</h1>");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Profile.jsp");
        requestDispatcher.include(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
