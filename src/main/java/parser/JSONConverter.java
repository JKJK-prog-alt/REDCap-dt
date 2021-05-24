package parser;

import java.util.*;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

/************************************************************
 * @Author: Lisamaria Eble and Niklaas-Benedikt Oehme * 
 * Obsolete class
 * **********************************************************/


public class JSONConverter {
	
	public void convert() throws IOException {
		
		
		
		List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Kalli\\Desktop\\new.JSON"));
        String response= lines.get(0);
      
		System.out.println(response);
	    JSONObject res=new JSONObject(response);
	    ArrayList<JSONArray> profitList = new ArrayList<>();
	    JSONArray array = res.getJSONArray("record_id");
	    ArrayList<ArrayList<Integer>> finalList = new ArrayList<>();
	    ArrayList<Integer> list1 = new ArrayList<>();
	    
	    for (int i = 0; i < array.length(); i++){
	        profitList.add(array.getJSONArray(i));
	    }
	    
	    for (int i = 0; i < profitList.size(); i++){
	    	
	        JSONArray array1 = profitList.get(i);
	        list1 = new ArrayList<>();
	        
	        for (int j = 0; j < array1.length(); j++){
	            list1.add(array1.getInt(j));
	        }	       
	        finalList.add(list1);
	        System.out.println(finalList);
	    }
	}	
	public static void main(String[] args) throws IOException {
		
	
			JSONConverter conv = new JSONConverter();
			conv.convert();
	}  
}

	

