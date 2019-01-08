package database;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Creates the main menu of the database.
 * Actions such as creating,editing or deleting a table
 * are managed by this window.
 * @author Theodosis Tsaklanos
 * @version 1.1
 */
public class DatabaseMenu extends Menu {


	//Singleton Pattern to ensure that only one object is created
    private static final DatabaseMenu databaseMenu =
					new DatabaseMenu("Database Main Menu");
    private final Dimension idealButtonSize = new Dimension(140, 30);
    private	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton createNewTableButton;
    private JButton editExistentTableButton;
    private JButton editButton;
    private JButton deleteExistentTableButton;
    private JButton deleteButton;
	private JButton deleteDatabaseButton;
	private JButton importTableButton;
	private JComboBox<Object> listOfTables;
	private JLabel welcomeLabel;
	private JLabel dateLabel;
	private JPanel databasePanel;
	private JPanel sidePanel;

	private DatabaseMenu(String tableName) {
	    super(tableName);
	    super.initializeGUI();;
	    setLayout(new FlowLayout(FlowLayout.CENTER, 0, screenSize.height / 3));
	    add(databaseMenu());
	    setVisible(true);
	}

	public static DatabaseMenu getDatabaseMenuInstance() {
		return databaseMenu;
	}

	/**
	 * Initializes all the
	 * components that are going
	 * to be used in this window.
	 * @return a JPanel component that represents the main menu.
	 */
	public JPanel databaseMenu() {
		databasePanel = new JPanel();
		databasePanel.setLayout(new BoxLayout(databasePanel, BoxLayout.Y_AXIS));
		databasePanel.setBackground(Color.WHITE);

		String username = System.getProperty("user.name");
		welcomeLabel = new JLabel("Welcome " + username);

		dateLabel = new JLabel("<html>Last access at:"
				+ "<br/>" + new Date().toString() + "</html>");

		importTableButton = new JButton("Import a Table");
		importTableButton.setMaximumSize(idealButtonSize);
		importTableButton.addActionListener(this::
		    importTableButtonActionPerformed);
		importTableButton.setIcon(
		        new ImageIcon(addButtonIcon("importTable.png")));

		createNewTableButton = new JButton("Create a Table");
		createNewTableButton.setMaximumSize(idealButtonSize);
		createNewTableButton.addActionListener(this::
			createNewTableButtonActionPerformed);
		createNewTableButton.setIcon(
		        new ImageIcon(addButtonIcon("plusIcon.png")));

		editExistentTableButton = new JButton("Update a Table");
		editExistentTableButton.setMaximumSize(idealButtonSize);
		editExistentTableButton.addActionListener(this::
			editExistentTableButtonActionPerformed);
		editExistentTableButton.setIcon(
		        new ImageIcon(addButtonIcon("updateTableIcon.png")));

		deleteExistentTableButton = new JButton("Delete a Table");
		deleteExistentTableButton.setMaximumSize(idealButtonSize);
		deleteExistentTableButton.addActionListener(this::
			deleteExistentTableButtonActionPerformed);
		deleteExistentTableButton.setIcon(
		        new ImageIcon(addButtonIcon("deleteTableIcon.png")));

		deleteDatabaseButton = new JButton("Clear Database");
		deleteDatabaseButton.setMaximumSize(idealButtonSize);
		deleteDatabaseButton.addActionListener(this::
			deleteDatabaseButtonActionPerformed);
		deleteDatabaseButton.setIcon(
		        new ImageIcon(addButtonIcon("deleteDatabaseIcon.png")));

		databasePanel.add(welcomeLabel);
		databasePanel.add(Box.createVerticalStrut(25));
	    databasePanel.add(importTableButton);
	    databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(createNewTableButton);
		databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(editExistentTableButton);
		databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(deleteExistentTableButton);
		databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(deleteDatabaseButton);
		databasePanel.add(Box.createVerticalStrut(15));
		databasePanel.add(dateLabel);

		return databasePanel;

	}

	/**Adds icons to the JButtons of the main menu */
	private Image addButtonIcon(String icon) {
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    URL url = getClass().getResource(icon);
	    return toolkit.getImage(url);
	}

	/**Allows the user to import a Table
	 * {@link database.TransferData#importFile(). */
	private void importTableButtonActionPerformed(ActionEvent e) {
	    super.refresh(sidePanel);
	    String importFile = JOptionPane.showInputDialog("Import an Excel file:"
	                    + "\n"
	                    + "*Note: Do NOT add .xls extension");
	    if (Standards.isNameValid(importFile)) {
	        TransferData transfer = new TransferData(importFile);
	        transfer.importFile();
	    }
	}

	/**
	 * Allows the user to create a new table in the database.
	 * @param e
	 */
	private void createNewTableButtonActionPerformed(ActionEvent e) {
		if (Database.getDatabaseInstance().isThereAnyTable()) {
		    super.refresh(sidePanel);
		}
		String tableName = JOptionPane.showInputDialog(this,
				"Please name the table");
		if (Standards.isNameValid(tableName)) {
			if(Database.getDatabaseInstance()
			    .checkForDuplicateTables(tableName)) {

			    Table table = new Table(tableName);
				Database.getDatabaseInstance().addTable(table);
				new TableMenu(table, tableName);
				setVisible(false);
			}
		}
	}

	/**
	 *
	 * Displays every table of the database and allows the user
	 * to choose the one they would like to edit.
	 * {@link database.DatabaseMenu#editButtonActionPerformed(ActionEvent)}
	 * @param e
	 */
	private void editExistentTableButtonActionPerformed(ActionEvent e) {
		if (Database.getDatabaseInstance().isThereAnyTable()) {
		    super.refresh(sidePanel);
			String message = "Select a table to edit";
			listOfTables = new JComboBox<Object>(
			    Database.getDatabaseInstance()
				.getTables()
				.toArray());

			listOfTables.setBackground(Color.WHITE);
			editButton = new JButton("Edit");
			editButton.addActionListener(this::editButtonActionPerformed);
			add(tableDestiny(message, listOfTables, editButton));
			validate();
		} else {
			JOptionPane.showMessageDialog(null, "There are no tables yet!");
		}
	}

	/**
	 * Invoked when
	 * {@link database.DatabaseMenu#editExistentTableButton} is clicked.
	 * Displays the menu of the chosen table.
	 * @param e
	 */
	private void editButtonActionPerformed(ActionEvent e) {
		super.refresh(sidePanel);
		int listOfTablesIndex = listOfTables.getSelectedIndex();
		Table table = Database.getDatabaseInstance().getTable(listOfTablesIndex);
		new TableMenu(table,listOfTables.getSelectedItem().toString());
		setVisible(false);
	}

	/**
	 * Displays every table of the database and allows the user
	 * to choose the one they would like to delete.
	 * {@link database.DatabaseMenu#deleteButton}
	 * @param e
	 */
	private void deleteExistentTableButtonActionPerformed(ActionEvent e) {
		if (Database.getDatabaseInstance().isThereAnyTable()) {
			super.refresh(sidePanel);
			String message = "Select a table to delete";
			listOfTables = new JComboBox<Object>(Database
							.getDatabaseInstance()
							.getTables()
							.toArray());

			listOfTables.setBackground(Color.WHITE);
			deleteButton = new JButton("Delete");
			deleteButton.addActionListener(this::deleteButtonActionPerformed);
			add(tableDestiny(message, listOfTables, deleteButton));
			validate();
		} else {
			JOptionPane.showMessageDialog(null, "There are no tables yet!");
		}
	}

	/**
	 * Invoked when
	 * {@link database.DatabaseMenu#deleteExistentTableButton} is clicked.
	 * Deletes the chosen table.
	 * @param e
	 */
	private void deleteButtonActionPerformed(ActionEvent e) {
		super.refresh(sidePanel);
		if (Standards.verify()) {
			int index = listOfTables.getSelectedIndex();
			Database.getDatabaseInstance().getTable(index).clearTable();
			Database.getDatabaseInstance().removeTable(index);
			System.out.println("Table was deleted succesfully!");
		}
	}

	/**
	 * Deletes every table from the database. <br>
	 * Note: No table is deleted if there are no tables in the database.
	 * @param e
	 */
	private void deleteDatabaseButtonActionPerformed(ActionEvent e) {
		if (Database.getDatabaseInstance().isThereAnyTable()) {
			super.refresh(sidePanel);
			if (Standards.verify()) {
			    int end = Database.getDatabaseInstance().numberOfTables();
				for (int i = 0; i < end; i++) {
					Database.getDatabaseInstance().getTable(i).clearTable();
				}
				Database.getDatabaseInstance().getTables().clear();
				JOptionPane.showMessageDialog(null,
						"Your database was successfully deleted!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "The database is empty!");
		}
	}

	/**
	 * @param message
	 * @param listOfTables
	 * @param actionButton
	 * @return a JPanel component that includes a list of tables
	 * as well as a button that declares the action to be applied
	 * on the chosen table.
	 */
	private JPanel tableDestiny(
	    String message,
	    JComboBox<Object> listOfTables,
	    JButton actionButton) {

        sidePanel = new JPanel();
        sidePanel.setBackground(Color.WHITE);
	    sidePanel.setLayout(new FlowLayout());
		JLabel label = new JLabel(message);
		sidePanel.add(label);
		sidePanel.add(listOfTables);
		sidePanel.add(actionButton);
		return sidePanel;
	}
}