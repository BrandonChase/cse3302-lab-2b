package cities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FunctionalZipCode {
	static final String INPUT_FILENAME = "L02b zip_code_database.csv";
	static final String OUTPUT_FILENAME = "Problem_2b_output.txt";
	
	public static void main(String[] args) {
		try {
			//-----Get Data-----
			ArrayList<zipCodeClass> zipCodes = new ArrayList<>();
			populateListFromFile(zipCodes);
				
			//-----Write First Column-----
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(OUTPUT_FILENAME));
			bWriter.write("County Name\tCounty Pop\tCity Name\tCity Pop.\tNo. Zip Codes");
			bWriter.newLine();
			
			//-----Write Data-----
			zipCodes.stream()
					 .map(zipCode -> zipCode.getCountyName()) //convert to stream of county names
					 .distinct()
					 .sorted()
					 .forEach(county -> { //go though each distinct county in alphabetical order
						 zipCodes.stream()
						 		 .filter(zipCode -> zipCode.getCountyName().equals(county)) //filter to stream of zipCodes in current county
						 		 .map(zipCode -> zipCode.getCityName()) //convert to stream of city names
						 		 .distinct()
						 		 .sorted()
						 		 .forEach(city -> { //go through each city in each county in alphabetical order
						 			 try {
							 			 //-----County Name-----
										 bWriter.write(county);
										 bWriter.write("\t");
										 
										 //-----County Pop-----
										 bWriter.write(NumberFormat.getNumberInstance(Locale.US).format(
												 	   zipCodes.stream()
												 			   .filter(zipCode -> zipCode.getCountyName().equals(county))
												 			   .mapToInt(zipCode -> zipCode.getEstPop())
												 			   .sum()));
										 bWriter.write("\t");
					 			 		 
										 //-----City Name-----
										 bWriter.write(city);
										 bWriter.write("\t");
										 
										 //-----City Population-----
										 bWriter.write(NumberFormat.getNumberInstance(Locale.US).format(
												 	   zipCodes.stream()
													           .filter(zipCode -> zipCode.getCountyName().equals(county) && zipCode.getCityName().equals(city))
													           .mapToInt(zipCode -> zipCode.getEstPop())
													           .sum()));
										 bWriter.write("\t");
										 
										 //-----Number of Zip Codes in City-----
										 bWriter.write(String.valueOf(zipCodes.stream()
													           		 .filter(zipCode -> zipCode.getCountyName().equals(county) && zipCode.getCityName().equals(city))
													           		 .count()));
										 bWriter.write("\t");
										 
										 bWriter.newLine();
						 			 } catch(Exception e) {
						 				System.out.println(e.getMessage());
						 			 }
						 		 });
						 });
					 
			bWriter.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Gets data from a .csv file about Texas zip codes and puts it into an ArrayList
	static void populateListFromFile(ArrayList<zipCodeClass> zipCodes) throws FileNotFoundException, IOException
	{
		String[] values;
		String line = "";
		BufferedReader bReader = new BufferedReader(new FileReader(INPUT_FILENAME));
		
		while((line = bReader.readLine()) != null)
		{
			values = line.split(",");
			zipCodes.add(new zipCodeClass(Integer.parseInt(values[0]), //zip code
										  values[1], //zip code type
										  values[2], //city name
										  values[3], //county name
										  Integer.parseInt(values[4]) //estimated population
										  ));				  
		}
		
		bReader.close();
	}
}
