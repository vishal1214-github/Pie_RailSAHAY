package servlet.AppData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import java.util.Base64;

import javax.crypto.SecretKey;

import util.logger.FoisLogger;

/**
 * @author shailu
 * Servlet implementation class ConfirmRegistration
 */
public class ConfirmRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static Connection currentCon = null;
    static ResultSet rs = null;
	static Logger logger = FoisLogger.getLogger(ConfirmRegistration.class.getName());

    private static SecretKeySpec secretKey;
       private static byte[] key;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("In get servlet");
                request.setCharacterEncoding("UTF-8"); 
		logger.info(request.getQueryString());
		Map<String, String> paramMap = ConfirmRegistration.getQueryParams(request.getQueryString());
		boolean confirmSuccess=false;
		String email="";
                String user="";
                String name="";
                String emailkey="";
                String inptURL="";
	    String plainData="",cipherTextcustid="",cipherTextemail="",cipherTextname=""/*,cipherTextaddress="",cipherTextpincode=""*/,decryptedTextcustid="",decryptedTextemail="",decryptedTextname=""/*,decryptedTextaddress="",decryptedTextpincode=""*/;
                HttpSession session = request.getSession(true);
		//session.setAttribute("user",paramMap.get("uname"));
            cipherTextcustid =  paramMap.get("uname");
	    cipherTextemail  =  paramMap.get("email");
            cipherTextname   =  paramMap.get("name");
            logger.info("CustID111::"+request.getParameter("uname")+"::EmailID::"+cipherTextemail+"::Name::"+cipherTextname);
	    try
	    {  
                emailkey = "harekrishnaharer";
                 setKey(emailkey);
                 Cipher aesCipher = Cipher.getInstance("AES");
                
	        byte[]   byteCipherTextemail=Base64.getUrlDecoder().decode(cipherTextemail.getBytes(StandardCharsets.UTF_8));
	        aesCipher.init(Cipher.DECRYPT_MODE,secretKey);
	        byte[] byteDecryptedTextemail = aesCipher.doFinal(byteCipherTextemail);
	        decryptedTextemail = new String(byteDecryptedTextemail);
	        logger.info("Decrypted Data EmailID: "+decryptedTextemail);
	  
         //   cipherText = new BASE64Encoder().encode(byteCipherText);
	    
               // decryptedTextcustid = new String(Base64.getUrlDecoder().decode(cipherTextcustid), StandardCharsets.UTF_8);

                byte[]   byteCipherTextcustid= Base64.getUrlDecoder().decode(cipherTextcustid);
	        aesCipher.init(Cipher.DECRYPT_MODE,secretKey);
	        byte[] byteDecryptedTextcustid = aesCipher.doFinal(byteCipherTextcustid);
	        decryptedTextcustid = new String(byteDecryptedTextcustid);
                logger.info("Decrypted Data CustID: "+decryptedTextcustid);
                
	        byte[]   byteCipherTextname=Base64.getUrlDecoder().decode(cipherTextname.getBytes(StandardCharsets.UTF_8));
	        aesCipher.init(Cipher.DECRYPT_MODE,secretKey);
	        byte[] byteDecryptedTextname = aesCipher.doFinal(byteCipherTextname);
	        decryptedTextname = new String(byteDecryptedTextname);
	        logger.info("Decrypted Data Name: "+decryptedTextname);
                
	        String rqstStringcustid="uname="+decryptedTextcustid;
	        String rqstStringemail="email="+decryptedTextemail;
	        String rqstStringname="name="+decryptedTextname;
	        logger.info("Decrypted  String Data : "+rqstStringcustid +"::"+ rqstStringemail+"::"+rqstStringname);
	      //  paramMap = ConfirmRegistration.getQueryParams(rqstString);
	        session.setAttribute("user",decryptedTextcustid);
	        session.setAttribute("email",decryptedTextemail);
	        session.setAttribute("name",decryptedTextname);
	    
	      
             }
	    catch(Exception e)
	    {
	            logger.info("Error Occured::"+e.getMessage());
                    e.printStackTrace();
	    }
                
		String strfrwd="/pages/RegistrationConfirmation.jsp";
		/*try {
			//confirmSuccess = RegistrationDB.confirmRegistration(paramMap);
                    //above is dropped for now
                    logger.info("DO Nothing just pass to jsp as OTP yet to capture"); 
		} catch (GG_Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			if(e instanceof GG_Exception)
			{
				GG_Exception ek		= (GG_Exception)e;
				logger.info("Inside GG_ERROR");
				String strErorDesc	= ek.getErrorCodeMessage(request).replaceAll("\"", "'");
				request.setAttribute("ErrorField", null);
	            request.setAttribute("Error", strErorDesc);
			}
			else
			{
				logger.info("NOT Inside GG_ERROR");
				GG_Exception ge		= new GG_Exception(e.toString(), e);
				logger.error(e.toString() + "\n" + ge.getStacktrace());
				
				String strErorDesc	= e.toString().replaceAll("\"", "'");
			}
		}*/
		
	    logger.info("Call Completed.");
	    NDC.pop();
	    NDC.remove();
		request.getRequestDispatcher(strfrwd).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("In Post servlet");
	}
	
	/**
	 * This method will return the number of parameters in the request url with its values 
	 * @author shailu
	 * @param url
	 * @return
	 */
	private static Map<String, String> getQueryParams(String query) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			if (query.length() > 1) {
				for (String param : query.split("&")) 
				{
					String[] pair = param.split("=", 2);
					String key = URLDecoder.decode(pair[0], "UTF-8");
					String value = "";
					if (pair.length > 1) {
						value = URLDecoder.decode(pair[1], "UTF-8");
					}
					params.put(key, value);
				}
			}

			return params;
		} catch (UnsupportedEncodingException ex) {
			throw new AssertionError(ex);
		}
	   
	}
    public static void setKey(String myKey) 
       {
           MessageDigest sha = null;
           try {
               key = myKey.getBytes("UTF-8");
               sha = MessageDigest.getInstance("SHA-1");
               key = sha.digest(key);
               key = Arrays.copyOf(key, 16); 
               secretKey = new SecretKeySpec(key, "AES");
           } 
           catch (NoSuchAlgorithmException e) {
               e.printStackTrace();
           } 
           catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }
}
