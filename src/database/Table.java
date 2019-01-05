package database;

import java.util.ArrayList;
/** 
*Edits the Table which contains all the Field and Entry type objects the user entered.
*
*@author DetIncredibles6
*@version 1.0
*/

public class Table {

    private ArrayList<Field> fields = new ArrayList<Field>(); //stores all Field type objects
    private ArrayList<Entry> entries = new ArrayList<Entry>(); //stores all Entry type objects 
    private String tableName;
 
    /**
    *Class Constructor. 
    *@param tableName
    */
    
    public Table(String tableName) {
        this.tableName = tableName;
        new TableMenu(this,tableName);
    }
    
    /** 
    *Getter for Table's name.
    *@return tableName
    */
    
    public String getTableName() {
        return tableName;
    }
    
    /**
    *Sets table name.
    *@param tableName
    */
    
    public void setTableName(String tableName) { //works as a rename method
        this.tableName = tableName;
    }
    
    /**
    *Used to add Fields.
    *@param field a new field added 
    */
    
    public void addField(Field field) {
        fields.add(field);
    }
    
    /**
    *Used to add Entries.
    *@param entry a new entry added 
    */
    
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    /** 
    * @return a String that represents all the entries (tuples) of the database
    */
    
    public ArrayList<Entry> getEntries() {
        return entries;
    }
    
    /**
    *Getter for Entries.
    *@param index a certain position
    *@return entries in this position
    */
    
    public Entry getEntry(int index) {
        return entries.get(index);
    }
    
    /**
    *Gets fields from a certain position.
    *@param index a certain position
    *@return fields in this position 
    */
    
    public Field getField(int index) {
        return fields.get(index);
    }
    
    /**
    *@return fields from ArrayList
    */
    
    public ArrayList<Field> getFields() {
        return fields;
    }
 
    /**
    * Clears the arrayLists that contain the fields.
    * Clears the entries of the certain table.
    */
    
    public void clearTable() {
        fields.clear();
        entries.clear();
    }

    /**
    * @return true if the table has one or more fields
    * Otherwise this method returns false.
    */
    
    public boolean isThereAnyField() {
        if (getFields().size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
    * @return a String representation of a certain Table 
    */
    
    @Override
    public String toString() {
        return tableName.toString();
    }

    /**
    * 
    * @return an Object[] that will be used to populate a JList
    */
    
    public ArrayList<Entry> getListEntries() {
        return entries;
    }
}
