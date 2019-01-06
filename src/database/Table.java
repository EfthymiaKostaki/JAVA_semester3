package database;

import java.util.ArrayList;

/**
 * @author Theodosis Tsaklanos
 * @version 1.1
 */
public class Table {

	private ArrayList<Field> fields = new ArrayList<Field>(); //stores all Field type objects

	private ArrayList<Entry> entries = new ArrayList<Entry>(); //stores all Entry type objects

	private String tableName;

	/**
	 * Creates Table type objects that represent a table of the database.
	 * @param tableName
	 */
	public Table(String tableName) {
		this.tableName = tableName;
		new TableMenu(this,tableName);
	}
	
	/**
	* Getter for Table's name.
	* @return tableName
	*/
	public String getTableName() {
		return tableName;
	}

	/**
	 * Renames the Table object.
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	* @return a string representation of table's name
	*/
	@Override
	public String toString() {
		return tableName.toString();
	}

	/**
	 * Adds a field (column) to this table.
	 * @param field
	 */
	public void addField(Field field) {
		fields.add(field);
	}

	/**
	 * Adds an entry (row) to this table.
	 * @param entry
	 */
	public void addEntry(Entry entry) {
		entries.add(entry);
	}

	/**
	 * @return all the Entry objects (rows) of this table
	 */
	public ArrayList<Entry> getEntries(){
		return entries;
	}
	
	/**
	 * @param index
	 * @return a specific entry(row)
	 */
	public Entry getEntry(int index) {
		return entries.get(index);
	}

	/**
	 * @return the fields (column names) of this table
	 */
	public ArrayList<Field> getFields(){
		return fields;
	}
	
	/**
	 * @param index
	 * @return a specific field(column)
	 */
	public Field getField(int index) {
		return fields.get(index);
	}

	/**
	 * Clears the Arraylists that contain the fields
	 * and the entries of the this table.
	 */
	public void clearTable() {
		fields.clear();
		entries.clear();
	}

	/**
	 * @return true if the table has one or more fields.
	 * Otherwise, returns false.
	 */
	public boolean isThereAnyField() {
	    if (getFields().size() > 0) {
	        return true;
	    } else {
	        return false;
	    }
	}


	/**
	 * Returns an Object[] that will be used
	 * to populate a JList
	 */
	public Object[] getListEntries(){
	    return entries.toArray();
	}

}
