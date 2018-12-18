package database;

import java.util.Scanner;
import java.util.ArrayList;

/**
* Contains methods to print menu and get information on users decisions.
*
*@author DetIncredibles6
*@version 1.0
*@since 1/12/2018
*/
public class TableMenu{
	private ArrayList<Object> entryArguements = new ArrayList<Object>();
	/** 
	*Constructor for TableMenu
	*/
	public TableMenu() {
		
	}
	/**
	*Method to print out menu in screen
	*/
	public void showMenu() {
		System.out.println("**This is the menu**");	
		System.out.println("0) Exit!");
		System.out.println("1) Add fields");
		System.out.println("2) Show the fields");
		System.out.println("3) Add entries");
		System.out.println("4) Show entries");
		System.out.println("Make a choice please:");
	}
	/*Creates a new Table object*/
	Table table = new Table();
	/**
	*Contains the activities to be done based on user's decision.
	@param choice keeps in number form the choice of user
	@return the choice it was made
	*/
	public int decider() {
		/** Creates a Scanner object*/
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		if (choice == 0) {
			runTests();
			input.close();
		} else if (choice == 1) {
			System.out.println("You are making the system heavier..ehh! Just give me the field name");
			table.addField(new Field(input.next()));
		} else if (choice == 2) {
			if (table.getFields().size() > 0) {
				System.out.println(table.getFields());
			} else {
				System.out.println("Get the fuck outta here..there is no single field");
			}
		} else if (choice == 3) {
			for (int i = 0; i<Field.fieldsCounter; i++) {
				System.out.println("Please insert the " + table.getField(i) + ":");
				entryArguements.add(input.next());
			}
			table.addEntry(new Entry(entryArguements)); //contrusts an Entry object and stores it to an ArrayList<Entry>
			entryArguements.clear();
		} else if (choice == 4) {
			if (table.getEntries().size() > 0) {
				System.out.println(table.getEntries());
			} else {
				System.out.println("There are no entries you fool");
			}
		}
		return choice;
	}
	/** Returns memory used*/ 
	public void runTests() {
		System.out.println("See you soon!");
		System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
		System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
	}

}
