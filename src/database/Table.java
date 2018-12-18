package database;

import java.util.ArrayList;
/**
* Edits the Table which contains all the objects the user entered.
*/
public class Table {
	/**stores all Field type objects*/
	private ArrayList<Field> fields = new ArrayList<Field>(); 
	/**stores all Entry type objects*/
	private ArrayList<Entry> entries = new ArrayList<Entry>();  
	/** Class Constructor */
	public Table() {
		
	}
	/** Method that adds new Fields*/
	public void addField(Field field) {
		fields.add(field);
	}
	/** Method that adds new Entries*/
	public void addEntry(Entry entry) {
		entries.add(entry);
	}
	
	/**
	 * 
	 * @return a String that represents all the entries (tuples) of the database
	 */
	public ArrayList<Entry> getEntries(){
		return entries;
	}
	
	public Field getField(int i) {
		return fields.get(i);
	}
	
	public ArrayList<Field> getFields(){
		return fields;
	}

}
