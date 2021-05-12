package upload;

/************************************************************
 * @Author: Keno Maerz, Jonas Schick, Julia Kurashvili
 * 
 * Test Class for Printer
 * **********************************************************/

import model.AbstractObservationModel;
import model.BooleanObservationModel;
import model.NumericalObservationModel;
import model.CategorialObservationModel;
import server.Server;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.hl7.fhir.r4.model.Enumerations;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPrint {
	private static Server server;
	private static Printer printer;
	
	@BeforeClass
    public static void setUp() {
        server = new Server();
        server.testConnection();
        printer = new Printer();
    }
	
	@Test
    public void testPrintPatients() {
        ArrayList<String> allPatients = server.getPatients();
        int numberAllPatients = allPatients.size();
        server.createPatient("Doe", "John", Enumerations.AdministrativeGender.MALE);
        ArrayList<String> allPatientsAdded = server.getPatients();
        printer.printPatients(allPatientsAdded);
        assertEquals("Anzahl an Patienten nicht gleich Arraygroesse",numberAllPatients + 1, allPatientsAdded.size());
    }
	
	@Test
    public void testPrintAllObs() {
        String patientID = server.createPatient("Doe", "Jane", Enumerations.AdministrativeGender.FEMALE);
        ArrayList<AbstractObservationModel> observations1 = server.getObservationsOfPatient(patientID);
        observations1.size();
        printer.printAllObs(observations1);
        assertEquals("Es ist nicht genau eine Observation vorhanden",1, observations1.size());
        NumericalObservationModel observationNumerical = new NumericalObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "bilirubin_concentration", 1, "mg/dl");
        server.createObservation(observationNumerical, patientID);
        ArrayList<AbstractObservationModel> observations2 = server.getObservationsOfPatient(patientID);
        printer.printAllObs(observations2);
        assertEquals("Es sind nicht genau 2 Observations vorhanden",2, observations2.size());
        CategorialObservationModel observationCategorical = new CategorialObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "lymph_node_staging", "http://sfb125.de/ontology/ihCCApplicationOntology/", "cN1");
        server.createObservation(observationCategorical, patientID);
        ArrayList<AbstractObservationModel> observations3 = server.getObservationsOfPatient(patientID);
        printer.printAllObs(observations3);
        assertEquals("Es sind nicht genau 3 Observations vorhanden",3, observations3.size());
        BooleanObservationModel observationCategorialBoolean = new BooleanObservationModel("http://sfb125.de/ontology/ihCCApplicationOntology/", "chronic_hepatitis_b_observation", true);
        server.createObservation(observationCategorialBoolean, patientID);
        ArrayList<AbstractObservationModel> observations4 = server.getObservationsOfPatient(patientID);
        printer.printAllObs(observations4);
        assertEquals("Es sind nicht genau 4 Observations vorhanden",4, observations4.size());
        
    }

}
