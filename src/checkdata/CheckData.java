package checkdata;

public class CheckData {
    public static boolean checkString(String temp){
        Boolean flag = false;
        if(temp.contains("drop")||temp.contains("table")||temp.contains("university")){
            return false;
        }
        char tempChar[] = temp.toCharArray();
        for(int i=0;i<tempChar.length;i++){
            if(tempChar[i]>=65 && tempChar[i]<=122 || tempChar[i]==' '||tempChar[i]=='-'){
                flag = true;
            }else {
                flag = false;
                return flag;
            }
        }
        return flag;
    }
    public static boolean checkNumber(String temp){
        if(temp.contains("drop")||temp.contains("table")||temp.contains("university")){
            return false;
        }
        Boolean flag = false;
        char tempChar[] = temp.toCharArray();
        for(int i=0;i<tempChar.length;i++){
            if(tempChar[i]>=65 && tempChar[i]<=122){
                flag = false;
                return flag;
            }else {
                flag = true;
            }
        }
        return flag;
    }
    public static boolean checkValidYear(Long sscYear,Long hscYear){
        if(sscYear>=1000 && sscYear<=3000 && hscYear>=1000 && hscYear<=3000 )
            return true;
        else
            return false;
    }
}
