package model;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import tuxedo.FOISTuxedo;

import util.logger.FoisLogger;

public class FSH_FNREnqTX   {

    public FSH_FNREnqTX() {super();}

    static Logger logger = FoisLogger.getLogger(FSH_FNREnqTX.class.getName());

    public static ArrayList getFRNEnquiry(String strCnmtId)
    {

        //NDC.push("FNREnquiries");
        
       logger.info("Entering FSH_FNREnqTX: getFRNEnquiry for FNRNumb:"+strCnmtId);
        
        ArrayList<String[][]> fnrlist = new ArrayList<String[][]>();
        
        String strDtls[][]      = null;
        String strDtls2[][]     = null;
        String strData3[][]     = null;
        String strDesc;
        FOISTuxedo SRVCNAME = null;
        String strError="";
        try
        {
               logger.info("Calling Tuxedo");
                SRVCNAME = new FOISTuxedo();
               logger.info("Client Object Got.");
        }
        catch (Exception ne)
        {
               logger.info("Tuxedo Connnection Failed.");
                strError="SERVER NOT RUNNING PLEASE TRY AFTER SOME TIME(1)";
               logger.info("strError"+strError);
                return fnrlist;
        }                  
        if (strCnmtId.length() != 11)
        {
               logger.info("strCnmtId.length.::"+strCnmtId.length());
                return fnrlist;                         
        }                       
        try
        {
           logger.info("Calling tuxInit.");

            SRVCNAME.tuxInit("RQFNRDTLS");
            SRVCNAME.setString("F_NUMB", strCnmtId);
            SRVCNAME.setString("F_USERID", "CRIS1");
            SRVCNAME.setString("F_CLNTID", "CRIS1");

           logger.info("Called Tux_Fchg.");

            SRVCNAME.call("N");

           logger.info("Called Tux_Call.");

            String erorCode1                        = null;
            try
            {
                    erorCode1                       = SRVCNAME.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode1 != null && (!erorCode1.equals("")))
            {
               logger.info("Tuxedo Connnection Failed.");
                strError="ERROR IN GETTING FNR TRACK DETAIL";
               logger.info("erorCode1"+erorCode1);
                return fnrlist;
            }
            String strData      = SRVCNAME.getStringItemDef("F_ROWCONT", 0);
            String strData1     = SRVCNAME.getStringItemDef("F_ROWCONT", 1);
            
            
           logger.info("F_ROWCONT :"+ strData);
           logger.info("F_ROWCONT1 :"+ strData1);

            int intRowCount=Integer.parseInt(strData);
            int intRowCount1=Integer.parseInt(strData1);
            strDtls = new String[intRowCount][16];
            for (int i=0;i<intRowCount;i++)
            {
                strDtls[i][0]   = SRVCNAME.getStringItemDef("F_ACTLWGHT",       i);
                strDtls[i][1]   = SRVCNAME.getStringItemDef("F_ACTLUNTS",       i);
                strDtls[i][2]   = SRVCNAME.getStringItemDef("F_ULDGSTTN",       i);
                strDtls[i][3]   = SRVCNAME.getStringItemDef("F_CNSRNAME",       i);
                strDtls[i][4]   = SRVCNAME.getStringItemDef("F_STTN",           i);
                strDtls[i][5]   = SRVCNAME.getStringItemDef("F_STTNUPTO",       i);
                strDtls[i][6]   = SRVCNAME.getStringItemDef("F_LOADSTTS",       i);
                strDtls[i][7]   = SRVCNAME.getStringItemDef("F_GRUPDESC",       i);
                strDtls[i][8]   = SRVCNAME.getStringItemDef("F_STTSCHNGTIME",   i);
                strDtls[i][9]   = SRVCNAME.getStringItemDef("F_ETAATDSTN",      i);

                strDtls[i][14]   = SRVCNAME.getStringItemDef("F_CNSR",      i); //Consignor
                strDtls[i][15]   = SRVCNAME.getStringItemDef("F_NEWCNSR",   i); //Consignee
    
                strDtls[i][7]=strDtls[i][7].replaceAll("LOAD", "");
                strDtls[i][10]= strDtls[i][8].substring(5,11)+" At "+strDtls[i][8].substring(0,5);
                strDtls[i][11]= strDtls[i][1]+"("+strDtls[i][0]+")";
                strDtls[i][12]= strDtls[i][3]+"("+strDtls[i][2]+")";
    
                if(strDtls[i][6].equals("DP"))
                        strDtls[i][13]=strDtls[i][7]+" FROM "+strDtls[i][5]+"("+strDtls[i][4]+") ON "+strDtls[i][8];
                else if(strDtls[i][6].equals("FD") ||strDtls[i][6].equals("AD")||strDtls[i][6].equals("RL")||strDtls[i][6].equals("RD")||strDtls[i][6].equals("TX"))
                    if(strDtls[i][4].equals(strDtls[i][0]))
                        strDtls[i][13]="AWAITING DEPARTURE FROM ORIGINATING STATION";
                    else
                        strDtls[i][13]= " ARRIVED AT "+strDtls[i][5]+"("+strDtls[i][4]+")"+" ON "+strDtls[i][8];
                else
                    if(strDtls[i][2].equals(strDtls[i][4]))
                    {
                            strDtls[i][13]="REACHED AT DESTINATION ON "+strDtls[i][8];
                            strDtls[i][9] = "NA";
                    }
                    else
                        strDtls[i][13]=strDtls[i][7]+ " AT "+strDtls[i][5]+"("+strDtls[i][4]+")"+" ON "+strDtls[i][8];
            }
    
            if(intRowCount==0)
            {
                strDtls = new String[intRowCount1][15];
                for (int i=0;i<intRowCount1;i++)
                {
                        strDtls[i][0]   = SRVCNAME.getStringItemDef("F_ACTLWGHT",       i);
                        strDtls[i][1]   = SRVCNAME.getStringItemDef("F_ACTLUNTS",       i);
                        strDtls[i][2]   = SRVCNAME.getStringItemDef("F_ULDGSTTN",       i);
                        strDtls[i][3]   = SRVCNAME.getStringItemDef("F_CNSRNAME",       i);
                        strDtls[i][4]   = "";
                        strDtls[i][5]   = "";
                        strDtls[i][6]   = "";
                        strDtls[i][7]   = "";
                        strDtls[i][8]   = "";
                        strDtls[i][9]   = "NA";
                        strDtls[i][10]  = "";
                        strDtls[i][11]= strDtls[i][1]+"("+strDtls[i][0]+")";
                        strDtls[i][12]= strDtls[i][3]+"("+strDtls[i][2]+")";
                        strDtls[i][13]= "AWAITING DEPARTURE FROM ORIGINATING STATION";
                }
            }
           logger.info("End RQFNRDTLS.");
            
           logger.info("Start Map RQFNRDTLS.");
            String strETA="";
        
            String strData2 = SRVCNAME.getStringItemDef("F_RPWBNUMB2", 0);  
           logger.info("Called Tux_Fchg.");
           logger.info("F_RPWBNUMB2 :"+ strData2);
            intRowCount=Integer.parseInt(strData2); 
            
            strDesc                = SRVCNAME.getStringItemDef("F_ORDRBY10",0,"0");
           logger.info("strDesc :"+ strDesc);
            
            strETA         = SRVCNAME.getStringItemDef("F_RPWBNUMB1",0,"0");
           logger.info("strETA :"+ strETA);
            
            strDtls2 = new String[intRowCount][16];
            //System.out.println("strDtls2");
            for(int i=0;i<intRowCount;i++)
                for(int j=0;j<11;j++)
                        strDtls2[i][j]="";
            String strCrntFlag="";
            int i=0;
            String strSttnName="";
                    
            for (i=0;i<intRowCount;i++)
            {
                strDtls2[i][0]  = SRVCNAME.getStringItemDef("F_ORDRBY1",        i);
                strDtls2[i][1]  = SRVCNAME.getStringItemDef("F_ORDRBY2",        i);
                strDtls2[i][2]  = SRVCNAME.getStringItemDef("F_ORDRBY3",        i);
                strDtls2[i][3]  = SRVCNAME.getStringItemDef("F_ORDRBY4",        i);
                strDtls2[i][4]  = SRVCNAME.getStringItemDef("F_ORDRBY5",        i);                     
                strDtls2[i][5]  = SRVCNAME.getStringItemDef("F_ORDRBY6",        i);
                strDtls2[i][6]  = SRVCNAME.getStringItemDef("F_ORDRBY7",        i);
                strDtls2[i][7]  = SRVCNAME.getStringItemDef("F_ORDRBY8",        i);
                strDtls2[i][8]  = SRVCNAME.getStringItemDef("F_ORDRBY9",        i);
                strDtls2[i][6]  = strDtls2[i][6].replace("'", "");
        
                strSttnName=strDtls2[i][6];
                String str ="";        
                char a ;
                for(int j =1;j<strSttnName.length();j++)
                {
                    a = strSttnName.charAt(j);           
                    if((a==' '))
                    {
                        str = str+Character.toLowerCase(a)+(Character.toUpperCase(strSttnName.charAt(j+1)));
                        j++; // "skip" the next element since it is now already processed
                    }
                    else
                    {
                        str =str+(Character.toLowerCase(a));
                    }
                    
                }
                strSttnName=(strSttnName.substring(0,1));
                strSttnName=strSttnName+str;
                strDtls2[i][6]=strSttnName;  
                if(i>0)                    
                    if(strDtls2[i][3].equals("5") && (!strDtls2[i-1][3].equals("5")))
                    {
                        strDtls2[i-1][10]="C";
                        strCrntFlag="Y";
                    }
                if(strDtls2[i][3].equals("5"))
                    if(i==intRowCount-1)
                            strDtls2[i][10]="D";   /*Destination*/
                    else
                        strDtls2[i][10]="F";   /*Forthcoming Stations*/
                if(strDtls2[i][3].equals("1"))
                        strDtls2[i][10]="S";   /*Source Station*/
                if(strDtls2[i][3].equals("2"))
                        strDtls2[i][10]="P";  /*Stations already Passed*/
                strDtls2[i][11]=  strETA;   /*ETA*/
                strDtls2[i][12]=  "";  
                strDtls2[i][13]=  "";  
                strDtls2[i][14]=  "";  
                strDtls2[i][15]=  "";  
            }
        
            if(strCrntFlag.equals(""))
                    strDtls2[i-1][10]="C";
            strDtls2[0][9]=  strDesc;       
           logger.info("End RQFGAMISCQRY.");
            
            
 /*          logger.info("Start Map Query from SAHAY");
            
            logger.info("Entering getFNRRoute....");
            logger.info("Function called with inputs :");
            logger.info("strCnmtId  #"+strCnmtId+"#");

            FOISTuxedo RQSAHAYPRTL  = null;

            try
            {
                    logger.info("Calling Tuxedo");
                    RQSAHAYPRTL = new FOISTuxedo();
                    logger.info("Client Object Got.");
            }
            catch (Exception ne)
            {
                    logger.fatal("Unable to get the Client Object: " + ne.toString());
                    return fnrlist;
            }

            try
            {
                    RQSAHAYPRTL.tuxInit("RQSAHAYPRTL");
                    RQSAHAYPRTL.setString("F_USERID",               0,      "CRIS1");
                    RQSAHAYPRTL.setString("F_CLNTID",               0,      "CRIS1");
                    RQSAHAYPRTL.setString("F_FLAG",                 0,      "FNR_TRACK");
                    RQSAHAYPRTL.setString("F_HLDGZONE",             0,      strCnmtId);
            }
            catch(Exception e)
            {
                    logger.fatal("Unable to write to buffer : " + e.toString());
                    return fnrlist;
            }
            try
            {
                    logger.info("Calling Tuxedo Service SRVCNAME ...");
                    RQSAHAYPRTL.call("N");
                    logger.info("CALL COMPLETED !");
            }
            catch(Exception e)
            {
                    logger.fatal("Exception while call to the service is " + e.toString());
                    return fnrlist;
            }
            // Checking For Any Error from Service
            erorCode1                        = null;
            try
            {
                    erorCode1                               = RQSAHAYPRTL.getStringItemDef("F_ERORNO",0,"0");
            }
            catch(Exception e)
            {
                    // F_ERORNO is not set by Developer, So, it is not an Error
            }
            if(erorCode1 != null && (!erorCode1.equals("")))
            {
                    logger.fatal(erorCode1);
                    return fnrlist;
            }
            String startRowCount1   = null;
            int start1                              = 0;

            try
            {
                    startRowCount1          = RQSAHAYPRTL.getStringItemDef("F_ROWCONT",0,"0");
                    start1 = new Integer(startRowCount1.trim()).intValue();
            }
            catch(Exception d)
            {
                    logger.fatal("Problem in Calling Service SRVCNAME and filling Row Count into array" + d.toString());
                    return fnrlist;
            }

            logger.info("start1 : " + start1);

            strData3 = new String[start1][9];

            for(i=0;i<start1;i++)
                for(int j=0;j<9;j++)
                        strData3[i][j]="";
            logger.info("Start reading data for FNR Route");
            
            for(i=0; i<start1; i++)
            {
                    try
                    {
                            strData3[i][0]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY1",i,"0").trim();
                            strData3[i][1]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY2",i,"0").trim();
                            strData3[i][2]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY3",i,"0").trim();
                            strData3[i][3]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY4",i,"0").trim();                               
                            strData3[i][4]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY5",i,"0").trim();                               
                            strData3[i][5]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY6",i,"0").trim();                               
                            strData3[i][6]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY7",i,"0").trim();                               
                            strData3[i][7]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY8",i,"0").trim();                               
                            strData3[i][8]           = RQSAHAYPRTL.getStringItemDef("F_ORDRBY9",i,"0").trim();       
            }
            catch(Exception d)
            {
            logger.fatal("Problem in Calling Service RQSAHAYPRTL and filling data into array" + d.toString());
                    return fnrlist;
            }
            
            } // End of for Loop
            try
            {
                    RQSAHAYPRTL.endSession();
            }
            catch(Exception e)
            {
                    logger.fatal("Error In End Session:" + e.toString());
                    return fnrlist;
            }
   */     }
        catch (Exception e)
        {
           logger.info("There was an exception while creating and using the FOIS."+e);
            strError="SERVER NOT RUNNING PLEASE TRY AFTER SOMR TIME(2)";
           logger.info("strError"+strError);
            return fnrlist;
        }
        finally
        {
            try
            {
                    SRVCNAME=null;
            }
            catch(Exception e)
            {
                   logger.info("Errror In Finally.");
                    strError="SERVER NOT RUNNING PLEASE TRY AFTER SOMR TIME(3)";
                   logger.info("strError"+strError);
                    return fnrlist;
            }
        }
        if(strDtls!=null)
        {
            fnrlist.add(strDtls);   //FNR Enqiry Data
            fnrlist.add(strDtls2); //Map Data only 3 pts
       //     fnrlist.add(strData3); //Map Data Complete route
        }
       logger.info("SUCCESSFULL return");
        return fnrlist;
    }
}
