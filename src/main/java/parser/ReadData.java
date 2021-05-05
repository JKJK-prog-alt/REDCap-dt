package parser;

//import redcapx.RecievingData;
import java.util.*;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class ReadData {
	
	public void split() throws IOException{
		
		// getting data from the RecievingData class;
		redcapx.RecievingData testen = new redcapx.RecievingData();	
		String rohdaten = testen.getData();
		
		//List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\lisam\\Documents\\Studium\\Projektarbeit\\Gruppe1\\Daten\\DATA_2021-03-31_2145.csv"));
		//	String liste="";
		//	StringBuffer sb = new StringBuffer(liste);	
		
		//for(int i = 0; i < lines.size(); i++) {
		//	sb.append(lines.get(i)+ ",");
		//}
		//liste = sb.toString();
		
		List<String> data = new ArrayList(Arrays.asList(rohdaten.split(",")));
		
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).isEmpty()) {
				data.set(i, "null");
			}
		}
		System.out.println(data);	
	}
	
	public static void main(String[] args) throws IOException {
		
		ReadData test = new ReadData();
		test.split();
	}
}
