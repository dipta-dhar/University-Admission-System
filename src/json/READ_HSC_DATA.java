package json;

import code.DATABASE_CONNECTION;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import code.*;

public class READ_HSC_DATA {
    public static void main(String args[]){
        Long rollNo,registrationNo,year;
        Double point;
        String banglaFirstPaper=null,banglaSecondPaper=null,englishFirstPaper=null,englishSecondPaper=null,mathematicsFirstPaper=null,
                mathematicsSecondPaper=null,biologyFirstPaper=null,biologySecondPaper=null,chemistryFirstPaper=null,
                chemistrySecondPaper=null,physicsFirstPaper=null, physicsSecondPaper=null,grade=null, group=null,name=null,
                dateOfBirth=null,fatherName=null,motherName=null,board=null,address=null;
        //read json data
        JSONParser jsonParser = new JSONParser();
        try{
            //clear database data
            Connection con = DATABASE_CONNECTION.get_connection();
            PreparedStatement deleteData = con.prepareStatement("DELETE FROM HSC_RESULT");
            deleteData.execute();
            //json code here
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("Json File\\HSC_Result.json"));
            for(Object temp : jsonArray){
                JSONObject student = (JSONObject) temp;
                rollNo = (Long) student.get("roll_no");
                registrationNo = (Long) student.get("registration_no");
                name = (String) student.get("name");
                fatherName = (String) student.get("father_name");
                motherName = (String) student.get("mother_name");
                address = (String) student.get("address");
                dateOfBirth = (String) student.get("date_of_birth");
                board = (String) student.get("board");
                group = (String) student.get("group");
                grade = (String) student.get("grade");
                point = (Double) student.get("point");
                year = (Long) student.get("year");
                //System.out.println(roll_no +" "+registration_no+" "+grade+" "+point+" "+group+" "+grade+" "+name);
                JSONArray result = (JSONArray) student.get("result");

                for(Object data : result){
                    JSONObject stu = (JSONObject) data;
                    banglaFirstPaper = (String) stu.get("bangla_first");
                    banglaSecondPaper = (String) stu.get("bangla_second");
                    englishFirstPaper = (String) stu.get("english_first");
                    englishSecondPaper = (String) stu.get("english_second");
                    mathematicsFirstPaper = (String) stu.get("mathematics_first");
                    mathematicsSecondPaper = (String) stu.get("mathematics_second");
                    physicsFirstPaper = (String) stu.get("physics_first");
                    physicsSecondPaper = (String) stu.get("physics_second");
                    chemistryFirstPaper = (String) stu.get("chemistry_first");
                    chemistrySecondPaper = (String) stu.get("chemistry_second");
                    biologyFirstPaper = (String) stu.get("biology_first");
                    biologySecondPaper = (String) stu.get("biology_second");
                    //System.out.println(bangla_first+bangla_second+english_first+english_second+mathematics+higher_mathematics+social_science+physics+chemistry+biology+religion);
                }
                //insert into database
                PreparedStatement ps = con.prepareStatement("INSERT INTO HSC_RESULT values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, rollNo.toString());
                ps.setString(2, registrationNo.toString());
                ps.setString(3,name);
                ps.setString(4,fatherName);
                ps.setString(5,motherName);
                ps.setString(6,address);
                ps.setString(7,dateOfBirth);
                ps.setString(8,board);
                ps.setString(9,group);
                ps.setString(10,grade);
                ps.setDouble(11,point);
                ps.setLong(12,year);
                ps.setString(13,banglaFirstPaper);
                ps.setString(14,banglaSecondPaper);
                ps.setString(15,englishFirstPaper);
                ps.setString(16,englishSecondPaper);
                ps.setString(17,mathematicsFirstPaper);
                ps.setString(18,mathematicsSecondPaper);
                ps.setString(19,physicsFirstPaper);
                ps.setString(20,physicsSecondPaper);
                ps.setString(21,chemistryFirstPaper);
                ps.setString(22,chemistrySecondPaper);
                ps.setString(23,biologyFirstPaper);
                ps.setString(24,biologySecondPaper);
                ps.execute();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
