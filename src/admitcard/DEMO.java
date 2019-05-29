package admitcard;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class DEMO {
    public static void main(String[] args){
        try{
            String tempPath = "C:\\Users\\OVI\\Desktop\\new.pdf";
            String imgPath = "C:\\Users\\OVI\\Desktop\\Crop Image\\DSC_5439.jpg";
            Document document = new Document(PageSize.A4,20,20,50,50);
            PdfWriter.getInstance(document, new FileOutputStream(tempPath));
            document.open();

            PdfPTable newTable = new PdfPTable(2);
            newTable.getDefaultCell().setBorder(0);
            newTable.addCell("Name: ");
            newTable.addCell("Avijit Bairagi");
            newTable.addCell("ID: ");
            newTable.addCell("20121201012");

            Image image = Image.getInstance(imgPath);
            image.scaleToFit(80,80);

            PdfPTable upTable = new PdfPTable(new float[]{1,1});
            upTable.getDefaultCell().setBorder(0);
            upTable.addCell(newTable);
            upTable.addCell(image);

            //document.add(newTable);

            document.add(upTable);

            Rectangle rect= new Rectangle(577,825,18,15); // you can resize rectangle
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            rect.setBorderColor(BaseColor.BLACK);
            rect.setBorderWidth(1);
            document.add(rect);

            document.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
