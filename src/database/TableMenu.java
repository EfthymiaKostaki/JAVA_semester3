package database;

import java.util.Scanner;
import java.util.ArrayList;

/**
* Contains methods to print menu and get information on users decisions.
* When user makes a choice then certain specific tasks are being carried out.
* These tasks refer to add fields, show fields, add entries, show entries to database or exit.
*
*@author DetIncredibles6
*@version 1.0
*@since 2018-12-01
*/
public class TableMenu{
	private ArrayList<Object> entryArguements = new ArrayList<Object>();
	/*Constructor for TableMenu*/
	public TableMenu() {
		
	}
	/**
	*Method that prints out menu on screen.
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
	*@param choice keeps an integer number between 0 and 4 that reflects the choice of the user.
	*@return the choice that was made.
	*/
	public int decider() {
		/* Creates a Scanner object*/
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		if (choice == 0) {
			/*method runTests is executed and then user exits the database*/
			runTests();
			input.close();
		} else if (choice == 1) {
			/*user adds fields to database*/
			System.out.println("You are making the system heavier..ehh! Just give me the field name");
			table.addField(new Field(input.next()));
		} else if (choice == 2) 
			/*existing fields are desplayed on screan*/
			/*If there are no fields an appropriate message appears*/
			if (table.getFields().size() > 0) {
				System.out.println(table.getFields());
			} else {
				System.out.println("Get the f@ck outta here..there is no single field");
			}
		} else if (choice == 3) {
			/*user adds etries to tadabase*/
			for (int i = 0; i<Field.fieldsCounter; i++) {
				System.out.println("Please insert the " + table.getField(i) + ":");
				entryArguements.add(input.next());
			}
			table.addEntry(new Entry(entryArguements)); //contrusts an Entry object and stores it to an ArrayList<Entry>
			entryArguements.clear();
		} else if (choice == 4) {
			/*existing entries are desplayed on screan*/
			/*If there are no entries an appropriate message appears*/
			if (table.getEntries().size() > 0) {
				System.out.println(table.getEntries());
			} else {
				System.out.println("There are no entries you fool");
			}
		}
		return choice;
	}
	/** 
	* Method that prints out the memory used on screen.
	*/ 
	public void runTests() {
		System.out.println("See you soon!");
		System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
		System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
	}

}
