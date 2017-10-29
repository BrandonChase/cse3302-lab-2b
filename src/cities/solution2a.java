/*
package cities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class solution2a {
		private static File infile = new File("L02a Cityname_wo_headers.csv");
		private static File outfile = new File("L02a_Functional_Output.txt");
		
		private static ArrayList<texasCitiesClass> initTheArray () throws IOException {
			ArrayList<texasCitiesClass> txcArray = new ArrayList<texasCitiesClass>();
				String [] values;
				String line = "";
				BufferedReader br = new BufferedReader (new FileReader(infile));
				
				while ((line = br.readLine()) != null) {
					values = line.split(",");
					txcArray.add(new texasCitiesClass(values[0],values[1],Integer.parseInt(values[2])));
				}
				br.close();
				return txcArray;
		}
		
		private static void writeCountyData2 (ArrayList<texasCitiesClass> txcArray) throws IOException {
				FileWriter writer = new FileWriter(outfile);
				writer.write("County name"+"\t"+"No. Cities"+"\t"+"Total Pop"+"\t"+"Ave Pop"+"\t"+"Largest City"+"\t"+"Population\n");
				DecimalFormat IntWithComma=new DecimalFormat("###,###,###");
				txcArray.stream().map(cnty -> cnty.getCounty()).distinct().sorted((cnty1,cnty2) -> cnty1.compareTo(cnty2))
					.forEach(p -> {
						int max = (int) txcArray.stream().filter(txc -> txc.getCounty().equals(p)).mapToInt(txc -> txc.getPopulation()).reduce(0, (x,y) -> x>y? x:y);
						int NumCities = (int) txcArray.stream().filter(txc -> txc.getCounty().equals(p)).mapToInt(txc -> txc.getPopulation()).count();
						int totalPop = (int) txcArray.stream().filter(txc -> txc.getCounty().equals(p)).mapToInt(txc -> txc.getPopulation()).sum();
						int avePop = totalPop/NumCities;
						String largestCity = txcArray.stream().filter(txc -> txc.getCounty().equals(p)).filter(txc -> txc.getPopulation()==max).map(txc -> txc.getName().toString()).reduce("", String::concat);
						String dataLine = (p.toString()+"\t"+IntWithComma.format(NumCities)+"\t"+IntWithComma.format(totalPop)+"\t"+IntWithComma.format(avePop)+"\t"+largestCity+"\t"+IntWithComma.format(max)+"\n");
						try {
							writer.write(dataLine);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
				writer.close();
		}
		
		public static void main(String[] args) {
			ArrayList<texasCitiesClass> txcArray = new ArrayList<texasCitiesClass>();
			try {
				txcArray=initTheArray();
				writeCountyData2(txcArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
*/