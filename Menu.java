package database;
import java.util.Scanner;

public class Menu {
	public static void showMenu() {
		System.out.println("**This is the menu**");	
		System.out.println("0) Exit!");
		System.out.println("1) Add first + last name");
		System.out.println("2) Show the names and play the games");
		System.out.println("Make a choice please:");
	}
	
	public static int decider() {
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		if (choice == 0) {
			System.out.println("See you soon!");
			input.close();
		} else if (choice == 1) {
			System.out.println("You are making the system heavier..ehh! Just give me the name");
			Object name = input.next();
			new Table(name);
			System.out.println(); //leaving a space
		} else if (choice == 2) {
			System.out.println(Table.getTable());
			System.out.println(); // leaving a space
		}
		return choice;
	}

}
