package Data;

import code.DATABASE_CONNECTION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class REGISTRATION_VALIDATION {
    public static Boolean checkValidation(Student student){
        Boolean flag=false;
        Connection con =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            con  = DATABASE_CONNECTION.get_connection();
            preparedStatement = con.prepareStatement("select * from REGISTER_STUDENT where SSCROLL=? AND HSCROLL=? AND SSCREG=? AND HSCREG=? AND SSCBOARD=? AND HSCBOARD=?");
            preparedStatement.setString(1,student.getSscRoll());
            preparedStatement.setString(2,student.getHscRoll());
            preparedStatement.setString(3,student.getSscReg());
            preparedStatement.setString(4,student.getHscReg());
            preparedStatement.setString(5,student.getSscBoard());
            preparedStatement.setString(6,student.getHscBoard());
            // AND SSCREG=? AND HSCREG=? AND SSCBOARD=? AND HSCBOARD=?
//            System.out.println(student.getSscRoll());
//            System.out.println(student.getHscRoll());
//            System.out.println(student.getSscReg());
//            System.out.println(student.getSscBoard());
//            System.out.println(student.getSscBoard());

            resultSet= preparedStatement.executeQuery();

            flag = resultSet.next();
//            System.out.println(flag);

        }catch (Exception e){
            System.out.println("error in register_validation " +e);
        }

        return flag;
    }
}
