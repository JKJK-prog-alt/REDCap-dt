package upload;

/************************************************************
 * @Author: Jonas Schick, Julia Kurashvili
 * 
 * This class uploads anonymous observations from the RedCap
 * server into the local server 
 * 
 * 	Methods:
 * 		+ transferData(Server)
 * 		+ upload(Server, ArrayList<ArrayList<AbstractObservationModel>>, ArrayList<String>)
 * 		+ upload(Server, ArrayList<AbstractObservationModel>, String)
 * **********************************************************/

import java.util.ArrayList;
import org.hl7.fhir.r4.model.Enumerations;
import kong.unirest.JsonNode;
import model.*;
import parser.RedCapModelConverter;
import redcapx.*;
import server.Server;

public class Controller {

	/* gets data from the RedCap server and uploads them into the local server
	 * 
	 * invokes classes in that order: RedCapServerUnirest -> RedCapModelConverter -> Server
	 * 
	 * */
	public static String transferData(Server server) {

		JsonNode rohdaten;
		
			/* If the RedCap server can be reached, use RedCapserverUnirest instead
			 * of RedCapMockServer 
			 * */
//			RedCapServerUnirest redcapServer = new RedCapServerUnirest();
//			rohdaten = redcapServer.getData();

		RedCapMockServer redcapMockServer = new RedCapMockServer();
		rohdaten = redcapMockServer.getData();
		
		RedCapModelConverter converter = new RedCapModelConverter(rohdaten);

		ArrayList<ArrayList<AbstractObservationModel>> patientList = new ArrayList<ArrayList<AbstractObservationModel>>();
		ArrayList<AbstractObservationModel> numericalObs = converter.getNumericalObs();
		ArrayList<AbstractObservationModel> booleanObs = converter.getBooleanObs();
		ArrayList<String> gender = converter.getGender();
		int genderLength = gender.size();

		for(int i = 0; i < genderLength; i++) {
			ArrayList<AbstractObservationModel> data = new ArrayList<AbstractObservationModel>();
			data.add(numericalObs.get(i));
			data.add(booleanObs.get(i));
			patientList.add(data);
		}

		return upload(server, patientList, gender);
	}

	/* uploads observations for one patient
	 * 
	 * */
	public static String upload(Server server, ArrayList<AbstractObservationModel> data, String gender) {
		final String family = "Bobbington"; // Placeholder
		final String name = "Bob";			// Placeholder
		int length = data.size();
		Enumerations.AdministrativeGender enumGender;

		try {
			enumGender = Enumerations.AdministrativeGender.fromCode(gender);

		}catch(Exception e) {
			System.out.println("Unkown gender, changed to other");
			enumGender = Enumerations.AdministrativeGender.OTHER;
		}
		String patientID = server.createPatient(family, name, enumGender);

		//for(int i = 0; i < length; i++) {
		for(AbstractObservationModel model : data) {

			server.createObservation(model, patientID);
		}
		return patientID;
	}

	/* uploads observations for a list of patients
	 * 
	 * */
	public static String upload(Server server, ArrayList<ArrayList<AbstractObservationModel>> datalist, ArrayList<String> genderlist) {
		int length = datalist.size();
		String lastPatientID = null;
		for(int i = 0; i < length; i++) {
			lastPatientID = upload(server, datalist.get(i), genderlist.get(i));
		}
		return lastPatientID;
	}


}
