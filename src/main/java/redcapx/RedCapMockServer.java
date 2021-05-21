package redcapx;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import util.Config;

public class RedCapMockServer extends RedCapServerUnirest {
	
	public boolean tryConnection(){
        return true;
    }

    public JsonNode getData(){
		return loadJsonFile("getData.json");
    }
    /*
    Sends a request to REDCap_API to get the patient metadata
    Returns the MetaData as JsonNode
    */
    public JsonNode getMetaData(){
    	return loadJsonFile("getMetadata.json");
    }
    
    private JsonNode loadJsonFile(String filename) {
    	ClassLoader classLoader = getClass().getClassLoader();
    	InputStream in = classLoader.getResourceAsStream(filename);
		String newLine = System.getProperty("line.separator");
		BufferedReader reader = new BufferedReader(
		        new InputStreamReader(in));
		StringBuilder result = new StringBuilder();
		try {
			for (String line; (line = reader.readLine()) != null; ) {
			    if (result.length() > 0) {
			        result.append(newLine);
			    }
			    result.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new JsonNode(result.toString());
    }
}
