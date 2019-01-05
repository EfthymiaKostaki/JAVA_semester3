package database;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Database is the class that holds responsibility for every table that exists in a database.
 * @author Theodosios Tsaklanos.
 *
 */

public class Database {

    private static final Database databaseInstance =
          new Database();

  //storing all the table objects in this class to represent the database
    private ArrayList<Table> tables = new ArrayList<Table>();


    private Database() {
        ;
    }

    /**
    * Having more than one Database objects is prevented and therefore,
    * the unique object that exists needs to be returned in this way.
    * @return a database object.
    */
    
    //Singleton pattern
    public static Database getDatabaseInstance() {
        return databaseInstance;
    }


    public void addTable(Table table) {
        tables.add(table);
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public Table getTable(int x) {
        return tables.get(x);
    }

    /**
    *
    * @param tableName//here needs a description.
    * @return true in case the given table name does not exist in the database
    * or false in case the given table name already exists and thus cannot
    * be duplicated.
    */
    
    public boolean checkForPossibleDuplicate(String tableName) {
        if (tables.toString().contains(tableName)) {
            JOptionPane.showMessageDialog(null,
                "You can't have a table name more than once");
            return false;
        } else {
            return true;
        }
    }
    
    /**
    *Checks if user has entered a table.
    *@return yes or no.
    */
    
    public boolean isThereAnyTable() {
        if (tables.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int numberOfTables() {
        return Database.getDatabaseInstance().getTables().size();
    }
}
