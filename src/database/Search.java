package database;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Searches the database to find something.
 * @author Nickolas Tamvakis
 * @author Theodosis Tsaklanos
 * @version 1.1
 */
public class Search {

	/**The text that the user made a search for.*/
	private String text;

	/**The table in which the user is searching.*/
    private Table table;

    /**Contains the <b>rows</b>, which have an element that is identical to
     * the given search text. {@link database.Search#text} */
    private ArrayList<Object> matchedEntries = new ArrayList<Object>();

    public Search(Table table, String text) {
        this.table = table;
        this.text = text;
    }

    /**
     * Performs a search in every row to find an element that is
     * identical to the given search text: {@link database.Search#text}. <br>
     * Adds those rows to an ArrayList: {@link database.Search#matchedEntries}.
     */
    public void searchDatabase() {
        for (int i = 0; i < table.numberOfEntries(); i++) {
            for (int j = 0; j < table.numberOfFields(); j++) {
                if (text.equals(table.getEntry(i).getEntryArgument(j))) {
                    matchedEntries.add(table.getEntry(i));
                }
            }
        }
        if (!matchedEntries.isEmpty()) {
            JOptionPane.showMessageDialog(null,matchedEntries.toArray());
        } else {
            JOptionPane.showMessageDialog(null, "Sorry!\n"
                    + "We could not find what you searched for.");
        }
    }
}
