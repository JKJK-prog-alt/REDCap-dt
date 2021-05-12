package upload;


import java.util.ArrayList;
import org.hl7.fhir.r4.model.Enumerations;
import model.*;
import server.Server;

/************************************************************
 * @Author: Jonas Schick, Julia Kurashvili
 * 
 * UploadData-Class uploads anonymous observations into the
 * local server 
 * **********************************************************/

public class UploadData {
	private String family = "Bobbington";
	private String name = "Bob";
	
	/* uploads observations for one patient
	 * 
	 * */
	public String upload(Server server, ArrayList<AbstractObservationModel> data, String gender) {
		int length = data.size();
		
		Enumerations.AdministrativeGender enumGender = Enumerations.AdministrativeGender.fromCode(gender);
		String patientID = server.createPatient(family, name, enumGender);
		
		for(int i = 0; i < length; i++) {
			
			server.createObservation(data.get(i), patientID);
		}
		return patientID;
	}
	
	/* uploads observations for a list of patients
	 * 
	 * */
	public String upload(Server server, ArrayList<ArrayList<AbstractObservationModel>> datalist, ArrayList<String> genderlist) {
		int length = datalist.size();
		String lastPatientID = null;
		for(int i = 0; i < length; i++) {
			lastPatientID = upload(server, datalist.get(i), genderlist.get(i));
		}
		return lastPatientID;
	}
	
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
