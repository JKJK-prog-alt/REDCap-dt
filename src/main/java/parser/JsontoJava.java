package parser;

import java.util.*;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import kong.unirest.json.JSONString;
import model.Datamodel;
import util.Config;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;


public class JsontoJava {	
		
    public void converter() throws IOException  {
   
    	redcapx.RedCapServerUnirest testen = new redcapx.RedCapServerUnirest();	
    	JsonNode rohdaten = testen.getData();
		
//    	List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\lisam\\Documents\\Studium\\Projektarbeit\\Gruppe1\\Daten\\test.json"));
//		String rohdaten= lines.get(0);
    	
//        System.out.println(rohdaten);
        ObjectMapper objectMapper = new ObjectMapper();
        
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> gen = new ArrayList<String>();
        ArrayList<String> hep = new ArrayList<String>();
        ArrayList<Double> bili = new ArrayList<Double>();
        ArrayList<String> form_com = new ArrayList<String>();
        
      
        for (int i = 0; i < rohdaten.getArray().length(); i++) {
        	JSONObject jobj = rohdaten.getArray().getJSONObject(i);
        	System.out.println(jobj);
        	
        	id.add(i, jobj.getString("record_id"));
        	gen.add(i, jobj.getString("gender"));
        	hep.add(i, jobj.getString("hepatitis_b"));       	
        	bili.add(i, jobj.getDouble("bilirubin_concentration"));
        	form_com.add(i, jobj.getString("form_1_complete"));       	
        }
        
//      System.out.println(id);
//    	System.out.println(gen);
//    	System.out.println(hep);
//    	System.out.println(bili);
//    	System.out.println(form_com);
    	
    	for (int i = 0; i < id.size(); i++) {
    		Datamodel data = new Datamodel(id.get(i).toString(),gen.get(i).toString(),hep.get(i).toString(),bili.get(i).doubleValue(),form_com.get(i).toString());
			System.out.println(data);
			
			
		}
    	
         

        
//       try {
//            Datamodel test = objectMapper.readValue(teste, Datamodel.class);
//            
//            System.out.println("ID = "+ test.getRecord_id()+ " and gender = "+ test.getGender() );  
//                 
//        } 
//        catch(JsonProcessingException  e   ) {
//            e.printStackTrace();
//        }
        

// https://www.youtube.com/watch?v=ynO4_XtUdOg

    }
    public static void main(String[] args) throws IOException {

            JsontoJava conv = new JsontoJava();
            conv.converter();
//          try {
//        	  conv.converter();
//		} catch (UnrecognizedPropertyException e) {
//			 
//		}
           
    }
}
