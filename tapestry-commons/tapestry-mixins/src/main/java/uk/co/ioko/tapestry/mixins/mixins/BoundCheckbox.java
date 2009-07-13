package uk.co.ioko.tapestry.mixins.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Checkbox;

@IncludeJavaScriptLibrary("boundCheckbox.js")
public class BoundCheckbox {
	@InjectContainer
	private ClientElement container;

	@Parameter
	private Checkbox master;

	@Environmental
	private RenderSupport renderSupport;

	@AfterRender
	void after(MarkupWriter writer) {
		String masterClientId = master == null ? "" : master.getClientId();
		renderSupport.addInit("boundCheckboxLoad", container.getClientId(),
				masterClientId);
	}
}
