package database;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Superclass whose members are going to be inherited
 * both by TableMenu and DatabaseMenu classes.
 * @author Theodosis Tsaklanos
 *
 */
public class Menu extends JFrame {

    private String tableName;
    private Table table;


    public Menu(String tableName) {
    	super(tableName);
    	this.tableName = tableName;
    }

    public Menu(Table table, String tableName) {
        super(tableName);
        this.table = table;
        this.tableName = tableName;
    }

    /**
     *
     * Defines fundamental things concerning a JFrame component.
     *
     */
    public void initializeGUI() {
        getContentPane().setBackground(Color.WHITE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        terminateAll();
    }

    /**
     * Terminates everything in the current JVM.
     */
    public void terminateAll() {
        addWindowListener(new WindowAdapter() {
            @Override
	    public void windowClosing(WindowEvent click) {
            	System.exit(0);
            }
        });
    }

    /**
     * Removes a JPanel component from a JFrame component.
     * @param panel
     *
     */
    public void refresh(JPanel panel) {
    	if (panel != null) {
    	    if (panel.isDisplayable()) {
    		remove(panel);
    		panel.revalidate();
    		validate();
    		repaint();
    	    }
    	}
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}
