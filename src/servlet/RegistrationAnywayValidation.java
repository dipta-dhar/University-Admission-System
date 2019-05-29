package servlet;

import Data.Student;
import checkdata.CheckData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationAnywayValidation")
public class RegistrationAnywayValidation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String name=null,sscRoll=null,sscReg=null,sscBoard=null,hscRoll=null,hscReg=null,hscBoard=null,phone=null;
            Long sscYear=null,hscYear=null;
            Student student = null;
            response.setContentType("text/html");
            name = request.getParameter("name");
            sscRoll = request.getParameter("sscRoll");
            sscReg = request.getParameter("sscReg");
            sscBoard = request.getParameter("sscBoard");
            sscYear = Long.valueOf(request.getParameter("sscYear"));
            hscRoll = request.getParameter("hscRoll");
            hscReg = request.getParameter("hscReg");
            hscBoard = request.getParameter("hscBoard");
            hscYear = Long.valueOf(request.getParameter("hscYear"));
            phone = request.getParameter("phone");

            //check all data
            Boolean bName =CheckData.checkString(name);
            Boolean bSscRoll = CheckData.checkNumber(sscRoll);
            Boolean bHscRoll = CheckData.checkNumber(hscRoll);
            Boolean bSscReg = CheckData.checkNumber(sscReg);
            Boolean bHscReg = CheckData.checkNumber(hscReg);
            Boolean bSscYear = CheckData.checkNumber(Long.toString(sscYear));
            Boolean bHscYear = CheckData.checkNumber(Long.toString(hscYear));
            Boolean bPhone = CheckData.checkNumber(phone);
            System.out.println(bName+" "+bSscRoll+" "+bHscRoll+" "+bSscReg+" "+
                    bHscReg+" "+bSscYear+" "+bHscYear+" "+bPhone);
            if(bHscReg && bHscRoll && bHscYear && bSscReg && bSscRoll && bSscYear && bName && bPhone){
                student = new Student(name,sscRoll,sscReg,sscBoard,sscYear,hscRoll,hscReg,hscBoard,hscYear);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("student",student);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("SubjectRegistration.jsp");
                requestDispatcher.forward(request,response);
            }else{
                PrintWriter writer = response.getWriter();
                writer.println("Error Input");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("RegistrationAnyway.jsp");
                requestDispatcher.include(request,response);
            }
        }catch (Exception e){
            System.out.println(e);
            PrintWriter writer = response.getWriter();
            writer.println("Error Input");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("RegistrationAnyway.jsp");
            requestDispatcher.include(request,response);
        }






//        System.out.println(name+" "+sscRoll+" "+sscReg+" "+sscBoard+" "+sscYear);
//        System.out.println(name+" "+hscRoll+" "+hscReg+" "+hscBoard+" "+hscYear);
//        System.out.println(phone);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
