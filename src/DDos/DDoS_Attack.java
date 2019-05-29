package DDos; 

import code.DATABASE_CONNECTION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DDoS_Attack {
    public static Boolean testMacAddress(String macAddress){
        //insert to database
        try {
            Connection con  = DATABASE_CONNECTION.get_connection();
            PreparedStatement macStatement = con.prepareStatement("INSERT INTO DDOS VALUES(?)");
            macStatement.setString(1,macAddress);
            macStatement.execute();
            //check hacker
            PreparedStatement hackerStatement = con.prepareStatement("SELECT COUNT(MAC_ADDRESS) FROM DDOS WHERE MAC_ADDRESS=?");
            hackerStatement.setString(1,macAddress);
            ResultSet hackerSet = hackerStatement.executeQuery();
            int count =0;
            if (hackerSet.next()){
                count = hackerSet.getInt(1);
            }
            if(count>50){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
}
