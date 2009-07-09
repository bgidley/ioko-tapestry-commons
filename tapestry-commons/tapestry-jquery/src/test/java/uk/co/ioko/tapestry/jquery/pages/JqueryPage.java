package uk.co.ioko.tapestry.jquery.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.RenderSupport;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 9, 2009 Time: 8:30:12 AM
 */
public class JqueryPage {
	@Inject
	private RenderSupport renderSupport;

	public void setupRender(){
		renderSupport.addScript("var x = '1';");
	}
}
