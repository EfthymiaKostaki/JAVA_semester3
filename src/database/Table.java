package database;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * A Table object represent a data structure with rows and columns
 * that exists in a database.
 * @author Theodosis Tsaklanos
 * @author Paris Mpampaniotis
 * @version 1.1
 */
public class Table {

	/** Stores all Field type objects of this table.*/
	private ArrayList<Field> fields = new ArrayList<Field>();

	/** Stores all Entry type objects of this table.*/
	private ArrayList<Entry> entries = new ArrayList<Entry>();

	private String tableName;

	/**
	 * Creates Table type objects that represent
	 * a table of the database.
	 * @param tableName
	 */
	public Table(String tableName) {
		this.tableName = tableName;
	}

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
	 * @return all the Entry objects (rows)
	 * of this table.
	 */
	public ArrayList<Entry> getEntries(){
		return entries;
	}

	public Entry getEntry(int index) {
		return entries.get(index);
	}

	/**
	 * @return the number of entries (rows)
	 * of this table
	 */
	public int numberOfEntries() {
	    return entries.size();
	}

	/**
	 * @return the fields (column names) of
	 * this table.
	 */
	public ArrayList<Field> getFields(){
		return fields;
	}

	public Field getField(int index) {
		return fields.get(index);
	}

	/**
	 * @return the number of fields (columns)
	 * of this table
	 */
	public int numberOfFields() {
	    return fields.size();
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
	 *
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

	/** When a new Field (column) is added to the database, all the existent
	 * records' value for this Field is set to NULL.*/
	public void checkForUnfilledElements(Table table, String fieldName) {
	    for (int q = 0; q < table.numberOfEntries(); q++) {
	        if (table.getEntry(q).getEntryArguments().size() < table.numberOfFields()) {
	            JOptionPane.showMessageDialog(null,
	                    "All your inserted entries now have a "
	                    + "NULL value for field: " + fieldName);
	            table.getEntry(q).getEntryArguments().add("NULL");
	        }
	    }
	}

}
