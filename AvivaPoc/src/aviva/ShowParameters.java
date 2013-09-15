package aviva;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

/**
 * Servlet implementation class ShowParameters
 */
@WebServlet("/ShowParameters")
public class ShowParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowParameters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Map ParamMap = new HashMap();
		System.out.println("Check 0");
		response.setContentType("text/html");
	    
	    int j=0;
	    String[] paramValueList = new String[30];
	    Enumeration paramNames = request.getParameterNames();
	    while(paramNames.hasMoreElements()) {
	      String paramName = (String)paramNames.nextElement();
	      String[] paramValues = request.getParameterValues(paramName);
	      paramValueList[j]=paramValues[0];
	      if (paramValues.length == 1) {
	        String paramValue = paramValues[0];
	        if (paramValue.length() == 0)
	        	ParamMap.put(paramName,"" );
	        else
	        	ParamMap.put(paramName,paramValue);
	      } else {
	        for(int i=0; i<paramValues.length; i++) {
	        	if(paramValues[i]!= null || paramValues[i]!= ""){
	        		ParamMap.put(paramName,paramValues[i]);
	        	}
	        }
	      }
	      j++;
	    }
	    String site;
	    String rid = CallSFDCCreateAviva.InsertAvivaRecord(ParamMap);
	    if(rid!="Failed"){
	    	site = new String("http://nuffieldhealth.parttwodev.cs2.force.com/avivaportal/ap_demo_home?rid="+rid);
	    	//https://nuffieldhealth.parttwodev.cs2.force.com/avivaportal/ap_demo_home?Rid=a11R0000001CbjcIAC
	    }
	    else{
	    	site = new String("http://nuffieldhealth.parttwodev.cs2.force.com/avivaportal");
	    }	
	     response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	     response.setHeader("Location", site); 
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
