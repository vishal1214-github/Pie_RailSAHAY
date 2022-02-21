package servlet.AjaxData;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;

public class HeaderFooterEvent extends PdfPageEventHelper {

   ServletContext ctx=null;
   public HeaderFooterEvent(ServletContext ctx)
   {
	   this.ctx=ctx;
   }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
       // addHeader(writer);
        addFooter(writer,document);
    }

  

    private void addFooter(PdfWriter writer, Document document){
        PdfPTable footer = new PdfPTable(1);
        Date today = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String strDate = formatter.format(today);

        BaseColor gray1=new BaseColor(154,162,167);
            // set defaults
           // footer.setWidths(new int[]{1});
            //footer.setWidthPercentage(100);
           footer.setTotalWidth(620);
            footer.setLockedWidth(true);
        	//footer.setWidthPercentage(100);
            Paragraph ir=new Paragraph("INDIAN RAILWAYS",new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(51,51,51)));
            Paragraph slogan=new Paragraph("Transforming Supply Chains with Technology and Innovations...",new Font(FontFamily.HELVETICA, 11, Font.BOLDITALIC, new BaseColor(0,64,128)));
            slogan.setAlignment(Paragraph.ALIGN_CENTER);
          

            PdfPTable imgtable = new PdfPTable(1);
            imgtable.setWidthPercentage(100);
            imgtable.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            imgtable.getDefaultCell().setBorder(0);
            imgtable.getDefaultCell().setPadding(0f);
            //imgtable.getDefaultCell().setM(0f);
            //String absoluteDiskPath ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/slogan1.png";/**FOR LOCAL*/
             String absoluteDiskPath ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/slogan1.jpg";/**FOR TEST*/
            // String  absoluteDiskPath ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/slogan1.png";/**FOR Online*/
            
            //String relativeWebPath = "resources/icons/brochure_icons/slogan1.jpg";
          //  String absoluteDiskPath = ctx.getRealPath(relativeWebPath);
            Image imghead = null;
            Image image = null;
            
            try{
            	imghead=Image.getInstance(absoluteDiskPath);
            	image=Image.getInstance(imghead);;
            }
            catch(Exception e)
            {
            	//do nothing
            }
            imgtable.setTotalWidth(648);
            imgtable.setLockedWidth(true);
            imgtable.addCell( image);
            PdfPCell cell;
            
            cell=new PdfPCell(imgtable);
            cell.setBorder(Rectangle.NO_BORDER);

            footer.addCell(cell);
            
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setBackgroundColor( gray1);
            cell.setBorder(Rectangle.NO_BORDER);
            footer.getDefaultCell().setMinimumHeight(50f);
           
            footer.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            footer.getDefaultCell().setBackgroundColor(gray1);
         
             
            cell=new PdfPCell(new Phrase("*The figures and data shown in the brochure are as applicable on " + strDate,new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL,BaseColor.BLACK)));
            
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setBackgroundColor( gray1);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPadding(5f);
            cell.setPaddingTop(12f);
           cell.setPaddingBottom(12f);
            //footer.addCell(cell);
          
            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
         /* 
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                    slogan,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() +1 , 0);
                    */
            footer.writeSelectedRows(0, -1, 0, 82, canvas);
            canvas.endMarkedContentSequence();
       
    }

}