package database;
/**
*Edits fields.
*
*@author DetIncredibles6
*@version 1.0
*/
public class Field {

    private Object fieldName;
    /**
    *Class Constuctor
    */
    
    public Field() {
        ;
    }
    /**
    *Class Constructor for certain fieldName
    *@param fieldName
    */
    
    public Field(Object fieldName) {
        this.fieldName = fieldName;
    }
    /** 
    *Gets the name of the field
    *@return field's name
    */

    public Object getFieldName() {
        return fieldName;
    }
    /** 
    *Sets the new field name name
    *@param name
    */

    public void setFieldName(Object name) {
        this.fieldName = name;
    }
    /** 
    *Checks if the given name of a field already exists.
    *@param table the Table object that already exists 
    *@param givenName the new name
    *@return false if the given name already exists as a field or true otherwise
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
    * @return a String representation of a Field type object
    */
    @Override
    public String toString() {
        return fieldName.toString();
    }

}
