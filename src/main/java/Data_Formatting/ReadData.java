package Data_Formatting;
import java.util.*;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;

public class ReadData {
	
	
	
	public void split() throws IOException {
		
		
		List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Kalli\\Desktop\\DATA_2021-03-31_2145.csv"));
		List<String> recList = lines;
		String[] result = new String[100];
		
		for(String liste : recList) {
			result = liste.split(",");			
					
		}
		for (int i = 0; i <= 100; i++) {
			System.out.println(result[i]);
		}
	}
	public static void main(String[] args) throws IOException {
		
		ReadData test = new ReadData();
		test.split();
		
	}
}
