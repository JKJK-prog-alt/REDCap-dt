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
    	ClassLoader classLoader = getClass().getClassLoader();
    	InputStream in = classLoader.getResourceAsStream("getData.json");
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
    /*
    Sends a request to REDCap_API to get the patient metadata
    Returns the MetaData as JsonNode
    */
    public JsonNode getMetaData(){
    	ClassLoader classLoader = getClass().getClassLoader();
    	InputStream in = classLoader.getResourceAsStream("getMetadata.json");
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
