package database;

import java.util.ArrayList;

public class Table {
	
	private ArrayList<Field> fields = new ArrayList<Field>(); //stores all Field type objects
	private ArrayList<Entry> entries = new ArrayList<Entry>(); //stores all Entry type objects 
	private String tableName;
	

	public Table(String tableName) {
		this.tableName = tableName;
		new TableMenu(this,tableName);
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) { //works as a rename method
		this.tableName = tableName;
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
	
	public Entry getEntry(int index) {
		return entries.get(index);
	}
	
	public Field getField(int index) {
		return fields.get(index);
	}
	
	public ArrayList<Field> getFields(){
		return fields;
	}
	
	/**
	 * clears the arraylists that contain the fields
	 * and the entries of the certain table
	 */
	public void clearTable() {
		fields.clear();
		entries.clear();
	}
	
	/**
	 * 
	 * @return true if the table has one or more fields.
	 * Otherwise, false is returned
	 */
	public boolean isThereAnyField() {
		if (getFields().size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return tableName.toString();
	}
	
	/**
	 * 
	 * @return an Object[] that will be used to populate a JList
	 */
/*	public Object[] getListEntries(){
		return entries.toArray();
	} */
	
	

}
