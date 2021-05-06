package parser;

//import redcapx.RecievingData;
import java.util.*;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class CSVtoJava {
	
	public void split() throws IOException{
		
		redcapx.RedCapServer.Format format = redcapx.RedCapServer.Format.CSV; 
    	redcapx.RedCapServer testen = new redcapx.RedCapServer();	
		
		// getting data from the RecievingData class;		
		String rohdaten = testen.getData(format);
		List<String> data = new ArrayList(Arrays.asList(rohdaten.split(",")));
		
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).isEmpty()) {
				data.set(i, "null");
			}
		}
		System.out.println(data);	
	}
	
	public static void main(String[] args) throws IOException {
		
		CSVtoJava test = new CSVtoJava();
		test.split();
	}
}
