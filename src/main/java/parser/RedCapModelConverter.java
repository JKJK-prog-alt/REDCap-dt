
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
public class RedCapModelConverter {	

	ArrayList<String> recordId = new ArrayList<String>();
	ArrayList<String> gender = new ArrayList<String>();
	ArrayList<Boolean> hepatitis_B = new ArrayList<Boolean>();
	ArrayList<Double> billirubin = new ArrayList<Double>();
	ArrayList<String> form_complete = new ArrayList<String>();    
	ArrayList<AbstractObservationModel> BooleanObservationModel = new ArrayList<AbstractObservationModel>();
	ArrayList<AbstractObservationModel> NumericalObservationModel = new ArrayList<AbstractObservationModel>();    

	/* Constructor gets a JSON Node from the Controller class
	 * next we transform JsonNode separated into different ArrayLists
	 * then adding the different elements to their respective ArrayLists
	 * afterwards we convert the String into an understandable boolean format
	 */


	public RedCapModelConverter(JsonNode rohdaten) {

		for (int i = 0; i < rohdaten.getArray().length(); i++) {
			JSONObject jobj = rohdaten.getArray().getJSONObject(i);         	
			recordId.add(i, jobj.getString("record_id"));
			gender.add(i, jobj.getString("gender"));

			if(jobj.getString("hepatitis_b").contentEquals("1")) {
				hepatitis_B.add(i, true);
			} else if(jobj.getString("hepatitis_b").contentEquals("0")) {
				hepatitis_B.add(i, false);
			}else {        		
				System.err.println("False input");
			}

			billirubin.add(i, jobj.getDouble("bilirubin_concentration"));
			form_complete.add(i, jobj.getString("form_1_complete"));       	
		}
	}
	/*
	 * fill object of BooleanObservationModel with the data & return an ArrayList of AbstractObservationModel
	 */ 
	public ArrayList<AbstractObservationModel> getBooleanObs () {

		for (int i = 0; i < hepatitis_B.size(); i++) {
			BooleanObservationModel boolobs = new BooleanObservationModel(Enumeration.HEPATITIS_B_CODE.getValue(), Enumeration.HEPATITIS_B_CODE.getValue(), hepatitis_B.get(i));
			BooleanObservationModel.add(i, boolobs);  
		}
		return BooleanObservationModel;
	}
	/*
	 * fill object of NumericalObservationModel with the data & return an ArrayList of AbstractObservationModel
	 */ 
	public ArrayList<AbstractObservationModel> getNumericalObs() {

		for (int i = 0; i < billirubin.size(); i++) {
			NumericalObservationModel numobs = new NumericalObservationModel(Enumeration.SYSTEM_BILLIRUBIN.getValue(), Enumeration.BILLIRUBIN_CODE.getValue(), billirubin.get(i).doubleValue(), Enumeration.UNIT.getValue());
			NumericalObservationModel.add(i, numobs);
		}
		return NumericalObservationModel;
	}
	/*
	 * Getter method for the gender Array
	 */ 
	public ArrayList<String> getGender() {
		return gender;
	}
}
