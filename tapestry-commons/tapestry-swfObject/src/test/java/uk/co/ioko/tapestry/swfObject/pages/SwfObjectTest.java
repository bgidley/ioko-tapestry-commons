package uk.co.ioko.tapestry.swfObject.pages;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 9, 2009 Time: 3:08:28 PM
 */
public class SwfObjectTest {

	/**
	 * It is very hard to test this from here some selenium tests may be more in order
	 * @throws InterruptedException
	 */
	@Test
	public void testRender() throws InterruptedException {

		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.swfObject", "Swf", "src/test/webapp");
		Document document = pageTester.renderPage("SwfObjectTestPage");
		Assert.assertNotNull(document);

		String content = document.toString();

		// Check for non express install variant
		Assert.assertTrue(content.contains("[\"/assets/app/1.0/pages/SwfObjectTest.swf\",\"swfObjectVariant\",\"300\",\"300\",\"10.0.22\",false,false,{\"Cat\":[\"Parsnip\"],\"Vegetable\":[\"Turnip\"]},{\"Cat\":[\"Parsnip\"],\"Vegetable\":[\"Turnip\"]}]"));
		
		
	}

}
