package upload;

/************************************************************
 * @Author: Jonas Schick, Julia Kurashvili
 * 
 * Test Class for UploadData.
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
		data.add(new NumericalObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "bilirubin_concentration", 33, "mg/dl"));

		String success = upload.upload(server, data, "male");
		
		int numberAllPatients = allPatients.size();
		 ArrayList<String> allPatientsAdded = server.getPatients();
		 printer.printPatients(allPatientsAdded);
		assertEquals("Anzahl an Patienten nicht gleich Arraygroesse",numberAllPatients + 1, allPatientsAdded.size());
		assertEquals(36, success.length());
		ArrayList<AbstractObservationModel> observations = server.getObservationsOfPatient(success);
        assertEquals(3, observations.size());
		
		
	}
	
	

}
