package database;

import java.util.ArrayList;

public class Table {
	
	private ArrayList<Field> fields = new ArrayList<Field>(); //stores all Field type objects
	private ArrayList<Entry> entries = new ArrayList<Entry>(); //stores all Entry type objects 
	
	public Table() {
		
	}
	
	public void addField(Field field) {
		fields.add(field);
	}
	
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
