package model;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import java.net.URLEncoder;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class LoginServiceClient {
    public LoginServiceClient() {
        super();
    }
    
    public static void main(String[] args) throws Exception  
        {
        boolean st=verifyUser("rajendravaishnav@cris.org.in","test");
        System.out.println("status:::"+st);
        }
    
    public static boolean verifyUser(String userName,String password){
        
        Boolean status=false;
        try {
            String encodedPassword=URLEncoder.encode(password, "UTF-8");

           String uri="http://10.60.201.195:50014/AuthService/resources/Login/Verify?user="+userName+"&password="+encodedPassword+"";
           // String uri="http://10.60.200.54:50014/AuthService/resources/Login/Verify?user="+userName+"&password="+encodedPassword+"";
            //String uri="http://10.60.201.195:50014/AuthService/resources/Login/Verify?user=rajendravaishnav@cris.org.in&password=test";
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.1.61", 8080));
          URL url=new URL(uri);
              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
              
              conn.setRequestMethod("GET");
              conn.setRequestProperty("Accept", "application/json");

              if (conn.getResponseCode() != 200) {
                      throw new RuntimeException("Failed : HTTP error code : "
                                      + conn.getResponseCode());
              }

              BufferedReader br = new BufferedReader(new InputStreamReader(
                      (conn.getInputStream())));

              String output;
            Object obj=null ;
              System.out.println("Output from Server .... \n");
              while ((output = br.readLine()) != null) {
                      System.out.println(output);
                  
                   obj = new JSONParser().parse(output);
                      //Write JSON file
                            
              }
            
         //   JSONArray data = (JSONArray) obj;
          //  System.out.println("total records::"+data.size());
              
          //  JSONObject row=(JSONObject)data.get(0);
          JSONObject row=(JSONObject)obj;
              
               status= (Boolean)row.get("resp");
              conn.disconnect();

        } catch (MalformedURLException e) {

              e.printStackTrace();

        } catch (IOException e) {

              e.printStackTrace();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status.booleanValue();
    }
    public static boolean verifyFBD(String userName,String password){
        
        Boolean status=false;
        try {
            String encodedPassword=URLEncoder.encode(password, "UTF-8");

           String uri="http://10.60.201.195:50014/AuthService/resources/Login/VerifyFBD?user="+userName+"&password="+encodedPassword+"";
          //  String uri="http://10.60.200.54:50014/AuthService/resources/Login/VerifyFBD?user="+userName+"&password="+encodedPassword+"";
            //String uri="http://10.60.201.195:50014/AuthService/resources/Login/Verify?user=rajendravaishnav@cris.org.in&password=test";
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.1.61", 8080));
          URL url=new URL(uri);
              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
              
              conn.setRequestMethod("GET");
              conn.setRequestProperty("Accept", "application/json");

              if (conn.getResponseCode() != 200) {
                      throw new RuntimeException("Failed : HTTP error code : "
                                      + conn.getResponseCode());
              }

              BufferedReader br = new BufferedReader(new InputStreamReader(
                      (conn.getInputStream())));

              String output;
            Object obj=null ;
              System.out.println("Output from Server .... \n");
              while ((output = br.readLine()) != null) {
                      System.out.println(output);
                  
                   obj = new JSONParser().parse(output);
                      //Write JSON file
                            
              }
            
         //   JSONArray data = (JSONArray) obj;
          //  System.out.println("total records::"+data.size());
              
          //  JSONObject row=(JSONObject)data.get(0);
          JSONObject row=(JSONObject)obj;
              
               status= (Boolean)row.get("resp");
              conn.disconnect();

        } catch (MalformedURLException e) {

              e.printStackTrace();

        } catch (IOException e) {

              e.printStackTrace();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status.booleanValue();
    }
}
