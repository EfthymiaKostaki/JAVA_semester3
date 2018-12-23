package database;
/**
*This is the main class of the project.
*Generates all the other classes.
*
*@author DetIncredibles6
*@version 1.0
*@since jdk1.8.0
*/
 
public class Main {
	public static void main(String[] args) {
		/*Creates TableMenu object*/
		TableMenu menu = new TableMenu();
		/*Prints menu*/
		do {
			menu.showMenu();
		} while(menu.decider() != 0); 
	}
}
