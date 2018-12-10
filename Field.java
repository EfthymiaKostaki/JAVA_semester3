package database;

public class Field {

	protected static int fieldsCounter; 
	private Object fieldName;
	
	public Field() {
		
	}
	
	public Field(Object fieldName) {
		this.fieldName = fieldName;
		fieldsCounter++;
	}
	
	public Object getFieldName() {
		return fieldName;
	}
	
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

