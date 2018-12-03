package database;

public class Main {
	public static void main(String[] args) {
		do {
			Menu.showMenu();
		} while(Menu.decider() != 0);
	}
}
		