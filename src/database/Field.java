package database;
/**
*Edits fields.
*
*@author DetIncredibles6
*@version 1.0
*@since jdk1.8.0
*/
public class Field {

	protected static int fieldsCounter; 
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
		fieldsCounter++;
	}
	/** 
	*Getter for Field names
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
	
	
	/**
	 * @return a String representation of a Field type object
	 */
	@Override
	public String toString() {
		return fieldName.toString();
	}
	
	public static int getFieldsCounter() {
		return fieldsCounter;
	}
	
}
