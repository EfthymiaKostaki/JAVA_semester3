package database;
import java.util.ArrayList;
/**
 *
 * @author Efthymia Kostaki
 * @author Paris Mpampaniotis
 * @author Theodosis Tsaklanos
 * @author Anna Maria Mersinoglou
 * @author Apostolis Moustakis
 * @version 1.1
 *
 */
public class Entry {
	private ArrayList<Object> entryArguments = new ArrayList<Object>();

	/**
	 * Constructor
	 * @param entryArguments, an arraylist of Object type elements each of which is corresponds to a field
	 */
	public Entry(ArrayList<Object> entryArguments) {
		this.entryArguments = new ArrayList<Object> (entryArguments);
	}

	/**
	 *
	 * @param entries
	 * @param table
	 * @return true if an identical entry already exists or false if no
	 * identical entry was found
	 */
	public static boolean checkIfSameEntries(ArrayList<Object> entries, Table table) {
		for (int i = 0; i < table.getEntries().size(); i++) {
			if (table.getEntries().get(i).getEntryArguments().equals(entries)) {
				return true; // found an identical entry
			}
		}
		return false; //no identical entry found
	}

	/**
	 *
	 * @return an entire row of the database (an entry)
	 */
	public ArrayList<Object> getEntryArguments() {
		return entryArguments;
	}

	/**
	 * @param index
	 * @return a specific element of a row
	 */
	public Object getEntryArgument(int index) {
		return entryArguments.get(index);
	}


	@Override
	public String toString() {
		return entryArguments.toString();
	}

}
