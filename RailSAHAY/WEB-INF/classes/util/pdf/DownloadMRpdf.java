package util.pdf;

import util.jdbc.dbConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import util.logger.FoisLogger;

public class DownloadMRpdf extends HttpServlet{
    public DownloadMRpdf() {
        super();
    }
	static Logger logger 	=	FoisLogger.getLogger(DownloadMRpdf.class.getName());
	Connection  connection	=	null;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{	
		logger.info("Entering in DownloadMRpdf Servlet..");
		String strTrxnID		=	(String)req.getParameter("TrxnId").trim();
		logger.debug("strTrxnID:"+strTrxnID);
		try
                {
                    connection = dbConnection.getConnection();       
    
                    logger.debug("Got Connection");
                    Statement statement = connection.createStatement();
                    logger.debug("Created Statement");
                    //String strQuery	=	"SELECT TABDOC,'MR.pdf' FROM TED_MRDOC WHERE TAVBANKTRXNID = '"+ strTrxnID +"'";
                    String strQuery     =       "SELECT T1.TABDOC,T2.TAVAPPTRXNID||'.pdf' " +
                                                "FROM TED_MRDOC T1,TEM_EPAYCPGTRXN T2 " +
                                                "WHERE T1.TAVBANKTRXNID = '"+ strTrxnID +"' " +
                                                "AND  T1.TAVBANKTRXNID  = T2.TAVBANKTRXNID " +
                                                "AND T1.TACGNRNFLAG = 'Y' ";
            
                    logger.debug("Query:"+strQuery);
                    
                    ResultSet rs 	= 	statement.executeQuery(strQuery);
                    
                    if(rs.next())
                    {
                        logger.info("Recieved MR from DB");
                        String strFileName              = rs.getString(2).trim();
                        
                        //res.setContentType( "application/pdf" );
                        res.setContentType( "APPLICATION/OCTET-STREAM" );
                        //res.addHeader( "Content-Disposition", "filename="+strFileName );
                        res.setHeader("Content-Disposition", "attachment; filename=\"" + strFileName+ "\"");
                                   
                        Blob  b = rs.getBlob("TABDOC");
                        res.setContentLength( (int) b.length());
                        InputStream is = b.getBinaryStream();
                        OutputStream os = res.getOutputStream();
                        byte buf[] = new byte[(int) b.length()];
                        is.read(buf);
                        os.write(buf);
                        //os.close();
                        rs.close();
                        statement.close();
                        connection.close();    
                        logger.debug("Closing os");
                    }
                    else {
                        res.setContentType("text/html");
                            PrintWriter out = res.getWriter();
        
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>PDF File</title>");
                            out.println("</head>");
                            out.println("<body bgcolor=\"white\">");
                            out.println("<h1>Money Receipt has not been generated yet. Please try after sometime.</h1>");
                            out.println("</body>");
                            out.println("</html>");
                        
                    }
                    logger.debug("Exiting successfully.");
        }
        catch (Exception e) 
        {
	    	logger.fatal("Other Exception: " + e.toString());
	    	System.out.println("Other Exception from DownloadMRpdf: " + e.getMessage());
	    	e.printStackTrace();  
        }
        finally 
        {
                try 
                {
                        if (connection != null) 
                                connection.close();
                }
                catch (SQLException e)
                {
                        logger.fatal("Connection Closing Exception: " + e.toString());
                        e.printStackTrace();  
                }        
        }
    }
}
