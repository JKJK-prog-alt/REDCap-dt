package Data_Formatting;
import java.util.*;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class ReadData {
	
	
	
	public void split() throws IOException {
		
		
		List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\lisam\\Documents\\Studium\\Projektarbeit\\Gruppe1\\Daten\\DATA_2021-03-31_2145.csv"));
		String[] result = new String[100];
		String liste="";
		
		for(int i=0; i<=3; i++) {
			StringBuffer sb = new StringBuffer(liste);
			sb.append(lines.get(i));
			
			liste = sb.toString();		
		}
		result = liste.split(",");	
		for (int x=0; x<=19; x++) {
			System.out.println(result[x]);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		ReadData test = new ReadData();
		test.split();
		
	}
}
