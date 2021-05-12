package parser;

import java.util.*;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import model.*;
import util.configuration;


public class JsontoJava {	
	
	ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> gen = new ArrayList<String>();
    ArrayList<Boolean> hep = new ArrayList<Boolean>();
    ArrayList<Double> bili = new ArrayList<Double>();
    ArrayList<String> form_com = new ArrayList<String>();
    
 //   ArrayList<String> obs = new ArrayList<String>();
		
    public void converter() {
    	
    	// getting JsonNode from RedCapServerUnirest;
    	redcapx.RedCapServerUnirest testen = new redcapx.RedCapServerUnirest();	
    	JsonNode rohdaten = testen.getData();
		
//    	List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\lisam\\Documents\\Studium\\Projektarbeit\\Gruppe1\\Daten\\test.json"));
//		String rohdaten= lines.get(0);   	
//      System.out.println(rohdaten);
      
    	// transform JsonNode separated into ArrayLists
        for (int i = 0; i < rohdaten.getArray().length(); i++) {
        	JSONObject jobj = rohdaten.getArray().getJSONObject(i);
        	System.out.println(jobj);
        	
//        	obs.add(jobj.getString("record_id"));
//        	obs.add(jobj.getString("hepatitis_b"));       	
//        	obs.add(jobj.getString("bilirubin_concentration"));
//        	obs.add(jobj.getString("form_1_complete")); 
        	
        	id.add(i, jobj.getString("record_id"));
        	gen.add(i, jobj.getString("gender"));
        	hep.add(i, jobj.getBoolean("hepatitis_b"));       	
        	bili.add(i, jobj.getDouble("bilirubin_concentration"));
        	form_com.add(i, jobj.getString("form_1_complete"));       	
        }
        
//      System.out.println(id);
//    	System.out.println(gen);
//    	System.out.println(hep);
//    	System.out.println(bili);
//    	System.out.println(form_com);
    	
    	for (int i = 0; i < id.size(); i++) {
    		Datamodel data = new Datamodel(id.get(i).toString(),gen.get(i).toString(),hep.get(i),bili.get(i).doubleValue(),form_com.get(i).toString());
			System.out.println(data);
			
			BooleanObservationModel boolobs = new BooleanObservationModel(configuration.observationSystem_BILIRUBIN, configuration.observationCode_BILIRUBIN_CODE, hep.get(i));
			System.out.println(boolobs);
			
			NumericalObservationModel numobs = new NumericalObservationModel(configuration.observationSystem_HEPATITIS_B, configuration.observationCode_HEPATITIS_B_CODE, bili.get(i).doubleValue(), configuration.UNIT);
			System.out.println(numobs);
			
		}
        
// https://www.youtube.com/watch?v=ynO4_XtUdOg

    }
    // transfer data to BooleanObservationModel
    public void booleanObs () {
    	
    	for (int i = 0; i < hep.size(); i++) {
    	
			BooleanObservationModel boolobs = new BooleanObservationModel(configuration.observationSystem_BILIRUBIN, configuration.observationCode_BILIRUBIN_CODE, hep.get(i));
			System.out.println(boolobs);
		}
    }
    // transfer data to NumericalObservationModel
    public void numericalObs() {
    	
    	for (int i = 0; i < bili.size(); i++) {
    		
			NumericalObservationModel numobs = new NumericalObservationModel(configuration.observationSystem_HEPATITIS_B, configuration.observationCode_HEPATITIS_B_CODE, bili.get(i).doubleValue(), configuration.UNIT);
			System.out.println(numobs);
		}
    }
    // transfer data to Datamodel
    public void allObs() {
    	
    	for (int i = 0; i < id.size(); i++) {
    		Datamodel data = new Datamodel(id.get(i).toString(),gen.get(i).toString(),hep.get(i),bili.get(i).doubleValue(),form_com.get(i).toString());
			System.out.println(data);
    	}
    }
    
    public static void main(String[] args) {

    	JsontoJava conv = new JsontoJava();
		conv.converter();
		conv.numericalObs();
		conv.booleanObs();
		conv.allObs();
           
    }
}
