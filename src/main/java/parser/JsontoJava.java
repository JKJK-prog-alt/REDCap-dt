package parser;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.Datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class JsontoJava {	
		
    public void converter() throws IOException  {
    	
    	
    	redcapx.RedCapServer.Format format = redcapx.RedCapServer.Format.JSON; 
    	redcapx.RedCapServer testen = new redcapx.RedCapServer();	
		String rohdaten = testen.getData(format);
		
//      List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Kalli\\Desktop\\new.json"));
//		String rohdaten= lines.get(0);
        System.out.println(rohdaten);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Datamodel test = objectMapper.readValue(rohdaten, Datamodel.class);
            System.out.println("ID = "+ test.getRecord_id()+ " and gender = "+ test.getGender() );  
                 
        } 
        catch(JsonProcessingException  e   ) {
            e.printStackTrace();
        }
        

// https://www.youtube.com/watch?v=ynO4_XtUdOg

    }
    public static void main(String[] args) throws IOException {

            JsontoJava conv = new JsontoJava();
          try {
        	  conv.converter();
		} catch (UnrecognizedPropertyException e) {
			 
		}
           
    }
}
