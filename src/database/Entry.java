package database;
import java.util.ArrayList;
/** 
*Edits Entries from user. 
*
*@author DetIncredibles6
*@version 1.0
*@param entriesAdded counter
*/

public class Entry {
    private ArrayList<Object> entryArguments = new ArrayList<Object>();
    protected static int entriesAdded;

    /**
    * Constructor
    * @param entryArguements, an arraylist of Object type elements each of which is corresponds to a field
    */
    
    public Entry(ArrayList<Object> entryArguments) {	
        this.entryArguments = new ArrayList<Object>(entryArguments);
        entriesAdded++;
    }

    /**
    * Checks if an array list of entries already exists.
    * @param entries arraylist objects of entries
    * @param table the Table that already exists
    * @return true if an identical entry already exists or false if no identical entry was found
    */
    public static boolean checkIfSameEntries(ArrayList<Object> entries, Table table) {
        for (int i = 0; i < table.getEntries().size(); i++) {
            if (table.getEntries().get(i).getEntryArguments().equals(entries)) { //comparing the whole array lists at once
                return true; // found an identical entry
            }
        }
        return false; //no identical entry found
    }

    /**
    * 
    * @return an entire row of the database (an entry)
    */
    public ArrayList<Object> getEntryArguments() {
        return entryArguments;
    }
    
    /**
    *@return a String with all the data concerning an Entry type object
    */
    @Override
    public String toString() {
        return entryArguments.toString();
    } 

}
