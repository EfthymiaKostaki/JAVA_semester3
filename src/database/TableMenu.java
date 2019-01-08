package database;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
* Prints the menu.
* When user makes a choice then specific tasks are being carried out.
* These tasks refer to adding,editing or displaying fields,entries.
*@author DetIncredibles6
*@version 1.1
*/
public class TableMenu extends Menu {

	/** Data of a potentially new row that will be added
	 * in case it is valid. <br>
	 *<b>See</b>: {@link database.TableMenu#requestNewEntryData()} <br>
	 *<b>See</b>: {@link database.Entry#Entry(ArrayList)} */
	private ArrayList<Object> entryArguments;

	/**Contains all the names of this table's columns */
	private JComboBox<Object> listOfFields;

	/**Contains all the rows of this table */
	private JComboBox<Object> listOfEntries;

	private ButtonGroup groupOfButtons;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon icon;
	private JButton okButton, goBackButton;
	private JButton exportButton;
	private JLabel searchText;
	private JRadioButton optionAddField;
	private JRadioButton optionEditField;
	private JRadioButton optionAddEntry;
	private JRadioButton optionEditEntry;
	private JRadioButton optionDeleteEntry;
	private JRadioButton optionDisplay;
	private JPanel menuPanel;
	private JPanel proceduresPanel;
    private JTextField searchBar;
    private Search explorer;
    private String givenFieldName;
    private Table table;
    private URL iconURL;

	public TableMenu(Table table, String tableName) {
		super(tableName);
		this.table = table;
		super.initializeGUI();
		setLayout(new FlowLayout(FlowLayout.LEFT,
							screenSize.width/12,
							screenSize.height/15));
		add(createMenu());
		setVisible(true);
	}

    /**Prints menu on the screen.*/
    private JPanel createMenu() {
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setBackground(Color.WHITE);

		iconURL = getClass().getResource("searchIcon.png");
		icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(iconURL));
		searchText = new JLabel("Search");
		searchText.setIcon(icon);
		searchText.setHorizontalTextPosition(JLabel.LEFT);
		searchBar = new JTextField(20);
		searchBar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent pressed_key) {
		        if (pressed_key.getKeyCode() == KeyEvent.VK_ENTER) {
		            refresh(proceduresPanel);
		            if (Standards.isNameValid(searchBar.getText())) {
		                explorer = new Search(table, searchBar.getText());
		                explorer.searchDatabase();
		            }
		            searchBar.setText(null);
		        }
		    }
		});
		searchBar.setToolTipText("Search in your database");

		optionAddField = new JRadioButton("Add a field", true);
		optionAddField.setOpaque(false);
		optionEditField = new JRadioButton("Edit a field", false);
		optionEditField.setOpaque(false);
		optionAddEntry = new JRadioButton("Add an entry", false);
		optionAddEntry.setOpaque(false);
		optionEditEntry = new JRadioButton("Edit an entry", false);
		optionEditEntry.setOpaque(false);
		optionDeleteEntry = new JRadioButton("Delete an entry", false);
		optionDeleteEntry.setOpaque(false);
		optionDisplay = new JRadioButton("Display this table", false);
		optionDisplay.setOpaque(false);

		groupOfButtons = new ButtonGroup();
		groupOfButtons.add(optionAddField);
		groupOfButtons.add(optionEditField);
		groupOfButtons.add(optionAddEntry);
		groupOfButtons.add(optionEditEntry);
		groupOfButtons.add(optionDeleteEntry);
		groupOfButtons.add(optionDisplay);

		okButton = new JButton("OK");
		okButton.addActionListener(this::
			okButtonActionPerformed);


		exportButton = new JButton("Export table");
		exportButton.addActionListener(this::
			exportButtonActionPerformed);

		goBackButton = new JButton("Back to Database Menu");
		goBackButton.addActionListener(this::
			goBackButtonActionPerformed);

		menuPanel.add(searchText);
		menuPanel.add(searchBar);
		menuPanel.add(Box.createVerticalStrut(20));
		menuPanel.add(optionAddField);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionEditField);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionAddEntry);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionEditEntry);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionDeleteEntry);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(optionDisplay);
		menuPanel.add(Box.createVerticalStrut(30));
		menuPanel.add(okButton);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(exportButton);
		menuPanel.add(Box.createVerticalStrut(15));
		menuPanel.add(goBackButton);

		return menuPanel;
	}

    /**Invoked when the user clicks the "OK" button.<br>
     *<b>See:</b> {@link database.TableMenu#decider()}
     * @param e
     */
    private void okButtonActionPerformed(ActionEvent e) {
	    super.refresh(proceduresPanel);
	    decider();
	}

    /**Invoked when the users clicks the "Export Table" button.*/
    private void exportButtonActionPerformed(ActionEvent e) {
        //there is no reason to export an empty table.
    	if(table.isThereAnyField()) {
    		JOptionPane.showMessageDialog(null, "The table was exported!");
        	TransferData transfer = new TransferData(table.toString());
        	transfer.exportFile(table);
        } else {
        	JOptionPane.showMessageDialog(
        		    null, "You cannot export an empty table!");
        }
	}

    /**Takes the user back to the main menu.*/
    private void goBackButtonActionPerformed(ActionEvent e) {
		setVisible(false);
		DatabaseMenu.getDatabaseMenuInstance().setVisible(true);
    }

    /** Decides what needs to be done based on the user's selection.*/
	private void decider() {
		if (optionAddField.isSelected()) {
			addField();
		} else if (optionEditField.isSelected()) {
		    if (table.numberOfFields() > 0) {
		        editField();
		    } else {
		        JOptionPane.showMessageDialog(null, "There are no fields!");
		    }
		} else if (optionAddEntry.isSelected()) {
			addEntry();
		} else if (optionEditEntry.isSelected()) {
		    if (table.numberOfEntries() > 0) {
		        editEntry();
		    } else {
		        JOptionPane.showMessageDialog(null, "There are no entries!");
		    }
		} else if (optionDeleteEntry.isSelected()) {
			if (table.numberOfEntries() > 0) {
				deleteEntry();
			} else {
				JOptionPane.showMessageDialog(null, "There are no entries yet!");
			}
		} else if (optionDisplay.isSelected()) {
			if (table.numberOfFields() > 0 || table.numberOfEntries() > 0) {
			    displayTable();
			} else {
			    JOptionPane.showMessageDialog(null, "The table is empty!");
			}
		}
	}

	/**Adds a Field (column) to the database */
	private void addField() {
		givenFieldName = JOptionPane.showInputDialog("Name the field");
		if (Standards.isNameValid(givenFieldName)) {
			if (Field.checkForDuplicate(table, givenFieldName)) {
				table.addField(new Field(givenFieldName));
				JOptionPane.showMessageDialog(this,
				    "The field was successfully added!");
			}
			table.checkForUnfilledElements(table, givenFieldName);
		}
	}

	/**Allows the user to rename or delete a Field (column) of the database.
	 * {@link database.TableMenu#editButtonActionPerformed(ActionEvent)}
	 * {@link database.TableMenu#deleteButtonActionPerformed(ActionEvent)}*/
	private void editField() {
	    proceduresPanel = new JPanel();
	    listOfFields = new JComboBox<Object>(table.getFields().toArray());
	    listOfFields.setMaximumRowCount(10);
	    JButton editButton = new JButton("Edit");
	    JButton deleteButton = new JButton("Delete column");
	    editButton.addActionListener(this::
	        editButtonActionPerformed);
	    deleteButton.addActionListener(this::
	        deleteButtonActionPerformed);
	    proceduresPanel.setLayout(new FlowLayout());
	    proceduresPanel.add(new JLabel("Select a field:"));
	    proceduresPanel.add(listOfFields);
	    proceduresPanel.add(editButton);
	    proceduresPanel.add(deleteButton);
	    add(proceduresPanel);
	    validate();
	}

	private void editButtonActionPerformed(ActionEvent e) {
	    super.refresh(proceduresPanel);
        String renamedField = JOptionPane.showInputDialog(
                        null,
                        "Rename the field",
                        listOfFields.getSelectedItem().toString());
        if (Standards.isNameValid(renamedField)) {
           table
           .getField(listOfFields.getSelectedIndex())
           .setFieldName(renamedField);
           JOptionPane.showMessageDialog(null, "Rename completed!");
        }
	}

	private void deleteButtonActionPerformed(ActionEvent e) {
	    super.refresh(proceduresPanel);
	    int index = listOfFields.getSelectedIndex();
	    if (Standards.verify()) {
	    	table.getFields().remove(index);
	    	for (int i = 0; i < table.numberOfEntries(); i++) {
	    		table.getEntry(i).getEntryArguments().remove(index);
	    	}
	    	JOptionPane.showMessageDialog(null, "The column was deleted!");
	    }
	}

	/**Adds a row to the current table. </br>
	 *<b>See:</b> {@link database.TableMenu#requestNewEntryData()} */
	private void addEntry() {
	    super.refresh(proceduresPanel);
		if (table.isThereAnyField()) {
			entryArguments = requestNewEntryData();
			if (Entry.checkIfSameEntries(entryArguments, table) == false) {
				table.addEntry(new Entry(entryArguments));
				JOptionPane.showMessageDialog(null, "The entry was added!");
			} else {
				JOptionPane.showMessageDialog(null,
					"The exact same entry already exists.\n"
					+ "Please insert a different row!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "There are no fields yet!");
		}
	}

	/**Request data from the user create a new row.*/
	public ArrayList<Object> requestNewEntryData() {
	    ArrayList<Object> entryArguments = new ArrayList<Object>();
	    for (int i = 0; i < table.getFields().size(); i++) {
	        Object givenElement = JOptionPane.showInputDialog(null,
	                        "Add " + table.getField(i));

	        if (Standards.isNameValid(givenElement)) {
	            entryArguments.add(givenElement);
	        } else {
	            entryArguments.add("NULL");
	        }
	    }
	    return entryArguments;
	}

	/**Edits the entries that have been inserted.*/
	private void editEntry() {
	    super.refresh(proceduresPanel);
	    proceduresPanel = new JPanel();
	    proceduresPanel.setLayout(new FlowLayout());

	    listOfEntries = new JComboBox<Object>(table.getEntries().toArray());
	    listOfEntries.setMaximumRowCount(10);
	    listOfFields = new JComboBox<Object>(table.getFields().toArray());
	    listOfFields.setMaximumRowCount(10);

	    JButton actionButton = new JButton("Edit");
		actionButton.addActionListener(new ActionListener() {
		    @Override
			public void actionPerformed(ActionEvent e) {
		        int entryIndex = listOfEntries.getSelectedIndex();
		        int fieldIndex = listOfFields.getSelectedIndex();

		        Object initialValue = table
		        		.getEntry(entryIndex)
		        		.getEntryArgument(fieldIndex);

		        String renamedElement =
						JOptionPane.showInputDialog(null,
								"Rename", initialValue);

		        refresh(proceduresPanel);
				if (Standards.isNameValid(renamedElement)) {
					table.getEntry(entryIndex)
					.setEntryArgument(fieldIndex, renamedElement);
					JOptionPane.showMessageDialog(null, "Rename completed!");
				}
			}
		});
		proceduresPanel.add(new JLabel("Please select an entry to edit:"));
		proceduresPanel.add(Box.createHorizontalStrut(15));
		proceduresPanel.add(listOfEntries);
		proceduresPanel.add(Box.createHorizontalStrut(15));
		proceduresPanel.add(new JLabel("Select a field too:"));
		proceduresPanel.add(Box.createHorizontalStrut(15));
		proceduresPanel.add(listOfFields);
		proceduresPanel.add(Box.createHorizontalStrut(15));
		proceduresPanel.add(actionButton);
		add(proceduresPanel);
		validate();
	}

	/**Allows the user to delete an entire entry (row)*/
	private void deleteEntry() {
		super.refresh(proceduresPanel);
		proceduresPanel = new JPanel();
		listOfEntries = new JComboBox<Object>(table.getEntries().toArray());
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh(proceduresPanel);
				if (Standards.verify()) {
					table.getEntries().remove(listOfEntries.getSelectedIndex());
					JOptionPane.showMessageDialog(
							null, "The entry has been removed successfully!");
				}
			}
		});
		proceduresPanel.add(new JLabel("Select an entry to delete"));
		proceduresPanel.add(listOfEntries);
		proceduresPanel.add(deleteButton);
		add(proceduresPanel);
		validate();
	}

	/**Displays the current table. */
	private void displayTable() {
		super.refresh(proceduresPanel);
	    proceduresPanel = new JPanel();
	    Object[][] data = new Object[table.numberOfEntries()][table.numberOfFields()];
	    String[] columns = new String[table.getFields().size()];
	    for (int i = 0; i < table.getEntries().size(); i++) {
	        for (int j = 0; j < table.getFields().size(); j++) {
	            data[i][j] = table.getEntry(i).getEntryArgument(j);
	        }
	    }
	    for (int j = 0; j < columns.length; j++) {
	        columns[j] = table.getField(j).toString();
	    }
	    JTable displayTable = new JTable(data, columns) {
	        @Override
	        public boolean isCellEditable(int row, int col) {
	            return false;
	        }
	    };
	    JScrollPane scr = new JScrollPane(displayTable);
	    scr.setHorizontalScrollBarPolicy(
	    		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scr.setVerticalScrollBarPolicy(
	    		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    proceduresPanel.add(scr);
	    add(proceduresPanel);
	    validate();
	}


	@Override
    public void setTable(Table table) {
		this.table = table;
	}

}
