package database;
/**
*Edits fields.
*
*@author DetIncredibles6
*@version 1.0
*@since jdk1.8.0
*/
public class Field {

	private Object fieldName;
	/**
	*Class Constuctor
	*/
	public Field() {
		
	}
	/**
	Class Constructor for certain fieldName
	*@param fieldName
	*/
	public Field(Object fieldName) {
		this.fieldName = fieldName;
	}
	/** 
	*Getter for Field name
	*@return field's name
	*/
	public Object getFieldName() {
		return fieldName;
	}
	/** 
	Setter for Field name
	*@param name
	*/
	public void setFieldName(Object name) {
		this.fieldName = name;
	}
	/** Checks if the given name of a field already exists.
	*@param table the Table object that already exists 
	*@param givenName the new name
	*/
	public static boolean checkForPossibleDuplicate(Table table, String givenName) {
		for(int i = 0; i < table.getFields().size(); i++) {
			if (givenName.equals(table.getField(i).toString())) {
				return false;
			}
		} 
		return true;
	}

	
	
	/**
	 * @return a String representation of a Field type object
	 */
	@Override
	public String toString() {
		return fieldName.toString();
	}
	
}
