package Data;

import java.util.Date;

public class Student {
    String name,sscRoll,sscReg,sscBoard,hscRoll,hscReg,hscBoard;
    //Date dateOfBirth;
    Long sscYear,hscYear;
    public Student(String name,String sscRoll,String sscReg,String sscBoard,Long sscYear,
                   String hscRoll,String hscReg,String hscBoard,Long hscYear) {
        this.name = name;
        //this.dateOfBirth = dateOfBirth;

        this.sscRoll = sscRoll;
        this.sscReg = sscReg;
        this.sscBoard = sscBoard;
        this.sscYear = sscYear;

        this.hscRoll = hscRoll;
        this.hscReg = hscReg;
        this.hscYear = hscYear;
        this.hscBoard = hscBoard;

    }
    public String getName() {
        return name;
    }
    public String getSscRoll() {
        return sscRoll;
    }
    public String getSscReg() {
        return sscReg;
    }
    public String getSscBoard() {
        return sscBoard;
    }
    public String getHscRoll() {
        return hscRoll;
    }
    public String getHscReg() {
        return hscReg;
    }
    public String getHscBoard() {
        return hscBoard;
    }
//    public Date getDateOfBirth() {
//        return dateOfBirth;
//    }
    public Long getSscYear() {
        return sscYear;
    }
    public Long getHscYear() {
        return hscYear;
    }
}
