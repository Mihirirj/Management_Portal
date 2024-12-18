package com.it.dashboard.util.common;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.Environment;




   public class Path{
      private static final String PROP_FILE="config.properties";
      
      
      
      public void readPropertiesFile(){
         
      }
      public static String getPath(){
        try {
            InputStream is = Path.class.getResourceAsStream(PROP_FILE);
            Properties prop = new Properties();
            prop.load(is);
            String path = prop.getProperty("pdfPath");
            
            return  path;
        } catch (IOException ex) {
            Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      }
      
      public String getReportPath(){
    	  //
          try {
        	  	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("META-INF/spring/SystemConfig.xml");
        	  	SystemConfig sc = (SystemConfig) ctx.getBean("SysConfig");
        	  
        	  	String REPORT_DIRECTORY = "" ;
        	  	if (System.getProperty("os.name").indexOf("Windows") != -1 )
  				{
  					REPORT_DIRECTORY = sc.getWindowsReportDir();
  				}	
  				else
  				{
  					REPORT_DIRECTORY = sc.getLinuxReportDir();
  				}
        	  
  				ctx.close();
              
  				return  REPORT_DIRECTORY;
          	} /*/catch (IOException ex) {
        	  ex.printStackTrace();
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          	}*/
          	catch(Exception e)
          	{
        	  e.printStackTrace();
        	  return null;
          	}
        }
      
      public String getReportOutPath(){
    	 
          try {
        	  	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("META-INF/spring/SystemConfig.xml");
        	  	SystemConfig sc = (SystemConfig) ctx.getBean("SysConfig");
        	  
        	  	String EXPORT_REPORT_DIRECTORY = "";
  			
        	  	if (System.getProperty("os.name").indexOf("Windows") != -1 )
        	  	{
  					EXPORT_REPORT_DIRECTORY = sc.getWindowsReportOutDir();

  				}	
  				else
  				{
  					EXPORT_REPORT_DIRECTORY = sc.getLinuxReportOutDir();
  				}
        	  
  				ctx.close();
              
  				return  EXPORT_REPORT_DIRECTORY;
          	} 
          	catch(Exception e)
          	{
        	  e.printStackTrace();
        	  return null;
          	}
        }
      
      public String getDBUrl(String region)
      {
    	  try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              
              String db_url = null;
              if (region.equals("R1")){
  				db_url = prop.getProperty("db_url_R1");  				 
  			  } else if (region.equals("R2")){
  				db_url = prop.getProperty("db_url_R2");  			 
  			  }else if (region.equals("R3")){
  				db_url = prop.getProperty("db_url_R3");  			 
  			  }else if (region.equals("R4")){
  				db_url = prop.getProperty("db_url_R4");  			 
  			  }else {
   				db_url = prop.getProperty("db_url_RX");  			 
   			  }
             
               
              return  db_url;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
      }
      
      public String getDBUserName(String region)
      {
    	  try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              
              String db_url = null;
            if (region.equals("R1")){
  				db_url = prop.getProperty("db_userName_R1");  				 
  			} else if (region.equals("R2")){
  				db_url = prop.getProperty("db_userName_R2");  			 
  			} else if (region.equals("R3"))	{
  				db_url = prop.getProperty("db_userName_R3");  			 
  			} else if (region.equals("R4")){
  				db_url = prop.getProperty("db_userName_R4");  			 
  			}else {
  				db_url = prop.getProperty("db_userName_RX");  			 
  			}
             
               
              return  db_url;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
      }
      
      public String getDBPassword(String region)
      {
    	  try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              
              String db_url = null;
              if (region.equals("R1")){
  				db_url = prop.getProperty("db_password_R1");  				 
  			  } else if (region.equals("R2"))	{
  				db_url = prop.getProperty("db_password_R2");  			 
  			  } else if (region.equals("R3"))	{
  				db_url = prop.getProperty("db_password_R3");  			 
  			  }else if (region.equals("R4")){
  				db_url = prop.getProperty("db_password_R4");  			 
  			  }else {
  				db_url = prop.getProperty("db_password_RX");  			 
  			  }
               
              return  db_url;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
      }
      
     
      
      public static String getBillExportPath(){
          try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              String path = prop.getProperty("BillExportPath");
              
              return  path;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
        }
      
      
      
     }