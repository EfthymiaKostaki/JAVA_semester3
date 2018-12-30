package database;
import java.util.ArrayList;
/** 
*Edits Entries from user 
*
*@author DetIncredibles6
*@version 1.0
*@since jdk1.8.0
*@param entriesAdded counter
*/
public class Entry {
//	protected static ArrayList<Entry> entries = 
//			new ArrayList<Entry>();
	
	private ArrayList<Object> entryArguements = new ArrayList<Object>();
	protected static int entriesAdded;
	
	/**
	 * Constructor
	 * Each object is stored on an ArrayList<Entry> 
	 * @param entryArguements, an arraylist of Object type elements each of them corresponds to a field
	 */
	public Entry(ArrayList<Object> entryArguements) {	
		this.entryArguements = new ArrayList<Object> (entryArguements);
	//	entries.add(this);
		entriesAdded++;
	}
	
	public static boolean checkIfSameEntries(ArrayList<Object> entries, Table table) {
		boolean same = true;
		for (int i =0; i < table.getEntries().size(); i++) {
			for (int j = 0; j < Field.fieldsCounter; j++) {
				if (table.getEntries().get(i).getEntryArguements().get(j) != entries.get(j)) {
					same = false;
				}
			}
		}
		return same;
	}
	
	public ArrayList<Object> getEntryArguements() {
		return this.entryArguements;
	}
	
	
	/**
	 * @return a String with all the data concerning an Entry type object
	 */
	@Override
	public String toString() {
		return entryArguements.toString();
	} 
	
}
