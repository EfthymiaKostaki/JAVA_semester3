import java.util.*;
import java.util.Date; //για να μην ερχεται σε συγκρουση με java.sql.Date
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color; //να βάλουμε λιγο χρωμα στο JComboBox (οταν κανουμε τα edits - επιλογη 2
import java.io.File;
import java.sql.*;

public class DetIncredibles6 {
	ArrayList<ArrayList<Object>> arr = new ArrayList<>(); // σκεφτειτε το ως ενα διανυσμα που αποτελειται απο αλλα
															// διανυσματα , οπου ολα αυξομειωνονται δυναμικα...

	protected Object inputElements;
	protected int entriesSelected, numberOfFields, i, rows;
	protected String field;
	protected JFrame frame, generalframe, displayFrame;
	protected JButton okbutton;
	protected JRadioButton option1, option2, option3;

	public DetIncredibles6() {

		Date dateobj = new Date();
		frame = new JFrame("Menu");
		frame.pack();
		frame.setLocation(750, 300); // frame set on center
		frame.setVisible(false); // temporary
		frame.setLayout(null);
		frame.setResizable(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel question = new JLabel("Choose what you wish to do:");
		question.setBounds(65, 50, 350, 60);
		frame.add(question);

		option1 = new JRadioButton("1) Insert new entry");
		option1.setBounds(70, 100, 350, 60);
		frame.add(option1);

		option2 = new JRadioButton("2) Edit an entry you have inserted");
		option2.setBounds(70, 150, 350, 60);
		frame.add(option2);

		JLabel readme = new JLabel("*More to follow*");
		readme.setBounds(70, 250, 350, 60);
		frame.add(readme);

		okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener() { // start of inner class
			@Override
			public void actionPerformed(ActionEvent e) {
				if (option1.isSelected()) { // if option1 is selected, the rest happen:
					arr.add(new ArrayList<Object>()); // adding another row
					rows++;
					entriesSelected++; // counts how many times this option (to add an entry) was selected
					newEntry(arr);// check below
					setArray(arr); // set the new array according to the changes done by newEntry() method
					frame.setVisible(true); // frame appears again
				}
				if (option2.isSelected()) {
					editEntry();// check below
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
		option1.setSelected(true); // option1 is selected by default
		//

		JOptionPane.showMessageDialog(null, "Database access was successful\n\n" + dateobj.toString());
		frame.setVisible(true);
	}

	public ArrayList<ArrayList<Object>> newEntry(ArrayList<ArrayList<Object>> arr) {
		frame.setVisible(false); // hiding the frame temporarily

		if (entriesSelected == 1) { // if it is the firt time , then you have to name the fields before inserting an
									// actual entry aka "dwse titlo se katheti"

			JOptionPane.showConfirmDialog(null,
					"Please name the fields you intend to add (eg. 'name','address','phone' etc)\n", "Title",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE);

			arr.add(new ArrayList<Object>()); // adding a row where the fields will be named

			// preparation of the TextField where the user can name his fields
			Object[] options1 = { "ADD", "Quit" };
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
				if (choice == JOptionPane.NO_OPTION) { // if the choice is QUIT , break!
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
			JOptionPane.showMessageDialog(null, "Now, insert the first entry");
		} // endif (entriesSelected==1)

		for (i = 0; i < arr.get(0).size(); i++) { // arr.get(0).size() is the number of fields inserted previously
			inputElements = JOptionPane.showInputDialog(null, "Insert here:", "Add " + arr.get(0).get(i),
					JOptionPane.OK_CANCEL_OPTION);
			arr.get(rows).add(inputElements);
		}

		int option = JOptionPane.showConfirmDialog(null,
				"Do you want to see how your databse looks like?\n" + "(check the console if you select yes)");
		/*
		 * if (option == 0) { for ( i = 0 ; i < arr.size() ; i++) {
		 * System.out.println(arr.get(i)+"\n"); } }
		 */
		if (option == 0) {
			// create a JList
			JList<Object> jlist = new JList<Object>(arr.toArray());
			jlist.setBounds(100, 100, 75, 75); // να βαλω οριζοντιες και καθετες scroll bars)
			jlist.setSize(200, 200);
			JFrame displayFrame = new JFrame("This is your database");
			displayFrame.setSize(800, 800);
			displayFrame.setLocation(650, 300);
			displayFrame.setLayout(null);
			displayFrame.setVisible(true);
			displayFrame.add(jlist);
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

			JComboBox<Object> combo = new JComboBox<Object>(arr.subList(1, arr.size()).toArray()); // προσθέτω τα πεδία
																									// (fields) σε
																									// ComboBox || εάν
																									// δεν μπει
																									// .toArray() τοτε
																									// εμφανιζονται ολα
																									// μαζι στην ιδια
																									// γραμμη ||
																									// Δεν
																									// συμπεριλαμβανεται
																									// η πρωτη λιστα
																									// γιατι εχει τα
																									// πεδια
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
				@Override
				public void actionPerformed(ActionEvent e) {
					int chosenItem = combo.getSelectedIndex() + 1; // chosenItem > 0
					combo2.setModel(new DefaultComboBoxModel<Object>(arr.get(chosenItem).toArray())); // the user chose
																										// to edit a
																										// certain
																										// entry.Now he
																										// can chose a
																										// certain
																										// element of
																										// this entry
					frame3.add(combo2);
					frame3.setVisible(true);
				}
			});// end of inner class
			generalbutton.setBounds(280, 150, 150, 20);
			frame2.add(generalbutton);

			JButton deletebutton = new JButton("Delete this field");
			deletebutton.addActionListener(new ActionListener() { // inner class in order to delete
				@Override
				public void actionPerformed(ActionEvent e) {
					arr.remove(combo.getSelectedIndex() + 1); // removes the selected item from the array
					combo.removeItemAt(combo.getSelectedIndex()); // removes the deleted entry immediately from the
																	// ComboBox as well
					
					System.out.println(arr);
				}
			});
			deletebutton.setBounds(280, 200, 150, 20);
			frame2.add(deletebutton);

			JButton menubutton = new JButton("Back to Menu");
			menubutton.addActionListener(new ActionListener() {
				@Override
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
				@Override
				public void actionPerformed(ActionEvent e) {
					inputElements = JOptionPane.showInputDialog(frame3, "Edit this element:");
					arr.get(combo.getSelectedIndex() + 1).set(combo2.getSelectedIndex(), inputElements); // replacing
																											// the
																											// selected
																											// element
																											// with the
																											// new one
					combo.setModel(new DefaultComboBoxModel<Object>(arr.subList(1,arr.size()).toArray())); //immediately updating combo
					combo2.setModel(new DefaultComboBoxModel<Object>(arr.get(combo.getSelectedIndex() + 1).toArray())); //immediately updating combo2
					System.out.println(arr);

				}
			}); // end of inner class of frame3 editbutton2
			editbutton2.setBounds(280, 150, 150, 20);
			frame3.add(editbutton2);

		} else {
			JOptionPane.showMessageDialog(null, "There are no entries yet");

		}
		return arr;

	}

	public void setArray(ArrayList<ArrayList<Object>> arr) {
		this.arr = arr;
	}

	public ArrayList<ArrayList<Object>> getArray() {
		return arr;
	}

	/*
	 * public static String calculateMemory() { //την εχω γράψει για να τρέξω εγώ
	 * κάποια τεστ μνήμης
	 * 
	 * double mb = Math.pow(1024, 2); Runtime runtime = Runtime.getRuntime(); return
	 * ("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / mb); }
	 */


}
