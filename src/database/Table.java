package database;

import java.util.ArrayList;
/** 
*Edits the Table which contains all the Field and Entry type objects the user entered.
*
*@author DetIncredibles6
*@version 1.0
*@since jdk1.8.0
*/
public class Table {
	
	private ArrayList<Field> fields = new ArrayList<Field>(); //stores all Field type objects
	private ArrayList<Entry> entries = new ArrayList<Entry>(); //stores all Entry type objects 
	private String tableName;
	
	/**
	*Constructor 
	*@param tableName
	*/
	public Table(String tableName) {
		this.tableName = tableName;
		new TableMenu(this,tableName);
	}
	/** 
	*Getter for @param tableName
	*/
	public String getTableName() {
		return tableName;
	}
	/**
	*Setter for @param tableName
	*/
	public void setTableName(String tableName) { //works as a rename method
		this.tableName = tableName;
	}
	/**Used to add Fields
	*@param field a new field added 
	*/
	public void addField(Field field) {
		fields.add(field);
	}
	/**Used to add Entries
	*@param entry a new entry added 
	*/
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
	/**Getter for Entries
	*@param index a certain position
	*/
	public Entry getEntry(int index) {
		return entries.get(index);
	}
	/**
	*Getter of Fields
	*@param index a certain position
	*/
	public Field getField(int index) {
		return fields.get(index);
	}
	/**
	*@return fields from ArrayList
	*/
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
