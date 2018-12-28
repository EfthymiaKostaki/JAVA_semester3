package database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.Scanner;

class TestTableMenu {
	private static Scanner input;
	static int choice;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		input = new Scanner(System.in);
		choice = 1;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		input.close();
	}

	@Test
	void testDecider() {
		int low = 0;
		int high = 4;
		Assert.assertEquals("failure-Not an option", choice, 1);
		choice = low;
		Assert.assertEquals("failure-Not an option", choice, low);
	}

}
