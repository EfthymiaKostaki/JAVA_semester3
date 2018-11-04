package Database;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	public static int printMenu(ArrayList<ArrayList<Object>> database) {
		int choice = 4;
			do {
				System.out.println();
				System.out.println("Make a choice");
				System.out.println("1.Add a field");
				System.out.println("2. Add Values");
				System.out.println("3. ...");
				System.out.println("4. Exit");
				Scanner input = new Scanner(System.in);
				choice = input.nextInt();
			} while (choice < 0 || choice > 4);
		return choice;
	}
}
