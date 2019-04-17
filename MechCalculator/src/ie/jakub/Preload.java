package ie.jakub;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;




public class Preload {
	private final int index;
	private final String title;
	private final Map<Double, Double> tensileStressArea;
	private BufferedReader br;


	/*
	 * Constructor
	 */
	public Preload(int index, String title) throws Exception {
		this.index = index;
		this.title = title;
		this.tensileStressArea = new HashMap<Double, Double>();
		parser("TensileStressTable");
	}
	/*
	 * Parses the file
	 */
	public void parser(String string) throws Exception {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(string)));
		br.readLine();
		String [] words;
		String line;
		while((line = br.readLine()) != null){
			words = line.split("=");		
			for(int i = 0; i < words.length; i++) {
				double first = Double.parseDouble(words[i]);
				i++;
				double second = Double.parseDouble(words[i]);
				addValues(first, second);
				
			}//for
		}

		//While
		//close the file
		br.close();
	}	
	/*
	 * Adds values to the table
	 */
	public void addValues(Double diameter, Double at) {
		tensileStressArea.put(diameter, at);
	}
	
	/*
	 * returns the index
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * Returns the title
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * Returns the Tensile Stress area table
	 */
	public Map<Double, Double> getTensileStressArea() {
		return new HashMap<Double, Double>(tensileStressArea);
	}
}
