package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private JFileChooser jfc;

	public TransferData() {

	}

    public TransferData(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Allows the user to choose a .xls file that contains a table's data
     * from a specific disk, directory and/or folder.
     * @param frame - the JFrame where the FileChooser will be displayed.
     */
    protected File importFile(JFrame frame) {
    	jfc = new JFileChooser();
    	int open = jfc.showOpenDialog(frame);
    	File selectedFile = null;
    	if (open == JFileChooser.APPROVE_OPTION) {
    		selectedFile = jfc.getSelectedFile();
    	}
    	return selectedFile;
    }

    /**Reads only the first line of the .xlsx and checks if the key
     * exists or not.
     * {@link database.TransferData#uniqueKeyIdentifier}
     * @return true if the key exists or false if it does not exist.
     * @param checkFile - the file that is going to be searched
     */
    protected boolean checkForUniqueKeyIdentifier(File checkFile) {
    	try {
			in = new BufferedReader(new FileReader(checkFile.toString()));
			String line;
			try {
				line = in.readLine();
				if (line.equals(uniqueKeyIdentifier)) {
					return true;
				} else {
                	JOptionPane.showMessageDialog(null,
                        			"This file does not comply with "
                        			+ "this database's standards.\n"
                        			+"The Key Identifier was not found.\n"
                        			+ "The correct key Identifier is:"
                        			+ uniqueKeyIdentifier);
                	return false;
				}
			} catch(IOException e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return false;
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found!");
			return false;
		}
    }

    /**
     * Imports an .xls file in order to fill the database. <br> <br>
     * <b>Note: </b> The .xls file must contain the key identifier
     * {@link database.TransferData#uniqueKeyIdentifier}.
     * @param readFile - the file that is going to be read.
     * @param tableName - the name of the table that is going to be created.
     */
    protected void startReadingFile(File readFile, String tableName) {
        try {
            in = new BufferedReader(new FileReader(readFile.toString()));
            JOptionPane.showMessageDialog(
            				null, "The table was imported!");
            Table table = new Table(tableName);
            Database.getDatabaseInstance().addTable(table);
            String line;
            int linesRead = 0;
            try {
                while ((line = in.readLine()) != null) {
                	linesRead++;
                	if (linesRead == 1) {
                		//do nothing
                		//the first line includes only the key.
                		//this is used symbolic to mark files.
                	}
                    ArrayList<Object> entryArguments = new ArrayList<Object>();
                    StringTokenizer st = new StringTokenizer(line, "\t");
                    while (st.hasMoreTokens()) {
                        if (linesRead == 2) {
                            table.addField(new Field(st.nextToken()));
                        } else {
                            entryArguments.add(st.nextElement());
                        }
                    }
                    if (linesRead > 2) {
                        table.addEntry(new Entry(entryArguments));
                    }
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
     * Allows the user to save a .xls file that contains a table's data
     * to a preferred destination in their computer.
     * @param frame - the JFrame where the FileChooser will be displayed.
     * @param table - the table to be exported
     */
    protected File exportFile(JFrame frame,Table table) {
        jfc = new JFileChooser();
        jfc.setSelectedFile(new File(fileName + ".xls"));
        jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files" ,".xls");
		jfc.addChoosableFileFilter(filter);
        File destination = null;
		int save = jfc.showSaveDialog(frame);
        if (save == JFileChooser.APPROVE_OPTION) {
        	destination = jfc.getSelectedFile();
        } else {
        	JOptionPane.showMessageDialog(frame, "You have canceled the export!");
        }
        return destination;

    }

    /** Writes the data of a Table to the selected .xls file.
     * @param table - the data to be written.
     * @param destination - the .xls file where the data are going
     * to be written in. */
    protected void startWritingFile(Table table,File destination) {
        try {
            FileWriter outFile =
                    new FileWriter(destination.toString(), false);
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
        	JOptionPane.showMessageDialog(null, "The table was exported!\n"
        					+ "The Key Identifier is: "
        					+ uniqueKeyIdentifier);
        	JOptionPane.showMessageDialog(null,
        		    "The Key Identifier is used "
        		    + "to mark every file created by this database.");
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
