package servlet.AppData;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class WatermarkPageEvent extends PdfPageEventHelper {

  //  Font FONT = new Font(Font.FontFamily.HELVETICA, 52, Font.BOLD, new GrayColor(0.85f));

   /* @Override
    public void onEndPage(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(writer.getDirectContentUnder(),
                Element.ALIGN_CENTER, new Phrase("CRIS", FONT),
                297.5f, 421, writer.getPageNumber() % 2 == 1 ? 45 : -45);
    }*/
    
   @Override
       public void onEndPage(PdfWriter pdfWriter, Document document) {
    
              System.out.println("Creating Waterwark Image in PDF");
              
              try {
                     
                     //Use this method if you want to get image from your Local system
                     //Image waterMarkImage = Image.getInstance("E:/tiger.jpg");
                     
                    /* String urlOfWaterMarKImage = "https://lh5.googleusercontent.com/"
                                  + "koRHmyNXUGLIjtkHFOJ-1tE5KKl-vZAW3AWLlynkAZSY3Z9m9HBn"
                                  + "boAPiakPTUiySX7W1I8xDwD4g49sD2JfgDDNJIPUdYVDWHfVomeh"
                                  + "FJHWUk1tDPYlpiB32eZ5TDCKUAAZUIQ"   ;*/
                     
                     //Get waterMarkImage from some URL
                     //Image waterMarkImage = Image.getInstance("D:\\logo.png");
                     Image waterMarkImage = Image.getInstance("/switch/applications/logo.png");
                 
                     //Get width and height of whole page
                     float pdfPageWidth = document.getPageSize().getWidth();
                     float pdfPageHeight =document.getPageSize().getHeight();
                    
                    float x= (pdfPageWidth/2)-200;
                    float y=(pdfPageHeight/2)-200;
                        
    
                     
                     //Set waterMarkImage on whole page
                     pdfWriter.getDirectContentUnder().addImage(waterMarkImage,
                                  400, 0, 0, 400, x, y);
    
              }catch(Exception e){
                     e.printStackTrace();
              }
       }
}

