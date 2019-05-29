package payment;

import code.DATABASE_CONNECTION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class PAYMENT {
    public static boolean sendMoney(Long regID,String[] unit,Long taka){
        try{
            Connection con = DATABASE_CONNECTION.get_connection();
            Boolean registered=false;
            PreparedStatement regStatement = con.prepareStatement("SELECT * FROM REGISTER_STUDENT WHERE REGID=?");
            regStatement.setLong(1,regID);
            ResultSet regResult = regStatement.executeQuery();
            if (regResult.next()){
                registered = true;
            }

            if(registered) {
                Vector<String> units = new Vector<>();
                units.clear();
                for (String temp : unit) {
                    units.add(temp);
                }
                System.out.println(taka + " " + regID + " " + units.size());


                PreparedStatement unitStatement = con.prepareStatement("SELECT UNIT,TAKA FROM UNIT_MONEY");
                ResultSet unitResult = unitStatement.executeQuery();
                Long totalTaka = 0l;
                while (unitResult.next()) {
                    String temp = unitResult.getString(1);
                    Long unitTaka = unitResult.getLong(2);
                    if (units.contains(temp)) {
                        System.out.println(temp + " " + unitTaka);
                        totalTaka = totalTaka + unitTaka;
                    }
                }
                System.out.println(totalTaka);
                if (taka>=totalTaka){
                    System.out.println("You can");
                    PreparedStatement regUnitsStatement = con.prepareStatement("SELECT UNIT,STATUS FROM REGISTER_UNIT WHERE REGID=?");
                    regUnitsStatement.setLong(1,regID);
                    ResultSet resultSet = regUnitsStatement.executeQuery();
                    while (resultSet.next()){
                        String tempUnit = resultSet.getString(1);
                        String status = resultSet.getString(2);
                        //System.out.println(tempUnit+" "+status);
                        if(status.equals("unpaid")&& units.contains(tempUnit)){
                            //update status
                            PreparedStatement updateStatement = con.prepareStatement("UPDATE REGISTER_UNIT SET STATUS=? where REGID=?");
                            updateStatement.setString(1,"paid");
                            updateStatement.setLong(2,regID);
                            updateStatement.execute();
                            System.out.println("Payment complete");
                            return true;
                        }
                    }
                    //System.out.println("ok");
                }else{
                    System.out.println("You can not");
                    return false;
                }
            }
            else {
                System.out.println("Not reg");
                return false;
            }
        }catch (Exception e){
            System.out.println("Payment "+e);
        }
        return false;
    }
}
