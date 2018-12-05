package database;

public class Main {
	public static void main(String[] args) {
		TableMenu menu = new TableMenu();
		do {
			menu.showMenu();
		} while(menu.decider() != 0); 
	}
}