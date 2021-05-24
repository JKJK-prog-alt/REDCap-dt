package upload;

/************************************************************
 * @Author: Jonas Schick und Julia Kurashvili
 * 
 * This class shows all patients and observations, that get
 * uploaded onto the Server.
 * 		
 * 	Methods:
 * 		+ printPatients(ArrayList<String>)
 * 		+ printAllObs(ArrayList<AbstractObservationModel>)
 * 		+ printAllObs(Server, String)
 * 
 * **********************************************************/

import java.util.ArrayList;
import model.*;
import util.configuration;
import server.Server;

public class Printer {
	
	/* shows patient IDs
	 * 
	 * */
	public void printPatients(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	
	}
	
	/* shows the observations of a patient
	 * 
	 * if numerical -> observationCode, numericalValue and unit are shown
	 * if categorical -> observationCode, valueSystem and valueCode are shown
	 * */
	public void printAllObs(ArrayList<AbstractObservationModel> list) {
		String ausgabe = null;
		String valUnitCode = null;
		String system = null;
		double value = 0;
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof NumericalObservationModel) {
	            NumericalObservationModel numerical = (NumericalObservationModel) list.get(i);
	             ausgabe = numerical.getObservationSystem().split(configuration.OBSERVATION_BASE_URL)[1];
	             valUnitCode = numerical.getUnit();
	             value = numerical.getValue();
	             System.out.println("*****NUMERICAL*****");
	             System.out.println("Observation System Code: " + ausgabe.split("/")[0]);
	             System.out.println("Value: " + value);
	             System.out.println("Unit: " + valUnitCode);
	        } else if (list.get(i) instanceof CategorialObservationModel) {
	            CategorialObservationModel categorial = (CategorialObservationModel) list.get(i);
	             ausgabe = categorial.getObservationSystem().split(configuration.OBSERVATION_BASE_URL)[1];
	             valUnitCode = categorial.getValueCode();
	             system = categorial.getValueSystem();
	             System.out.println("*****CATEGORIAL*****");
	             System.out.println("Observation System Code: " + ausgabe.split("/")[0]);	
	             System.out.println("Value System: " + system);
	             System.out.println("Value: " + valUnitCode);
	        }
	 
			System.out.println("------------");
		}
	}
	
	/* gets the observations of a patient from server and shows them
	 * 
	 * */
	public void printAllObs(Server server, String patientID) {
		ArrayList<AbstractObservationModel> observations = server.getObservationsOfPatient(patientID);
		printAllObs(observations);
	}
	
}
