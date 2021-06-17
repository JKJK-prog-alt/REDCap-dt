package upload;

/************************************************************
 * @Author: Jonas Schick, Julia Kurashvili
 * 
 * This class uploads anonymous observations into the local
 * server 
 * 
 * 	Methods:
 * 		+ transferData(Server)
 * 		+ upload(Server, ArrayList<AbstractObservationModel>, String)
 * 		+ upload(Server, ArrayList<ArrayList<AbstractObservationModel>>, ArrayList<String>)
 * **********************************************************/

import java.util.ArrayList;
import org.hl7.fhir.r4.model.Enumerations;
import model.*;
import parser.JsontoJava;
import server.Server;

public class UploadData {
	private String family = "Bobbington";
	private String name = "Bob";
	
	/* gets data from the RedCAP server and uploads them into the local server
	 * 
	 * */
	public String transferData(Server server) {
		JsontoJava conv = new JsontoJava();
        conv.converter();
        ArrayList<AbstractObservationModel> num = conv.numericalObs();
        ArrayList<AbstractObservationModel> bool = conv.booleanObs();
        ArrayList<String> gen = conv.getGen();
        ArrayList<ArrayList<AbstractObservationModel>> patientList = new ArrayList<ArrayList<AbstractObservationModel>>();
        int length = gen.size();
        
        for(int i = 0; i < length; i++) {
        	ArrayList<AbstractObservationModel> data = new ArrayList<AbstractObservationModel>();
        	data.add(num.get(i));
        	data.add(bool.get(i));
        	patientList.add(data);
        }
        
		return upload(server, patientList, gen);
	}
	
	/* uploads observations for one patient
	 * 
	 * */
	public String upload(Server server, ArrayList<AbstractObservationModel> data, String gender) {
		int length = data.size();
		Enumerations.AdministrativeGender enumGender;
		
		try {
			enumGender = Enumerations.AdministrativeGender.fromCode(gender);
			
		}catch(Exception e) {
			System.out.println("Unkown gender, changed to other");
			enumGender = Enumerations.AdministrativeGender.OTHER;
		}
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
