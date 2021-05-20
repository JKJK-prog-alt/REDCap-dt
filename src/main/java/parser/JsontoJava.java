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
    
    ArrayList<AbstractObservationModel> absBoolObs = new ArrayList<AbstractObservationModel>();
    ArrayList<AbstractObservationModel> absNumObs = new ArrayList<AbstractObservationModel>();
		
    public void converter() {
    	
    	// getting JsonNode from RedCapServerUnirest;
    	redcapx.RedCapServerUnirest testen = new redcapx.RedCapServerUnirest();	
    	JsonNode rohdaten = testen.getData();

    	// transform JsonNode separated into different ArrayLists
        for (int i = 0; i < rohdaten.getArray().length(); i++) {
        	JSONObject jobj = rohdaten.getArray().getJSONObject(i);
   
        	// adding the different elements to their respective ArrayLists
        	id.add(i, jobj.getString("record_id"));
        	gen.add(i, jobj.getString("gender"));
        	
        	// convert the String into understandable boolean format
        	if(jobj.getString("hepatitis_b").contentEquals("1")) {
        		hep.add(i, true);
        	} else {
        		hep.add(i, false);
        	}
        	bili.add(i, jobj.getDouble("bilirubin_concentration"));
        	form_com.add(i, jobj.getString("form_1_complete"));       	
        }
    }
    // fill object of BooleanObservationModel with the data & return an ArrayList of AbstractObservationModel
    public ArrayList<AbstractObservationModel> booleanObs () {
    	
    	for (int i = 0; i < hep.size(); i++) {
			BooleanObservationModel boolobs = new BooleanObservationModel(configuration.observationSystem_HEPATITIS_B, configuration.observationCode_HEPATITIS_B_CODE, hep.get(i));
			absBoolObs.add(i, boolobs);
		}
    	return absBoolObs;
    }
    // fill object of NumericalObservationModel with the data & return an ArrayList of AbstractObservationModel
    public ArrayList<AbstractObservationModel> numericalObs() {
    	
    	for (int i = 0; i < bili.size(); i++) {
			NumericalObservationModel numobs = new NumericalObservationModel(configuration.observationSystem_BILIRUBIN, configuration.observationCode_BILIRUBIN_CODE, bili.get(i).doubleValue(), configuration.UNIT);
			absNumObs.add(i, numobs);
		}
    	return absNumObs;
    }
    // Getter method for the gender Array
    	public ArrayList<String> getGen() {
    		return gen;
    	}

//    public static void main(String[] args) {
//
//    	JsontoJava conv = new JsontoJava();
//		conv.converter();
//		conv.numericalObs();
//		conv.booleanObs();
//		  
//    }
}
