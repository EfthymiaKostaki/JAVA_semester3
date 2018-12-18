package database;
/** 
*Generates all the other classes.
*
*@author DetIncredibles6
*@version 1.0
*@since 1/12/2018
*/
 
public class Main {
	public static void main(String[] args) {
		/** Creates TableMenu object*/
		TableMenu menu = new TableMenu();
		/** Prints menu*/
		do {
			menu.showMenu();
		} while(menu.decider() != 0); 
	}
}
