package Database;

import java.util.Scanner;
import java.util.ArrayList;

public class Values {
	static int rows=1;
	public static void giveValues(ArrayList<ArrayList<Object>> database) {
		Scanner input=new Scanner(System.in);
		database.add(new ArrayList<Object>());
		for (int column=0;column < database.get(0).size();column++) {
			System.out.println("Please insert "+ database.get(0).get(column));
			Object t=input.next();
			database.get(rows).add(t);
		}
		System.out.println();
		System.out.println("Confirmed");
		rows++;
		for (int i=0;i<rows;i++) {
			System.out.println(database.get(i));
		}
		
	}
}
