package servlet;

import code.STUDENT_VALIDATION;

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

@WebServlet(name = "StudentValidation")
public class StudentValidation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sscRoll,hscRoll,sscBoard,hscBoard;
        Connection con = null;
        PreparedStatement hscPreparedStatement,sscPreparedStatement;
        ResultSet sscResultSet,hscResultSet;
        Double sscResult,hscResult;
        Boolean data;
        HttpSession httpSession = request.getSession();
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        sscRoll = request.getParameter("sscRoll");
        hscRoll = request.getParameter("hscRoll");
        sscBoard = request.getParameter("sscBoard");
        hscBoard = request.getParameter("hscBoard");
        //System.out.println(sscBoard+hscBoard);
        try{
            data  = STUDENT_VALIDATION.CHECK_VALID_STUDENT(sscRoll,hscRoll,sscBoard,hscBoard);
            if(data){
                httpSession.setAttribute("sscRoll",sscRoll);
                httpSession.setAttribute("hscRoll",hscRoll);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("StudentInformation.jsp");
                requestDispatcher.forward(request,response);
            }else{
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                printWriter.println("<h1>Invalid SSC or HSC Roll</h1>");
                requestDispatcher.include(request,response);
            }
        }catch (Exception e){
            //System.out.println("Error in checkvalidity.jsp");
            response.setContentType("html/text");
            PrintWriter printWriter1 = response.getWriter();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.jsp");
            requestDispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
