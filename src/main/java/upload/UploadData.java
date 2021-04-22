package upload;

import java.io.BufferedReader;
import java.util.ArrayList;

import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import server.Server;

/************************************************************
 * @Author: Keno Maerz, Jonas Schick, Julia Kurashvili
 * 
 * UploadData-Class
 * **********************************************************/

public class UploadData {

	public boolean filter(Server server, ArrayList<String> list) {
		int length = list.size();
		AdministrativeGender gender = null;
		String nachname = null;
		String vorname = null;
		for(int i = 10; i < length; i+=2) {
			String varname = list.get(i);
			String varwert = list.get(i+1);
			if(varname.contentEquals("name")) {
				vorname = varwert;
			} else if(varname.contentEquals("nachname")) {
				nachname = varname;
			} else if(varname.contentEquals("gender")) {
				gender = Enumerations.AdministrativeGender.fromCode(varwert);
			}
			
			/*if((var.contentEquals("bilirubin_concentration")) | (var.contentEquals("hepatitis_b"))) {
				String observationCode = var;
				
				
			}*/
		}
		if((gender == null) | (nachname == null) | (vorname == null)) {
			return false;
		} 
		String patientID = server.createPatient(vorname, nachname, gender);
		return true;
	}
}
