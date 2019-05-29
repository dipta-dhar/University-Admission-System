package seatplan;

import code.DATABASE_CONNECTION;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Data {
    private static HashMap<String,Vector<Long>> ids = new HashMap<>();
    public static void rawData(String unit){
        Vector<Long> id = new Vector<>();
        id.clear();
        try{
            Connection con = DATABASE_CONNECTION.get_connection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM REGISTER_UNIT where UNIT=? ORDER BY REGID");
            statement.setString(1,unit);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                id.add(resultSet.getLong(1));
            }
            ids.put(unit,id);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void printRawData(){
        try{
            String dest = "C:\\Users\\OVI\\Dropbox\\Admission\\web\\Seat Plan\\Total\\";
            String logoPath = "C:\\Users\\OVI\\Dropbox\\Admission\\img\\logo.png";

            Set<String> str = ids.keySet();
            for(String te : str){
                int k=0;
                String unitName =te;
                //System.out.println(unitName);

                //make file
                String newFileName = dest+unitName+".txt";
                String newPdf = dest+unitName+".pdf";
                Document document = new Document(PageSize.A4,30,30,50,50);
                PdfWriter.getInstance(document, new FileOutputStream(newPdf));
                document.open();
                //header data
                Image univLogo = Image.getInstance(logoPath);
                univLogo.scaleAbsolute(80,80);
                Paragraph univName = new Paragraph("Bangabandhu Sheikh Mujibur Rahman Science & Technology University, Gopalganj-8100",
                        FontFactory.getFont(FontFactory.COURIER,22,Font.BOLD,new CMYKColor(255,255,255,0)));
                univName.setAlignment(1);
                //header table
                PdfPTable header = new PdfPTable(new float[]{1,5});
                header.setWidthPercentage(100);
                header.getDefaultCell().setBorder(0);
                header.addCell(univLogo);
                header.addCell(univName);

                document.add(header);

                //design1
                Paragraph design1 = new Paragraph("------------------------------------------------------",
                        FontFactory.getFont(FontFactory.COURIER,14,Font.BOLD,new CMYKColor(100,200,0,0)));
                design1.setAlignment(1);
                design1.setSpacingAfter(10);
                document.add(design1);
                Paragraph seatPlan = new Paragraph(unitName+ " Unit.",
                        FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new CMYKColor(255,255,255,0)));
                seatPlan.setAlignment(1);
                seatPlan.setSpacingAfter(20);
                document.add(seatPlan);

                File newFile = new File(newFileName);
                PrintWriter writer = new PrintWriter(newFile);
                if(!newFile.exists())
                    newFile.createNewFile();

                Connection con = DATABASE_CONNECTION.get_connection();
                PreparedStatement totalStatement = con.prepareStatement("SELECT COUNT(REGID) as total FROM REGISTER_UNIT where UNIT=?");
                totalStatement.setString(1,unitName);
                ResultSet totalResult = totalStatement.executeQuery();
                Long total = 0l;
                if(totalResult.next()){
                    total = totalResult.getLong(1);
                }

                writer.println("----Total : "+total+"----");
                Paragraph totalStudent = new Paragraph("Total student : "+total);
                totalStudent.setAlignment(1);
                totalStudent.setSpacingAfter(20);
                document.add(totalStudent);

                System.out.println(unitName+" "+total);
                Vector<Long> longs = ids.get(te);
                String ids = "";
                for (Long tt: longs
                        ) {
                    ids = ids+tt+"  ";
                    writer.println(tt);
                    System.out.println(tt);
                }
                document.add(new Paragraph(ids));
                Rectangle rect= new Rectangle(577,825,18,15); // you can resize rectangle
                rect.enableBorderSide(1);
                rect.enableBorderSide(2);
                rect.enableBorderSide(4);
                rect.enableBorderSide(8);
                rect.setBorderColor(BaseColor.BLACK);
                rect.setBorderWidth(1);
                document.add(rect);


                con.close();
                writer.close();
                document.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public static HashMap<String,Vector<Long>> getData(){
        return ids;
    }
}
