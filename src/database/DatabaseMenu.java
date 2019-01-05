package database;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Creates the menu of the database.
 * Actions such as creating,editing or deleting a table
 * are managed by this window.
 * @author Theodosis Tsaklanos
 *
 */
public class DatabaseMenu extends Menu {

	//Singleton Pattern to ensure that only one object is created
	private static final DatabaseMenu databaseMenu =
					new DatabaseMenu("Database Main Menu");
	private final Dimension idealButtonSize = new Dimension(140, 25);
	private	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JButton createNewTableButton;
	private JButton editExistentTableButton;
	private JButton editButton;
	private JButton deleteExistentTableButton;
	private JButton deleteButton;
	private JButton deleteDatabaseButton;
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

		createNewTableButton = new JButton("Create a new table");
		createNewTableButton.setMaximumSize(idealButtonSize);
		createNewTableButton.addActionListener(this::
			createNewTableButtonActionPerformed);

		editExistentTableButton = new JButton("Edit a table");
		editExistentTableButton.setMaximumSize(idealButtonSize);
		editExistentTableButton.addActionListener(this::
			editExistentTableButtonActionPerformed);

		deleteExistentTableButton = new JButton("Delete a table");
		deleteExistentTableButton.addActionListener(this::
			deleteExistentTableButtonActionPerformed);
		deleteExistentTableButton.setMaximumSize(idealButtonSize);

		deleteDatabaseButton = new JButton("Delete database");
		deleteDatabaseButton.setMaximumSize(idealButtonSize);
		deleteDatabaseButton.addActionListener(this::
			deleteDatabaseButtonActionPerformed);

		databasePanel.add(welcomeLabel);
		databasePanel.add(Box.createVerticalStrut(25));
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
			if(Database.getDatabaseInstance().checkForDuplicateTables(tableName)) {
				Database.getDatabaseInstance().addTable(new Table(tableName));
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
			listOfTables = new JComboBox<Object>(Database
							.getDatabaseInstance()
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
		int index = listOfTables.getSelectedIndex();
		new TableMenu(Database.getDatabaseInstance().getTable(index),
						listOfTables.getSelectedItem().toString());
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
		int index = listOfTables.getSelectedIndex();
		Database.getDatabaseInstance().getTable(index).clearTable();
		Database.getDatabaseInstance().removeTable(index);
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
				for (int i = 0; i < Database.getDatabaseInstance().numberOfTables(); i++) {
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
	 *
	 * @param message
	 * @param listOfTables
	 * @param actionButton
	 * @return a JPanel component that includes a list of tables
	 * as well as a button that declares the action to be applied
	 * on the chosen table.
	 */
	private JPanel tableDestiny(String message, JComboBox<Object> listOfTables, JButton actionButton) {
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