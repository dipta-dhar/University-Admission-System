package json;

import code.DATABASE_CONNECTION;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class READ_SSC_DATA {
    public static void main(String args[]){
        Long rollNo,registrationNo,year;
        Double point;
        String banglaFirstPaper=null,banglaSecondPaper=null,englishFirstPaper=null,englishSecondPaper=null,mathematics=null,
                higherMathematics=null,socialScience=null,biology=null,religion=null,chemistry=null,physics=null,grade=null,
                group=null,name=null, dateOfBirth=null,fatherName=null,motherName=null,board=null,address=null;
        //read json data
        JSONParser jsonParser = new JSONParser();
        try{
            //clear database data
            Connection con = DATABASE_CONNECTION.get_connection();
            PreparedStatement deleteData = con.prepareStatement("DELETE FROM SSC_RESULT");
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
                    mathematics = (String) stu.get("mathematics");
                    higherMathematics = (String) stu.get("higher_mathematics");
                    socialScience = (String) stu.get("social_science");
                    physics = (String) stu.get("physics");
                    chemistry = (String) stu.get("chemistry");
                    biology = (String) stu.get("biology");
                    religion = (String) stu.get("religion");
                    //System.out.println(bangla_first+bangla_second+english_first+english_second+mathematics+higher_mathematics+social_science+physics+chemistry+biology+religion);
                }
                //insert into database
                PreparedStatement ps = con.prepareStatement("INSERT INTO SSC_RESULT values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                ps.setString(14,banglaFirstPaper);
                ps.setString(15,englishFirstPaper);
                ps.setString(16,englishFirstPaper);
                ps.setString(17,mathematics);
                ps.setString(18,higherMathematics);
                ps.setString(19,socialScience);
                ps.setString(20,physics);
                ps.setString(21,chemistry);
                ps.setString(22,biology);
                ps.setString(23,religion);
                ps.execute();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
