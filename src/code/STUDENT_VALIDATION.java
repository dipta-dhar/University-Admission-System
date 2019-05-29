package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class STUDENT_VALIDATION {
    public static boolean CHECK_VALID_STUDENT(String sscRoll,String hscRoll,String sscBoard,String hscBoard){
        boolean valid =false;
        Connection con=null;
        PreparedStatement sscPreparedStatement=null,hscPreparedStatement=null;
        ResultSet sscResultSet=null,hscResultSet=null;
        Double sscResult,hscResult;
        String sName=null,hName=null,sFatherName=null,hFatherName=null,sMotherName=null,hMotherName=null,
                sDateOfBirth=null,hDateOfBirth=null,sscBoard1=null,hscBoard1=null;
        Long sscPassingYear=Long.valueOf(0),hscPassingYear= Long.valueOf(0);
        try{

            //System.out.println(sscRoll+" "+hscRoll);
            con = DATABASE_CONNECTION.get_connection();
            //get ssc information
            sscPreparedStatement = con.prepareStatement("SELECT * FROM SSC_RESULT WHERE ROLL_NO=?");
            sscPreparedStatement.setString(1,sscRoll);
            sscResultSet = sscPreparedStatement.executeQuery();
            while(sscResultSet.next()){
                sName = sscResultSet.getString(3);
                sFatherName = sscResultSet.getString(4);
                sMotherName = sscResultSet.getString(5);
                sDateOfBirth = sscResultSet.getString(7);
                sscBoard1 = sscResultSet.getString(8);
                sscPassingYear = sscResultSet.getLong(12);
            }
            //get hsc information
            hscPreparedStatement = con.prepareStatement("SELECT * FROM HSC_RESULT where ROLL_NO=?");
            hscPreparedStatement.setString(1,hscRoll);
            hscResultSet = hscPreparedStatement.executeQuery();
            while(hscResultSet.next()){
                hDateOfBirth = hscResultSet.getString(7);
                hscBoard1 = hscResultSet.getString(8);
                hscPassingYear = hscResultSet.getLong(12);
                hName = hscResultSet.getString(3);
                hFatherName = hscResultSet.getString(4);
                hMotherName = hscResultSet.getString(5);

            }

        }catch (Exception e){
            System.out.println("STUDENT_VALIDATION.java : "+e);
        }
        //check equality of student
        //check passing year
        if(sName.equals(hName) && sFatherName.equals(hFatherName) && sMotherName.equals(hMotherName) && sDateOfBirth.equals(hDateOfBirth)
                &&sscPassingYear>=2010 && hscPassingYear>=2012 && sscBoard.equals(sscBoard1) && hscBoard.equals(hscBoard1))
            valid = true;
        return valid;
    }
}
