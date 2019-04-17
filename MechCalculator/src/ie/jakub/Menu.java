package ie.jakub;

import java.util.Scanner;

public class Menu {
	/*
	 * Class variables
	 */
	private Calculator calculator;
	private Scanner in = new Scanner(System.in);
	private String menuInput = null;
	private String askedDouble = null;
	private Double reusableOrNot = 0.0;
	private Double diameter = 0.0;
	private Double grade = 0.0;

	/*
	 * Constructor
	 */
	public Menu() {
		try {
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() throws Exception {
		calculator = new Calculator();
		menu();

		/*
		 * Initializes menu loop
		 */
	}

	private void menu() throws Exception {

		do {
			showOptions(); // method that shows Menu options
			menuInput = in.nextLine(); // reads console input

			if (menuInput.equals("1")) {
				showPreloadMenu();

				askedDouble = in.nextLine();	
				double testDiameter = isDouble(askedDouble, diameter);//Checks if input is correct
				if (!containsTensileValue(testDiameter)) {
					System.out.println("Please ensure the input value is correct");
					menu();
				}
				diameter = testDiameter; 
				calculator.setDiameter(diameter); //Assigns the diameter

				showReusableOrNot(); //Shows the options

				askedDouble = in.nextLine();
				int design = (int) isDouble(askedDouble, reusableOrNot);
				if (design != 1 || design != 2) {
					System.out.println("Bolt design is re-usable by default");
				}

				showTorquedMenu();

				askedDouble = in.nextLine();
				double testGrade = isDouble(askedDouble, grade);//Checks if input is correct
				if (!containsGradeValue(testGrade)) {
					System.out.println("Please ensure the input value is correct");
					menu();
				}
				grade = testGrade;

				/*
				 * Ensures that values match and are valid
				 */
				if ((grade == 4.6 && (diameter < 5 || diameter > 36))
						|| (grade == 4.8 && (diameter < 1.6 || diameter > 16))
						|| (grade == 5.8 && (diameter < 5 || diameter > 24))
						|| (grade == 8.8 && (diameter < 16 || diameter > 36))
						|| (grade == 9.8 && (diameter < 1.6 || diameter > 16))
						|| (grade == 10.9 && (diameter < 5 || diameter > 36))
						|| (grade == 12.9 && (diameter < 1.6 || diameter > 36))) {
					System.out.println("Grade " + grade + " is not suitable for " + diameter + "diameter");
					break;
				}
				calculator.setGrade(grade);//Assigns the grade
				calculator.process(design);//Starts the calculating process

			} else if (menuInput.equals("2")) { //Quit on input 2
				System.out.println("Quiting...");
				System.exit(0);

			} else {
				System.out.println("Invalid input");
			}
		} while (!menuInput.equals("2"));
	}
	/*
	 * Method that checks if the Tensile value is valid
	 */
	private boolean containsTensileValue(double value) {
		if (Calculator.getTensileStressAreaValues().get(Calculator.getTensile()).getTensileStressArea()
				.get(value) == null) {
			return false; //Fails if the value is not in the table
		}
		return true;//Ensures the value is correct
	}
	/*
	 * Method that checks if the Tensile value is valid
	 */
	private boolean containsGradeValue(double value) {
		if (Calculator.getProofStrengthValues().get(Calculator.getStrength()).getProofStrength().get(value) == null) {
			return false;//Fails if the value is not in the table
		}
		return true;//Ensures the value is correct
	}

	/*
	 * Checks if the value is correct / parses double from the input
	 */
	private double isDouble(String askedDouble, Double doubleValue) {
		try {
			doubleValue = Double.parseDouble(askedDouble);
		} catch (Exception e) {
			return 0;//If value is not double returns 0 (Is used as condition)
		}
		return doubleValue;//Returns the double
	}

	/*
	 * Prints Options for the first menu
	 */
	private void showOptions() {
		System.out.println("================================================================");
		System.out.println("== Welcome to the Mechanical Engineering Essential calculator ==");
		System.out.println("================================================================");
		System.out.println(" (1) Calculate the preload required and the torque required to apply preload");
		System.out.println(" (2) Quit");
		System.out.println("Select an option [1-2]> ");

	}

	/*
	 * Prints menu for reusable or not
	 */
	private void showReusableOrNot() {

		System.out.println(" Is the bolt designed to be reusable or permament?" + " \n(1) Reusable bolt design "
				+ " \n(2) Permament bolt design ");

	}
	/*
	 * Prints Diameter menu
	 */

	private void showPreloadMenu() {
		System.out.println("====================================");
		System.out.println("== Calculate the required preload ==");
		System.out.println("====================================");
		System.out.println(" Please enter the Diameter of the bolt ");

	}
	/*
	 * Prints Torque menu
	 */

	private void showTorquedMenu() {
		System.out.println("====================================================");
		System.out.println("== Calculate the torque required to apply preload ==");
		System.out.println("====================================================");
		System.out.println("----------------------------------------------------");
	    System.out.printf("%10s %20s %30s" , "Grade class", "Size range", "Proof Strength");
	    System.out.println();
		System.out.println("----------------------------------------------------");
	    System.out.printf("%-20s %-10s %30s" , "4.6", "M5 to M36	", "225");
	    System.out.println();
	    System.out.printf("%-20s %-10s %30s" , "4.8", "M1.6 to M16", "310");
	    System.out.println();
	    System.out.printf("%-20s %-10s %30s" , "5.8", "M5 to M24	", "380");
	    System.out.println();
	    System.out.printf("%-20s %-10s %30s" , "8.8", "M16 to M36	", "600");
	    System.out.println();
	    System.out.printf("%-20s %-10s %30s" , "9.8", "M1.6 to M16", "650");
	    System.out.println();
	    System.out.printf("%-20s %-10s %30s" , "10.9", "M5 to M36	", "830");
	    System.out.println();
	    System.out.printf("%-20s %-10s %30s" , "12.9", "M1.6 to M36", "970");
	    System.out.println();
	    
		System.out.println(" Please enter the Grade of the bolt ");

	}
}
