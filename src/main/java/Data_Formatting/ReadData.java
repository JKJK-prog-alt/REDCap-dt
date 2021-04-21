package Data_Formatting;
import java.util.*;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class ReadData {
	
	public void split() throws IOException {
		
		
		List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\lisam\\Documents\\Studium\\Projektarbeit\\Gruppe1\\Daten\\DATA_2021-03-31_2145.csv"));
		
		String liste="";
		StringBuffer sb = new StringBuffer(liste);
	//	String[] result = new String[100];
		
		
		System.out.println(lines);
		for(int i = 0; i < lines.size(); i++) {
			
			sb.append(lines.get(i)+ ",");
		}
		liste = sb.toString();	
		
		System.out.println(liste);
		//result = liste.split(",");
		List<String> data = new ArrayList(Arrays.asList(liste.split(",")));
		for (int x=0; x<=19; x++) {
			System.out.println(data.get(x));
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		ReadData test = new ReadData();
		test.split();
		
	}
}
