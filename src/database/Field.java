package database;

public class Field {

	private Object fieldName;
	
	public Field() {
		
	}
	
	public Field(Object fieldName) {
		this.fieldName = fieldName;
	}
	
	public Object getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(Object name) {
		this.fieldName = name;
	}
	
	public static boolean checkForPossibleDuplicate(Table table, String givenName) {
		for(int i = 0; i < table.getFields().size(); i++) {
			if (givenName.equals(table.getField(i).toString())) {
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
