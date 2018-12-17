package database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Scanner;

class TestEntry {
		private static Scanner input = new Scanner(System.in);
		private static ArrayList<Object> entryArguements;
		private Object firstElement = 5;
		private Object missingElement = 15;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		entryArguements = new ArrayList<Object>();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		input.close();
		entryArguements.clear();
	}

	
	@Test
	void testConstructor() {
		Assert.assertEquals("failure - wrong size", entryArguements.size(), 1);
		entryArguements.add(1);
		Assert.assertEquals("failure - wrong size", entryArguements.size(), 2);
		Assert.assertTrue("failure - does not contain first element",
				entryArguements.contains(firstElement));
        Assert.assertFalse("failure - contains missing element",
        		entryArguements.contains(missingElement ));
	}
	

    @Test
    public void testToString() {
        Assert.assertEquals("failure - wrong to String", entryArguements.toString(),
                (firstElement).toString());
        final Object secondElement = 1000;
        entryArguements.add(secondElement);
        Assert.assertEquals("failure - wrong to String", entryArguements.toString(),
                (secondElement).toString() + " -> " +
                (firstElement).toString());
    }

}
