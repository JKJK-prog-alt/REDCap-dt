package upload;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import model.ObservationModel;
import server.Server;

/************************************************************
 * @Author: Jonas Schick, Julia Kurashvili
 * 
 * Observation Creation Class for uploading to server
 * **********************************************************/


public class ObsCreator {

	String patientID;
	String Observationsystem;
	String Observationcode;
	String NumCode;
	String unit;
	String valueSystem;
	String valueCode;
	
	
	public static ObservationModel createObservationModel(String varname, String varwert) {
		switch(varname) {
		case "bilirubin_concentration": {
			Double numwert = Double.parseDouble(varwert);
			return new ObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "bilirubin_concentration", numwert, "mg/dl");
		}
//		case "hepatitis_b": {
//			//wir wissen nicht wie boolean funktioniert also machen wir categorical
//			return new ObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "chronic_hepatitis_b_observation", varwert, "mg/dl");
//		}
		}
		return null;
	}
}
