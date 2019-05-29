package admitcard;
 
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import code.DATABASE_CONNECTION;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class AdmitCard {
    public static void PDFMaker(){
        try{
            String logoPath = "C:\\Users\\OVI\\Dropbox\\Admission\\img\\logo.png";
            String path = "C:\\Users\\OVI\\Dropbox\\Admission\\web\\Admit Card\\";
            File file = new File("C:\\Users\\OVI\\Dropbox\\Admission\\web\\regImage\\");
            File[] files = file.listFiles();
            for(File temp : files){
                String tempName = temp.getName().replace(".jpg",".pdf");
                String temptempName = temp.getName().replace(".jpg","");
                String imagePath = temp.getAbsolutePath();
                String tempPath = path+tempName;
                //System.out.println(tempPath);

                //get student data
                Connection con = DATABASE_CONNECTION.get_connection();
                PreparedStatement statement = con.prepareStatement("SELECT * FROM REGISTER_STUDENT where REGID=?");
                statement.setLong(1,Long.parseLong(temptempName));
                ResultSet resultSet = statement.executeQuery();


                while(resultSet.next()){
                    //System.out.println(resultSet.getString(1));
                    //student data
                    String name = resultSet.getString(1);
                    String user = resultSet.getString(9);
                    String reg = ""+resultSet.getLong(8);
                    String phone = resultSet.getString(11);
                    String unit = "A";
                    String building = "Academic";
                    String room = "205C";
                    String time = "2.00pm";
                    //String information = "Name: "+name+"\n\n"+"Username: "+user+"\n\n"+"Reg: "+reg+"\n\n"+
                    //        "Phone: "+phone+"\n\n"+"Unit: "+unit+"\n\n"+"Location: "+building+
                    //        "\n\n"+"Room: "+room+"\n\n"+"Time: "+time;


                    //new table

                    PdfPTable newTable = new PdfPTable(2);
                    newTable.getDefaultCell().setBorder(0);
                    newTable.addCell("Name: ");
                    newTable.addCell(name);
                    newTable.addCell("Username: ");
                    newTable.addCell(user);
                    newTable.addCell("Reg. ID: ");
                    newTable.addCell(reg);
                    newTable.addCell("Phone: ");
                    newTable.addCell(phone);
                    newTable.addCell("Unit: ");
                    newTable.addCell(unit);
                    newTable.addCell("Location: ");
                    newTable.addCell(building);
                    newTable.addCell("Room: ");
                    newTable.addCell(room);
                    newTable.addCell("Time: ");
                    newTable.addCell(time);

//                    Image image = Image.getInstance(imgPath);
//                    image.scaleToFit(80,80);
//
//                    PdfPTable upTable = new PdfPTable(new float[]{1,1});
//                    upTable.getDefaultCell().setBorder(0);
//                    upTable.addCell(newTable);
//                    upTable.addCell(image);




                    Document document = new Document(PageSize.A4,30,30,50,50);
                    PdfWriter.getInstance(document, new FileOutputStream(tempPath));
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
                    design1.setSpacingAfter(20);
                    document.add(design1);

                    Paragraph admit = new Paragraph("Admit Card",
                            FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new CMYKColor(255,255,255,0)));
                    admit.setAlignment(1);
                    admit.setSpacingAfter(20);
                    document.add(admit);


                    //Paragraph info = new Paragraph(information,
                    //        FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD,new CMYKColor(255,255,255,0)));
                    Image photo = Image.getInstance(imagePath);
                    photo.scaleAbsolute(80f,80f);

                    //data table
                    PdfPTable data = new PdfPTable(new float[]{1,1});
                    data.getDefaultCell().setBorder(0);
                    //data.addCell(info);
                    data.addCell(newTable);
                    data.addCell(photo);
                    document.add(data);


                    Paragraph noticeBoard = new Paragraph("Important Notices",
                            FontFactory.getFont(FontFactory.COURIER,20,Font.BOLDITALIC,new CMYKColor(255,255,255,0)));
                    noticeBoard.setAlignment(1);
                    noticeBoard.setSpacingBefore(20);
                    noticeBoard.setSpacingAfter(20);
                    document.add(noticeBoard);
                    //design2
                    Paragraph design2 = new Paragraph("------------------------------------------------------",
                            FontFactory.getFont(FontFactory.COURIER,14,Font.BOLD,new CMYKColor(100,200,0,0)));
                    design2.setAlignment(1);
                    design2.setSpacingAfter(20);
                    document.add(design2);

                    //notice data
                    String text1 = "1. Always bring your admit card, pencil, pen and calculator.";
                    String text2 = "2. Do not come after starting the examination.";
                    String text3 = "3. Do not bring your Mobile, headphone, bluetooth and such kinds of electronic devices.";
                    //String text = text1+"\n"+text2+"\n"+text3;
                    //Paragraph notice = new Paragraph(text,
                    //        FontFactory.getFont(FontFactory.COURIER,14,Font.BOLDITALIC,new CMYKColor(255,255,255,0)));
                    PdfPTable notice = new PdfPTable(1);
                    notice.getDefaultCell().setBorder(0);
                    notice.addCell(text1);
                    notice.addCell(text2);
                    notice.addCell(text3);
                    document.add(notice);


                    //footer

                    Paragraph footer = new Paragraph("© 2017 www.bsmrstu.edu.bd. All rights reserved.",
                            FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD,new CMYKColor(255,255,255,0)));
                    footer.setAlignment(1);
                    footer.setSpacingBefore(30);
                    document.add(footer);


                    Rectangle rect= new Rectangle(577,825,18,15); // you can resize rectangle
                    rect.enableBorderSide(1);
                    rect.enableBorderSide(2);
                    rect.enableBorderSide(4);
                    rect.enableBorderSide(8);
                    rect.setBorderColor(BaseColor.BLACK);
                    rect.setBorderWidth(1);
                    document.add(rect);


                    document.close();
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
