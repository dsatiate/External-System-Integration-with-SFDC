package aviva;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.sobject.*;
import com.sforce.soap.partner.*;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.ConnectionException;
import com.sforce.soap.partner.Error;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class CallSFDCCreateAviva {
       static final String USERNAME = "username";
       static final String PASSWORD = "password + sec token";
       //static EnterpriseConnection connection;
       static PartnerConnection partnerConnection = null;
       
       public static String InsertAvivaRecord(@SuppressWarnings("rawtypes") Map ParamMap) {
    	      SaveResult[] saveResults = null;
    	      
              try {
            	  CallSFDCCreateAviva samples = new CallSFDCCreateAviva();
            	  samples.login();
                
            	  SObject[] acc = new SObject[1]; 
                  
            	  acc[0]  = new SObject();
                  
            	  acc[0].setType("AvivaAccountStaging__c");  
  
  				acc[0].setField("Title__c",(String) ParamMap.get("Field1")); 
                acc[0].setField("First_Name__c",(String) ParamMap.get("Field2"));
                acc[0].setField("Last_Name__c",(String) ParamMap.get("Field3"));
                
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
                cal.setTime(sdf.parse((String)ParamMap.get("Field4")));
                acc[0].setField("Birth_date__c", ( cal));
                
                acc[0].setField("Gender__c",(String) ParamMap.get("Field5"));
                acc[0].setField("Address_1__c",(String) ParamMap.get("Field6"));
                acc[0].setField("Address_2__c",(String) ParamMap.get("Field7"));
                acc[0].setField("City__c",(String) ParamMap.get("Field8"));
                acc[0].setField("County__c",(String) ParamMap.get("Field9")); 
                acc[0].setField("Postcode__c",(String) ParamMap.get("Field10")); 
                acc[0].setField("Email__c",(String) ParamMap.get("Field12")); 
                acc[0].setField("Telephone_1__c",(String) ParamMap.get("Field13"));  
                acc[0].setField("Telephone_2__c",(String) ParamMap.get("Field14"));
                acc[0].setField("Mobile_Number__c",(String) ParamMap.get("Field15"));
                acc[0].setField("Assessment_Ref__c",(String) ParamMap.get("Field16")); 
                acc[0].setField("Policy_Number__c",(String) ParamMap.get("Field17")); 
                acc[0].setField("Member_Number__c",(String) ParamMap.get("Field18")); 
                acc[0].setField("Claims_Assessor_ID__c",(String) ParamMap.get("Field19"));
                
                saveResults = partnerConnection.create(acc);
                System.out.print("saveResults"+saveResults);
                //connection.logout();

              } catch (ConnectionException e1) {
                  System.out.println("hello world");
                  e1.printStackTrace();
              } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}               
              if (saveResults[0].isSuccess()) {
                  System.out.println("Successfully created ID: "+ saveResults[0].getId());
                  return saveResults[0].getId();
              }
              else {
            	  
            	  return "Failed";
              }
              
              }
       
       private boolean login() {
           boolean success = false;
           
           try {
             ConnectorConfig config = new ConnectorConfig();
             config.setUsername("*******************");
             config.setPassword("*******************");
             
             config.setAuthEndpoint("https://test.salesforce.com/services/Soap/u/24.0/");
             config.setTraceFile("traceLogs.txt");
             config.setTraceMessage(true);
             config.setPrettyPrintXml(true);
             
             // display some current settings
             System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
             System.out.println("Service EndPoint: "+config.getServiceEndpoint());
             System.out.println("Username: "+config.getUsername());
             System.out.println("SessionId: "+config.getSessionId());

             partnerConnection = new PartnerConnection(config);          

             success = true;
           } catch (ConnectionException ce) {
             ce.printStackTrace();
           } catch (FileNotFoundException fnfe) {
             fnfe.printStackTrace();
           }

           return success;
         }
}
