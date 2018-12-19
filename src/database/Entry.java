package database;
import java.util.ArrayList;
/** 
*Edits Entries from user 
*
*@author DetIncredibles6
*@version 1.0
*@since 2018-12-01
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
	
	
	/**
	 * @return a String with all the data concerning an Entry type object
	 */
	@Override
	public String toString() {
		return entryArguements.toString();
	} 
	
}
