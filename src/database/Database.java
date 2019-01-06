package database;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Database is the class that holds responsibility for every table that exists
 * in a database.
 *
 * @author Theodosis Tsaklanos
 * @author Paris Mpampaniotis
 * @version 1.1
 */
public class Database {

    /** Unique instance of Database class*/
    private static final Database databaseInstance = new Database();

    /** Storing all the table objects to represent the database. */
    private ArrayList<Table> tables = new ArrayList<Table>();

    /**
     * Having more than one Database objects is prevented and therefore the unique
     * object that exists needs to be returned in this way
     *
     * @return a database object
     */
    // Singleton pattern
    public static Database getDatabaseInstance() {
        return databaseInstance;
    }

    /**
     * Adds a Table object to the ArrayList that stores every Table object.
     *
     * @param table
     */
    public void addTable(Table table) {
        tables.add(table);
    }

    /**
     * Removes a specific Table object from the ArrayList that stores the tables.
     *
     * @param index
     */
    public void removeTable(int index) {
        tables.remove(index);
    }

    /**
     * @return an ArrayList that contains Table type objects each of which
     * corresponds to a table that exists in the current database.
     */
    public ArrayList<Table> getTables() {
        return tables;
    }

    public Table getTable(int x) {
        return tables.get(x);
    }

    /**
     * @param tableName
     * @return true in case the given table name does not exist in the database or
     *         false in case the given table name already exists and thus cannot be
     *         duplicated.
     */
    public boolean checkForDuplicateTables(String tableName) {
        if (tables.toString().contains(tableName)) {
            JOptionPane.showMessageDialog(null, "You can't have a table name more than once");
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return true if there exists one or more tables in the database.
     */
    public boolean isThereAnyTable() {
        if (tables.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the size of the ArrayList that stores the Table objects.
     */
    public int numberOfTables() {
        return Database.getDatabaseInstance().getTables().size();
    }

}
