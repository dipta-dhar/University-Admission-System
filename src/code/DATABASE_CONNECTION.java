package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DATABASE_CONNECTION {
    static  Connection con= null;
    public static Connection get_connection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AVIJIT", "12345");
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }
}
