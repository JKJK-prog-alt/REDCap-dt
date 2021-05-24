package parser;

import java.util.*;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import model.*;
import util.configuration;

/************************************************************
 * @Author: Lisamaria Eble and Niklaas-Benedikt Oehme
 * 
 * Parsing the Redcap data into a readable format so that we can
 * transfer the parsed data to our own server
 * **********************************************************/
public class JsontoJava {	
	
	ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> gen = new ArrayList<String>();
    ArrayList<Boolean> hep = new ArrayList<Boolean>();
    ArrayList<Double> bili = new ArrayList<Double>();
    ArrayList<String> form_com = new ArrayList<String>();    
    ArrayList<AbstractObservationModel> absBoolObs = new ArrayList<AbstractObservationModel>();
    ArrayList<AbstractObservationModel> absNumObs = new ArrayList<AbstractObservationModel>();    
    /*
     * getting JsonNode from RedCapServerUnirest;
     * next we transform JsonNode separated into different ArrayLists
     * then adding the different elements to their respective ArrayLists
     * afterwards we convert the String into an understandable boolean format
    */		
    public void converter() {
    	
    	redcapx.RedCapMockServer testen = new redcapx.RedCapMockServer();
//    	redcapx.RedCapServerUnirest testen = new redcapx.RedCapServerUnirest();	
    	JsonNode rohdaten = testen.getData();
    	
        for (int i = 0; i < rohdaten.getArray().length(); i++) {
        	JSONObject jobj = rohdaten.getArray().getJSONObject(i);         	
        	id.add(i, jobj.getString("record_id"));
        	gen.add(i, jobj.getString("gender"));

        	if(jobj.getString("hepatitis_b").contentEquals("1")) {
        		hep.add(i, true);
        	} else if(jobj.getString("hepatitis_b").contentEquals("0")) {
        		hep.add(i, false);
        	}else {        		
        		System.err.println("False input");
        	}
        	
        	bili.add(i, jobj.getDouble("bilirubin_concentration"));
        	form_com.add(i, jobj.getString("form_1_complete"));       	
        }
    }
    /*
     * fill object of BooleanObservationModel with the data & return an ArrayList of AbstractObservationModel
     */ 
    public ArrayList<AbstractObservationModel> booleanObs () {
    	
    	for (int i = 0; i < hep.size(); i++) {
			BooleanObservationModel boolobs = new BooleanObservationModel(configuration.observationSystem_HEPATITIS_B, configuration.observationCode_HEPATITIS_B_CODE, hep.get(i));
			absBoolObs.add(i, boolobs);
		}
    	return absBoolObs;
    }
    /*
     * fill object of NumericalObservationModel with the data & return an ArrayList of AbstractObservationModel
     */ 
    public ArrayList<AbstractObservationModel> numericalObs() {
    	
    	for (int i = 0; i < bili.size(); i++) {
			NumericalObservationModel numobs = new NumericalObservationModel(configuration.observationSystem_BILIRUBIN, configuration.observationCode_BILIRUBIN_CODE, bili.get(i).doubleValue(), configuration.UNIT);
			absNumObs.add(i, numobs);
		}
    	return absNumObs;
    }
    /*
     * Getter method for the gender Array
     */ 
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
