package servlet.AjaxData;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;
/*
Purpose: Commodity/Sub-Commodity Brochure for FBD Portal
Created By: Nidhi Mahajan
On Date:18-01-2021
*/
public class GG_GnrtCmdtPDF extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Logger logger = FoisLogger.getLogger(GG_GnrtCmdtPDF.class.getName());
	String strData[]=null;
	private String strAddlData[][]=null;

	String si_UserID="";
	String si_ClntID="";
	String strRmrk="";
	private String strCallStts="S";
	private String strCallMesg="";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("In GG_GnrtCmdtPDF");
		response.setContentType("application/pdf");
		String strDocName="";

		try
		{

			String strMainCmdtCode=(String)request.getParameter("MainCmdtCode").trim();
			String strMainCmdtName=(String)request.getParameter("MainCmdtName").trim();
			String strSubCmdtCode=(String)request.getParameter("SubCmdtCode").trim();
			String strSubCmdtName=(String)request.getParameter("SubCmdtName").trim();

			if(!strSubCmdtCode.equals(""))
				strDocName="IR_"+strSubCmdtName+"_Transportation";
			else
				strDocName="IR_"+strMainCmdtCode+"_Transportation";

			ByteArrayOutputStream objk1	= gnrtProgressData(strMainCmdtCode,strMainCmdtName,strSubCmdtCode,strSubCmdtName);
			byte[] arrk1 = objk1.toByteArray();
			response.setHeader("Content-disposition", "attachment; filename=\""+strDocName+".pdf\"");
			OutputStream output = response.getOutputStream();
			logger.info("Got output");
			output.write(arrk1);
			logger.info("Write here");
			output.close();
		}
		catch(Exception e)
		{
			logger.info("Exception Occurred");
			e.printStackTrace();
		}
	}

	public ByteArrayOutputStream gnrtProgressData(String strMainCmdtCode, String strMainCmdtName, String strSubCmdtCode, String strSubCmdtName) throws Exception
    {
        logger.info("Entring gnrtProgressData");
        logger.info("Inputs Received:"+strMainCmdtCode+", "+strMainCmdtName+", "+strSubCmdtCode+", "+strSubCmdtName);
        strData=this.getCmdtBrocProp(strMainCmdtCode, strSubCmdtCode);
		ByteArrayOutputStream baos = null;

		Document doc=null;

		baos = new ByteArrayOutputStream();

		doc = new Document(PageSize.A4,0f,0f,10f,10f);
		doc.setMargins(0,0,0,30f);
		doc.addTitle("Coal");
		PdfWriter writer= PdfWriter.getInstance(doc, baos);
	    HeaderFooter event1 = new HeaderFooter();
		writer.setPageEvent(event1);
        doc.open();
        logger.info("doc open and then close");
        PdfPTable imgtable = new PdfPTable(1);
        imgtable.setWidthPercentage(100);
        imgtable.setHorizontalAlignment(Element.ALIGN_LEFT);

        imgtable.getDefaultCell().setBorder(0);
        imgtable.getDefaultCell().setPadding(0f);
        //imgtable.getDefaultCell().setM(0f);
       //  String absoluteDiskPath ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/BRO_"+strMainCmdtCode+".jpg";/**FOR LOCAL*/
        String absoluteDiskPath ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/BRO_"+strMainCmdtCode+".jpg";/**FOR TEST*/
        //String absoluteDiskPath ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/BRO_"+strMainCmdtCode+".jpg";/**FOR Online*/
        //String relativeWebPath = "resources/icons/brochure_icons/BRO_"+strMainCmdtCode+".jpg";
       // String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
        logger.info("image path:"+absoluteDiskPath);
        Image imghead = Image.getInstance(absoluteDiskPath);
        Image image = Image.getInstance(imghead);
        imgtable.addCell( image);
        doc.add(imgtable);
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);
        System.setProperty("http.agent", "Chrome"); // to avoid to throw exception in case didn't get image

        Font head1 = new Font(FontFamily.HELVETICA, 16, Font.BOLD, new BaseColor(154,162,167));
        Font head1a = new Font(FontFamily.HELVETICA, 13, Font.BOLD, new BaseColor(154,162,167));
        Font head2 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(155,5,15));
        logger.info("Starting Document Text Content");
        Chunk f1 = new Chunk("Reliable Deliveries", head1);
        Paragraph pp = new Paragraph();
        pp.setIndentationLeft(40);
        pp.setIndentationRight(30);
        pp.setAlignment(Element.ALIGN_LEFT);

        pp.setSpacingBefore(10f);
        Font fontDesc = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font fontDesc1 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        pp.add(f1);
        doc.add(pp);
        doc.add(Chunk.NEWLINE);
        Chunk introc= new Chunk(strData[10],fontDesc);
       	logger.info("Before Introduction Paragraph");

    	Paragraph introp = new Paragraph(introc);
    	introp.setAlignment(Element.ALIGN_JUSTIFIED);
    	introp.setIndentationLeft(40);
    	introp.setIndentationRight(30);
    	Chunk f2=null;
    	if(strSubCmdtCode.equals(""))
    		f2 = new Chunk("We offer multiple solutions for Transportation of " + strMainCmdtName+".",fontDesc);
    	else
    		f2 = new Chunk("We offer multiple solutions for Transportation of " + strMainCmdtName+" including "+strSubCmdtName+".",fontDesc);

        Chunk f3 = new Chunk("\nThere are "+strData[0]+" types of "+ strMainCmdtName +" products being transported by Indian Railways.",fontDesc);
        Paragraph p = new Paragraph();
	    p.setAlignment(Element.ALIGN_LEFT);
	    p.setIndentationLeft(40);
        p.setIndentationRight(30);
        p.add(f2);
        p.add(f3);
        doc.add(introp);
        doc.add(Chunk.NEWLINE);
	    doc.add(p);
	    doc.add(Chunk.NEWLINE);

	    logger.info("Checking for e-Drishti Statistics");
	    if(!strData[1].equals(""))
	    {
	    if(Double.parseDouble(strData[1])>0)
	    {
	    	Font ff= new Font(FontFamily.HELVETICA, 11, Font.BOLDITALIC, BaseColor.BLACK);
	    	Font ff1= new Font(FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.BLACK);
	    	logger.info("3 " +strData[1]);
	        PdfPCell cell;
	        Chunk c1;
	        Chunk c2;
	        Paragraph stp;
	        Font tblFont=new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
	        PdfPTable statTbl = new PdfPTable(4);
	        statTbl.setWidthPercentage(100);
	        statTbl.setHorizontalAlignment(Element.ALIGN_LEFT);
	        logger.info("4 ");
	        statTbl.setWidths(new int[]{1,1,1,1});

			 statTbl.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			 statTbl.getDefaultCell().setMinimumHeight(20f);
		// statTbl.getDefaultCell().setBorder( PdfPCell.LEFT | PdfPCell.RIGHT) ;
		// statTbl.getDefaultCell().setBorderWidthTop(1f);
		// statTbl.getDefaultCell().setBorderWidthBottom(1f);
		// statTbl.getDefaultCell().setPadding(10f);
		 statTbl.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        Chunk prd= new Chunk("We are proud to touch new heights in loading of "+ strMainCmdtName,ff);
        Chunk mt= new Chunk("*Figures in Million Tonnes (MT)",ff1);
        c1=new Chunk(strData[1],head2);
		 Chunk c5=new Chunk("Yesterday",tblFont);
		 c2=new Chunk(strData[2],head2);
		 Chunk c6=new Chunk("Last 7 Days",tblFont);
		 Chunk c3=new Chunk(strData[3],head2);
		 Chunk c7=new Chunk("Month To Date",tblFont);
		 Chunk c4=new Chunk(strData[4],head2);
		 Chunk c8=new Chunk("Year To Date",tblFont);
		 stp=new Paragraph(prd);
		 stp.setIndentationLeft(40f);
		 doc.add(stp);
		/*
		 cell = new PdfPCell(stp);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        cell.setColspan(4);
        cell.setBorder(PdfPCell.TOP);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        statTbl.addCell(cell);
*/

		 stp=new Paragraph(c1);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(255,255,255));
		 cell.setBackgroundColor(BaseColor.WHITE);
		 statTbl.addCell(cell);
		 stp=new Paragraph(c2);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(255,255,255));
	     cell.setBackgroundColor(BaseColor.WHITE);
		 statTbl.addCell(cell);
		 stp=new Paragraph(c3);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(255,255,255));
	     cell.setBackgroundColor(BaseColor.WHITE);
		 statTbl.addCell(cell);
		 stp=new Paragraph(c4);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(255,255,255));
	     cell.setBackgroundColor(BaseColor.WHITE);
		 statTbl.addCell(cell);

		 stp=new Paragraph(c5);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(160,160,160));
	     cell.setBackgroundColor(new BaseColor(242,242,242));
	     cell.setMinimumHeight(18f);
		 statTbl.addCell(cell);
		 stp=new Paragraph(c6);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(160,160,160));
	     cell.setBackgroundColor(new BaseColor(242,242,242));
		 statTbl.addCell(cell);
		 stp=new Paragraph(c7);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(160,160,160));
	     cell.setBackgroundColor(new BaseColor(242,242,242));
		 statTbl.addCell(cell);
		 stp=new Paragraph(c8);
		 cell = new PdfPCell(stp);
		 cell.setBorder(PdfPCell.TOP);
		 cell.setBorderColor(new BaseColor(160,160,160));
	     cell.setBackgroundColor(new BaseColor(242,242,242));
		 statTbl.addCell(cell);

        Paragraph StPr= new Paragraph();
	    StPr.setIndentationLeft(40);
	    StPr.setIndentationRight(30);
	    statTbl.setSpacingBefore(1.01f);       // Space Before table starts, like margin-top in CSS
	    statTbl.setSpacingAfter(1.0f);
	   // Chunk prd= new Chunk(statTbl);
		   // prd.setBackground(new BaseColor(154,162,167),5,10,10,10);
	    statTbl.setSpacingBefore(10);
	    StPr.add(statTbl);
	    doc.add(StPr);
	    Paragraph mtPr=new Paragraph(mt);
	    mtPr.setAlignment(Element.ALIGN_RIGHT);
	    mtPr.setIndentationRight(30);
	    doc.add(mtPr);
	    }
	    }

	    doc.add(Chunk.NEWLINE);
        Font head3 = new Font(FontFamily.HELVETICA, 13, Font.BOLD, new BaseColor(155,5,15));
        Font head3a = new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(155,5,15));
        Font head4 = new Font(FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.BLACK);
	    Chunk adc= new Chunk("Advantages For You",head1a);
	    Chunk adv1=new Chunk("\n \u2022  High-performance transport built on smart transport concepts",fontDesc);
	    Chunk adv2=new Chunk("\n \u2022  Product purity, ensured by our fleet of specialised wagons",fontDesc);
	    Chunk adv3=new Chunk("\n \u2022  Simple integration of our logistical services into your logistics processes",fontDesc);
	    Chunk adv4=new Chunk("\n \u2022  High level of efficiency due to precisely scheduled turnaround cycles",fontDesc);
	    Chunk adv5=new Chunk("\n \u2022  Reasonable time limits for Loading and Unloading",fontDesc);
	    Paragraph adh =new Paragraph(adc);
	    Paragraph adp=new Paragraph();
	    adp.add(adv1);
	    adp.add(adv2);
	    adp.add(adv3);
	    adp.add(adv4);
	    adp.add(adv5);
	    adh.setIndentationLeft(40);
        adh.setIndentationRight(30);
        adp.setIndentationLeft(40);
        adp.setIndentationRight(30);
	    doc.add(adh);
	    doc.add(adp);

	   doc.setMargins(10f,10f,25f,20f);
	   doc.newPage();
    	Paragraph p1 = new Paragraph("WHAT WE OFFER", head3);
    	 p1.setIndentationLeft(40);
         p1.setIndentationRight(30);
         p1.setSpacingBefore(50f);
    	 doc.add(p1);
    	// doc.add(Chunk.NEWLINE);
    	 String relativeWebPath1;
    	 String absoluteDiskPath1;
    	 Image imghead1;
    	 Image image1;
    	 PdfPCell cell;
    Font tblFont=new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
    Font tblFont1=new Font(FontFamily.HELVETICA, 8, Font.NORMAL,new BaseColor(82, 86, 89));

    if(strSubCmdtCode.equals(""))
    {
    PdfPTable table = new PdfPTable(5);
    Chunk tc1;
    Chunk tc2;
    table.setWidthPercentage(100);
    table.setHorizontalAlignment(Element.ALIGN_LEFT);
    table.setWidths(new int[]{1,3,1,1,3});
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    Paragraph tblp;
        // absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_trml.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_trml.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_trml.png";/**FOR Online*/
        
    //relativeWebPath1 = "resources/icons/brochure_icons/bro_trml.png";
    //absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
     logger.info("image path:"+absoluteDiskPath1);
     imghead1 = Image.getInstance(absoluteDiskPath1);
     image1 = Image.getInstance(imghead1);
     table.addCell( image1);
   tc1= new Chunk( "Upto "+strData[5]+" Handling Terminals",tblFont);
   tc2= new Chunk( " An extensive network coverage of more than 68000 Kms across the country with facility of "+strMainCmdtName+" transportation from almost all major districts and states.",tblFont1);
   tc1.setLineHeight(11);
   tc2.setLineHeight(11);
   tblp=new Paragraph();
   tblp.add(tc1);
   tblp.add(tc2);
   tblp.setAlignment(Element.ALIGN_JUSTIFIED);
  // tblp.setLeading(15);
   cell = new PdfPCell(tblp);
   cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
   cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
   cell.setBorder(Rectangle.NO_BORDER);
   cell.setMinimumHeight(50f);
   cell.setPadding(10f);
   cell.setPaddingTop(0f);
   table.addCell(cell);
   table.addCell(new Phrase ("     "));
       // absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_wagon.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_wagon.png";/**FOR TEST*/
        // absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_wagon.png";/**FOR Online*/
        
   //relativeWebPath1 = "resources/icons/brochure_icons/bro_wagon.png";
  // absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
   logger.info("image path:"+absoluteDiskPath1);
   imghead1 = Image.getInstance(absoluteDiskPath1);
   image1 = Image.getInstance(imghead1);
   table.addCell(image1);
    tc1= new Chunk( "Upto "+strData[6]+" distinct Wagon Types ",tblFont);
    tc2= new Chunk( "Thanks to an extensive fleet of wagons adapted to different customer requirements with distinctive loadability features for loading "+strMainCmdtName+".",tblFont1);
    tc1.setLineHeight(11);
    tc2.setLineHeight(11);
    tblp=new Paragraph();
    tblp.add(tc1);
    tblp.add(tc2);
    tblp.setAlignment(Element.ALIGN_JUSTIFIED);
   // tblp.setMultipliedLeading(2);
    cell = new PdfPCell(tblp);
    cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setMinimumHeight(50f);
    cell.setPadding(10f);
    cell.setPaddingTop(0f);
    table.addCell(cell);

   // doc.setMargins(0,0,30,30);
    table.getDefaultCell().setPaddingTop(20f);
   // doc.newPage();
   //absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_incentive.png";/**FOR LOCAL*/
    absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_incentive.png";/**FOR TEST*/
   //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_incentive.png";/**FOR Online*/
   
   // relativeWebPath1 = "resources/icons/brochure_icons/bro_incentive.png";
   // absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
    logger.info("image path:"+absoluteDiskPath1);
    imghead1 = Image.getInstance(absoluteDiskPath1);
    image1 = Image.getInstance(imghead1);
       //   doc.newPage();
        //  doc.setMargins(0,0,0,0);
     tc1= new Chunk("Upto "+ strData[7]+" Freight Incentive Schemes",tblFont);
     tc2= new Chunk( " Unique concession and rebate schemes on "+strMainCmdtName+" transportation to facilitate your logistics requirements with Indian Railways.",tblFont1);
     tc1.setLineHeight(11);
     tc2.setLineHeight(11);
     tblp=new Paragraph();
     tblp.add(tc1);
     tblp.add(tc2);
     tblp.setAlignment(Element.ALIGN_JUSTIFIED);
     cell = new PdfPCell(tblp);
     cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
     cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
     cell.setBorder(Rectangle.NO_BORDER);
     cell.setMinimumHeight(50f);
     cell.setPadding(10f);
    // cell.setPaddingTop(20f);
     table.addCell( image1);
     table.addCell(cell);
     table.addCell(new Phrase ("     "));
       // absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_rateslab.png";/**FOR LOCAL*/
        absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_rateslab.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_rateslab.png";/**FOR Online*/
        
     //relativeWebPath1 = "resources/icons/brochure_icons/bro_rateslab.png";;
     // absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
       logger.info("image path:"+absoluteDiskPath1);
       imghead1 = Image.getInstance(absoluteDiskPath1);
       image1 = Image.getInstance(imghead1);
       table.addCell( image1);
       tc1= new Chunk( "Upto "+strData[8]+" Rate Slab",tblFont);
       tc2= new Chunk( " Distinctive freight classes and flexible fares for different distance ranges for "+strMainCmdtName+" transportation over Indian Railways network.",tblFont1);
       tc1.setLineHeight(11);
       tc2.setLineHeight(11);
       tblp=new Paragraph();
       tblp.add(tc1);
       tblp.add(tc2);
       tblp.setAlignment(Element.ALIGN_JUSTIFIED);
       cell = new PdfPCell(tblp);
       cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
       cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
       cell.setBorder(Rectangle.NO_BORDER);
       cell.setMinimumHeight(50f);
       cell.setPadding(10f);
       //cell.setPaddingTop(20f);
       table.addCell(cell);
        //absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_twopt.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_twopt.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_twopt.png";/**FOR Online*/
        
       //relativeWebPath1 = "resources/icons/brochure_icons/bro_twopt.png";
      // absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
         logger.info("image path:"+absoluteDiskPath1);
         imghead1 = Image.getInstance(absoluteDiskPath1);
         image1 = Image.getInstance(imghead1);

         tc1= new Chunk("Upto "+strData[9]+" Two Point Combinations",tblFont);
         tc2= new Chunk( " facility to transport your cargo in a Train for more than one destinations using Two Point/Multi Point schemes while availing Train load freight rates.",tblFont1);
         tc1.setLineHeight(11);
         tc2.setLineHeight(11);
         tblp=new Paragraph();
         tblp.add(tc1);
         tblp.add(tc2);
         tblp.setAlignment(Element.ALIGN_JUSTIFIED);

         cell = new PdfPCell(tblp);
         cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
         cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
         cell.setBorder(Rectangle.NO_BORDER);
         cell.setMinimumHeight(50f);
         cell.setPadding(10f);
      //   cell.setPaddingTop(20f);
        // cell.setPaddingBottom(10f);
         table.addCell( image1);
         table.addCell(cell);
         table.addCell(new Phrase ("     "));
       // absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_support.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_support.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_support.png";/**FOR Online*/
        
       //  relativeWebPath1 = "resources/icons/brochure_icons/bro_support.png";
      //   absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
          logger.info("image path:"+absoluteDiskPath1);
          imghead1 = Image.getInstance(absoluteDiskPath1);
          image1 = Image.getInstance(imghead1);

         tc1= new Chunk("Comprehensive Customer Support ",tblFont);
         tc2= new Chunk("Round the clock, 365 days a year availability of communication means to contact Indian Railways Freight Officials for all your queries and concerns.",tblFont1);
         tc1.setLineHeight(11);
         tc2.setLineHeight(11);
         tblp=new Paragraph();
         tblp.add(tc1);
         tblp.add(tc2);
         tblp.setAlignment(Element.ALIGN_JUSTIFIED);
         cell = new PdfPCell(tblp);
         cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
         cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
         cell.setBorder(Rectangle.NO_BORDER);
         cell.setMinimumHeight(50f);
         cell.setPadding(10f);
       //  cell.setPaddingTop(20f);
         table.addCell( image1);
         table.addCell(cell);
   table.setSpacingBefore(15f);       // Space Before table starts, like margin-top in CSS
   // table.setSpacingAfter(1.0f);
    Paragraph pt = new Paragraph();

    pt.setIndentationLeft(30);
    pt.setIndentationRight(30);
   // doc.add(Chunk.NEWLINE);
    pt.add(table);
  //  doc.add(Chunk.NEWLINE);
    doc.add(pt);
    }
    else
    {
        PdfPTable table = new PdfPTable(5);
        Chunk tc1;
        Chunk tc2;
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{1,3,1,1,3});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        Paragraph tblp;
        //absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_trml.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_trml.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_trml.png";/**FOR Online*/
        
        //relativeWebPath1 = "resources/icons/brochure_icons/bro_trml.png";
       // absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
         logger.info("image path:"+absoluteDiskPath1);
         imghead1 = Image.getInstance(absoluteDiskPath1);
         image1 = Image.getInstance(imghead1);
         table.addCell( image1);
       tc1= new Chunk(strData[5]+" Handling Terminals",tblFont);
       tc2= new Chunk( " An extensive network coverage of more than 68000 Kms across the country with facility of "+strSubCmdtName+" transportation from almost all major districts and states.",tblFont1);
       tc1.setLineHeight(11);
       tc2.setLineHeight(11);
       tblp=new Paragraph();
       tblp.add(tc1);
       tblp.add(tc2);
       tblp.setAlignment(Element.ALIGN_JUSTIFIED);
      // tblp.setLeading(15);
       cell = new PdfPCell(tblp);
       cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
       cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
       cell.setBorder(Rectangle.NO_BORDER);
       cell.setMinimumHeight(50f);
       cell.setPadding(10f);
       cell.setPaddingTop(0f);
       table.addCell(cell);
       table.addCell(new Phrase ("     "));
      //  absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_wagon.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_wagon.png";/**FOR TEST*/
        // absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_wagon.png";/**FOR Online*/
        
      // relativeWebPath1 = "resources/icons/brochure_icons/bro_wagon.png";
      // absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
       logger.info("image path:"+absoluteDiskPath1);
       imghead1 = Image.getInstance(absoluteDiskPath1);
       image1 = Image.getInstance(imghead1);
       table.addCell(image1);
        tc1= new Chunk( strData[6]+" distinct Wagon Types ",tblFont);
        tc2= new Chunk( "Thanks to an extensive fleet of wagons adapted to different customer requirements with distinctive loadability features for loading "+strSubCmdtName+".",tblFont1);
        tc1.setLineHeight(11);
        tc2.setLineHeight(11);
        tblp=new Paragraph();
        tblp.add(tc1);
        tblp.add(tc2);
        tblp.setAlignment(Element.ALIGN_JUSTIFIED);
       // tblp.setMultipliedLeading(2);
        cell = new PdfPCell(tblp);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setMinimumHeight(50f);
        cell.setPadding(10f);
        cell.setPaddingTop(0f);
        table.addCell(cell);

       // doc.setMargins(0,0,30,30);
        table.getDefaultCell().setPaddingTop(20f);
       // doc.newPage();
      // absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_incentive.png";/**FOR LOCAL*/
       absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_incentive.png";/**FOR TEST*/
       //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_incentive.png";/**FOR Online*/
       
      //  relativeWebPath1 = "resources/icons/brochure_icons/bro_incentive.png";
      //  absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
        logger.info("image path:"+absoluteDiskPath1);
        imghead1 = Image.getInstance(absoluteDiskPath1);
        image1 = Image.getInstance(imghead1);
           //   doc.newPage();
            //  doc.setMargins(0,0,0,0);
         tc1= new Chunk(strData[7]+" Freight Incentive Schemes",tblFont);
         tc2= new Chunk( " Unique concession and rebate schemes on "+strSubCmdtName+" transportation to facilitate your logistics requirements with Indian Railways.",tblFont1);
         tc1.setLineHeight(11);
         tc2.setLineHeight(11);
         tblp=new Paragraph();
         tblp.add(tc1);
         tblp.add(tc2);
         tblp.setAlignment(Element.ALIGN_JUSTIFIED);
         cell = new PdfPCell(tblp);
         cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
         cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
         cell.setBorder(Rectangle.NO_BORDER);
         cell.setMinimumHeight(50f);
         cell.setPadding(10f);
        // cell.setPaddingTop(20f);
         table.addCell( image1);
         table.addCell(cell);
         table.addCell(new Phrase ("     "));
       // absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_rateslab.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_rateslab.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_rateslab.png";/**FOR Online*/
        
        // relativeWebPath1 = "resources/icons/brochure_icons/bro_rateslab.png";;
        //  absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
           logger.info("image path:"+absoluteDiskPath1);
           imghead1 = Image.getInstance(absoluteDiskPath1);
           image1 = Image.getInstance(imghead1);
           table.addCell( image1);
           tc1= new Chunk(strData[8]+" Rate Slab",tblFont);
           tc2= new Chunk( " Distinctive freight classes and flexible fares for different distance ranges for "+strSubCmdtName+" transportation over Indian Railways network.",tblFont1);
           tc1.setLineHeight(11);
           tc2.setLineHeight(11);
           tblp=new Paragraph();
           tblp.add(tc1);
           tblp.add(tc2);
           tblp.setAlignment(Element.ALIGN_JUSTIFIED);
           cell = new PdfPCell(tblp);
           cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
           cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
           cell.setBorder(Rectangle.NO_BORDER);
           cell.setMinimumHeight(50f);
           cell.setPadding(10f);
           //cell.setPaddingTop(20f);
           table.addCell(cell);
        //absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_twopt.png";/**FOR LOCAL*/
        absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_twopt.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_twopt.png";/**FOR Online*/
        
           //relativeWebPath1 = "resources/icons/brochure_icons/bro_twopt.png";
         //  absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
             logger.info("image path:"+absoluteDiskPath1);
             imghead1 = Image.getInstance(absoluteDiskPath1);
             image1 = Image.getInstance(imghead1);

             tc1= new Chunk(strData[9]+" Two Point Combinations",tblFont);
             tc2= new Chunk( " facility to transport your cargo in a Train for more than one destinations using Two Point/Multi Point schemes while availing Train load freight rates.",tblFont1);
             tc1.setLineHeight(11);
             tc2.setLineHeight(11);
             tblp=new Paragraph();
             tblp.add(tc1);
             tblp.add(tc2);
             tblp.setAlignment(Element.ALIGN_JUSTIFIED);

             cell = new PdfPCell(tblp);
             cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
             cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
             cell.setBorder(Rectangle.NO_BORDER);
             cell.setMinimumHeight(50f);
             cell.setPadding(10f);
          //   cell.setPaddingTop(20f);
            // cell.setPaddingBottom(10f);
             table.addCell( image1);
             table.addCell(cell);
             table.addCell(new Phrase ("     "));
        //absoluteDiskPath1 ="C:/JDeveloper/mywork/RailSAHAY/RailSAHAY/public_html/resources/icons/brochure_icons/bro_support.png";/**FOR LOCAL*/
         absoluteDiskPath1 ="/switch/Applications/RailSAHAY/resources/icons/brochure_icons/bro_support.png";/**FOR TEST*/
        //  absoluteDiskPath1 ="/switch/applications/FOISPORTAL/RailSAHAY/resources/icons/brochure_icons/bro_support.png";/**FOR Online*/
        
            // relativeWebPath1 = "resources/icons/brochure_icons/bro_support.png";
        //     absoluteDiskPath1 = getServletContext().getRealPath(relativeWebPath1);
              logger.info("image path:"+absoluteDiskPath1);
              imghead1 = Image.getInstance(absoluteDiskPath1);
              image1 = Image.getInstance(imghead1);

             tc1= new Chunk("Comprehensive Customer Support ",tblFont);
             tc2= new Chunk("Round the clock, 365 days a year availability of communication means to contact Indian Railways Freight Officials for all your queries and concerns.",tblFont1);
             tc1.setLineHeight(11);
             tc2.setLineHeight(11);
             tblp=new Paragraph();
             tblp.add(tc1);
             tblp.add(tc2);
             tblp.setAlignment(Element.ALIGN_JUSTIFIED);
             cell = new PdfPCell(tblp);
             cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
             cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
             cell.setBorder(Rectangle.NO_BORDER);
             cell.setMinimumHeight(50f);
             cell.setPadding(10f);
           //  cell.setPaddingTop(20f);
             table.addCell( image1);
             table.addCell(cell);
       table.setSpacingBefore(15f);       // Space Before table starts, like margin-top in CSS
       // table.setSpacingAfter(1.0f);
        Paragraph pt = new Paragraph();

        pt.setIndentationLeft(30);
        pt.setIndentationRight(30);
       // doc.add(Chunk.NEWLINE);
        pt.add(table);
      //  doc.add(Chunk.NEWLINE);
        doc.add(pt);
    }


    Chunk dtlh= new Chunk("For detail and information on:",head1a);
    Chunk dtlc1=new Chunk("\n \u2022  Booking Process",fontDesc1);
    Chunk dtlc2=new Chunk("\n \u2022  Expected Freight Charges and Estimate Transit time",fontDesc1);
    Chunk dtlc3=new Chunk("\n \u2022  First & Last Mile Logistics Service Providers",fontDesc1);
    Chunk dtlc4=new Chunk("\n \u2022  Timetabled and Mini Rake Services",fontDesc);
    Chunk dtlc5=new Chunk("\n \u2022  e-Registration of Indents",fontDesc1);
    Chunk dtlc6=new Chunk("\n \u2022  Indents Pendency and Rake Allotments",fontDesc1);
    Chunk dtlc7=new Chunk("\n \u2022  Consignment Track & Trace",fontDesc1);
    Chunk dtlc8=new Chunk("\n \u2022  Process Detail to Own a Terminal",fontDesc1);
    Chunk dtlc9=new Chunk("\n \u2022  Process Detail for Investment in Rolling Stock",fontDesc1);
    Chunk dtlc10=new Chunk("\n \u2022  Facility for Raising and Tracking Service Requests",fontDesc1);

    Paragraph dtlp =new Paragraph();
    dtlp.setSpacingBefore(10f);
    dtlp.add(dtlh);
    dtlp.add(Chunk.NEWLINE);
    dtlp.add(dtlc1);
    dtlp.add(dtlc2);
    dtlp.add(dtlc3);
    dtlp.add(dtlc4);
    dtlp.add(dtlc5);
    dtlp.add(dtlc6);
    dtlp.add(dtlc7);
    dtlp.add(dtlc8);
    dtlp.add(dtlc9);
    dtlp.add(dtlc10);
    dtlp.setIndentationLeft(40);
    dtlp.setIndentationRight(30);
    doc.add(Chunk.NEWLINE);
    doc.add(dtlp);
    doc.add(Chunk.NEWLINE);

    Chunk infoc= new Chunk("Please visit ", fontDesc1);
    Chunk infoc1= new Chunk("\nIndian Railways Freight Business Development Portal",new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK));
    Chunk infoc3= new Chunk("\nhttps://www.fois.indianrail.gov.in/RailSAHAY",new Font(FontFamily.HELVETICA, 11, Font.BOLD, new BaseColor(155,5,15)));
    Paragraph infop =new Paragraph();
    infop.add(infoc);
    infop.add(infoc1);
    infop.add(infoc3);
    infop.setIndentationLeft(40);
    infop.setIndentationRight(30);
    doc.add(infop);


    HeaderFooterEvent event = new HeaderFooterEvent(getServletContext());
    writer.setPageEvent(event);
    doc.close();

            logger.info("Exiting gnrtProgressData PDF of Freight Calculator");
            return baos;
    }

	public PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}

    public String[] getCmdtBrocProp(String si_MainCmdtCode, String si_SubCmdtCode)
    {
            String strData[] = null;   // Variable to be returned as output of function

            logger.info("Entering getCmdtBrocProp....");
            logger.info("Function called with inputs :");
            logger.info("si_MainCmdt  #"+si_MainCmdtCode+"#");
            logger.info("si_SubCmdt  #"+si_SubCmdtCode+"#");
            FOISTuxedo RQSAHAYMNTR  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYMNTR = new FOISTuxedo();
                    logger.info("Client Object Got.");
            }
            catch (Exception ne)
            {
                    logger.fatal("Unable to get the Client Object: " + ne.toString());
                    strCallStts="F";
                    strCallMesg="Unable to get Client Object";
                    return null;
            }

            try
            {
                    RQSAHAYMNTR.tuxInit("RQSAHAYMNTR");
                    RQSAHAYMNTR.setString("F_USERID",               0,      "SYSTEM");
                    RQSAHAYMNTR.setString("F_CLNTID",               0,      "1.1.1.1");
                    RQSAHAYMNTR.setString("F_FLAG",                 0,      "CMDT_BROCHURE_PROP");
                    RQSAHAYMNTR.setString("F_HLDGZONE1",             0,      si_MainCmdtCode);
                    RQSAHAYMNTR.setString("F_HLDGZONE1",             1,      si_SubCmdtCode);
            }
            catch(Exception e)
            {
                    logger.fatal("Unable to write to buffer : " + e.toString());
                    strCallStts="F";
                    strCallMesg="Unable to write to buffer";
                    return null;
            }
            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQSAHAYMNTR.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    strCallStts="F";
                    strCallMesg="Exception in Service Call";
                    return null;
            }
                    //*************************************************************************************
                                                            //END of WTC calling
                    //*************************************************************************************
            // Checking For Any Error from Service
            String erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYMNTR.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode1 != null && (!erorCode1.equals("")))
            {
                    logger.fatal(erorCode1);
                    strCallStts="F";
                    strCallMesg=erorCode1;
                    return null;
            }

        strData=new String[11];
        logger.info("Start fetching commodity brochure data");
        for(int i=0; i<11; i++)
        {
            try
            {
                    strData[i]           = RQSAHAYMNTR.getStringItemDef("F_ORDRBY1",i,"0").trim();
                    logger.info(strData[i]);
            }
            catch(Exception d)
            {
                logger.fatal("Problem in Calling Service RQSAHAYMNTR and filling data into array" + d.toString());
                strCallStts="F";
                strCallMesg="Data Problem";
                return null;
            }
        }
        try
        {
                RQSAHAYMNTR.endSession();
        }
        catch(Exception e)
        {
                logger.fatal("Error In End Session:" + e.toString());
                strCallStts="F";
                strCallMesg="Session Problem";
                return null;
        }
        return strData;
    }
}
