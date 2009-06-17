package uk.co.ioko.tapestry.swfObject.pages;

import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.json.JSONObject;
import uk.co.ioko.tapestry.swfObject.components.SwfObject;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 8, 2009 Time: 4:38:43 PM
 */
@IncludeJavaScriptLibrary("readFlashStatus.js")
public class SwfObjectTestPage {

	@Property
	private JSONObject flashVars;

	@Environmental
	private RenderSupport renderSupport;

	@Component
	private SwfObject swfObject;

	public void setupRender() {
		flashVars = new JSONObject();
		flashVars.append("Cat", "Parsnip");
		flashVars.append("Vegetable", "Turnip");

	}

	public void afterRender() {

		renderSupport.addInit("readFlashStatus", swfObject.getClientId(), "flashvarResults");

	}
}
