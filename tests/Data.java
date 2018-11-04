package Database;

import java.util.*;
import java.util.ArrayList;

public class Data {
	static ArrayList<ArrayList<Object>> database = new ArrayList<ArrayList<Object>>();
	int counter=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int counter=0,column, row;
		int choice; 
		AddField.addField(counter,database);
		counter++;
		do {
			choice= Menu.printMenu(database);
			if (choice == 1) {
				AddField.addField(counter,database);
			} else if (choice==2) {
				Values.giveValues(database);
			} else if (choice==3) {
				
			} 
		} while (choice != 4);
	}

	public Data(ArrayList<ArrayList<Object>> database) {
		super();
		this.database = database;
	}

}
