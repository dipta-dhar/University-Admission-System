package Data;

public class Register_Student {
    private  String name, username, phone, regdate;
    Long regid;
    public Register_Student(String name, Long regid, String username, String phone, String regdate ){
        this.name = name;
        this.regid = regid;
        this.username = username;
        this.phone = phone;
        this.regdate = regdate;
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getPhone() {
        return phone;
    }
    public String getRegdate() {
        return regdate;
    }
    public Long getRegid() {
        return regid;
    }
}
