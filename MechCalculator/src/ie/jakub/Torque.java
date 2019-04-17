package ie.jakub;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Torque {
	
	private final int index;
	private final String title;
	private final Map<Double, Double> proofStrength;
	private BufferedReader br;

	/*
	 * Constructor
	 */
	public Torque(int index, String title) throws Exception {
		this.index = index;
		this.title = title;
		this.proofStrength = new HashMap<Double, Double>();
		parser("ProofStrengthTable");

	}
	/*
	 * Parses the file
	 */
	public void parser(String string) throws Exception {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(string)));
		br.readLine();
		String[] words;
		String line;
		while ((line = br.readLine()) != null) {
			words = line.split("=");
			for (int i = 0; i < words.length; i++) {
				double first = Double.parseDouble(words[i]);
				i++;
				double second = Double.parseDouble(words[i]);
				addValues(first, second);

			} // for
		}

		// While
		// close the file
		br.close();
	}
	/*
	 * Adds the values to the Proof Strength table
	 */
	public void addValues(Double diameter, Double at) {
		proofStrength.put(diameter, at);
	}

	/*
	 * Returns the index
	 */
	
	public int getIndex() {
		return index;
	}
	
	/*
	 * Returns he title
	 */
	public String getTitle() {
		return title;
	}
	/*
	 * Returns the Proof Strength table
	 */

	public Map<Double, Double> getProofStrength() {
		return new HashMap<Double, Double>(proofStrength);
	}

}
