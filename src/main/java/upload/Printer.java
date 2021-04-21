package upload;

/************************************************************
 * @Author: Jonas Schick und Julia Kurashvili
 * 
 * This class prints all patients and observations, that get uploaded onto the Server.
 * **********************************************************/

import java.util.ArrayList;
import model.ObservationModel;
import util.Config;

public class Printer {
	public void printPatients(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	
	}
	
	public void printAllObs(ArrayList<ObservationModel> list) {
		for(int i = 0; i < list.size(); i++) {
			//http://sfb125.de/ontology/ ihCCApplicationOntology/
			String ausgabe = list.get(i).getObservationSystem().split(Config.OBSERVATION_BASE_URL)[1];
			System.out.println(ausgabe.split("/")[0]);
		}
	}
	
}
