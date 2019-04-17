package ie.jakub;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

	/*
	 * Class Variables
	 */
	private static Map<Integer, Preload> tensileStressAreaValues;
	private static Map<Integer, Torque> proofStrengthValues; 

	private static int tensile = 1;
	private static int strength = 1;
	private static final double CONSTANT_REUSABLE = 0.75; //Constant Value for reusable bolt
	private static final double CONSTANT_PERMAMENT = 0.90;//Constant Value for permament bolt
	private double diameter;
	private double grade;

	/*
	 * Constructor
	 */
	public Calculator() throws Exception {
		tensileStressAreaValues = new HashMap<Integer, Preload>();
		proofStrengthValues = new HashMap<Integer, Torque>();
		tensileStressAreaValues.put(tensile, new Preload(tensile, "Tensile stress area"));
		proofStrengthValues.put(strength, new Torque(strength, "Proof strength values"));
	}

	/*
	 * Calculates the Preload required
	 */
	public static double preLoad(double at, double sp, double cons) {
		double fi = cons * (at * sp);
		System.out.println("Calculated preload required : " + fi + " N");
		return fi;
	}
	
	/*
	 * Calculates the torque required to apply Preload
	 */
	
	public static double torque(double fi, double boltOD) {
		double t = (0.2 * fi * boltOD)/1000;
		System.out.println("Calculated torque required to apply preload : " + t + " Nm");
		return t;
	}

	/*
	 * Getters and Setters
	 */
	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public static Map<Integer, Preload> getTensileStressAreaValues() {
		return tensileStressAreaValues;
	}

	public static Map<Integer, Torque> getProofStrengthValues() {
		return proofStrengthValues;
	}

	public static int getTensile() {
		return tensile;
	}

	public static int getStrength() {
		return strength;
	}
	
	/*
	 * Initiates the calculations
	 */
	public void process(int reusableOrPermament ) {


		double aT = tensileStressAreaValues.get(tensile).getTensileStressArea().get(diameter);
		double sP = proofStrengthValues.get(strength).getProofStrength().get(grade);

		System.out.println("Tensile stress area is : " + aT);
		System.out.println("Proof strength is : " + sP);
		
		double cons = 0;
		switch (reusableOrPermament) {
		case 1:
			cons = CONSTANT_REUSABLE;
			break;
		case 2:
			cons = CONSTANT_PERMAMENT;
			break;
		default:
			cons = 1;
			System.out.println("Invalid input");
		}
		double fi = preLoad(aT, sP, cons);
		torque(fi, diameter);
	}

	
	
	
	
}