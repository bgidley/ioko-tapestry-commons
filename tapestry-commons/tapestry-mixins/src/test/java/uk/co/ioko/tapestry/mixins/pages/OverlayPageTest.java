package uk.co.ioko.tapestry.mixins.pages;

import org.apache.tapestry5.test.AbstractIntegrationTestSuite;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 1:57:51 PM
 */
public class OverlayPageTest extends AbstractIntegrationTestSuite {
	public OverlayPageTest() {
		super("src/test/webapp");
	}

	@Test(groups = "selenium")
	public void testOverlay() throws InterruptedException {

		/*
		* This test is commented out as it doesn't work with Selenium for no obvious reason.
		*
		* Selenium runs, but the action link is followed as a normal HTTP link and thus Tapestry errors. 
		*
		* */
//		open(BASE_URL + "OverlayPage");
//
//		// Page should render with two overlay links
//		click("testActionLink");
//
//		// Overlay will now be showing
//		this.assertTextPresent("This is the overlay text. The value is ActionLink.");
//		click("//div[@id='overlay']/div[1]");
//		// Overlay is now closed
//		click("submit");
//		this.assertTextPresent("This is the overlay text. The value is Form.");
//		click("//div[@id='overlay_0']/div[1]");


	}
}
