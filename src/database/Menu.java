package database;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Superclass whose members are going to be inherited
 * both by TableMenu and DatabaseMenu classes.
 * Represents a Menu.
 * @author Theodosis Tsaklanos
 * @version 1.1
 */
public class Menu extends JFrame {

    private String tableName;
    private Table table;


    public Menu(String tableName) {
    	super(tableName);
    	this.tableName = tableName;
    }

    /**
     * Defines fundamental things concerning a JFrame component
     * such as its size and background color.
     */
    public void initializeGUI() {
        getContentPane().setBackground(Color.WHITE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        termination();
    }

    /**
     * Terminates everything in the current JVM.
     */
    public void termination() {
        addWindowListener(new WindowAdapter() {
        	@Override
			public void windowClosing(WindowEvent click) {
        		System.exit(0);
        	}
        });
    }

    /**
     * Removes a JPanel component from a JFrame component.
     * @param panel - the panel to be removed
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

    /**Returns the name of the table */
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
