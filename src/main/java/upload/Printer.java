package upload;

/************************************************************
 * @Author: Jonas Schick und Julia Kurashvili
 * 
 * This class prints all patients and observations, that get uploaded onto the Server.
 * **********************************************************/

import java.util.ArrayList;
import model.*;
import util.Config;
import server.Server;

public class Printer {
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
	             System.out.println("*****NUMERICAL*****");
	             System.out.println(ausgabe.split("/")[0]);
	             System.out.println(value);
	        } else if (list.get(i) instanceof CategorialObservationModel) {
	            CategorialObservationModel categorial = (CategorialObservationModel) list.get(i);
	             ausgabe = categorial.getObservationSystem().split(Config.OBSERVATION_BASE_URL)[1];
	             valUnitCode = categorial.getValueCode();
	             system = categorial.getValueSystem();
	             System.out.println("*****CATEGORIAL*****");
	             System.out.println(ausgabe.split("/")[0]);	
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
	
	public void printAllObs(Server server, String patientID) {
		ArrayList<AbstractObservationModel> observations = server.getObservationsOfPatient(patientID);
		printAllObs(observations);
	}
	
}
