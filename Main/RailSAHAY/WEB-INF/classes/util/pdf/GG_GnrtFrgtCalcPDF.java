package util.pdf;

import java.io.ByteArrayOutputStream;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import tuxedo.FOISTuxedo;
import util.logger.FoisLogger;

import java.io.OutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import util.GG_Validate;

public class GG_GnrtFrgtCalcPDF extends HttpServlet 
{
	static Logger logger = FoisLogger.getLogger(GG_GnrtFrgtCalcPDF.class.getName());
	JSONObject strInptVal;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.info("In GG_GnrtFrgtCalcPDF");
		String inputval =  (String) request.getParameter("Param").trim();
		logger.info("Input:"+inputval);
		response.setContentType("application/pdf");
		String strDocName="";
		strDocName="Freight_Calculator";
		try
		{
			logger.info("Convertinginput to JSON format");
			strInptVal		  =	new JSONObject(inputval);
			logger.info("strInptVal:"+strInptVal);
	        JSONObject resObj = new JSONObject();
	        resObj            = getFrgtCalc(strInptVal);
	        
			ByteArrayOutputStream objk1	= gnrtProgressData(resObj);
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
	
	public ByteArrayOutputStream gnrtProgressData(JSONObject inpVal) throws Exception
    {
        logger.info("Entring gnrtProgressData");   

        String date     =   inpVal.getString("date");
        String sttnfrom =   inpVal.getString("sttnfrom");
       // sttnfrom		=	sttnfrom.substring(sttnfrom.lastIndexOf("-")+1).trim();
        String sttnto   =   inpVal.getString("sttnto");
        //sttnto			=	sttnto.substring(sttnto.lastIndexOf("-")+1).trim();
        String wagontype=   inpVal.getString("wagontype");
        String cmdtname =   inpVal.getString("cmdtname");
        String rkpm     =   inpVal.getString("rkpm");
        String wgonnumbs=   inpVal.getString("wgonnumbs");
        if(wgonnumbs.equals(""))
            wgonnumbs   = "0";
        
        logger.info("date:"+date); 
        logger.info("sttnfrom:"+sttnfrom);        
        logger.info("sttnto:"+sttnto);        
        logger.info("wagontype:"+wagontype);        
        logger.info("cmdtname:"+cmdtname);       
        logger.info("rkpm:"+rkpm);
        logger.info("wgonnumbs:"+wgonnumbs);
        
		ByteArrayOutputStream baos = null;

		Document doc=null;		
		
		baos = new ByteArrayOutputStream();

		doc = new Document(PageSize.A4,10f,10f,10f,10f);
		doc.setMargins(30, 30, 30, 30);
		doc.addTitle("Freight Calculator");
		PdfWriter.getInstance(doc, baos);
        logger.info("printed");
        doc.open();
        logger.info("doc open and then close");
        

        Font fois = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
        Chunk f1 = new Chunk("INDIAN RAILWAYS FREIGHT SERVICES", fois);
        Paragraph pp = new Paragraph();
        pp.setAlignment(Element.ALIGN_RIGHT);
        pp.add(f1);     
        doc.add(pp);
        
        String relativeWebPath = "resources/images/rail.gif";
        String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
        logger.info("image path:"+absoluteDiskPath);
        Image imghead = Image.getInstance(absoluteDiskPath);
        Image image = Image.getInstance(imghead); 
        image.setAbsolutePosition(30f, 780f);
        doc.add(image);
        System.setProperty("http.agent", "Chrome"); // to avoid to throw exception in case didn't get image
        
/*       
        Font ir = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0));
        Chunk ir1 = new Chunk("RAIL ", ir);
        Font sug = new Font(FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0));
        Chunk sugam = new Chunk("SAHAY", sug);
 */
 //       Font f = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
 //       Chunk fois = new Chunk("FOIS", f);        

//       Chunk glue = new Chunk(new VerticalPositionMark());
//         p33.add(glue);
//         p33.add(fois);
/*           Phrase p33 = new Phrase();
           p33.add(ir1);
           p33.add(sugam);
           doc.add(p33);
 */              

/*        Font ir = new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
        Chunk ir1 = new Chunk("RAIL SAHAY", ir);                
        Paragraph para = new Paragraph();
        para.setAlignment(Element.ALIGN_RIGHT);
	    para.add(ir1);	  
	    doc.add(para);	    
 */        

    /*    Rectangle rect1 = new Rectangle(0, 785, 650, 876); //upper rectangle
        rect1.setBackgroundColor(BaseColor.PINK);
        doc.add(rect1);
       
        Rectangle rect = new Rectangle(0, 0, 600, 36);	//lower rectangle
        rect.setBackgroundColor(BaseColor.PINK);
        doc.add(rect);
    */        
/*
        Paragraph pp2 = new Paragraph("\n",new Font(FontFamily.HELVETICA, 24));
        pp2.setAlignment(Element.ALIGN_CENTER);
	    doc.add(pp2);
*/	    
        Font blue = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        Chunk blueText = new Chunk("\nEXPECTED FREIGHT CHARGES", blue);
                
        Paragraph p = new Paragraph();
	    p.setAlignment(Element.ALIGN_CENTER);
	    p.add(blueText);	    
	    doc.add(p);
                
        Paragraph datep = new Paragraph("Date:"+inpVal.getString("date"),new Font(FontFamily.HELVETICA, 10));
        datep.setAlignment(Element.ALIGN_RIGHT);
	    doc.add(datep);

        Paragraph p2 = new Paragraph("\n",new Font(FontFamily.HELVETICA, 9));
        p2.setAlignment(Element.ALIGN_CENTER);
	    doc.add(p2);
         
        Paragraph sttnfromp = new Paragraph("Station From: "+inpVal.getString("sttnfrom")+"\n"	+	
        								"	Station To: "+inpVal.getString("sttnto")+"\n"		+
        								"	Wagon Type: "+inpVal.getString("wagontype")+"\n"		+
        								"	Commodity: "+inpVal.getString("cmdtname")
        		, new Font(FontFamily.HELVETICA, 10));
        sttnfromp.setAlignment(Element.ALIGN_LEFT);
	    doc.add(sttnfromp);

        Paragraph sttntop1 = new Paragraph("Wagon Load Freight Rate(for Class-"+inpVal.getString("CmdtClssWL")+"): "+inpVal.getString("RateWL")+" Rs/Tonne, "+ 
                                           "Train Load Freight Rate(for Class-"+inpVal.getString("CmdtClssTL")+"): "+inpVal.getString("RateTL")+" Rs/Tonne"
                                                                        //      +", "           +
                                                                        //"     R/M/P:"+inpVal.getString("rkpm")
                        , new Font(FontFamily.HELVETICA, 10));
        sttntop1.setAlignment(Element.ALIGN_LEFT);
            doc.add(sttntop1);
	    
        Paragraph sttnfromp2 = new Paragraph("Distance: "+inpVal.getString("Dist")+" Kms, Carrying Capacity Information: "+inpVal.getString("CCInfo")+" Tonnes"+"\n"	+
				"	Permissible Carrying Capacity: "+inpVal.getString("PCC")+" Tonnes/Wagon, "		+
				"	Expected Travel Time: "+inpVal.getString("ETA")+" Hrs, "		+
				"	No. of Wagons: "+inpVal.getString("wgonnumbs")//("RakeSize")//("wgonnumbs")
		, new Font(FontFamily.HELVETICA, 10));
        sttnfromp2.setAlignment(Element.ALIGN_LEFT);
		doc.add(sttnfromp2);
	    
/*        Paragraph routep = new Paragraph("Route("+inpVal.getString("RoutInfo")+"):"+inpVal.getString("ViaDesc"),new Font(FontFamily.HELVETICA, 10));
        routep.setAlignment(Element.ALIGN_LEFT);
	    doc.add(routep);
*/
        Paragraph p1 = new Paragraph("\n",new Font(FontFamily.HELVETICA, 9));
        p1.setAlignment(Element.ALIGN_CENTER);
	    doc.add(p1);

	    PdfPTable table	=	null;
	    
	    if(inpVal.getString("rkpm").equals("P"))
	    {
	        table = new PdfPTable(2);
	        table.setWidths(new int[]{4, 3 });
	    }
	    else
	    {
	        table = new PdfPTable(3);
	        table.setWidths(new int[]{4, 3, 3 });
	    }
        table.setWidthPercentage(100);
        PdfPCell cell;
        // row 1, cell 1
        cell = new PdfPCell(new Phrase("Charge Name"));
        cell.setBorder(Rectangle.RECTANGLE|Rectangle.TOP);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(169, 207, 249));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 1, cell 2
        cell = new PdfPCell(new Phrase("Wagon Load"));
        cell.setBorder(Rectangle.RECTANGLE|Rectangle.TOP);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(169, 207, 249));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 1, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase("Train Load"));
	        cell.setBorder(Rectangle.RECTANGLE|Rectangle.TOP);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(169, 207, 249));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 2, cell 1
        cell = new PdfPCell(new Phrase("Charged Rate Class"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 2, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonloadclss")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 2, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranloadclss")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Basic Freight Rate (Rs./T)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonbascfrgt")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranbascfrgt")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(206, 223, 253));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("*Surcharge (Rs./T)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonsurchrg")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("transurchrg")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Normal Tariff Rate (Rs./T)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonntr")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranntr")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(206, 223, 253));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("**Other Charge (Rs./T)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonchrg")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranchrg")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("***Rebate (Rs./T)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonrebtchrg")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranrebtchrg")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(206, 223, 253));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Freight Rate (Rs./T)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonloadfrgt")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranloadfrgt")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Total Chargeable Weight (T)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonchblwght")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("totlchblwght")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(206, 223, 253));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Total Freight (Rs.)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgontotlfrgt")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("trantotlfrgt")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Other Lumpsum Charges (Rs.)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonothrchrg")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranothrchrg")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(206, 223, 253));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Other Lumpsum Rebate (Rs.)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonothrrebt")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranothrrebt")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Indicative Freight (Rs.)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonfinlfrgt")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranfinlfrgt")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(206, 223, 253));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("+GST @5% (if app.) (Rs.)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgongsttax")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("trangsttax")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);
	    }
        
        // row 3, cell 1
        cell = new PdfPCell(new Phrase("Indicative Freight (incl.GST) (Rs.)"));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 2
        cell = new PdfPCell(new Phrase(inpVal.getString("wgonfrgttax")));
        cell.setBorder(Rectangle.RECTANGLE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(206, 223, 253));
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        // row 3, cell 3
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        cell = new PdfPCell(new Phrase(inpVal.getString("tranfrgttax")));
	        cell.setBorder(Rectangle.RECTANGLE);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	        cell.setBackgroundColor(new BaseColor(206, 223, 253));
	        cell.setMinimumHeight(25f);
	        table.addCell(cell);  
	    }
        
        doc.add(table);

        Paragraph p3 = new Paragraph("\n",new Font(FontFamily.HELVETICA, 9));
        p3.setAlignment(Element.ALIGN_CENTER);
	    doc.add(p3);
	    
        Paragraph finfrgt_wwords = new Paragraph("Indicative Freight (incl. GST) for Wagon Load (in words): "+inpVal.getString("wgonfrgttaxwrds"),new Font(FontFamily.HELVETICA, 9));
        finfrgt_wwords.setAlignment(Element.ALIGN_LEFT);
	    doc.add(finfrgt_wwords);
	    
	    if(!inpVal.getString("rkpm").equals("P"))
	    {
	        Paragraph finfrgt_twords = new Paragraph("Indicative Freight (incl. GST) for Train Load (in words): "+inpVal.getString("tranfrgttaxwrds"),new Font(FontFamily.HELVETICA, 9));
	        finfrgt_twords.setAlignment(Element.ALIGN_LEFT);
		    doc.add(finfrgt_twords); 
	    }
	    
        Paragraph dis = new Paragraph("+GST if applicable, will be leivied as per extant rules.",new Font(FontFamily.HELVETICA, 9));
        dis.setAlignment(Element.ALIGN_LEFT);
	    doc.add(dis);
	    
//	    if(!inpVal.getString("rkpm").equals("P"))
//	    {
		    if(!inpVal.getString("surchrg").equals(""))
		    {
		        Paragraph sur = new Paragraph("*Surcharge includes "+inpVal.getString("surchrg"),new Font(FontFamily.HELVETICA, 9));
		        sur.setAlignment(Element.ALIGN_LEFT);
			    doc.add(sur); 
		    }
	
		    if(!inpVal.getString("othrchrg").equals(""))
		    {
		        Paragraph othr = new Paragraph("**Other Charge includes "+inpVal.getString("othrchrg"),new Font(FontFamily.HELVETICA, 9));
		        othr.setAlignment(Element.ALIGN_LEFT);
			    doc.add(othr);
		    }
		    
		    if(!inpVal.getString("rebtchrg").equals(""))
		    {
		        Paragraph rebt = new Paragraph("***Rebate includes "+inpVal.getString("rebtchrg"),new Font(FontFamily.HELVETICA, 9));
		        rebt.setAlignment(Element.ALIGN_LEFT);
			    doc.add(rebt);  
		    }			    
                    
                    if(!inpVal.getString("wgonrebtrmrk").equals(""))
		    {
		        Paragraph WLrmrk = new Paragraph("Wagon Load Remarks: "+inpVal.getString("wgonrebtrmrk"),new Font(FontFamily.HELVETICA, 9));
		        WLrmrk.setAlignment(Element.ALIGN_LEFT);
                        doc.add(WLrmrk);  
		    }    
                    if(!inpVal.getString("rkpm").equals("P"))
                    {                   
                        if(!inpVal.getString("tranrebtrmrk").equals(""))
                        {
                            Paragraph TLrmrk = new Paragraph("Train Load Remarks: "+inpVal.getString("tranrebtrmrk"),new Font(FontFamily.HELVETICA, 9));
                            TLrmrk.setAlignment(Element.ALIGN_LEFT);
                            doc.add(TLrmrk);  
                        }
                    }
//	    }
            Paragraph note = new Paragraph("\n"+inpVal.getString("note"),new Font(FontFamily.HELVETICA, 10 , Font.BOLD));
            note.setAlignment(Element.ALIGN_LEFT);
            doc.add(note);
            
            Paragraph disclaimer = new Paragraph("\nDisclaimer: The Freight Calculator only provides indicative and approximate freight to be charged. The Actual Chargeable Freight would depend on number of other factors including type of wagons, actual weight of cargo, exact classification of commodity etc. It is hereby informed that the information provided herein is indicative in nature and cannot be used as evidence in any Court of Law or cited as evidence for RTI.",new Font(FontFamily.HELVETICA, 10 , Font.BOLD));
            disclaimer.setAlignment(Element.ALIGN_LEFT);
            doc.add(disclaimer);  
            
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
	
    public JSONObject getFrgtCalc(JSONObject inpVal) throws NamingException, JSONException
    {        
        logger.info("Entring getFrgtCalc");   
        JSONObject response =   new  JSONObject();     
        logger.info("Inputs:"+inpVal);
        
        Date today = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = formatter.format(today);
        logger.info("Today : " + strDate);
        
        String date     =  strDate;//inpVal.getString("date");
        String sttnfrom =   inpVal.getString("sttnfrom");
        String sttnto   =   inpVal.getString("sttnto");
        String wagontype=   inpVal.getString("wagontype");       

        String wgnType  =   inpVal.getString("wgnType");
        String wgnCtgy  =   inpVal.getString("wgnCtgy");        
        String cmdtname =   inpVal.getString("cmdtname");

        cmdtname = cmdtname.toUpperCase();
        sttnfrom = sttnfrom.toUpperCase();
        sttnto = sttnto.toUpperCase();
        
        response.put("sttnfrom",sttnfrom);
        response.put("sttnto",sttnto);
        if("".equals(wagontype))
            response.put("wagontype",wgnType);
        else
            response.put("wagontype",wagontype);
        response.put("cmdtname",cmdtname);
        
        try
        {
            if(sttnfrom.indexOf("-") != -1)
                sttnfrom		=	sttnfrom.substring(sttnfrom.lastIndexOf("-")+1,sttnfrom.lastIndexOf("(")).trim();
            if(sttnto.indexOf("-") != -1)        
                sttnto			=	sttnto.substring(sttnto.lastIndexOf("-")+1,sttnto.lastIndexOf("(")).trim();       
         //   if(cmdtname.indexOf("-") != -1)        
          //      cmdtname		=	cmdtname.substring(cmdtname.lastIndexOf("-")+1).trim();       
          if(wagontype.indexOf("-") != -1)
              wagontype           =       wagontype.substring(0,wagontype.indexOf("-")).trim();
/*    
            if(wgnCtgy.equals("T"))
            {
                if(wagontype.indexOf("-") != -1)
                    wagontype		=	wagontype.substring(0,wagontype.indexOf("-")).trim();
                else if(wgnType != null)
                    if(!wgnType.equals(""))
                    wagontype  =   wgnType;
            }
            else
                wagontype  =   wgnType;
  */
        }
        catch(Exception e){}
        
        String rkpm     =   inpVal.getString("rkpm");
        String wgonnumbs=   inpVal.getString("wgonnumbs");
        String wgonfrgtcalc=   inpVal.getString("wgonnumbs");
                
        if(wgonnumbs.equals(""))
            wgonnumbs   = "0";                
        if(wgonfrgtcalc.equals(""))
            wgonfrgtcalc   = "0";
        
        logger.info("date:"+date); 
        logger.info("sttnfrom:"+sttnfrom);        
        logger.info("sttnto:"+sttnto);        
        logger.info("wagontype:"+wagontype);        
        logger.info("cmdtname:"+cmdtname);       
        logger.info("rkpm:"+rkpm);
        logger.info("wgonnumbs:"+wgonnumbs);
        logger.info("wgonfrgtcalc:"+wgonfrgtcalc);
        
        logger.info("wgnCtgy:"+wgnCtgy);
        logger.info("wgnType:"+wgnType);
        
        response.put("date",date);   
        response.put("wgonnumbs",wgonnumbs);
        
        // output variables 
        String strCmdtClssTL    =  "";
        String strCmdtClssWL    =  "";
        String strRateTL        =  "";
        String strRateWL        =  "";
        String strRoutInfo      =  "";
        String strViaDesc       =  "";
        String strCCInfo        =  "";
        String strDist          =  "";
        String strRakeSize      =  "";
        String strETA           =  "";
        String strPCC           =  "";
        String strMiniRake      =  "";
        String strTotlChblWght  =  "";
        String strMinimumRakeSize   =   "";
        
        /*
        *    To be modified
        * String strClntId                = request.getRemoteAddr();
        if(strClntId == null)
        {       
                strClntId       = "172.16.1.61";        //Public Users
                strUserType     = "P";
        }
        if (strClntId.substring(0,3).equals("10.") || strClntId.substring(0,4).equals("172."))
        {
                strUserType     = "R";
                //strClntId     = "172.16.4.68";        // For Rly Clients
        }
        else
        {
                //strClntId     = "172.16.1.61";        // For Public Users
                strUserType     = "P";
        }
        */
        /////////////////////////////////////////////////////
        FOISTuxedo TQPREFRGTCALC    = null;
        FOISTuxedo TQFRGTCALC       = null;
        try
        {

            if(!GG_Validate.chkName(cmdtname)) return null;
            if(!GG_Validate.chkName(sttnfrom)) return null;
            if(!GG_Validate.chkName(sttnto)) return null;
            
            try
            {
                logger.info("Calling Tuxedo for TQPREFRGTCALC");
                TQPREFRGTCALC = new FOISTuxedo();
            }
            catch (Exception ne)
            {
                logger.info("Unable to get the Client Object");
            }
            
            try
            {
                TQPREFRGTCALC.tuxInit("TQPREFRGTCALC");
                
                TQPREFRGTCALC.setString("F_DATE",       0, date);
                TQPREFRGTCALC.setString("F_STTNFROM",   0, sttnfrom);
                TQPREFRGTCALC.setString("F_STTNTO",     0, sttnto);
                TQPREFRGTCALC.setString("F_CMDT",       0, cmdtname);
                TQPREFRGTCALC.setString("F_WGONTYPE",   0, wagontype);
                TQPREFRGTCALC.setString("F_USERID",     0, "CRIS1");
                TQPREFRGTCALC.setString("F_CLNTID",     0, "CRIS1");
                TQPREFRGTCALC.setString("F_FLAG",       0, "P");
                logger.info("after setting filters");
            }
            catch(Exception e)
            {
                e.printStackTrace();
                logger.info("Could not set input buffers." + e.toString());
                response.put("error","Service is currently unavailable.Please try after some time.");
                return response;
            }    
            String erorCode                 = null;            
            try
            {
                logger.info("Calling Tuxedo Service TQPREFRGTCALC ...");
                TQPREFRGTCALC.call("N");
                logger.info("CALL COMPLETED TQPREFRGTCALC!");
            }
            catch(Exception e)
            {                               
                e.printStackTrace();
                logger.info("Unable to call TQPREFRGTCALC." + e.toString());
                response.put("error","Service is currently unavailable.Please try after some time.");
                return response;
            }            
            try
            {
                erorCode = TQPREFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
                logger.info("ErrorCode: " + erorCode+":");
            }
            catch(Exception e){}
            if(erorCode != null && (!erorCode.equals("")))
            {
                logger.info("ErrorCode: " + erorCode);
                response.put("error",erorCode);
                return response;
            }
            strCmdtClssTL    =        TQPREFRGTCALC.getStringItemDef("F_RATECLSS",   0,      "0").trim();
            strCmdtClssWL    =        TQPREFRGTCALC.getStringItemDef("F_PRTYCLSS",   0,      "0").trim();
            strRateTL        =        TQPREFRGTCALC.getStringItemDef("F_RATE",       0,      "0").trim();
            strRateWL        =        TQPREFRGTCALC.getStringItemDef("F_FRGTRATE",   0,      "0").trim();
            strRoutInfo      =        TQPREFRGTCALC.getStringItemDef("F_ROUTID",     0,      "0").trim();
            strViaDesc       =        TQPREFRGTCALC.getStringItemDef("F_VIA",        0,      "0").trim();
            strCCInfo        =        TQPREFRGTCALC.getStringItemDef("F_ROUTNUMB",   0,      "0").trim();
            strDist          =        TQPREFRGTCALC.getStringItemDef("F_CHBLDIST",   0,      "0").trim();
            strRakeSize      =        TQPREFRGTCALC.getStringItemDef("F_NUMBWGON",   0,      "0").trim();
            strPCC           =        TQPREFRGTCALC.getStringItemDef("F_CHBLWGHT",   0,      "0").trim();
            strETA           =        TQPREFRGTCALC.getStringItemDef("F_CALLTIME",   0,      "0").trim();
            strMiniRake      =        TQPREFRGTCALC.getStringItemDef("F_TOTLWGON",   0,      "0").trim();
            strMinimumRakeSize=        TQPREFRGTCALC.getStringItemDef("F_ORDRBY1",   0,      "0").trim();
            if(strPCC.equals("0.00")|| strPCC.equals(""))
                strPCC  =   "1.00";
            if(strRoutInfo.endsWith("R"))
                strRoutInfo     =       "Rationalized";
            else if(strRoutInfo.endsWith("S"))
                strRoutInfo     =       "Shortest";
            
            if(rkpm.equals("R"))
                wgonnumbs   =   strRakeSize;
            else if(rkpm.equals("M"))
                wgonnumbs   =   strMiniRake;
            logger.info("wgonnumbs:"+wgonnumbs);            
            logger.info("After getting output from TQPREFRGTCALC");
            
            response.put("CmdtClssTL",strCmdtClssTL);            
            response.put("CmdtClssWL",strCmdtClssWL);            
            response.put("RateTL",strRateTL);            
            response.put("RateWL",strRateWL);            
            response.put("RoutInfo",strRoutInfo);            
            response.put("ViaDesc",strViaDesc);     
            response.put("CCInfo",strCCInfo);     
            response.put("Dist",strDist);     
            response.put("RakeSize",strRakeSize);     
            response.put("PCC",strPCC);        
            response.put("ETA",Math.round(Double.parseDouble(strETA)));    
            response.put("MiniRake",strMiniRake);
            response.put("minimumrakesize",strMinimumRakeSize);
            
            logger.info("response:"+response);
            
            TQPREFRGTCALC.endSession();            
            logger.info("Session closed for TQPREFRGTCALC");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            logger.info("Calling Tuxedo for TQFRGTCALC");
            TQFRGTCALC = new FOISTuxedo();
            logger.info("Client Object Got.");
        }
        catch (Exception ne)
        {
            ne.printStackTrace();
            logger.info("Could not get Tuxedo Client" + ne.toString());
            response.put("error","Service is currently unavailable.Please try after some time.");
            return response;
        }
        try
        {
            logger.info("sttnfrom:"+sttnfrom);
            logger.info("sttnto:"+sttnto);
            logger.info("strDist:"+strDist);
            logger.info("wgonfrgtcalc:"+wgonfrgtcalc);
            TQFRGTCALC.tuxInit("TQFRGTCALC");
            // Passing Input
            TQFRGTCALC.setString("F_DATE",          0, date);
            TQFRGTCALC.setString("F_STTNFROM",      0, sttnfrom);
            TQFRGTCALC.setString("F_STTNTO",        0, sttnto);
            TQFRGTCALC.setString("F_VIA",           0, strRoutInfo);
            TQFRGTCALC.setString("F_USERID",        0, "CRIS1");
            TQFRGTCALC.setString("F_CLNTID",        0, "CRIS1");
            TQFRGTCALC.setString("F_DIST",          0, strDist);
            TQFRGTCALC.setString("F_PAIDTYPE",      0, "");
            TQFRGTCALC.setString("F_RISKRATE",      0, "");
            TQFRGTCALC.setString("F_CMDTDESC",      0, "");
            TQFRGTCALC.setString("F_TRFCTYPE",      0, "G");
            TQFRGTCALC.setString("F_RATE",          0, "C");
            TQFRGTCALC.setString("F_WGONTYPE",      0, wagontype);
            TQFRGTCALC.setString("F_CMDTCODE",      0, cmdtname);
            TQFRGTCALC.setString("F_PMBLWGHT",      0, strPCC);
            
            logger.info("WagonNumber:Before"+wgonnumbs+":"); 
            String strFlag  =   "F";
            String strNote  =   "";
               // System.out.println("wgonnumbs:"+wgonnumbs+":strRakeSize:"+strRakeSize+":strMinimumRakeSize:"+strMinimumRakeSize+":strMiniRake:"+strMiniRake+":");
            if(wgonnumbs.equals("") || wgonnumbs.equals("strRakeSize"))
            {
                wgonnumbs  =   strRakeSize;
                rkpm      =   "R";
            }
            if(Integer.parseInt(wgonnumbs) >= Integer.parseInt(strRakeSize))
            {
                rkpm        =   "R";
                strFlag     =   "RakeSize";
            }
            else if(Integer.parseInt(wgonnumbs) >= Integer.parseInt(strMinimumRakeSize))
            {
                rkpm        =   "R";
                strFlag     =   "MinimumRakeSize";
                strNote     =   "Note: Train Load will be granted on loading of minimum "+wgonnumbs+" wagons. However, Mini Rake demand shall be registered for atleast "+strRakeSize+" wagons.";
            }
            else if(Integer.parseInt(wgonnumbs) >= Integer.parseInt(strMiniRake))
            {
                rkpm        =   "R";
                strFlag     =   "MiniRakeSize";
                strNote     =   "Note: Train Load will be granted on loading of minimum "+wgonnumbs+" wagons. However, Rake demand shall be registered for atleast "+strMiniRake+" wagons.";
            }
            if(strPCC.equals("1.00"))
                wgonnumbs  =   "1";
            logger.info("WagonAfter:"+wgonnumbs+":");
            logger.info("rkpm:"+rkpm+":");
            logger.info("strFlag:"+strFlag+":");
                        
            response.put("flag",strFlag);
            response.put("note",strNote);
            response.put("rkpm",rkpm);
            
            TQFRGTCALC.setString("F_NUMB",          0, wgonnumbs);//wgonfrgtcalc);
            TQFRGTCALC.setString("F_LOADTYPE",      0, rkpm);
            TQFRGTCALC.setString("F_RAKETYPE",      0, rkpm);
            strTotlChblWght = (Double.parseDouble(strPCC) * Double.parseDouble(wgonnumbs)) + "";
            //strTotlChblWght = (Double.parseDouble(strPCC) * Double.parseDouble(wgonfrgtcalc)) + "";
        }
        catch(Exception e)
        {
            logger.info("There was an exception while creating and using the FOIS." + e);
            response.put("error","1.Service is currently unavailable.Please try after some time.");
            return response;
        }
        try
        {
            logger.info("Calling Tuxedo Service TQFRGTCALC ...");
            TQFRGTCALC.call("N");
            logger.info("CALL COMPLETED !");
        }
        catch(Exception e)
        {
            logger.info("Errror In Tuxedo Call for TQFRGTCALC.");
            response.put("error","2.Service is currently unavailable.Please try after some time.");
            return response;
        }
        String erorCode = null;
        try
        {
            erorCode = TQFRGTCALC.getStringItemDef("F_ERORNO",0,"0");
            logger.info("ErrorCode: " + erorCode+":");
        }
        catch(Exception e){}
        if(erorCode != null && (!erorCode.equals("")))
        {
            logger.info("ErrorCode: " + erorCode);
            response.put("error",erorCode);
            return response;
        }
            
        int intRebtTranCont     = 0;
        int intOthrTranCont     = 0;
        int intRebtWgonCont     = 0;
        int intOthrWgonCont     = 0;
        int intSurWgonCont      = 0;
        int intSurTranCont      = 0;
        
        int intOthrPcml =       0;
        int intRebtPcml =       0;
        int intSurPcml  =       0;
        
        String strRebtTranCont  = null;
        String strOthrTranCont  = null;
        String strRebtWgonCont  = null;
        String strOthrWgonCont  = null;
        String strSurWgonCont   = null;
        String strSurTranCont   = null;
        
        String strOthrPcml      = null;
        String strRebtPcml      = null;
        String strSurPcml       = null;
          
        if(!rkpm.equals("P"))
        {
            try
            {
                strRebtTranCont  = TQFRGTCALC.getStringItemDef("F_ROWCONT5",0,"0");
                strOthrTranCont  = TQFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                strRebtWgonCont  = TQFRGTCALC.getStringItemDef("F_ROWCONT3",0,"0");
                strOthrWgonCont  = TQFRGTCALC.getStringItemDef("F_ROWCONT4",0,"0");
                strSurWgonCont   = TQFRGTCALC.getStringItemDef("F_NUMBROWS",0,"0");
                strSurTranCont   = TQFRGTCALC.getStringItemDef("F_NUMBRR",0,"0");
                                
                String surchrg   = TQFRGTCALC.getStringItemDef("F_CHRGCODE",0,"0");      //surcharge codes String
                String othrchrg  = TQFRGTCALC.getStringItemDef("F_CHRGTYPE",0,"0");      // Other Charge Codes String
                String rebtchrg  = TQFRGTCALC.getStringItemDef("F_CHRGACRD",0,"0");      // Rebate Codes String
                
                if(surchrg.length() > 0)
                    response.put("surchrg",surchrg.substring(0,surchrg.length()-1));
                else
                    response.put("surchrg","");
                if(othrchrg.length() > 0)          
                    response.put("othrchrg",othrchrg.substring(0,othrchrg.length()-1));  
                else
                    response.put("othrchrg","");                
                if(rebtchrg.length() > 0)              
                    response.put("rebtchrg",rebtchrg.substring(0,rebtchrg.length()-1));
                else
                    response.put("rebtchrg","");
                
                if(surchrg.length()>1)
                    surchrg         = surchrg.substring(0,surchrg.length()-1);      
                if(othrchrg.length()>1)
                    othrchrg        = othrchrg.substring(0,othrchrg.length()-1);    
                if(rebtchrg.length()>1)
                    rebtchrg        = rebtchrg.substring(0,rebtchrg.length()-1);    
                
                logger.info("strRebtTranCont: " + strRebtTranCont + ":");
                logger.info("strOthrTranCont: " + strOthrTranCont + ":");
                logger.info("strRebtWgonCont: " + strRebtWgonCont + ":");
                logger.info("strOthrWgonCont: " + strOthrWgonCont + ":");
                logger.info("strSurWgonCont: " + strSurWgonCont + ":");
                logger.info("strSurTranCont: " + strSurTranCont + ":");
                
                response.put("tranrebtcont",strRebtTranCont);
                response.put("tranothrcont",strOthrTranCont);
                response.put("transurcont",strSurTranCont);
                response.put("wgonrebtcont",strRebtWgonCont);
                response.put("wgonothrcont",strOthrWgonCont);
                response.put("wgonsurcont",strSurWgonCont);                
            }
            catch(Exception d)
            {
                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                response.put("error","3.Service is currently unavailable.Please try after some time.");
                return response;
            }
                    
            if(strRebtTranCont == null || strRebtTranCont.equals("0") || strRebtTranCont.equals(""))
            {
                strRebtTranCont         = "0";
                response.put("tranrebtcont","0");
            }
            if(strOthrTranCont == null || strOthrTranCont.equals("0") || strOthrTranCont.equals(""))
            {
                strOthrTranCont         = "0";
                response.put("tranothrcont","0");
            }
            if(strRebtWgonCont == null || strRebtWgonCont.equals("0") || strRebtWgonCont.equals(""))
            {
                strRebtWgonCont         = "0";
                response.put("wgonrebtcont","0");
            }
            if(strOthrWgonCont == null || strOthrWgonCont.equals("0") || strOthrWgonCont.equals(""))
            {
                strOthrWgonCont         = "0";
                response.put("wgonothrcont","0");
            }
            if(strSurWgonCont == null || strSurWgonCont.equals("0") || strSurWgonCont.equals(""))
            {
                strSurWgonCont          = "0";
                response.put("wgonsurcont","0");
            }
            if(strSurTranCont == null || strSurTranCont.equals("0") || strSurTranCont.equals(""))
            {
                strSurTranCont          = "0";
                response.put("transurcont","0");
            }
            intRebtTranCont = new Integer(strRebtTranCont.trim()).intValue();
            intOthrTranCont = new Integer(strOthrTranCont.trim()).intValue();
            intRebtWgonCont = new Integer(strRebtWgonCont.trim()).intValue();
            intOthrWgonCont = new Integer(strOthrWgonCont.trim()).intValue();                    
            intSurWgonCont  = new Integer(strSurWgonCont.trim()).intValue();
            intSurTranCont  = new Integer(strSurTranCont.trim()).intValue();
            
            try
            {
                for(int i=0;i<intOthrWgonCont;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCODE", i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_DESC",         i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRGWAVD", i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGACRD", i,      "0").trim();
                    response.put("wgonothrcode"+i,strCode);
                    response.put("wgonothrdesc"+i,strDesc);
                    response.put("wgonothrprcn"+i,strPrcn);
                    response.put("wgonothramnt"+i,strAmnt);
                    logger.info("In Other Wagon Count:"+i+":");
                }
                for(int i=0;i<intOthrTranCont;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_ORDRBY9",      i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_FUNCDESC",     i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_ORDRBY10",     i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCLTD", i,      "0").trim();                    
                    response.put("tranothrcode"+i,strCode);
                    response.put("tranothrdesc"+i,strDesc);
                    response.put("tranothrprcn"+i,strPrcn);
                    response.put("tranothramnt"+i,strAmnt);
                    logger.info("In Other Train Count:"+i+":");
                }                
                for(int i=0;i<intRebtWgonCont;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_REBTCODE",             i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_CHRGDESC",             i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRG",             i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBTAMNT",             i,      "0").trim();                    
                    response.put("wgonrebtcode"+i,strCode);
                    response.put("wgonrebtdesc"+i,strDesc);
                    response.put("wgonrebtprcn"+i,strPrcn);
                    response.put("wgonrebtamnt"+i,strAmnt);
                    logger.info("In Rebate Wagon Count:"+i+":");
                }
                for(int i=0;i<intRebtTranCont;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_REBKINVCNUMB",         i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_REBKPAIDTYPE",         i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_REBKSTTNFROM",         i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBKSTTNTO",           i,      "0").trim();                  
                    response.put("tranrebtcode"+i,strCode);
                    response.put("tranrebtdesc"+i,strDesc);
                    response.put("tranrebtprcn"+i,strPrcn);
                    response.put("tranrebtamnt"+i,strAmnt);
                    logger.info("In Rebate Train Count:"+i+":");
                }
                for(int i=0;i<intSurWgonCont;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_SRNUMB",       i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_SRVC",         i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_SRVCCHNGSTTN", i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_SRVGSTTN",     i,      "0").trim();                 
                    response.put("wgonsurcode"+i,strCode);
                    response.put("wgonsurdesc"+i,strDesc);
                    response.put("wgonsurprcn"+i,strPrcn);
                    response.put("wgonsuramnt"+i,strAmnt);
                    logger.info("In Surcharge Wagon Count:"+i+":");
                }
                for(int i=0;i<intSurTranCont;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_SSPNXPRYTIME", i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_STAKLOCN",     i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_STBLLINENUMB", i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_STBLYRDGFLAG", i,      "0").trim();               
                    response.put("transurcode"+i,strCode);
                    response.put("transurdesc"+i,strDesc);
                    response.put("transurprcn"+i,strPrcn);
                    response.put("transuramnt"+i,strAmnt);
                    logger.info("In Surcharge Train Count:"+i+":");
                }
            }
            catch(Exception d)
            {
                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                response.put("error","4.Service is currently unavailable.Please try after some time.");
                return response;
            }
        }
        else if(rkpm.equals("P"))
        {
            try
            {
                strOthrPcml             = TQFRGTCALC.getStringItemDef("F_ROWCONT2",0,"0");
                strRebtPcml             = TQFRGTCALC.getStringItemDef("F_ROWCONT3",0,"0");
                strSurPcml              = TQFRGTCALC.getStringItemDef("F_NUMBROWS",0,"0");                            
                logger.info("strOthrPcml: " + strOthrPcml);
                logger.info("strRebtPcml: " + strRebtPcml);
                logger.info("strSurPcml: " + strSurPcml);
                response.put("pcmlothrcont",strOthrPcml);
                response.put("pcmlrebtcont",strRebtPcml);
                response.put("pcmlsurcont",strSurPcml);
                
                response.put("surchrg",TQFRGTCALC.getStringItemDef("F_CHRGCODE",0,"0"));      //surcharge codes String
                response.put("othrchrg",TQFRGTCALC.getStringItemDef("F_CHRGTYPE",0,"0"));      // Other Charge Codes String
                response.put("rebtchrg",TQFRGTCALC.getStringItemDef("F_CHRGACRD",0,"0"));      // Rebate Codes String
                
            }
            catch(Exception d)
            {
                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                response.put("error","5.Service is currently unavailable.Please try after some time.");
                return response;
            }                    
            if(strOthrPcml == null || strOthrPcml.equals("0") || strOthrPcml.equals(""))
            {
                strOthrPcml             = "0";
                response.put("pcmlothrcont","0");
            }
            if(strRebtPcml == null || strRebtPcml.equals("0") || strRebtPcml.equals(""))
            {
                strRebtPcml             = "0";
                response.put("pcmlothrcont","0");
            }
            if(strSurPcml == null || strSurPcml.equals("0") || strSurPcml.equals(""))
            {
               strSurPcml              = "0";
                response.put("pcmlothrcont","0");
            }
            intOthrPcml     = new Integer(strOthrPcml.trim()).intValue();
            intRebtPcml     = new Integer(strRebtPcml.trim()).intValue();
            intSurPcml      = new Integer(strSurPcml.trim()).intValue();                    
            try
            {
                for(int i=0;i<intOthrPcml;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGCODE",     i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_DESC",             i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRGWAVD",     i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_OTHRCHRGACRD",     i,      "0").trim();
                    logger.info("In Other Charges Picemeal Count:"+i+":");
                    response.put("wgonothrcode"+i,strCode);
                    response.put("wgonothrdesc"+i,strDesc);
                    response.put("wgonothrprcn"+i,strPrcn);
                    response.put("wgonothramnt"+i,strAmnt);
                }
                for(int i=0;i<intRebtPcml;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_REBTCODE",             i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_CHRGDESC",             i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_PRCNCHRG",             i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_REBTAMNT",             i,      "0").trim();
                    logger.info("In Rebate Picemeal Count:"+i+":");
                    response.put("wgonrebtcode"+i,strCode);
                    response.put("wgonrebtdesc"+i,strDesc);
                    response.put("wgonrebtprcn"+i,strPrcn);
                    response.put("wgonrebtamnt"+i,strAmnt);
                }
                for(int i=0;i<intSurPcml;i++)
                {
                    String strCode  = TQFRGTCALC.getStringItemDef("F_SRNUMB",               i,      "0").trim();
                    String strDesc  = TQFRGTCALC.getStringItemDef("F_SRVC",                 i,      "0").trim();
                    String strPrcn  = TQFRGTCALC.getStringItemDef("F_SRVCCHNGSTTN", i,      "0").trim();
                    String strAmnt  = TQFRGTCALC.getStringItemDef("F_SRVGSTTN",             i,      "0").trim();
                    logger.info("In Surcharge Picemeal Count:"+i+":");
                    response.put("wgonsurcode"+i,strCode);
                    response.put("wgonsurdesc"+i,strDesc);
                    response.put("wgonsurprcn"+i,strPrcn);
                    response.put("wgonsuramnt"+i,strAmnt);
                }
            }
            catch(Exception d)
            {
                logger.info("Problem in Calling Service TQFRGTCALC and filling Row Count into array " + d.toString());
                response.put("error","6.Service is currently unavailable.Please try after some time.");
                return response;
            }
        }
        try
        {
            //Train Load
            if(!rkpm.equals("P"))
            {
                response.put("totlchblwght",strTotlChblWght);                                                                                                                              //Total Chbl Weight
                response.put("tranloadclss",TQFRGTCALC.getStringItemDef("F_RATECLSS",               1,      "0").trim());            //si_ClssTL;
                response.put("tranbascfrgt",TQFRGTCALC.getStringItemDef("F_FRGT",                   1,      "0").trim());            //Basic Freight
                response.put("transurchrg",TQFRGTCALC.getStringItemDef("F_CHRGAMNT",                1,      "0").trim());            //Surcharg
                response.put("tranntr",TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",                  1,      "0").trim());            //ntr
                response.put("tranchrg",TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",         1,      "0").trim());            //other charge
                response.put("tranrebtchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",             1,      "0").trim());            //rebate
                response.put("tranloadfrgt",TQFRGTCALC.getStringItemDef("F_TOTLAMNTCLTD",           0,      "0").trim());            //si_FrgtTL;                                                                                                                                    //Freight Rate
                response.put("trantotlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",           1,      "0").trim());            //total freight
                response.put("tranothrchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",         1,      "0").trim());            //Other Charges
                response.put("tranothrrebt",TQFRGTCALC.getStringItemDef("F_ARCHFLAG",               1,      "0").trim());            //Other Rebate
                response.put("tranfinlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",         1,      "0").trim());            //Final Freight
                response.put("trangsttax",TQFRGTCALC.getStringItemDef("F_FRGTCLTD",                 1,      "0").trim());            //Service Tax
                response.put("tranfrgttax",TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",            1,      "0").trim());            //Final Freight + Service Tax
                response.put("tranfrgttaxwrds",TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS2",        1,      "0").trim());            //Final Freight + Service Tax IN WORDS
                String strTemp  =   TQFRGTCALC.getStringItemDef("F_CDFDRMRK",               1,      "0").trim();
                if(strTemp.indexOf("TRADITIONAL EMPTY FLOW DIRECTION")>0)
                    strTemp    += "THIS REBATE SHALL ONLY BE APPLICABLE UPTO END OF CURRENT MONTH.";
                response.put("tranrebtrmrk",strTemp);            //Rebate Remarks
            }
            logger.info("Wagon load class started");
            //Wagon Load
            response.put("wgonchblwght",strTotlChblWght);                                                                              //Total Chbl Weight
            response.put("wgonloadclss",TQFRGTCALC.getStringItemDef("F_RATECLSS",             0,      "0").trim());                    // si_ClssWL;
            response.put("wgonbascfrgt",TQFRGTCALC.getStringItemDef("F_FRGT",                 0,      "0").trim());                    //Basic Freight
            response.put("wgonsurchrg", TQFRGTCALC.getStringItemDef("F_CHRGAMNT",             0,      "0").trim());                    //Surcharg
            response.put("wgonntr",     TQFRGTCALC.getStringItemDef("F_SIGNONTRAN",           0,      "0").trim());                    //ntr
            response.put("wgonchrg",TQFRGTCALC.getStringItemDef("F_NEXTZONEIFFLAG",       0,      "0").trim());                    //other charge
            response.put("wgonrebtchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNTO",           0,      "0").trim());                    //rebate
            response.put("wgonloadfrgt",TQFRGTCALC.getStringItemDef("F_AMNTPYBL",             0,      "0").trim());                    //si_FrgtWL;                                                                                                                                    //Freight Rate
            response.put("wgontotlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICPT",         0,      "0").trim());                    //      total freight
            response.put("wgonothrchrg",TQFRGTCALC.getStringItemDef("F_NEXTDVSNDRTNTO",       0,      "0").trim());                    //Other Charges
            response.put("wgonothrrebt",TQFRGTCALC.getStringItemDef("F_ARCHFLAG",             0,      "0").trim());                    //Other Rebate
            response.put("wgonfinlfrgt",TQFRGTCALC.getStringItemDef("F_NEXTDVSNICFLAG",       0,      "0").trim());                    //Final Freight
            response.put("wgongsttax"  ,TQFRGTCALC.getStringItemDef("F_FRGTCLTD",             0,      "0").trim());                    //Service Tax
            response.put("wgonfrgttax" ,TQFRGTCALC.getStringItemDef("F_TOTLAMNTACRD",         0,      "0").trim());                    //Final Freight + Service Tax
            response.put("wgonfrgttaxwrds",TQFRGTCALC.getStringItemDef("F_AMNTPOLCLSS1",      0,      "0").trim());                    //Final Freight + Service Tax IN WORDS
            String strTemp  =   TQFRGTCALC.getStringItemDef("F_CDFDRMRK",             0,      "0").trim();
            if(strTemp.indexOf("TRADITIONAL EMPTY FLOW DIRECTION")>0)
                strTemp    += "THIS REBATE SHALL ONLY BE APPLICABLE UPTO END OF CURRENT MONTH.";
            response.put("wgonrebtrmrk",strTemp);            //Rebate Remarks
        }
        catch(Exception e)
        {
            logger.info("Unable to write to buffer : " + e.toString());
            response.put("error","7.Service is currently unavailable.Please try after some time.");
            return response;
        }
            
        try
        {
            TQFRGTCALC.endSession();
        }
        catch(Exception e)
        {
            logger.info("Error In End Session:" + e.toString());
            response.put("error","8.Service is currently unavailable.Please try after some time.");
            return response;
        }
        logger.info("Response after tqfrgtcalc:"+response);
        logger.info("Exiting FrgtCalc..");
        return response;
    }	
}
