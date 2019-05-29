package admitcard;

import seatplan.Data;
import seatplan.SeatPlan;

public class Main {
    public static void main(String[] args)throws Exception{

            AdmitCard.PDFMaker();
            for (char x='A';x<'G';x++){
                String unit = x+"";
                Data.rawData(unit);
            }
            Data.printRawData();
            for (char x='A';x<'G';x++){
                String unit = x+"";
                SeatPlan.getUnitSeatPlan(unit);
            }
            //Data.printRawData();


        //HashMap<String,Vector<Long>> ids = Data.getData();
    }
}
