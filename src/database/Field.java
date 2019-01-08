package database;

import javax.swing.JOptionPane;
/**
 * A Field object is a data structure for a single piece of data.
 * It is connected to the data inserted in a specific row.
 * @author DetIncredibles6
 * @version 1.1
 */
public class Field {

	private Object fieldName;

	public Field(Object fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldName() {
		return fieldName;
	}

	public void setFieldName(Object name) {
		this.fieldName = name;
	}

	/**
	 * Checks if the field the user wants to add already exists or not
	 * @param table
	 * @param givenName
	 * @return true if the the certain field does not exist or false
	 * in case it exists
	 */
	public static boolean checkForDuplicate(Table table, String givenName) {
		for(int i = 0; i < table.getFields().size(); i++) {
			if (givenName.equals(table.getField(i).toString())) {
				JOptionPane.showMessageDialog(null,
						"You can't have the same field twice");
				return false;
			}
		}
		return true;
	}

	/**
	 * Used to return a String representation of a Field type object
	 * @return
	 */
	@Override
	public String toString() {
		return fieldName.toString();
	}

}
