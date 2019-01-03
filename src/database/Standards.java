package database;

import javax.swing.JOptionPane;

/**
 * Nomenclature Standards ensure that no single table/field/element in the database have an unacceptable value such as a single whitespace.
 * Such standards have to be strictly followed in order to avoid any inconveniences and dysfunction of the database.
 * @author Θεοδόσης
 *
 */
public class Standards {
	
	public static boolean isNameValid(String input) {
		if (input == null) {
			JOptionPane.showMessageDialog(null, "No problem, you can add it later!");
			return false;
		} else {
			if (!input.trim().isEmpty()) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "It can't be empty!");
				return false;
			}
		}
	}
	
	public static boolean isNameValid(Object input) {
		if (input == null) {
			JOptionPane.showMessageDialog(null, "We discourage you to have null values!");
			JOptionPane.showMessageDialog(null, "We have discarded this whole entry");
			return false;
		} else {
			if (!input.toString().trim().isEmpty()) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "It can't be empty!");
				return false;
			}
		}
	}
	
	public static boolean verify() {
		int dealer = JOptionPane.showConfirmDialog(null, "Are you sure?");
		if (dealer == 0) {
			return true; //the user confirms to proceed
		} else {
			return false; //the user had second thoughts
		} 
	}
}
