package uk.co.ioko.tapestry.mixins.pages;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 1:28:12 PM
 */
public class DisableSortingTest {

	@Test
	public void testDisableSorting() {
		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.mixins", "Test", "src/test/webapp");
		Document page1 = pageTester.renderPage("DisableSortingPage");

		// Find the table headers and verify they don't have sorting features

		String markup = page1.getRootElement().getChildMarkup();

		// This is the headers without sorting features

		Assert.assertTrue(markup.contains("<th class=\"description t-last\">Description</th>"));
		Assert.assertTrue(markup.contains("<th class=\"name t-first\">Name</th>"));
	}
}
