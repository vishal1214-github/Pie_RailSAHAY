package util;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;

public class GG_DBConnection
{
	String strDriver="";
	String strPoolName="";

	public GG_DBConnection()
	{
		this.strDriver="weblogic.jdbc.pool.Driver";
		this.strPoolName="jdbc:weblogic:pool:RMSFIELD";
	}
	public GG_DBConnection(String strPoolName)
	{
		this.strDriver="weblogic.jdbc.pool.Driver";
		this.strPoolName=strPoolName;
	}

	public GG_DBConnection(String strDriver,String strPoolName)
	{
		this.strDriver=strDriver;
		this.strPoolName=strPoolName;
	}

	public Connection funcGetDirectConn() throws Exception
	{
		Connection connection = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.30.1.103:1521:TEST1", "RMS3T", "RMS3T");
			return connection;
		}
		catch(ClassNotFoundException exception)
		{
			exception.printStackTrace();
			throw new Exception("Driver Not Available.");
		}

		catch(Exception exception)
		{
			exception.printStackTrace();
			throw new Exception("Error In GetConnection");
		}

	}

	public Connection funcGetConn(String dts) throws Exception
    {
        Connection con = null;
	   	  try
	   	  { 
	   		  Properties p = new Properties();
          p.put("java.naming.factory.initial", "weblogic.jndi.T3InitialContextFactory");
          //p.put("java.naming.provider.url", "t3://10.60.200.94:50006,10.60.200.95:50006,10.60.200.96:50006,10.60.200.97:50006");
          p.put("java.naming.provider.url", "t3://10.60.201.195:50014");
		 Context ctx = new InitialContext(p);
	   		
	   		 DataSource ds  = (DataSource)ctx.lookup(dts);
	   		 con = ds.getConnection();
	   	  }
	   	  catch(Exception ex)
	   	  {
	   		System.out.println("Exception Occured in UserRole connection TESSTTSTS " + ex);
	   	  }
	  return con;
    }

    public Connection getConnection()
	{
	  Connection con = null;
	  try
	  {
          Properties p = new Properties();
          p.put("java.naming.factory.initial", "weblogic.jndi.T3InitialContextFactory");
          p.put("java.naming.provider.url", "t3://10.60.200.11:50014");
		 Context ctx = new InitialContext(p);
		 DataSource ds  = (DataSource)ctx.lookup("RMSFIELD");
		 con = ds.getConnection();
	  }
	  catch(Exception ex)
	  {
		System.out.println("Exception Occured in UserRole connection 123333" + ex);
	  }
	  return con;
	}
}