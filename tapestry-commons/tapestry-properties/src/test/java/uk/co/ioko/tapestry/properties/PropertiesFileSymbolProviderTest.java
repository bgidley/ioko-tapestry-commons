package uk.co.ioko.tapestry.properties;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: aboyd Date: 15-Dec-2009 Time: 12:28:35
 */
public class PropertiesFileSymbolProviderTest {
	@Test
	public void testSinglePropertiesFile() throws Exception {
		PropertiesFileSymbolProvider pfsp = new PropertiesFileSymbolProvider("one.properties");
		assertEquals("one", pfsp.valueForSymbol("one.one"));
		assertEquals("two", pfsp.valueForSymbol("one.two"));
	}

	@Test
	public void testMultiplePropertiesFiles() throws Exception {
		String[] files = new String[] {
				"one.properties",
				"two.properties"
		};


		PropertiesFileSymbolProvider pfsp = new PropertiesFileSymbolProvider(files);

		assertEquals("one", pfsp.valueForSymbol("one.one"));
		assertEquals("two", pfsp.valueForSymbol("one.two"));
		assertEquals("one", pfsp.valueForSymbol("two.one"));
		assertEquals("two", pfsp.valueForSymbol("two.two"));
	}

	@Test
	public void testNoFile() throws Exception {
		try {
			new PropertiesFileSymbolProvider((String) null);
			Assert.fail("Expected an IllegalArgumentException when no properties file is provided.");
		} catch (IllegalArgumentException e) {
			// Success
		}
	}

	@Test
	public void testNoFiles() throws Exception {
		try {
			new PropertiesFileSymbolProvider((String []) null);
			Assert.fail("Expected an IllegalArgumentException when no properties file is provided.");
		} catch (IllegalArgumentException e) {
			// Success
		}


		try {
			String [] emptyArray = new String[0];
			new PropertiesFileSymbolProvider(emptyArray);
			Assert.fail("Expected an IllegalArgumentException when no properties file is provided.");
		} catch (IllegalArgumentException e) {
			// Success
		}
	}


}
