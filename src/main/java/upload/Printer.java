package upload;

/************************************************************
 * @Author: Jonas Schick und Julia Kurashvili
 * 
 * This class prints all patients and observations, that get uploaded onto the Server.
 * **********************************************************/

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import model.AbstractObservationModel;
import model.BooleanObservationModel;
import model.CategorialObservationModel;
import model.NumericalObservationModel;
import util.Config;

public class Printer {
	FhirContext ctx = FhirContext.forR4();
	IGenericClient client = ctx.newRestfulGenericClient(Config.SERVER_BASE_URL);
	public void printPatients(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	
	}
	
	public void printAllObs(ArrayList<AbstractObservationModel> list) {
		String ausgabe = null;
		String valUnitCode = null;
		String system = null;
		double value = 0;
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof NumericalObservationModel) {
	            NumericalObservationModel numerical = (NumericalObservationModel) list.get(i);
	             ausgabe = numerical.getObservationSystem().split(Config.OBSERVATION_BASE_URL)[1];
	             valUnitCode = numerical.getUnit();
	             value = numerical.getValue();
	             System.out.println(ausgabe.split("/")[0]);
	             System.out.println("*****NUMERICAL*****");
	             System.out.println(value);
	        } else if (list.get(i) instanceof CategorialObservationModel) {
	            CategorialObservationModel categorial = (CategorialObservationModel) list.get(i);
	             ausgabe = categorial.getObservationSystem().split(Config.OBSERVATION_BASE_URL)[1];
	             valUnitCode = categorial.getValueCode();
	             system = categorial.getValueSystem();
	             System.out.println(ausgabe.split("/")[0]);	
	             System.out.println("*****CATEGORIAL*****");
	             System.out.println(system);
	             System.out.println(valUnitCode);
//	        } else {
//	            BooleanObservationModel bool = (BooleanObservationModel) list.get(i);
//	            System.out.println("Val sys" + bool.getValueSystem());
//	            System.out.println("val vode" + bool.getValueCode());
//	             ausgabe = bool.getObservationSystem().split(Config.OBSERVATION_BASE_URL)[1];
//	             System.out.println(ausgabe.split("/")[0]);
//	             System.out.println("*****BOOLEAN*****");
	        }
	        
	        
			//String ausgabe = list.get(i).getObservationSystem().split(Config.OBSERVATION_BASE_URL)[1];
			System.out.println("------------");
		}
	}
	
}
