package upload;


import java.util.ArrayList;

import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import model.ObservationModel;
import server.Server;
import upload.ObsCreator;

/************************************************************
 * @Author: Keno Maerz, Jonas Schick, Julia Kurashvili
 * 
 * UploadData-Class (to be cleaned)
 * **********************************************************/

public class UploadData {

	public String filter(Server server, ArrayList<String> list) {
		int length = list.size();
		AdministrativeGender gender = null;
		String nachname = null;
		String vorname = null;
		String catwert = null;
		double numwert = 0;

		ObservationModel observation;
		for(int i = 0; i < length; i+=2) {
			String varname = list.get(i);
			String varwert = list.get(i+1);
			if(varname.contentEquals("name")) {
				vorname = varwert;
			} else if(varname.contentEquals("familyname")) {
				nachname = varwert;
			} else if(varname.contentEquals("gender")) {
				gender = Enumerations.AdministrativeGender.fromCode(varwert);
			}
		}
		if((gender == null) | (nachname == null) | (vorname == null)) {
			return null;
		} 
		String patientID = server.createPatient(nachname, vorname, gender);

		for(int i = 0; i < length; i+=2) {
			String varname = list.get(i);
			String varwert = list.get(i+1);

			observation = ObsCreator.createObservationModel(varname,varwert);

			if(observation != null)
				server.createNumericalObservation(observation, patientID);	
		}
		return patientID;
	}
}
