package upload;

/************************************************************
 * @Author: Jonas Schick, Julia Kurashvili
 * 
 * Test Class for UploadData
 * **********************************************************/

import model.*;
import server.Server;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUploadData {
	private static Server server;
	private static UploadData upload;
	private static Printer printer;
	
	@BeforeClass
    public static void setUp() {
		server = new Server();
		server.testConnection();
        printer = new Printer();
        upload = new UploadData();
    }
	
	@Test
	public void testUpload() {
		ArrayList<String> allPatients = server.getPatients();
		
		ArrayList<AbstractObservationModel> data = new ArrayList<AbstractObservationModel>();
		data.add(new BooleanObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/","chronic_hepatitis_b_observation", true));
		data.add(new NumericalObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "bilirubin_concentration", 22, "mg/dl"));

		String success = upload.upload(server, data, "male");
		
		int numberAllPatients = allPatients.size();
		 ArrayList<String> allPatientsAdded = server.getPatients();
		 printer.printPatients(allPatientsAdded);
		assertEquals("Anzahl an Patienten nicht gleich Arraygroesse",numberAllPatients + 1, allPatientsAdded.size());
		assertEquals(36, success.length());
		ArrayList<AbstractObservationModel> observations = server.getObservationsOfPatient(success);
        assertEquals(3, observations.size());
		
		
	}
	
	@Test
	public void testUploadTwo() {
		ArrayList<String> allPatientsTwo = server.getPatients();
		
		ArrayList<AbstractObservationModel> data = new ArrayList<AbstractObservationModel>();
		data.add(new BooleanObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/","chronic_hepatitis_b_observation", true));
		data.add(new NumericalObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "bilirubin_concentration", 22, "mg/dl"));

		ArrayList<AbstractObservationModel> data2 = new ArrayList<AbstractObservationModel>();
		data2.add(new BooleanObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/","chronic_hepatitis_b_observation", false));
		data2.add(new NumericalObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "bilirubin_concentration", 45, "mg/dl"));
		
		ArrayList<AbstractObservationModel> data3 = new ArrayList<AbstractObservationModel>();
		data3.add(new BooleanObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/","chronic_hepatitis_b_observation", true));
		data3.add(new NumericalObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "bilirubin_concentration", 21, "mg/dl"));
		
		ArrayList<ArrayList<AbstractObservationModel>> all = new ArrayList<ArrayList<AbstractObservationModel>>();
		all.add(data);
		all.add(data2);
		all.add(data3);
		
		ArrayList<String> gender = new ArrayList<String>();
		gender.add("female");
		gender.add("male");
		gender.add("female");
		
		assertEquals(3, all.size());
		assertEquals(3, gender.size());
		
		String successTwo = upload.upload(server, all, gender);
		assertEquals(36, successTwo.length());
	}
	
	@Test
	public void testTransfer() {
		ArrayList<String> patients = server.getPatients();
		String lastPatientID = upload.transferData(server);
		ArrayList<String> updatedPatients = server.getPatients();
		ArrayList<AbstractObservationModel> observations = server.getObservationsOfPatient(lastPatientID);
		assertEquals(36, lastPatientID.length());
		assertEquals(patients.size() + 3, updatedPatients.size());
		assertEquals(3, observations.size());
		
	}
	
	

}