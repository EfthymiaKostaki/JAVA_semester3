package database;

import javax.swing.JOptionPane;

/**
 * Standards ensure that no single table/field/element in the database
 * have an unacceptable value such as a single whitespace.
 * Such standards have to be strictly followed
 * in order to avoid any inconveniences and dysfunction of the database.
 * @author Theodosis Tsaklanos
 * @author Anna Maria Mersinoglou
 * @version 1.1
 */
public class Standards {

    /**Prevents the user from giving an empty value.
     * @param input - the input being checked.
     * @return true if the name is valid or false if it is invalid.*/
	public static boolean isNameValid(String input) {
		if (input == null) {
			JOptionPane.showMessageDialog(null,
			                "No problem, you can add it later!");
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

    /**Prevents the user from giving an empty value.
     * @param input - the input being checked.
     * @return true if the name is valid or false if it is invalid.*/
	public static boolean isNameValid(Object input) {
		if (input == null) {
			JOptionPane.showMessageDialog(null,
			                "Empty cells will be given a NULL value!\n"
			                + "You can edit them later if you wish.");
			return false;
		} else {
			if (!input.toString().trim().isEmpty()) {
				return true;
			} else {
			    return false;
			}
		}
	}

	/**Asks the user to confirm an actions.
	 * @return true if the user agree to continue. */
	public static boolean verify() {
		int dealer = JOptionPane.showConfirmDialog(null, "Are you sure?");
		if (dealer == 0) {
			return true; //the user confirms to proceed
		} else {
			return false; //the user had second thoughts
		}
	}
}
