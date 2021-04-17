package upload;

/************************************************************
 * @Author: Jonas Schick und Julia Kurashvili
 * 
 * This class prints all patients and observations, that get uploaded onto the Server.
 * **********************************************************/

import java.util.ArrayList;
import model.ObservationModel;

public class Printer {
	public void printPatients(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	
	}
	
	public void printAllObs(ArrayList<ObservationModel> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getObservationSystem());
		}
	}
	
}
