package uk.co.ioko.tapestry.mixins.pages;

import org.apache.tapestry5.test.AbstractIntegrationTestSuite;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 12:42:10 PM
 */
public class BoundCheckboxTest extends AbstractIntegrationTestSuite {
	public BoundCheckboxTest() {
		super("src/test/webapp");
	}


	@Test
	public void testBoundCheckBox(){
		open(BASE_URL + "BoundCheckboxPage");

		Assert.assertFalse(isChecked("checkbox_1"));

		click("selectAllCheckbox");

		Assert.assertTrue(isChecked("checkbox_1"));

		click("selectAllCheckbox");

		Assert.assertFalse(isChecked("checkbox_1"));

				
	}

}
