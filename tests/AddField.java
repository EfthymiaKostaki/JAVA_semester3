package Database;

import java.util.ArrayList;
import java.util.Scanner;

public class AddField {
	public static void addField(int counter,ArrayList<ArrayList<Object>> database) {
		int rows = 0;
		int z =0;
		System.out.println("Please name the field you want to add");
		Scanner input = new Scanner(System.in);
		Object x = input.next();
		if (counter==0) {
			database.add(new ArrayList<Object>());
		}
		database.get(0).add(x);
			int flag = 0;
			while (flag == 0) {
				System.out.println("Do you want to add another field ? y/n");
				String t = input.next();
				if (t.equals("y")) {
					System.out.println("Please name the field you want to add");
					Object l = input.next();
					database.get(0).add(l);
					z=1;
				} else if (t.equals("n")) {
					flag = 1;
					z = 1;
					System.out.println(database);
				}
			}
	}
}
