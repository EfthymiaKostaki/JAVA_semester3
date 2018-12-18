package database;
/**
*Edits fields.
*
*@author DetIncredibles6
*@version 1.0
*@since 1/12/2018
*/
public class Field {

	protected static int fieldsCounter; 
	private Object fieldName;
	/**Class Constuctor*/
	public Field() {
		
	}
	/**Class Constructor for certain fieldName*/
	public Field(Object fieldName) {
		this.fieldName = fieldName;
		fieldsCounter++;
	}
	/** Getter for Field names*/
	public Object getFieldName() {
		return fieldName;
	}
	/** Setter for Field name*/
	public void setFieldName(Object name) {
		this.fieldName = name;
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
