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
import java.util.HashMap;
import java.util.Vector;

public class SeatPlan {
    private static HashMap<String,Vector<Long>> ids = Data.getData();
    //get unit data
    public static void getUnitSeatPlan(String unit)throws Exception{
        //make file
        String logoPath = "C:\\Users\\OVI\\Dropbox\\Admission\\img\\logo.png";
        String dest = "C:\\Users\\OVI\\Dropbox\\Admission\\web\\Seat Plan\\";
        String newFileName = dest+unit+".txt";
        String newPdf = dest+unit+".pdf";
        //System.out.println(newFileName);
        //make pdf
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
        Paragraph seatPlan = new Paragraph("Seat Plan "+unit+ " Unit.",
                FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new CMYKColor(255,255,255,0)));
        seatPlan.setAlignment(1);
        seatPlan.setSpacingAfter(20);
        document.add(seatPlan);


        File newFile = new File(newFileName);
        if(!newFile.exists())
            newFile.createNewFile();
        PrintWriter writer = new PrintWriter(newFile);


        Vector<Long> id = ids.get(unit);
        Vector<Long> part1 = new Vector<>();
        Vector<Long> part2 = new Vector<>();
        part1.clear();
        part2.clear();
        Long unitTotal=0l;
        try {
            Connection con = DATABASE_CONNECTION.get_connection();

            PreparedStatement statement1 = con.prepareStatement("SELECT * FROM REGISTER_UNIT where UNIT=?");
            PreparedStatement totalStatement = con.prepareStatement("SELECT COUNT(REGID) as total FROM REGISTER_UNIT where UNIT=?");
            PreparedStatement seatStatement = con.prepareStatement("SELECT * FROM SEAT_ROOM_DETAILS ORDER BY BUILDINGNAME");

            statement1.setString(1,unit);
            totalStatement.setString(1,unit);

            //ResultSet resultSet1 = statement1.executeQuery();
            ResultSet totalResult = totalStatement.executeQuery();
            ResultSet seatResult = seatStatement.executeQuery();

            if (totalResult.next()){
                unitTotal = totalResult.getLong(1);
                //System.out.println("TotalUnit "+unit+" : "+unitTotal);
            }
            writer.println("Total student : "+unitTotal);
            Paragraph totalStudent = new Paragraph("Total student : "+unitTotal);
            totalStudent.setAlignment(1);
            document.add(totalStudent);

            while(unitTotal>0){
                while(seatResult.next()&&unitTotal>0){
                    Long total = seatResult.getLong(5);
                    if(unitTotal>=0&&unitTotal>=total){
                        unitTotal = unitTotal -total;
                        //System.out.println(seatResult.getString(2)+
                        //        " : "+total);
                        writer.println();
                        writer.println("-----"+seatResult.getString(2)+"-----");
                        document.add(new Paragraph(seatResult.getString(2),
                                FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new CMYKColor(255,255,255,0))));

                        PdfPTable table = new PdfPTable(2);
                        table.getDefaultCell().setBorder(0);

                        writer.println("Floor : "+seatResult.getString(3));
                        //document.add(new Paragraph("Floor : "+seatResult.getString(3)));
                        table.addCell("Floor: ");
                        table.addCell(seatResult.getString(3));

                        writer.println("Room No : "+seatResult.getString(4));
                        //document.add(new Paragraph("Room No : "+seatResult.getString(4)));
                        table.addCell("Room No: ");
                        table.addCell(seatResult.getString(4));

                        writer.println("Student No : "+total);
                        //document.add(new Paragraph("Student No : "+total));
                        table.addCell("Students : ");
                        table.addCell(total+"");


                        writer.print("Registration id : ");
                        //document.add(new Paragraph("Student Range : "));
                        String ids = "";
                        for(int i=0;i<total;i++){
                            ids = ids+id.elementAt(0)+" ";
                            writer.print(id.elementAt(0)+" ");
                            part1.add(id.elementAt(0));
                            //System.out.print(id.elementAt(0)+", ");
                            id.remove(0);
                        }
                        //System.out.println(part1.firstElement()+ " "+part1.lastElement());
                        //document.add(new Paragraph(ids));
                        //document.add(new Paragraph(part1.firstElement()+" - "+part1.lastElement()));
                        table.addCell("Range: ");
                        table.addCell(part1.firstElement()+" - "+part1.lastElement());
                        part1.clear();
                        document.add(table);
                        writer.println();
                        //System.out.println();
                    }else{
                        writer.println();
                        writer.println("-----"+seatResult.getString(2)+"-----");
                        document.add(new Paragraph(seatResult.getString(2),
                                FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new CMYKColor(255,255,255,0))));

                        PdfPTable table = new PdfPTable(2);
                        table.getDefaultCell().setBorder(0);

                        writer.println("Floor : "+seatResult.getString(3));
                        //document.add(new Paragraph("Floor : "+seatResult.getString(3)));
                        table.addCell("Floor: ");
                        table.addCell(seatResult.getString(3));

                        writer.println("Room No : "+seatResult.getString(4));
                        //document.add(new Paragraph("Room No : "+seatResult.getString(4)));
                        table.addCell("Room No: ");
                        table.addCell(seatResult.getString(4));

                        writer.println("Students No : "+unitTotal);
                        //document.add(new Paragraph("Students No : "+unitTotal));
                        table.addCell("Students: ");
                        table.addCell(unitTotal+"");

                        //System.out.println(seatResult.getString(2)+
                        //        " : "+unitTotal);


                        //document.add(new Paragraph("Student Range : "));
                        writer.print("Registration id : ");
                        String ids = "";
                        for(int i=0;i<unitTotal;i++){
                            ids = ids +" "+id.elementAt(0);
                            part1.add(id.elementAt(0));
                            writer.print(id.elementAt(0)+" ");
                            //System.out.print(id.elementAt(0)+", ");
                            id.remove(0);
                        }
                        //document.add(new Paragraph(ids));
                        //document.add(new Paragraph(part1.firstElement()+" - "+part1.lastElement()));
                        table.addCell("Range: ");
                        table.addCell(part1.firstElement()+" - "+part1.lastElement());
                        part1.clear();
                        document.add(table);
                        writer.println();
                        //System.out.println();
                        unitTotal = unitTotal -total;
                        //break;
                        //System.out.println(unitTotal);
                    }
                }

            }
            Rectangle rect= new Rectangle(577,825,18,15); // you can resize rectangle
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            rect.setBorderColor(BaseColor.BLACK);
            rect.setBorderWidth(1);
            document.add(rect);

        }catch (Exception e){
            System.out.println(e);
        }
        writer.close();
        document.close();
    }
}
