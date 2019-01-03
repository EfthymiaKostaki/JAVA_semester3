package database;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
* Contains methods to print menu and get information on users decisions.
* When user makes a choice then certain specific tasks are being carried out.
* These tasks refer to add fields, show fields, add entries, show entries to database or exit.
*
*@author DetIncredibles6
*@version 1.0
*@since jdk1.8.0
*/

public class TableMenu extends JFrame {
	

	private ButtonGroup groupOfButtons;
	private JButton okButton, goBackButton;
	private JRadioButton optionAddField, 
	                     optionEditField, 
	                     optionAddEntry, 
	                     optionEditEntry, 
	                     optionDisplay;
	private Table table;


	private JPanel menuPanel;
	private String givenFieldName;
	private ArrayList<Object> entryArguments;
	/**
	*Construstor 
	*@param table an object of Table 
	*@param tableName a certain String
	*/
	public TableMenu(Table table,String tableName) {
		super(tableName);
		this.table = table;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setLayout(new FlowLayout(FlowLayout.LEFT, (DatabaseMenu.getScreenSize().width/12),
				(DatabaseMenu.getScreenSize().height/15)));
		setLocationRelativeTo(null);
		add(createMenu());
		setVisible(true);
	}
		
	/**
	*Method that prints out menu on screen.
	*/
	private JPanel createMenu() {
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		
		optionAddField = new JRadioButton("Add a field", true);
		optionEditField = new JRadioButton("Edit a field", false);
		optionAddEntry = new JRadioButton("Add an entry", false);
		optionEditEntry = new JRadioButton("Edit an entry", false);
		optionDisplay = new JRadioButton("Display this table's content", false);
		
		groupOfButtons = new ButtonGroup();
		groupOfButtons.add(optionAddField);
		groupOfButtons.add(optionEditField);
		groupOfButtons.add(optionAddEntry);
		groupOfButtons.add(optionEditEntry);
		groupOfButtons.add(optionDisplay);
		
		menuPanel.add(optionAddField);
		menuPanel.add(Box.createVerticalStrut(15)); //adding vertical trailing spaces
		menuPanel.add(optionEditField);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionAddEntry);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionEditEntry);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionDisplay);
		menuPanel.add(Box.createVerticalStrut(30));
		
		
		okButton = new JButton("OK");
		okButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decider();
			}
		});
		menuPanel.add(okButton);
		menuPanel.add(Box.createVerticalStrut(15));
		
		goBackButton = new JButton("Back to Database Menu");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				DatabaseMenu.getDatabaseMenuInstance().setVisible(true);
			}
		});
		menuPanel.add(goBackButton);
		
		return menuPanel;
	}
	
	//einai lathos h methodos (den exei nohma na thn kalesw diaforetikes fores
	//krataw thn logikh
    private void followUpAction(String message,
    							String action,
								JComboBox<Object> combo) {
    	
		JPanel proceduresPanel = new JPanel();
		proceduresPanel.setLayout(new FlowLayout());
		JLabel label = new JLabel(message);
		JButton actionButton = new JButton(action);
		actionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				givenFieldName = JOptionPane
						.showInputDialog(null, "Rename the chosen item:", null); // the 3rd arguement sets the default text!!!
				remove(proceduresPanel);
				proceduresPanel.revalidate();
				validate();
				repaint();
				if (Standards.isNameValid(givenFieldName)) { //giving a new name and checking if it is valid simultaneously
					table.getField(combo.getSelectedIndex()).setFieldName(givenFieldName);
				} 
			}
		});
		proceduresPanel.add(label);
		proceduresPanel.add(Box.createVerticalStrut(15));
		proceduresPanel.add(combo);
		proceduresPanel.add(Box.createVerticalStrut(15));
		proceduresPanel.add(actionButton);
		add(proceduresPanel);
		validate();
	}
	/**
	*Contains the activities to be done based on user's decision. Also made with graphics.
	*/
	private void decider() {
		if (optionAddField.isSelected()) {
			givenFieldName = JOptionPane.showInputDialog("Name the field");
			if (Standards.isNameValid(givenFieldName)) {
				if (Field.checkForPossibleDuplicate(table, givenFieldName)) {					
					table.addField(new Field(givenFieldName));
				}
			}
		} else if (optionEditField.isSelected()) {
			JComboBox<Object> fieldsCombo = new JComboBox<Object>();
			fieldsCombo.setModel(
					new DefaultComboBoxModel<Object>(table.getFields().toArray()));
			fieldsCombo.setMaximumRowCount(7);
			followUpAction("Select a field to edit: ", "Edit", fieldsCombo);
		} else if (optionAddEntry.isSelected()) {
			if (table.isThereAnyField()) {
				entryArguments = requestNewEntryData();
				if (Entry.checkIfSameEntries(entryArguments, table) == false) {
					table.addEntry(new Entry(entryArguments)); //contrusts an Entry object and stores it to an ArrayList<Entry>
				} else {
					JOptionPane.showMessageDialog(null,
							"The exact same entry already exists."
							+ "Please insert a different row!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "There are no fields yet!");
			}
		} else if (optionEditEntry.isSelected()) {
			editEntry();
			
		} else if (optionDisplay.isSelected()) {
			System.out.println(table.getEntries());
		}
	}
	/** 
	*Edits the entries that have been made.
	*/
	private void editEntry() {
		//TODO (Paris, Anna - Maria , Effie)
	}

	/**
	*Gets new Entries and places them into the ArrayList
	*/
	public ArrayList<Object> requestNewEntryData() {
			ArrayList<Object> entryArguments = new ArrayList<Object>();				
			for (int i = 0; i < table.getFields().size(); i++) {
				for (;;) {
					Object givenElement = JOptionPane.showInputDialog(null,
							"Add " + table.getField(i));
					if (Standards.isNameValid(givenElement)) {
						entryArguments.add(givenElement);
						break;
					}
				}
			}
			return entryArguments;
	} 
	/**
	*Getter for Table object
	@return table
	*/
	public Table getTable() {
		return table;
	}
	/**
	*Setter for table object
	*@param table
	*/
	public void setTable(Table table) {
		this.table = table;
	}
	

}
