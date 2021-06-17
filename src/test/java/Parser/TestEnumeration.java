
package Parser;
import parser.Enumeration;
import parser.RedCapModelConverter;
import redcapx.RedCapMockServer;
import upload.Printer;

/************************************************************
 * @Author: Lisamaria Eble, Niklaas-Benedikt Oehme and Julia Kurashvili * 
 * Test class to check if the observationmodels are correctly filled with the Enumerations * 
 * **********************************************************/

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import kong.unirest.JsonNode;
import model.AbstractObservationModel;

public class TestEnumeration {


	private Enumeration enumerate;


	@Test
	public void isfilled() {


		JsonNode rohdaten;
		RedCapMockServer redcapMockServer = new RedCapMockServer();
		rohdaten = redcapMockServer.getData();
		RedCapModelConverter converter = new RedCapModelConverter(rohdaten);
		ArrayList<AbstractObservationModel> NumericalObservationModel = converter.getNumericalObs();  		
		ArrayList<AbstractObservationModel> BooleanObservationModel = converter.getBooleanObs();  	

		assertEquals("Anzahl der Observationen != 3",3, NumericalObservationModel.size());
		assertEquals("Anzahl der Observationen != 3",3, BooleanObservationModel.size());


	}
}
