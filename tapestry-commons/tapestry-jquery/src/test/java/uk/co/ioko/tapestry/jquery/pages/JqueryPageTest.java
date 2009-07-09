package uk.co.ioko.tapestry.jquery.pages;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 9, 2009 Time: 8:46:59 AM
 */
public class JqueryPageTest {

	@Test
	public void testLoadedJquery() {
		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.jquery", "Test", "src/test/webapp");
		Document page1 = pageTester.renderPage("JqueryPage");

		Element head = (Element) page1.getRootElement().getChildren().get(0);


		boolean foundJquery = false;

		for (Node node : head.getChildren()) {
			// Interating over the tags
			Element element = (Element) node;
			if (element.getName().equals("script")) {
				if (element.getAttribute("src").contains("jquery-1.3.2.js")) {
					if (foundJquery) {
						Assert.fail("found jquery twice");
					} else {
						foundJquery = true;
					}
				} else if (element.getAttribute("src").contains("jquery-1.3.2.min.js")) {
					Assert.fail("found jquery min");

				}
			}
		}

		Assert.assertTrue(foundJquery);

	}

	@Test
	public void testLoadedJqueryProd() {
		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.jquery", "TestProd", "src/test/webapp");
		Document page1 = pageTester.renderPage("JqueryPage");

		Element head = (Element) page1.getRootElement().getChildren().get(0);


		boolean foundJquery = false;

		for (Node node : head.getChildren()) {
			// Interating over the tags
			Element element = (Element) node;
			if (element.getName().equals("script")) {
				 if (element.getAttribute("src").contains("jquery-1.3.2.js")) {
					Assert.fail("found jquery normal");

				}
			}
		}

		// Can't actually test for jquery being in rolled up file :(
	}
}
