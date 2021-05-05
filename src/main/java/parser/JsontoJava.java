package parser;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Datamodel;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class JsontoJava {

    public void converter() throws IOException  {
      

        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Kalli\\Desktop\\new.json"));
        String data= lines.get(0);
        System.out.println(data);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Datamodel test = objectMapper.readValue(data, Datamodel.class);
            System.out.println("ID = "+ test.getRecord_id()+ " and gender = "+ test.getGender() );
        } 
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }

// https://www.youtube.com/watch?v=ynO4_XtUdOg

    }
    public static void main(String[] args) throws IOException {

            JsontoJava conv = new JsontoJava();
            conv.converter();
    }
}
