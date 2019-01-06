import javax.swing.JOptionPane;

/**
 * Nomenclature Standards ensures that not a single table/field/element in the database 
 * has an unacceptable value such as a single whitespace.
 * Such standards have to be strictly followed in order to avoid 
 * any inconveniences and dysfunctions in the database.
 * @author Theodosis Tsaklanos
 * @author Anna Maria Mersinoglou
 *
 */
public class Standards {
    /**
    * isNameValid method prevents the user to give an empty value.
    * @param input
    * @return whether the given name is empty or not.
    */
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
    /**
    * Used to prevent the user from giving an empty value.
    *@param input
    * @return
    */
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
    /**
    * verify method that asks the user to give confirmation.
    * @return
    */
    public static boolean verify() {
        int dealer = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (dealer == 0) {
            return true; //the user confirms to proceed
        } else {
            return false; //the user had second thoughts
        } 
    }
}
