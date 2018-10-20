/* I started writing a code on how I have it in my mind. I know that there are
 * many mistakes and problems so any recommendations are welcome on how to fix them.
 * Firstly I started with the menu which is very simple since it's just the beginning.
 * If you know how to solve any of the bugs don't hesitate and contact me or upload a new
 * improved code. Also I'm very curious to see your other codes and how you have thought it.
 * -Anna Maria
 */


import java.util.*;
public class DetIncredibles {

	public static void main(String[] args) {
		ArrayList<ArrayList<Object>> outerList=new ArrayList<ArrayList<Object>>();
		ArrayList<Object> innerList=new ArrayList<Object>();
		Scanner sc=new Scanner(System.in);
		String name;
		int n1=0;
		int n2=0;
		String b;
		do {
			//First printing out a menu
			System.out.println("What do you want to do?");
			System.out.println("1.Add");
			System.out.println("2.Remove");
			System.out.println("3.Print everything");
			System.out.println("4.Exit");
			b=sc.next();
			// An if statement to find out what the user actually wants
			if(b=="1") {
				System.out.println("Would you like to add a column?Y/N");
				String ap=sc.next()	;
				if (ap=="Y") {

				}
				System.out.println("Give name:");
				name=sc.next();
				outerList.add(name); //Any ideas on how to fix the problem?
				System.out.println("Do you want to add something in the column you've created?Y/N");
				String ap1=sc.next();
				if(ap1=="Y") {
					System.out.println("Enter Object:");
					Object ob=sc.next(); // I don't know how to read an object with Scanner.
					outerList.add(innerList);
					innerList.add(ob);
				} else {
					System.out.println("Would you like to add information?Y/N");
					String ap2=sc.next();
					if (ap2=="Y") {
						System.out.println("In which column would you like to add it?	(1,2,..,"+outerList.size());
						int colu=sc.nextInt();
						//I don't know how to find the exact column in the ArrayList.
					}
				}
			} else if(b=="2") {
				System.out.println("Would you like to remove a column?Y/N");
				String ap=sc.next();
				if(ap=="Y") {

				} else {
					System.out.println("Would you like to remove an item?Y/N");
					String ap1=sc.next();
					if (ap1=="Y") {
						System.out.println("From which column? (1,2,..,"+outerList.size());
						System.out.println("Which item?");
						Object ap2=sc.hasNext(); // I don't know how to read an object with Scanner.
					}
				}

			} else if (b=="3") {
				for(int i=0;i<outerList.size();i++) {
					for(int j=0;j<outerList.get(i).size();j++) {
						System.out.println(outerList.get(i).get(j));
					}
					System.out.println();
				}
			} else {
				break;
			}

		}while (b!="4");

	}
}
