package unit;

import code.DATABASE_CONNECTION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UPDATE_UNIT {
    private static Connection con= null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static String status = "unpaid";
    public static void delete(Long regId,String unit){
        try{
            con  = DATABASE_CONNECTION.get_connection();
            preparedStatement = con.prepareStatement("DELETE FROM REGISTER_UNIT WHERE REGID=? and UNIT=?");
            preparedStatement.setLong(1,regId);
            preparedStatement.setString(2,unit);
            preparedStatement.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println("error in class unit/update_unit : delete "+e);
        }
        System.out.println("deleted : "+unit);
    }
    public static void add(Long regId,String unit){
        try{
            con  = DATABASE_CONNECTION.get_connection();
            preparedStatement = con.prepareStatement("INSERT INTO REGISTER_UNIT VALUES(?,?,?)");
            preparedStatement.setLong(1,regId);
            preparedStatement.setString(2,unit);
            preparedStatement.setString(3,status);
            preparedStatement.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println("error in class unit/update_unit : add "+e);
        }
        System.out.println("added : "+unit);
    }
}
