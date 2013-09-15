package aviva;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args){
		
		Map<String, String> ParamMap = new HashMap<String, String>();
		ParamMap.put("Field1","MR" );
		ParamMap.put("Field2","DEEPAK" );
		ParamMap.put("Field3","KUMAR" );
		CallSFDCCreateAviva.InsertAvivaRecord(ParamMap);
	}
}
