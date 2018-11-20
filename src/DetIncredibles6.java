import java.util.*;
import java.util.Date;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.io.File;
import javafx.*;

public class DetIncredibles6 {
	static ArrayList<ArrayList<Object>> arr = new ArrayList<>(); 
	//think of it as vector that consists of other vectors that fluctuate dynamically
	
	protected Object inputElements;
	protected int entriesSelected, numberOfFields, i, rows;
	protected String field;
	protected JFrame frame, generalframe, displayFrame;
	protected JButton okbutton;
	protected JRadioButton option1, option2, option3, option4;

	public DetIncredibles6() {

		Date dateobj = new Date();
		JOptionPane.showMessageDialog(null, "Database access was successful\n\n" + dateobj.toString());

		frame = new JFrame("Menu");
		frame.setLayout(null);
		frame.setResizable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel question = new JLabel("Choose what you wish to do:");
		question.setBounds(65, 50, 350, 60);
		frame.add(question);
		
		option1 = new JRadioButton("1) Add fields");
		option1.setBounds(70,100,350,60);
		frame.add(option1);

		option2 = new JRadioButton("2) Insert new entry");
		option2.setBounds(70, 150, 350, 60);
		frame.add(option2);

		option3 = new JRadioButton("3) Edit an entry you have inserted");
		option3.setBounds(70, 200, 350, 60);
		frame.add(option3);

		option4 = new JRadioButton("4) Show my database");
		option4.setBounds(70,250,350,60);
		frame.add(option4);

		okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener() { // start of inner class
			@Override
			public void actionPerformed(ActionEvent e) {
				if(option1.isSelected()) {
					addFields();
				}
				if (option2.isSelected()) { // if option1 is selected, the rest happen:
					if(arr.size() > 0) { //if there are fields
						arr.add(new ArrayList<Object>()); // adding another row
						rows++;
						entriesSelected++; // counts how many times this option (to add an entry) was selected
						newEntry(arr);// check below
						setArray(arr); // set the new array according to the changes done by newEntry() method
						frame.setVisible(true); // frame appears again
					} else {
						JOptionPane.showMessageDialog(null, "Name the fields first");
					}
				}
				if (option3.isSelected()) {
					editEntry();// check below
				}
				if (option4.isSelected()) {
					showResults(arr);//check below
				}
			}
		}); // end of inner-class

		okbutton.setBounds(150, 350, 150, 30);
		frame.add(okbutton);

		// ensuring that only one option can be selected at a time
		ButtonGroup bg = new ButtonGroup();
		bg.add(option1);
		bg.add(option2);
		bg.add(option3);
		bg.add(option4);
		option1.setSelected(true); // option1 is selected by default
		//

	}
	
	public ArrayList<ArrayList<Object>> addFields() {
		if (arr.size() == 0) {
			JOptionPane.showConfirmDialog(null,
					"Please name the fields you intend to add (eg. 'name','address','phone' etc)\n", "Title",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE);
			arr.add(new ArrayList<Object>()); // adding a row where the fields will be named
		}
		// preparation of the TextField where the user can name his fields
		Object[] options1 = { "ADD" };
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter a field"));
		JTextField textField = new JTextField(10);
		panel.add(textField);
		// end of TextField preparation

		// checking when the user wants to stop adding fields
		for (;;) {
			int choice = JOptionPane.showOptionDialog(null, panel, "Name your fields", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options1, null);
			if (choice == JOptionPane.YES_OPTION) {
				if (textField.getText() != null && !textField.getText().trim().isEmpty()) { // checking if the field
																							// is empty or has only
																							// spaces
					arr.get(0).add(textField.getText()); // adding the text
					textField.setText(null); // reset of textfield
				} else {
					textField.setText(null); // reset of textfield
					JOptionPane.showMessageDialog(null, "The field cannot be empty");
				}
			}
			if (choice == JOptionPane.CLOSED_OPTION) { // if the choice is QUIT , break!
				textField.setText(null); // reset of textfield
				choice = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					if (arr.get(0).size() > 0) {
						break;
					} else {
						JOptionPane.showMessageDialog(null, "You need to name at least one field");
					}
				}
			}
		} // end of check

		JOptionPane.showConfirmDialog(null, arr.get(0).toString(), "These are your fields!",
				JOptionPane.PLAIN_MESSAGE);
		return arr;
		
	}

	public ArrayList<ArrayList<Object>> newEntry(ArrayList<ArrayList<Object>> arr) {
		frame.setVisible(false); // hiding the frame temporarily

		if(arr.get(0).size() > 0) {
			for (i = 0; i < arr.get(0).size(); i++) { // arr.get(0).size() is the number of fields inserted previously
				for(;;) {
				inputElements = JOptionPane.showInputDialog(null, "Insert here:", "Add " + arr.get(0).get(i),
						JOptionPane.OK_CANCEL_OPTION);
					if ((inputElements!= null) && (!( inputElements.toString().isEmpty()))) {
						arr.get(rows).add(inputElements);
						break;
					} else {
						JOptionPane.showMessageDialog(null, "It can't be empty!");
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "You need fields first");
		}
		return arr;
	}// end of newEntry() method

	public ArrayList<ArrayList<Object>> editEntry() {
		if (entriesSelected > 0) { // editing does not make sense if there are no entries!
			frame.setVisible(false); // hiding the first frame
			// creating a second frame (while the first one is of course hidden)
			JFrame frame2 = new JFrame();
			frame2.setSize(500, 500);
			frame2.setLocation(650, 300);
			frame2.setLayout(null);
			frame2.setVisible(true);

			JComboBox<Object> combo = new JComboBox<Object>(arr.subList(1, arr.size()).toArray()); 
			combo.setBounds(50, 50, 220, 20);
			frame2.add(combo);
			combo.setEditable(false);
			combo.getEditor().getEditorComponent().setBackground(Color.lightGray); // changing the color of the combo
																					// box to lightGray
			combo.setMaximumRowCount(10); // 10 rows will be displayed at first - scroll for the rest

			JLabel text = new JLabel("Select which entry you wish to edit:");
			text.setBounds(45, 30, 300, 20);
			frame2.add(text); // question added to frame2

			// this will be used inside the JComboBox ActionListener
			JFrame frame3 = new JFrame();
			frame3.setSize(500, 500);
			// frame3.setLocation(1150,300);
			frame3.setLocationRelativeTo(frame2);
			frame3.setLayout(null);
			frame3.setVisible(false); // it will be visible as soon as it is needed

			JComboBox<Object> combo2 = new JComboBox<Object>(); // it will be used in frame3
			combo2.setBounds(50, 50, 180, 20);
			combo2.setEditable(false);
			combo2.setMaximumRowCount(10); // 10 rows will be displayed at first - scroll for the rest

			JLabel text2 = new JLabel("Select which element you wish to edit");
			text2.setBounds(45, 30, 300, 20);
			frame3.add(text2);

			JButton generalbutton = new JButton("Edit this field");
			generalbutton.addActionListener(new ActionListener() { // inner class in order to edit
				public void actionPerformed(ActionEvent e) {
					if(arr.size() > 1) { //arr.size() is at least 1 because of the fields. In order to edit something, though, the size has to be > 1
					int chosenItem = combo.getSelectedIndex() + 1; // chosenItem > 0
					combo2.setModel(new DefaultComboBoxModel<Object>(arr.get(chosenItem).toArray()));
					//the user chose to edit a certain entry. Now he can choose a certain element of the entryy
					
					frame3.add(combo2);
					frame3.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "There is nothing to actually edit!");
					}
				}
			});// end of inner class
			generalbutton.setBounds(280, 150, 150, 20);
			frame2.add(generalbutton);

			JButton deletebutton = new JButton("Delete this field");
			deletebutton.addActionListener(new ActionListener() { // inner class in order to delete
				public void actionPerformed(ActionEvent e) {
					if(arr.size() > 1) { //arr.size() is at least 1 because of the fields.
							     //In order to delete something, the size has to be greater than 1. 
					arr.remove(combo.getSelectedIndex() + 1); // removes the selected item from the array
					combo.removeItemAt(combo.getSelectedIndex()); // removes the deleted entry immediately from the
										      // ComboBox as well
					rows--;
					} else {
						JOptionPane.showMessageDialog(null, "There is nothing to actually delete!");
					}
				}
			});
			deletebutton.setBounds(280, 200, 150, 20);
			frame2.add(deletebutton);

			JButton menubutton = new JButton("Back to Menu");
			menubutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame3.setVisible(false);
					frame2.setVisible(false);
					frame.setVisible(true);
				}
			});
			menubutton.setBounds(280, 250, 150, 20);
			frame2.add(menubutton);

			JButton editbutton2 = new JButton("Edit this field");
			editbutton2.addActionListener(new ActionListener() { // inner class in order to edit
				public void actionPerformed(ActionEvent e) {
					inputElements = JOptionPane.showInputDialog(frame3, "Edit this element:");
					arr.get(combo.getSelectedIndex() + 1).set(combo2.getSelectedIndex(), inputElements);
					//replacing the selected element with the new one

					combo2.setModel(new DefaultComboBoxModel<Object>(arr.get(combo.getSelectedIndex() + 1).toArray())); //immediately updating combo2
					//combo has to be updated after combo2 is closed so as not to lose its selectedIndex! ***Significant***
					combo.setModel(new DefaultComboBoxModel<Object>(arr.subList(1,arr.size()).toArray())); //immediately updating combo
				}
			}); // end of inner class of frame3 editbutton2
			editbutton2.setBounds(280, 150, 150, 20);
			frame3.add(editbutton2);
		
		} else {
			JOptionPane.showMessageDialog(null, "There are no entries yet");

		}
		return arr;
	}
	
	public void showResults(ArrayList<ArrayList<Object>> arr) {
		// create a JList
		if(arr.size() > 0) {
			JFrame displayFrame = new JFrame("This is your database");
			displayFrame.setSize(300,200);
			displayFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
			displayFrame.setLocationRelativeTo(frame); //frame is the menu
			displayFrame.setVisible(true);
			
			JList<Object> jlist = new JList<Object>(arr.toArray());
			JScrollPane scr = new JScrollPane(jlist); //adding scrollPane to add scroll bars
			scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

			displayFrame.getContentPane().add(scr, BorderLayout.CENTER);
		} else {
			JOptionPane.showMessageDialog(null, "Your database is empty!");
		}
	}

	public static void setArray(ArrayList<ArrayList<Object>> arr) {
		DetIncredibles6.arr = arr;
	}

	public static ArrayList<ArrayList<Object>> getArray() {
		return arr;
	}
	
	public static void main(String[] args) {
		new DetIncredibles6();
	}
}
