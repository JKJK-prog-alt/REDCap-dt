package upload;

/************************************************************
 * @Author: Jonas Schick, Julia Kurashvili
 * 
 * Test Class for UploadData
 * **********************************************************/

import model.ObservationModel;
import server.Server;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.hl7.fhir.r4.model.Enumerations;
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
	public void testFilter() {
		ArrayList<String> allPatients = server.getPatients();
		
		ArrayList<String> daten = new ArrayList<String>();
		daten.add("bilirubin_concentration");
		daten.add("33");
		daten.add("form_1_complete");
		daten.add("2");
		daten.add("gender");
		daten.add("male");
		daten.add("hepatitis_b");
		daten.add("1");
		daten.add("record_id");
		daten.add("1");
		daten.add("familyname");
		daten.add("Dolittle");
		daten.add("name");
		daten.add("Jonas");
		
		
		Boolean succsses = upload.filter(server, daten);
		
		int numberAllPatients = allPatients.size();
		 ArrayList<String> allPatientsAdded = server.getPatients();
		 printer.printPatients(allPatientsAdded);
		 //ArrayList<ObservationModel> observations = server.getObservationsOfPatient(patientID);
		assertEquals("Anzahl an Patienten nicht gleich Arraygroesse",numberAllPatients + 1, allPatientsAdded.size());
		assertEquals(true, succsses);
		
		
		
	}
	
	

}
