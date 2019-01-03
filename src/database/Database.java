package database;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Database {
	
	private static Database databaseInstance =
			new Database(); //ensuring that ONLY one object is created
	
	private ArrayList<Table> tables = new ArrayList<Table>();
	
	private Database() {
		
	}
	
	public static Database getDatabaseInstance() { //Singleton's pattern
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

	public boolean checkForPossibleDuplicate(String tableName) {
		if (tables.toString().contains(tableName)) {
			JOptionPane.showMessageDialog(null,
					"You can't have a table name more than once");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isThereAnyTable() {
		if (tables.size() > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public int numberOfTables() {
		return Database.getDatabaseInstance().getTables().size();
	}
}
