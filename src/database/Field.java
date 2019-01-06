package database;

/**
* Used for anything in relation with Fields.  
* @author Paris Mpampaniotis
* @author Theodosis Tsaklanos
* @author Anna Maria Mersinoglou
* @author Apostolis Moustakis
* @version 1.1
*/

public class Field {

    private Object fieldName;
	
    /**
    *Constructs Field type objects (column name).
    *@param fieldName
    */
    public Field(Object fieldName) {
        this.fieldName = fieldName;
    }
	
    /** 
    *Returns the name of the field.
    */
    public Object getFieldName() {
        return fieldName;
    }
	
    /** 
    *Sets the new field name.
    *@param name
    */
    public void setFieldName(Object name) {
        this.fieldName = name;
    }
	
    /** 
    *Checks if the given name of a field already exists.
    *@param table the Table object that already exists.
    *@param givenName the new name.
    *@return false if the given name already exists as a field or true otherwise.
    */
    public static boolean checkForPossibleDuplicate(Table table, String givenName) {
        for (int i = 0; i < table.getFields().size(); i++) {
            if (givenName.equals(table.getField(i).toString())) {
                return false;
            }
        }
        return true;
    }

    /**
    * @return a String representation of a Field type object.
    */
    @Override
    public String toString() {
        return fieldName.toString();
    }

}
