package checkdata;

public class Main {
    public static void main(String[] args){
        //Boolean aBoolean = CheckData.checkString("");
        //Boolean aBoolean = CheckData.checkNumber("");
        Boolean aBoolean = CheckData.checkValidYear(999l,2013l);
        System.out.println(aBoolean);
        System.out.println((int)'-');

    }
}
