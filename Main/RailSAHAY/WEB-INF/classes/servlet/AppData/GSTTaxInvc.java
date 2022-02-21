package servlet.AppData;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;

public class GSTTaxInvc extends HttpServlet
{
	
	Logger logger = FoisLogger.getLogger(GSTTaxInvc.class.getName());
	String strError="";

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		
		System.out.println("In creating print tax invoice");
		ByteArrayOutputStream baos=null;
		String strTaxInvcNo= (String)req.getParameter("txtTaxInvc");
		baos= pdfDownloadTaxInvc(strTaxInvcNo,req,res);
		//baos= pdfDownloadTaxInvc("170603390000014");
        System.out.println("Back in create PDF with ByteArrayOutputStream:::"+baos.size());
        
        byte doc[]=baos.toByteArray();
       
                      OutputStream output = null;


        try {
                        // Init servlet response.
        	res.reset();
        	res.setHeader("Content-Type", "application/pdf");
        	res.setHeader("Content-Length", String.valueOf(doc.length));
        	res.setHeader("Content-Disposition", "attachment; filename=\"" + strTaxInvcNo+".pdf" + "\"");
            output=res.getOutputStream();
                        System.out.println("output:::"+doc.length);
                        
                        baos.writeTo(output);
                        // Finalize task.
                        output.flush();
                    } 
        catch(Exception ex)
        {
            ex.printStackTrace();
            
            }finally {
                        // Gently close streams.
                        //close(output);
                        //close(input);
            try{
                        output.close();
            }catch(IOException ex)
            {
                
                ex.printStackTrace();
            }
            }
	
	}
    public ByteArrayOutputStream pdfDownloadTaxInvc(String si_TaxInvcId,HttpServletRequest req, HttpServletResponse res)
    {

                   ByteArrayOutputStream baos =null;
           		RequestDispatcher rd=null;
                   req.setAttribute("GSTTAXINVC",si_TaxInvcId);

                   try {

                	   FOISTuxedo TQTAXINVC   = null;


                                           //*************************************************************************************
                                                                                            //Calling Tuxedo Service from WTC
                                           //**************************Calling Service TQEDMNDPDF*********************************

                                           try
                                           {
                                                   System.out.println("Calling Tuxedo");
                                                   TQTAXINVC = new FOISTuxedo();
                                                   System.out.println("Client Object Got.");
                                           }
                                           catch (Exception ne)
                                           {
                                                   System.out.println("Unable to get the Client Object: " + ne.toString());
                                           
                                           }

                                           try
                                           {
                                                   TQTAXINVC.tuxInit("TQTAXINVC");
                                                   // Passing Input
                                                   TQTAXINVC.setString("F_USERID",        0,      "SYSTEM");
                                                   TQTAXINVC.setString("F_CLNTID",        0,      "1.1.1.1");
                                                   TQTAXINVC.setString("F_INVCID"  ,        0,      si_TaxInvcId);
                                                   TQTAXINVC.setString("F_DATE"  ,    0,      "A");
                                                   TQTAXINVC.setString("F_FLAG"  ,  0,      "TAXC");
                                           }
                                           catch(Exception e)
                                           {
                                                   System.out.println("Unable to write to buffer : " + e.toString());
                                           
                                           }

                                           try
                                           {
                                                   System.out.println("Calling Tuxedo Service TQEDMNDPDF ...");
                                                   TQTAXINVC.call("N");
                                                   System.out.println("CALL COMPLETED !");
                                           }
                                           catch(Exception e)
                                           {
                                                   System.out.println("Exception while call to the service is " + e.toString());
                                           
                                           }

                                       // Checking For Any Error from Service
                                       String erorCode         = null;
                                       
                                       try
                       				{
                                    	   erorCode				= TQTAXINVC.getStringItemDef("F_ERORNO",0,"0");
                       				}
                       				catch(Exception e)
                       				{
                       					// F_ERORNO is not set by Developer, So, it is not an Error
                       				}
                                       
                                       if(erorCode != null && (!erorCode.equals("")))
                       				{
                       					logger.info("Tuxedo Connnection Failed.");
                       					strError="ERROR IN GETTING DATA";
                       					logger.info("erorCode1"+erorCode);
                       					req.setAttribute("ERROR",erorCode);
                       					rd=req.getRequestDispatcher("pages/GSTTaxInvc.jsp");
                       					rd.forward(req,res);
                       					return null;
                       				}
                                       
                                       if(erorCode != null && (!erorCode.equals("")))
                                       {
                                           System.out.println(erorCode);
                                           
                                       }

                                       int start1                          = 0;
                                       String startRowCount1   = null;
                                       // getting Row Count
                                           try
                                           {
                                                   startRowCount1   = TQTAXINVC.getStringItemDef("F_ROWCONT",0,"0");
                                       }
                                       catch(Exception d)
                                       {
                                           System.out.println("Problem in Calling Service TQEDMNDPDF and filling Row Count into array" + d.toString());
                                          
                                       }

                                       if(startRowCount1 == null || startRowCount1.equals("0") || startRowCount1.equals(""))
                                       {
                                           startRowCount1      = "0";
                                           System.out.println("NO ROWS in BUFFER");
                                           logger.info("NO ROWS in BUFFER");
                                           strError="NO DATA AVIALABLE";
                                           logger.info("erorCode1"+erorCode);
                                           req.setAttribute("ERROR",erorCode);
                                           rd=req.getRequestDispatcher("pages/GSTTaxInvc.jsp");
                                           rd.forward(req,res);
                                           return null;
                                       }
                                       start1 = new Integer(startRowCount1.trim()).intValue();
                                       System.out.println("No. of Records : " + start1);
                                       // Variable Declaration for Query 1

                                       String str_taxinvcid="";
                                       String str_taxinvcdate="";
                                       String str_rrmrnumb="";
                                       String str_rrmrdate="";
                                       String str_zone="";
                                       String str_dvsn="";
                                       String str_sttn="";
                                       String str_cltnprps="";
                                       String str_gsdesc="";
                                       String str_hsncode="";
                                       String str_splrgstn="";
                                       String str_splrstat="";
                                       String str_splrname="";
                                       String str_splraddr="";
                                       String str_splrmobl="";
                                       String str_splremal="";
                                       String str_rgstflag="";
                                       String str_rcptgstn="";
                                       String str_rcptstat="";
                                       String str_rcptname="";
                                       String str_rcptaddr="";
                                       String str_rcptmobl="";
                                       String str_rcptemal="";
                                       String str_gerflag="";
                                       String str_rvrschrg="";
                                       String str_stattradflag="";
                                       String str_plceofsply="";
                                       String str_splyvalu="";
                                       String str_txblvalu="";
                                       String str_cltdvalu="";
                                       String str_igstrate="";
                                       String str_cgstrate="";
                                       String str_sgstrate="";
                                       String str_ugstrate="";
                                       String str_cessrate="";
                                       String str_igstamnt="";
                                       String str_cgstamnt="";
                                       String str_sgstamnt="";
                                       String str_ugstamnt="";
                                       String str_cessamnt="";
                                       String str_totlgst="";
                                       String str_mdfyflag="";
                                       String str_rmrk="";

                                       for(int k=0; k<=start1-1; k++)
                                       {
                                           try
                                           {
                                               str_taxinvcid=TQTAXINVC.getStringItemDef("F_ACDLCONT",           k,      "0");
                                               str_taxinvcdate=TQTAXINVC.getStringItemDef("F_ACDLFRGN",           k,      "0");
                                               str_rrmrnumb=TQTAXINVC.getStringItemDef("F_ACDLFRGNCONT",           k,      "0");
                                               str_rrmrdate=TQTAXINVC.getStringItemDef("F_ACINFLAG",           k,      "0");
                                               str_zone=TQTAXINVC.getStringItemDef("F_ACKNTIME",           k,      "0");
                                               str_dvsn=TQTAXINVC.getStringItemDef("F_ACMLKM",           k,      "0");
                                               str_sttn=TQTAXINVC.getStringItemDef("F_ACNTHEAD",           k,      "0");
                                               str_cltnprps=TQTAXINVC.getStringItemDef("F_ACNTNUMB",           k,      "0");
                                               str_gsdesc=TQTAXINVC.getStringItemDef("F_ACNTOFCR",           k,      "0");
                                               str_hsncode=TQTAXINVC.getStringItemDef("F_ACNTTYPE",           k,      "0");
                                               str_splrgstn=TQTAXINVC.getStringItemDef("F_ACRDFROMTIME",           k,      "0");
                                               str_splrstat=TQTAXINVC.getStringItemDef("F_ACRDTOTIME",           k,      "0");
                                               str_splrname=TQTAXINVC.getStringItemDef("F_ACRLAMNT",           k,      "0");
                                               str_splraddr=TQTAXINVC.getStringItemDef("F_ACRLDATE",           k,      "0");
                                               str_splrmobl=TQTAXINVC.getStringItemDef("F_ACTLFRWH",           k,      "0");
                                               str_splremal=TQTAXINVC.getStringItemDef("F_ACTLICFLAG",           k,      "0");
                                               str_rgstflag=TQTAXINVC.getStringItemDef("F_ACTLICTIME",           k,      "0");
                                               str_rcptgstn=TQTAXINVC.getStringItemDef("F_ACTLUNTS",           k,      "0");
                                               str_rcptstat=TQTAXINVC.getStringItemDef("F_ACTLWGHT",           k,      "0");
                                               str_rcptname=TQTAXINVC.getStringItemDef("F_ACTLWGHTARVD",           k,      "0");
                                               str_rcptaddr=TQTAXINVC.getStringItemDef("F_ACTLWGHTUNIT",           k,      "0");
                                               str_rcptmobl=TQTAXINVC.getStringItemDef("F_ADVCSTCKFLAG",           k,      "0");
                                               str_rcptemal=TQTAXINVC.getStringItemDef("F_ADVSTIME",           k,      "0");
                                               str_gerflag=TQTAXINVC.getStringItemDef("F_AIRBRAKTYPE",           k,      "0");
                                               str_rvrschrg=TQTAXINVC.getStringItemDef("F_AJSTFLAG",           k,      "0");
                                               str_stattradflag=TQTAXINVC.getStringItemDef("F_ALLSTTNFLAG",           k,      "0");
                                               str_plceofsply=TQTAXINVC.getStringItemDef("F_ALOTTIME",           k,      "0");
                                               str_splyvalu=TQTAXINVC.getStringItemDef("F_AMNT",           k,      "0");
                                               str_txblvalu=TQTAXINVC.getStringItemDef("F_AMNTACRD",           k,      "0");
                                               str_cltdvalu=TQTAXINVC.getStringItemDef("F_AMNTATNRMLRATE",           k,      "0");
                                               str_igstrate=TQTAXINVC.getStringItemDef("F_AMNTCLTD",           k,      "0");
                                               str_cgstrate=TQTAXINVC.getStringItemDef("F_AMNTDUE",           k,      "0");
                                               str_sgstrate=TQTAXINVC.getStringItemDef("F_AMNTOTSG",           k,      "0");
                                               str_ugstrate=TQTAXINVC.getStringItemDef("F_AMNTPOLCLSS1",           k,      "0");
                                               str_cessrate=TQTAXINVC.getStringItemDef("F_AMNTPOLCLSS2",           k,      "0");
                                               str_igstamnt=TQTAXINVC.getStringItemDef("F_AMNTPYBL",           k,      "0");
                                               str_cgstamnt=TQTAXINVC.getStringItemDef("F_AMNTRFND",           k,      "0");
                                               str_sgstamnt=TQTAXINVC.getStringItemDef("F_AMNTWAVD",           k,      "0");
                                               str_ugstamnt=TQTAXINVC.getStringItemDef("F_APLYDATE",           k,      "0");
                                               str_cessamnt=TQTAXINVC.getStringItemDef("F_APPLATSTTN",           k,      "0");
                                               str_totlgst=TQTAXINVC.getStringItemDef("F_APPLSTTN",           k,      "0");
                                               str_mdfyflag=TQTAXINVC.getStringItemDef("F_ARFACRD",           k,      "0");
                                               str_rmrk=TQTAXINVC.getStringItemDef("F_RMRK",           k,      "0");

                                           }
                                           catch(Exception d)
                                           {
                                               System.out.println("Problem in Calling Service TQEDMNDPDF and filling data into array" + d.toString());
                                               
                                           }


                                       } // End of for Loop

                                           try
                                           {
                                                   TQTAXINVC.endSession();
                                           }
                                           catch(Exception e)
                                           {
                                                   System.out.println("Error In End Session:" + e.toString());
                                           
                                           }

                                        
                                      
                                           baos = new ByteArrayOutputStream();
                                           Document document = new Document();
                                           PdfWriter    writer=    PdfWriter.getInstance(document, baos);

                                           writer.setPageEvent(new WatermarkPageEvent());

                                           Font font =FontFactory.getFont("HELVETICA", 12,Font.BOLD);
                                           Font fontVal =FontFactory.getFont("HELVETICA", 8,Font.BOLD);
                                           Font fontValS =FontFactory.getFont("HELVETICA", 8);
                                         
                                           Paragraph CAuth=new Paragraph();
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(new Phrase("Date_______________                                                                                                                                    Signature_______________",fontVal));
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(new Phrase("Name of the Railway Official_______________",fontVal));
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.add(new Phrase("Designation(with Stamp)",fontVal));
                                           CAuth.add(Chunk.NEWLINE);
                                           CAuth.setAlignment(Element.ALIGN_RIGHT);
                                           CAuth.setIndentationLeft(20);
                                           CAuth.setIndentationRight(20);

                                           PdfPTable datatable=new PdfPTable(12);
                                           datatable.setWidthPercentage(100);
                                          // Font font =FontFactory.getFont("HELVETICA", 12,Font.BOLD);
                                          // Font fontVal =FontFactory.getFont("HELVETICA", 8,Font.BOLD);
                                          // Font fontValS =FontFactory.getFont("HELVETICA", 8);

                             
                                           PdfPCell cell1 = new PdfPCell (new Paragraph ("Government of India",font));
                                          // cell1.addElement();
                                           cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell1.setColspan(12);

                                           datatable.addCell(cell1);

                                           PdfPCell cell2 = new PdfPCell (new Paragraph("Ministry of Railways",font));
                                          // cell2.addElement();
                                           cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell2.setColspan(12);

                                           datatable.addCell(cell2);

                                           PdfPCell cell3 = new PdfPCell (new Paragraph ("Goods and Service Tax Invoice",font));
                                           cell3.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell3.setColspan(12);

                                           datatable.addCell(cell3);

                                           PdfPCell cell4 = new PdfPCell ();
                                           cell4.addElement(new Paragraph("Tax Invoice No.:",fontVal));
                                           cell4.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell4.setColspan(3);

                                           datatable.addCell(cell4);

                                           PdfPCell cell5 = new PdfPCell ();
                                           cell5.addElement(new Paragraph(str_taxinvcid,fontValS));
                                           cell5.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell5.setColspan(3);

                                           datatable.addCell(cell5);

                                           PdfPCell cell6 = new PdfPCell ();
                                           cell6.addElement(new Paragraph("Tax Invoice Date:",fontVal));
                                           cell6.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell6.setColspan(3);

                                           datatable.addCell(cell6);

                                           PdfPCell cell7 = new PdfPCell ();
                                           cell7.addElement(new Paragraph(str_taxinvcdate,fontValS));
                                           cell7.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell7.setColspan(3);

                                           datatable.addCell(cell7);

                                           PdfPCell cell8 = new PdfPCell ();
                                           cell8.addElement(new Paragraph("RR/MR No.:",fontVal));
                                           cell8.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell8.setColspan(3);

                                           datatable.addCell(cell8);

                                           PdfPCell cell9 = new PdfPCell ();
                                           cell9.addElement(new Paragraph(str_rrmrnumb,fontValS));
                                           cell9.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell9.setColspan(3);

                                           datatable.addCell(cell9);

                                           PdfPCell cell10 = new PdfPCell ();
                                           cell10.addElement(new Paragraph("RR/MR Date:",fontVal));
                                           cell10.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell10.setColspan(3);

                                           datatable.addCell(cell10);

                                           PdfPCell cell11 = new PdfPCell ();
                                           cell11.addElement(new Paragraph(str_rrmrdate,fontValS));
                                           cell11.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell11.setColspan(3);

                                           datatable.addCell(cell11);

                                           PdfPCell cell12 = new PdfPCell ();
                                           cell12.addElement(new Paragraph("Zone:",fontVal));
                                           cell12.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell12.setColspan(3);

                                           datatable.addCell(cell12);

                                           PdfPCell cell13 = new PdfPCell ();
                                           cell13.addElement(new Paragraph(str_zone,fontValS));
                                           cell13.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell13.setColspan(3);

                                           datatable.addCell(cell13);

                                           PdfPCell cell14 = new PdfPCell ();
                                           cell14.addElement(new Paragraph("Division:",fontVal));
                                           cell14.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell14.setColspan(3);

                                           datatable.addCell(cell14);

                                           PdfPCell cell15 = new PdfPCell ();
                                           cell15.addElement(new Paragraph(str_dvsn,fontValS));
                                           cell15.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell15.setColspan(3);

                                           datatable.addCell(cell15);

                                           PdfPCell cell16 = new PdfPCell ();
                                           cell16.addElement(new Paragraph("Station Code/Name:",fontVal));
                                           cell16.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell16.setColspan(3);

                                           datatable.addCell(cell16);

                                           PdfPCell cell17 = new PdfPCell ();
                                           cell17.addElement(new Paragraph(str_sttn,fontValS));
                                           cell17.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell17.setColspan(9);

                                           datatable.addCell(cell17);

                                           PdfPCell cell18 = new PdfPCell ();
                                           cell18.addElement(new Paragraph("Collection Purpose:",fontVal));
                                           cell18.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell18.setColspan(3);

                                           datatable.addCell(cell18);

                                           PdfPCell cell19 = new PdfPCell ();
                                           cell19.addElement(new Paragraph(str_cltnprps,fontValS));
                                           cell19.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell19.setColspan(9);

                                           datatable.addCell(cell19);

                                           PdfPCell cell20 = new PdfPCell ();
                                           cell20.addElement(new Paragraph("Goods and Service",fontVal));
                                           cell20.addElement(new Paragraph("Description:",fontVal));
                                           cell20.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell20.setColspan(3);

                                           datatable.addCell(cell20);

                                           PdfPCell cell21 = new PdfPCell ();
                                           cell21.addElement(new Paragraph(str_gsdesc,fontValS));
                                           cell21.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell21.setColspan(3);

                                           datatable.addCell(cell21);

                                           PdfPCell cell22 = new PdfPCell ();
                                           cell22.addElement(new Paragraph("HSN Code:",fontVal));
                                           cell22.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell22.setColspan(3);

                                           datatable.addCell(cell22);

                                           PdfPCell cell23 = new PdfPCell ();
                                           cell23.addElement(new Paragraph(str_hsncode,fontValS));
                                           cell23.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell23.setColspan(3);

                                           datatable.addCell(cell23);

                                           PdfPCell cell24 = new PdfPCell (new Paragraph("Details of Supplier (Railways)",fontVal));
                                           cell24.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell24.setColspan(12);

                                           datatable.addCell(cell24);

                                           PdfPCell cell25 = new PdfPCell ();
                                           cell25.addElement(new Paragraph("GSTIN:",fontVal));
                                           cell25.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell25.setColspan(3);

                                           datatable.addCell(cell25);

                                           PdfPCell cell26 = new PdfPCell ();
                                           cell26.addElement(new Paragraph(str_splrgstn,fontValS));
                                           cell26.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell26.setColspan(3);

                                           datatable.addCell(cell26);

                                           PdfPCell cell27 = new PdfPCell ();
                                           cell27.addElement(new Paragraph("State:",fontVal));
                                           cell27.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell27.setColspan(3);

                                           datatable.addCell(cell27);

                                           PdfPCell cell28 = new PdfPCell ();
                                           cell28.addElement(new Paragraph(str_splrstat,fontValS));
                                           cell28.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell28.setColspan(3);

                                           datatable.addCell(cell28);

                                           PdfPCell cell29 = new PdfPCell ();
                                           cell29.addElement(new Paragraph("Name:",fontVal));
                                           cell29.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell29.setColspan(3);

                                           datatable.addCell(cell29);

                                           PdfPCell cell30 = new PdfPCell ();
                                           cell30.addElement(new Paragraph(str_splrname,fontValS));
                                           cell30.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell30.setColspan(9);

                                           datatable.addCell(cell30);

                                           PdfPCell cell31 = new PdfPCell ();
                                           cell31.addElement(new Paragraph("Address:",fontVal));
                                           cell31.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell31.setColspan(3);

                                           datatable.addCell(cell31);

                                           PdfPCell cell32 = new PdfPCell ();
                                           cell32.addElement(new Paragraph(str_splraddr,fontValS));
                                           cell32.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell32.setColspan(9);

                                           datatable.addCell(cell32);

                                           PdfPCell cell33 = new PdfPCell ();
                                           cell33.addElement(new Paragraph("Mobile:",fontVal));
                                           cell33.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell33.setColspan(3);

                                           datatable.addCell(cell33);

                                           PdfPCell cell34 = new PdfPCell ();
                                           cell34.addElement(new Paragraph(str_splrmobl,fontValS));
                                           cell34.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell34.setColspan(9);

                                           datatable.addCell(cell34);

                                           PdfPCell cell35 = new PdfPCell ();
                                           cell35.addElement(new Paragraph("Email Id:",fontVal));
                                           cell35.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell35.setColspan(3);

                                           datatable.addCell(cell35);

                                           PdfPCell cell36 = new PdfPCell ();
                                           cell36.addElement(new Paragraph(str_splremal,fontValS));
                                           cell36.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell36.setColspan(9);

                                           datatable.addCell(cell36);

                                           PdfPCell cell37 = new PdfPCell (new Paragraph("Details of Recipient ("+str_rgstflag+")",fontVal));
                                           cell37.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell37.setColspan(12);

                                           datatable.addCell(cell37);

                                           PdfPCell cell38 = new PdfPCell ();
                                           cell38.addElement(new Paragraph("GSTIN:",fontVal));
                                           cell38.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell38.setColspan(3);

                                           datatable.addCell(cell38);

                                           PdfPCell cell39 = new PdfPCell ();
                                           cell39.addElement(new Paragraph(str_rcptgstn,fontValS));
                                           cell39.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell39.setColspan(3);

                                           datatable.addCell(cell39);

                                           PdfPCell cell40 = new PdfPCell ();
                                           cell40.addElement(new Paragraph("State:",fontVal));
                                           cell40.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell40.setColspan(3);

                                           datatable.addCell(cell40);

                                           PdfPCell cell41 = new PdfPCell ();
                                           cell41.addElement(new Paragraph(str_rcptstat,fontValS));
                                           cell41.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell41.setColspan(3);

                                           datatable.addCell(cell41);

                                           PdfPCell cell42 = new PdfPCell ();
                                           cell42.addElement(new Paragraph("Name:",fontVal));
                                           cell42.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell42.setColspan(3);

                                           datatable.addCell(cell42);

                                           PdfPCell cell43 = new PdfPCell ();
                                           cell43.addElement(new Paragraph(str_rcptname,fontValS));
                                           cell43.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell43.setColspan(9);

                                           datatable.addCell(cell43);

                                           PdfPCell cell44 = new PdfPCell ();
                                           cell44.addElement(new Paragraph("Address:",fontVal));
                                           cell44.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell44.setColspan(3);

                                           datatable.addCell(cell44);

                                           PdfPCell cell45 = new PdfPCell ();
                                           cell45.addElement(new Paragraph(str_rcptaddr,fontValS));
                                           cell45.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell45.setColspan(9);

                                           datatable.addCell(cell45);

                                           PdfPCell cell46 = new PdfPCell ();
                                           cell46.addElement(new Paragraph("Mobile:",fontVal));
                                           cell46.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell46.setColspan(3);

                                           datatable.addCell(cell46);

                                           PdfPCell cell47 = new PdfPCell ();
                                           cell47.addElement(new Paragraph(str_rcptmobl,fontValS));
                                           cell47.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell47.setColspan(9);

                                           datatable.addCell(cell47);

                                           PdfPCell cell48 = new PdfPCell ();
                                           cell48.addElement(new Paragraph("Email Id:",fontVal));
                                           cell48.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell48.setColspan(3);

                                           datatable.addCell(cell48);

                                           PdfPCell cell49 = new PdfPCell ();
                                           cell49.addElement(new Paragraph(str_rcptemal,fontValS));
                                           cell49.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell49.setColspan(9);

                                           datatable.addCell(cell49);

                                           PdfPCell cell50 = new PdfPCell (new Paragraph("Goods and Service Tax Details",fontVal));
                                           cell50.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell50.setColspan(12);

                                           datatable.addCell(cell50);

                                           PdfPCell cell51 = new PdfPCell ();
                                           cell51.addElement(new Paragraph("Transaction Category:",fontVal));
                                           cell51.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell51.setColspan(3);

                                           datatable.addCell(cell51);

                                           PdfPCell cell52 = new PdfPCell ();
                                           cell52.addElement(new Paragraph(str_gerflag,fontValS));
                                           cell52.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell52.setColspan(3);

                                           datatable.addCell(cell52);

                                           PdfPCell cell53 = new PdfPCell ();
                                           cell53.addElement(new Paragraph("Reverse Charge:",fontVal));
                                           cell53.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell53.setColspan(3);
                       
                                            datatable.addCell(cell53);

                                           PdfPCell cell54 = new PdfPCell ();
                                           cell54.addElement(new Paragraph(str_rvrschrg,fontValS));
                                           cell54.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell54.setColspan(3);

                                           datatable.addCell(cell54);

                                           PdfPCell cell55 = new PdfPCell ();
                                           cell55.addElement(new Paragraph("Inter/Intra State:",fontVal));
                                           cell55.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell55.setColspan(3);

                                           datatable.addCell(cell55);

                                           PdfPCell cell56 = new PdfPCell ();
                                           cell56.addElement(new Paragraph(str_stattradflag,fontValS));
                                           cell56.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell56.setColspan(3);

                                           datatable.addCell(cell56);

                                           PdfPCell cell57 = new PdfPCell ();
                                           cell57.addElement(new Paragraph("State of Place of Supply:",fontVal));
                                           cell57.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell57.setColspan(3);
                       
                                            datatable.addCell(cell57);

                                           PdfPCell cell58 = new PdfPCell ();
                                           cell58.addElement(new Paragraph(str_plceofsply,fontValS));
                                           cell58.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell58.setColspan(3);

                                           datatable.addCell(cell58);

                                           PdfPCell cell59 = new PdfPCell ();
                                           cell59.addElement(new Paragraph("Supply Value",fontVal));
                                           cell59.addElement(new Paragraph("(In Rs.):",fontVal));
                                           cell59.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell59.setColspan(2);
                       
                                            datatable.addCell(cell59);

                                           PdfPCell cell60 = new PdfPCell ();
                                           cell60.addElement(new Paragraph(str_splyvalu,fontValS));
                                           cell60.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell60.setColspan(2);

                                           datatable.addCell(cell60);

                                           PdfPCell cell61 = new PdfPCell ();
                                           cell61.addElement(new Paragraph("Taxable Value",fontVal));
                                           cell61.addElement(new Paragraph("(In Rs.):",fontVal));
                                           cell61.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell61.setColspan(2);
                       
                                            datatable.addCell(cell61);

                                           PdfPCell cell62 = new PdfPCell ();
                                           cell62.addElement(new Paragraph(str_txblvalu,fontValS));
                                           cell62.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell62.setColspan(2);

                                           datatable.addCell(cell62);

                                           PdfPCell cell63 = new PdfPCell ();
                                           cell63.addElement(new Paragraph("Collected Value",fontVal));
                                           cell63.addElement(new Paragraph("(In Rs.):",fontVal));
                                           cell63.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell63.setColspan(2);
                       
                                            datatable.addCell(cell63);

                                           PdfPCell cell64 = new PdfPCell ();
                                           cell64.addElement(new Paragraph(str_cltdvalu,fontValS));
                                           cell64.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell64.setColspan(2);

                                           datatable.addCell(cell64);

                                           PdfPCell cell65 = new PdfPCell (new Paragraph("Tax Components",fontVal));
                                           cell65.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell65.setColspan(12);
                       
                                            datatable.addCell(cell65);

                                           PdfPCell cell66 = new PdfPCell (new Paragraph("GST Rates(In %)",fontVal));
                                           cell66.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell66.setColspan(6);
                       
                                            datatable.addCell(cell66);

                                           PdfPCell cell67 = new PdfPCell (new Paragraph("GST Rates(In Rs.)",fontVal));
                                           cell67.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell67.setColspan(6);
                       
                                            datatable.addCell(cell67);

                                           PdfPCell cell68 = new PdfPCell ();
                                           cell68.addElement(new Paragraph("IGST",fontVal));
                                           cell68.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell68.setColspan(3);
                       
                                            datatable.addCell(cell68);

                                           PdfPCell cell69 = new PdfPCell ();
                                           cell69.addElement(new Paragraph(str_igstrate,fontValS));
                                           cell69.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell69.setColspan(3);

                                           datatable.addCell(cell69);

                                           PdfPCell cell70 = new PdfPCell ();
                                           cell70.addElement(new Paragraph("IGST",fontVal));
                                           cell70.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell70.setColspan(3);
                       
                                            datatable.addCell(cell70);

                                           PdfPCell cell71 = new PdfPCell ();
                                           cell71.addElement(new Paragraph(str_igstamnt,fontValS));
                                           cell71.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell71.setColspan(3);

                                           datatable.addCell(cell71);
                       
                       
                       
                       
                       


                                       PdfPCell cell72 = new PdfPCell ();
                                       cell72.addElement(new Paragraph("CGST",fontVal));
                                       cell72.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell72.setColspan(3);
                                       
                                        datatable.addCell(cell72);

                                       PdfPCell cell73 = new PdfPCell ();
                                       cell73.addElement(new Paragraph(str_cgstrate,fontValS));
                                       cell73.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell73.setColspan(3);

                                       datatable.addCell(cell73);

                                       PdfPCell cell74 = new PdfPCell ();
                                       cell74.addElement(new Paragraph("CGST",fontVal));
                                       cell74.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell74.setColspan(3);
                                       
                                        datatable.addCell(cell74);

                                       PdfPCell cell75 = new PdfPCell ();
                                       cell75.addElement(new Paragraph(str_cgstamnt,fontValS));
                                       cell75.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell75.setColspan(3);

                                       datatable.addCell(cell75);

                                           PdfPCell cell76 = new PdfPCell ();
                                           cell76.addElement(new Paragraph("SGST",fontVal));
                                           cell76.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell76.setColspan(3);
                       
                                            datatable.addCell(cell76);

                                           PdfPCell cell77 = new PdfPCell ();
                                           cell77.addElement(new Paragraph(str_sgstrate,fontValS));
                                           cell77.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell77.setColspan(3);

                                           datatable.addCell(cell77);

                                           PdfPCell cell78 = new PdfPCell ();
                                           cell78.addElement(new Paragraph("SGST",fontVal));
                                           cell78.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell78.setColspan(3);
                       
                                            datatable.addCell(cell78);

                                           PdfPCell cell79 = new PdfPCell ();
                                           cell79.addElement(new Paragraph(str_sgstamnt,fontValS));
                                           cell79.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell79.setColspan(3);

                                           datatable.addCell(cell79);

                                           PdfPCell cell80 = new PdfPCell ();
                                           cell80.addElement(new Paragraph("UGST",fontVal));
                                           cell80.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell80.setColspan(3);
                       
                                            datatable.addCell(cell80);

                                           PdfPCell cell81 = new PdfPCell ();
                                           cell81.addElement(new Paragraph(str_ugstrate,fontValS));
                                           cell81.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell81.setColspan(3);

                                           datatable.addCell(cell81);

                                           PdfPCell cell82 = new PdfPCell ();
                                           cell82.addElement(new Paragraph("UGST",fontVal));
                                           cell82.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell82.setColspan(3);
                       
                                            datatable.addCell(cell82);

                                           PdfPCell cell83 = new PdfPCell ();
                                           cell83.addElement(new Paragraph(str_ugstamnt,fontValS));
                                           cell83.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell83.setColspan(3);

                                           datatable.addCell(cell83);

                                           PdfPCell cell84 = new PdfPCell ();
                                           cell84.addElement(new Paragraph("CESS",fontVal));
                                           cell84.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell84.setColspan(3);
                       
                                            datatable.addCell(cell84);

                                           PdfPCell cell85 = new PdfPCell ();
                                           cell85.addElement(new Paragraph(str_cessrate,fontValS));
                                           cell85.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell85.setColspan(3);

                                           datatable.addCell(cell85);

                                           PdfPCell cell86 = new PdfPCell ();
                                           cell86.addElement(new Paragraph("CESS",fontVal));
                                           cell86.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell86.setColspan(3);
                       
                                            datatable.addCell(cell86);

                                           PdfPCell cell87 = new PdfPCell ();
                                           cell87.addElement(new Paragraph(str_cessamnt,fontValS));
                                           cell87.setHorizontalAlignment (Element.ALIGN_CENTER);
                                            cell87.setColspan(3);

                                           datatable.addCell(cell87);
                       
                                       PdfPCell cell88 = new PdfPCell ();
                                       cell88.addElement(new Paragraph("",fontVal));
                                       cell88.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell88.setColspan(3);
                                        cell88.setBorder(0);
                                       
                                        datatable.addCell(cell88);

                                       PdfPCell cell89 = new PdfPCell ();
                                    cell89.addElement(new Paragraph("",fontValS));
                                       cell89.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell89.setColspan(3);
                                        cell89.setBorder(0);

                                       datatable.addCell(cell89);

                                       PdfPCell cell90 = new PdfPCell ();
                                       cell90.addElement(new Paragraph("Total GST:",fontVal));
                                       cell90.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell90.setColspan(3);
                                       
                                        datatable.addCell(cell90);

                                       PdfPCell cell91 = new PdfPCell ();
                                       cell91.addElement(new Paragraph(str_totlgst,fontValS));
                                       cell91.setHorizontalAlignment (Element.ALIGN_CENTER);
                                        cell91.setColspan(3);

                                       datatable.addCell(cell91);
                       
                                            
                                            
                                    /*   Paragraph SgnAuth=new Paragraph("Signature____________________",fontVal);
                                       SgnAuth.setAlignment(Element.ALIGN_RIGHT);
                                       SgnAuth.setIndentationLeft(20);
                                       SgnAuth.setIndentationRight(50);
                       
                                       Paragraph SgnAuthName=new Paragraph("Name of the Railway Official____________________",fontVal);
                                       SgnAuthName.setAlignment(Element.ALIGN_RIGHT);
                                       SgnAuthName.setIndentationLeft(20);
                                       SgnAuthName.setIndentationRight(50);
                       */
                                    //  Paragraph desg=new Paragraph("Designation (with Stamp)",fontVal);
                                    //  SgnAuthName.setAlignment(Element.ALIGN_RIGHT);
                                   //   SgnAuthName.setIndentationLeft(20);
                                   //   SgnAuthName.setIndentationRight(50);
                       
                                      Paragraph disclaimer=new Paragraph("Disclaimer: This is a computer generated Tax Invoice. It is valid only for Tax Invoice/ITC Claim Purpose if signed by the authorized Railway Official with Stamp and Date.",fontVal);
                                      disclaimer.setAlignment(Element.ALIGN_JUSTIFIED);
                                      disclaimer.setIndentationLeft(20);
                                      disclaimer.setIndentationRight(20);
                                      
                                      Paragraph remarks=new Paragraph(str_rmrk,fontVal);
                                      remarks.setAlignment(Element.ALIGN_LEFT);
                                       remarks.setIndentationLeft(20);
                                       remarks.setIndentationRight(20);


                                     /*      datatable.addCell(new Paragraph(strCustDtls1,fontVal));
                                           datatable.addCell(new Paragraph(strPrty,fontVal));
                                           datatable.addCell(new Paragraph(strDstnName,fontVal));
                                           datatable.addCell(new Paragraph(strCoalFld,fontVal));
                                           datatable.addCell(new Paragraph(strPilot,fontVal));
                                           datatable.addCell(new Paragraph(strRakes,fontValS));
                                           datatable.addCell(new Paragraph(strCoalType,fontVal));
                                           datatable.addCell(new Paragraph(strCoalGrad,fontVal));
                                           datatable.addCell(new Paragraph(strSpnsDesc,fontVal));

                                           PdfPCell cell10=new PdfPCell();
                                           cell10.addElement(new Paragraph(strPara3,fontVal));
                                           cell10.addElement(new Paragraph(strPara4,fontVal));
                                           cell10.addElement(new Paragraph(strPara5,fontVal));
                                           cell10.setColspan(5);
                                           datatable.addCell(cell10);

                                           PdfPCell cell11=new PdfPCell();
                                           cell11.addElement(new Paragraph(strPara3a,fontVal));
                                           cell11.addElement(new Paragraph(strPara4a,fontVal));
                                           cell11.addElement(new Paragraph(strPara5a,fontVal));
                                           cell11.addElement(new Paragraph(strPara6a,fontVal));
                                           cell11.addElement(new Paragraph(strPara7a,fontVal));
                                           cell11.setColspan(4);
                                           datatable.addCell(cell11);

                                           datatable.setSpacingBefore(1.0f);       // Space Before table starts, like margin-top in CSS
                                           datatable.setSpacingAfter(1.0f);

                                           document.open();
                                           document.top(1);

                                           System.out.println("Document opened");
                                           document.add(Heading);
                                           document.add(new Paragraph("From:",fonthead1));
                                           document.add(From);
                                           document.add(new Paragraph(""));
                                           document.add(new Paragraph("To:",fonthead1));
                                           document.add(To);
                                           document.add(Head1);
                                           document.add(new Paragraph("Dear Sir,",fonthead1));
                                           document.add(new Paragraph("Sub:        BLOCK RAKE PROGRAMME FOR PREFERENTIAL RAKE ALLOTMENT AND MOVEMENT UNDER                           PRIORITY-C OF RAILWAY'S PREFERENTIAL TRAFFIC ORDER.",fonthead1));
                                           document.add(Chunk.NEWLINE);
                                           //document.add(para1);
                                            document.add(para01);
                                            document.add(prgId);
                                            document.add(para02);
                                           document.add(mnth);
                                           document.add(para2);
                                           document.add(Faithfully);
                                           //document.add(VldtDate);
                                           document.add(Chunk.NEWLINE);
                                           document.add(CustName);
                                           document.add(CAuth);
                                           document.add(Chunk.NEWLINE);
                                           document.add(SpnsAuth);
                                           document.add(SnctLetrNo);
                                           document.add(BlckRake);
                                           document.add(AprvClss);
                                           document.add(Chunk.NEWLINE);
                                           document.add(SnctAuth);
                                           document.add(DirRM);
                                           //document.add(RBKolkata);
                                           //document.add(Chunk.NEWLINE);
                                           document.add(pSysStmt);*/
                       
                                      document.open();
                                      document.top(1);
                                      
                                      document.add(datatable);
                                      document.add(remarks);
                                      document.add(CAuth);
                                      document.add(disclaimer);
                                            
                                            document.close();
                                           System.out.println("Document Closed");

                                           //file.close();

                                           System.out.println("File Closed");

                                           System.out.println("Pdf created successfully..");

                                   }
                                   catch (Exception e)
                                   {
                                       System.out.println("Inside Exception in PDF Writing");
                                       e.printStackTrace();
                                   }
                           return baos;
               }

}
