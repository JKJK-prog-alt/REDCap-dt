
package parser;
import org.hl7.fhir.exceptions.FHIRException;

/************************************************************
 * @Author: Lisamaria Eble, Niklaas-Benedikt Oehme  
 * Enumerations to fill the Observationmodels
 * **********************************************************/

public enum Enumeration {

	SYSTEM_HEPATITIS_B ("http://sfb125.de/ontology/ihCCApplicationOntology/"),
	SYSTEM_BILLIRUBIN ("http://sfb125.de/ontology/ihCCApplicationOntology/"),
	HEPATITIS_B_CODE ("chronic_hepatitis_b_observation"),
	BILLIRUBIN_CODE ("bilirubin_concentration"),
	OBSERVATION_BASE_URL ("http://sfb125.de/ontology/"),
	UNIT ("mg/dl");

	private final String value;

	private Enumeration(final String value) {
		this.value = value;
	}

	public String getValue() { return value; }
}




