\package database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.ArrayList;

class TestTable {
	private static Table input;
	private static Field field;
	private static Entry entry;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ArrayList<Object> entries = new ArrayList<Object>();
		entries.add("chris");
		entries.add("monokrousos");
		entry = new Entry(entries);
		input = new Table();
		field = new Field("Name");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testField() {
		input.addField(field);
		Assert.assertTrue("failure to add field",input.getFields().toString().contains("Name"));
		field.setFieldName("Surname");
		input.addField(field);
		Assert.assertTrue("failure to add field",input.getFields().toString().contains("Surname"));
	}

	@Test
	void testEntries() {
		input.addEntry(entry);
		Assert.assertTrue("failure to add entry",input.getEntries().toString().contains("chris"));
		Assert.assertTrue("failure to add entry",input.getEntries().toString().contains("monokrousos"));
	}


}
