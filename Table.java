package database;
import java.util.ArrayList;
public class Table {
	protected static ArrayList<Object> table = new ArrayList<Object>();
	private Object name;
	
	public Table() {
		
	}
	
	public Table(Object name) {
		this.name = name;
		table.add(name);
	}
	
	public Object getName() {
		return name;
	}
	
	public void setName(Object name) {
		this.name = name;
	}
	
	public static ArrayList<Object> getTable(){
		return table;
	}
	
}
