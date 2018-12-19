package database;

import java.util.ArrayList;
/**
* Edits the Table which contains all the Field and Entry type objects the user entered.
*
*@author DetIncredibles6
*@version 1.0
*@since 2018-12-01
*/
*/
public class Table {
	/*stores all Field type objects*/
	private ArrayList<Field> fields = new ArrayList<Field>(); 
	/*stores all Entry type objects*/
	private ArrayList<Entry> entries = new ArrayList<Entry>();  
	/** 
	*Class Constructor 
	*/
	public Table() {
		
	}
	/** 
	*Method that adds new Fields
	*/
	public void addField(Field field) {
		fields.add(field);
	}
	/** 
	Method that adds new Entries
	*/
	public void addEntry(Entry entry) {
		entries.add(entry);
	}
	
	/** 
	 * @return a String that represents all the entries (tuples) of the database
	 */
	public ArrayList<Entry> getEntries(){
		return entries;
	}
	/**
	* @return a specific field
	*/
	
	public Field getField(int i) {
		return fields.get(i);
	}
	/** 
	 * @return a String that represents all the fields of the database
	 */
	public ArrayList<Field> getFields(){
		return fields;
	}

}
