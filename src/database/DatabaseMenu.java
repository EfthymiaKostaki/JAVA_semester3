package database;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/** 
*Used to create a graphical interface in our Menu
*
*@author DetIncredibles6
*@version 1.0
*@since jdk1.8.0
*/
public class DatabaseMenu extends JFrame {
	private JPanel panel = new JPanel();
	private static Dimension screenSize;
	private static final DatabaseMenu databaseMenu = new DatabaseMenu(); //Singleton Pattern to ensure that only one object is created 
	/**
	*Class Constructor 
	*/
	private DatabaseMenu() {
		getContentPane().setBackground(Color.WHITE);
		add(databaseMenu());
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	    addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	            exitProcedure();
	        }
	    });
	}
	
	/** 
	* Method that prints out memory details on screen.
	*/ 
	public void exitProcedure() {
		System.out.println("See you soon!");
		System.out.println("Max memory: " + Runtime.getRuntime().maxMemory());
		System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
		System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
		System.out.println("Used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		System.out.println(System.getProperty("os.name"));
		System.exit(0);
	}
	/**
	*Getter for Menu 
	*@return databaseMenu
	*/
	public static DatabaseMenu getDatabaseMenuInstance() {
		return databaseMenu;
	}
	
	public JPanel databaseMenu() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension idealButtonSize = new Dimension(140, 25);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, screenSize.height / 3));
		JPanel databasePanel = new JPanel();
		databasePanel.setLayout(new BoxLayout(databasePanel, BoxLayout.Y_AXIS));
		
		JButton createNewTableButton = new JButton("Create a new table");
		createNewTableButton.setMaximumSize(idealButtonSize);
		createNewTableButton.addActionListener(this::
			createNewTableButtonActionPerformed);
		JButton editExistentTableButton = new JButton("Edit a table");
		editExistentTableButton.setMaximumSize(idealButtonSize);
		editExistentTableButton.addActionListener(this::
			editExistentTableButtonActionPerformed);
		JButton deleteExistentTableButton = new JButton("Delete a table");
		deleteExistentTableButton.addActionListener(this::
			deleteExistentTableButtonActionPerformed);
		deleteExistentTableButton.setMaximumSize(idealButtonSize);
		
		JButton deleteDatabaseButton = new JButton("Delete database");
		deleteDatabaseButton.setMaximumSize(idealButtonSize);
		deleteDatabaseButton.addActionListener(this::
			deleteDatabaseButtonActionPerformed);
		
		databasePanel.setBackground(Color.WHITE);
		databasePanel.add(createNewTableButton);
		databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(editExistentTableButton);
		databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(deleteExistentTableButton);
		databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(deleteDatabaseButton);
		
		return databasePanel;

	}
	
	public void refresh() {
		remove(panel);
		panel.revalidate();
		validate();
		repaint();
	}
	
	private void createNewTableButtonActionPerformed(ActionEvent e) {
		refresh();
		String tableName = JOptionPane.showInputDialog("Please name the table");
		if (Standards.isNameValid(tableName)) {
			if(Database.getDatabaseInstance().checkForPossibleDuplicate(tableName)) {			
				Database.getDatabaseInstance().addTable(new Table(tableName));
				setVisible(false);
			}
		}
	}
	
	private void editExistentTableButtonActionPerformed(ActionEvent e) {
		refresh();
		if (Database.getDatabaseInstance().isThereAnyTable()) {
			String message = "Select a table to edit";
			JComboBox<Object> listOfTables = new JComboBox<Object>(
					Database.getDatabaseInstance()
							.getTables()
							.toArray());
			listOfTables.setBackground(Color.WHITE);
			JButton editButton = new JButton("Edit");
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refresh();
					new TableMenu(
							Database.getDatabaseInstance()
							.getTable(listOfTables.getSelectedIndex()),
							listOfTables.getSelectedItem().toString());
				}
			});
			add(tableDestiny(message, listOfTables, editButton));
			validate();
		} else {
			JOptionPane.showMessageDialog(null, "There are no tables yet!");
		}
	}
	/**
	*Used to delete a table
	*@param e
	*/
	private void deleteExistentTableButtonActionPerformed(ActionEvent e) {
		refresh();
		if (Database.getDatabaseInstance().isThereAnyTable()) {
			String message = "Select a table to delete";
			JComboBox<Object> listOfTables = new JComboBox<Object>(
					Database.getDatabaseInstance()
					.getTables()
					.toArray());
			listOfTables.setBackground(Color.WHITE);
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Database.getDatabaseInstance().getTables().remove(listOfTables.getSelectedIndex());
					refresh();
				}
			});
			add(tableDestiny(message, listOfTables, deleteButton));
			validate();
		} else {
			JOptionPane.showMessageDialog(null, "There are no tables yet!");
		}
	}
	/**
	*Used to delete the whole database
	*@param e
	*/
	private void deleteDatabaseButtonActionPerformed(ActionEvent e) {
		refresh();
		if (Database.getDatabaseInstance().isThereAnyTable()) {
			if (Standards.verify()) {
				for (int i = 0; i < Database.getDatabaseInstance().numberOfTables(); i++) {
					Database.getDatabaseInstance().getTable(i).clearTable();
				}
				Database.getDatabaseInstance().getTables().clear();
				JOptionPane.showMessageDialog(null, 
						"Your database was successfully deleted");
			}
		} else {
			JOptionPane.showMessageDialog(null, "The database is empty!");
		}
	}
	
	private JPanel tableDestiny(String message, JComboBox<Object> listOfTables, JButton actionButton) {
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel(message);
		panel.add(label);
		panel.add(listOfTables);
		panel.add(actionButton);
		return panel;
	}
	/**
	*Helps to make the programm suitable for every screen type
	*/
	public static Dimension getScreenSize() {
		return screenSize;
	}
}
