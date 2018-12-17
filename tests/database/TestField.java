package database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

class TestField {

	private static Field f;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		f = new Field("JAVA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	void testConstructor() {
		Assert.assertEquals("failure - wrong size", Field.getFieldsCounter(), 1);
		Assert.assertEquals("failure - wrong element", f.getFieldName(), "JAVA");
		f = new Field("Name");
		Assert.assertNotEquals("failure - wrong element", f.getFieldName(), "JAVA");
		Assert.assertEquals("failure - wrong size", Field.getFieldsCounter(), 2);
	}

}
