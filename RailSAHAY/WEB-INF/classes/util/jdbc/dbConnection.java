package util.jdbc;

import java.sql.Connection;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class dbConnection {
    public dbConnection() {
        super();
    }
    public static Connection getConnection()
    {

            Connection conn = null;
      try
      {
            // Properties p = new Properties();
            // p.put("java.naming.factory.initial", "weblogic.jndi.T3InitialContextFactory");
            // p.put("java.naming.provider.url", "t3://10.60.200.53:50015,10.60.200.49:50015");
             Context ctx = new InitialContext();
             DataSource ds  = (DataSource)ctx.lookup("RMSFIELD");

             conn = ds.getConnection();
             if(conn!=null)
                    System.out.println("Database Connection obtained");
      }
      catch(Exception ex)
      {
            System.out.println("Exception Occured in UserRole connection " + ex);
      }
      return conn;
    }
}
