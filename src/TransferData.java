package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

/**
 * Exports data of Database's tables to Excel Sheets,
 * which can be imported at another time.
 * @author Theodosis Tsaklanos
 * @version 1.1
 */
public class TransferData {

    /**A specific key used to mark every Excel file created by this database.
     * It is printed on the first line of an Excel file
     * when a Table is being exported and is afterwards used to identify
     * if a Table being imported was created by this database. <br>
     * Excel files that do not bear this mark <b>cannot</b> be imported.*/
    protected final String uniqueKeyIdentifier = "ad36$plw";

    private String fileName;

    private BufferedReader in;

    public TransferData(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Imports an .xls file in order to fill the database. <br> <br>
     * <b>Note: </b> The .xls file must contain the key identifier
     * {@link database.TransferData#uniqueKeyIdentifier}.
     */
    protected void importFile() {
        try {
            in = new BufferedReader(new FileReader(fileName + ".xls"));
            Table table = new Table(fileName);
            Database.getDatabaseInstance().addTable(table);
            String line;
            int linesRead = 0;
            try {
                while ((line = in.readLine()) != null) {
                    if (linesRead == 0) {
                        if(!line.equals(uniqueKeyIdentifier)) {
                            break;
                        }
                    }
                    ArrayList<Object> entryArguments = new ArrayList<Object>();
                    StringTokenizer st = new StringTokenizer(line, "\t");
                    while (st.hasMoreTokens()) {
                        if (linesRead == 1) {
                            table.addField(new Field(st.nextToken()));
                        } else {
                            entryArguments.add(st.nextElement());
                        }
                    }
                    if (linesRead > 1) {
                        table.addEntry(new Entry(entryArguments));
                    }
                    linesRead++;
                }
                in.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }

    }

    /**
     * Exports a table to an .xls file.
     * @param table - the table to be exported
     */
    protected void exportFile(Table table) {
        try {
            FileWriter outFile =
                    new FileWriter(fileName + ".xls", false);
            PrintWriter out = new PrintWriter(outFile);
            out.println(uniqueKeyIdentifier); //key
            for (int i = 0; i < table.numberOfFields(); i++) {
                out.print(table.getField(i) + "\t");
            }
            out.println();
            for (int i = 0; i < table.numberOfEntries(); i++) {
                for (int j = 0; j < table.numberOfFields(); j++) {
                    out.print(table.getEntry(i).getEntryArgument(j) + "\t");
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {
           JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return fileName;
    }


}
