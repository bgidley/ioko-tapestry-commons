package uk.co.ioko.tapestry.mixins.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.ClientBehaviorSupport;

@IncludeStylesheet("overlay.css")
@IncludeJavaScriptLibrary( { "overlay.js" })
public class Overlay {
	@InjectContainer
	private ClientElement container;

	@Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.LITERAL)
	private String zone;

	@Parameter(allowNull = false)
	private boolean expose;

	@Parameter(allowNull = false, defaultPrefix = "literal")
	private String exposeColour;

	@Environmental
	private RenderSupport renderSupport;

	@Environmental
	private ClientBehaviorSupport clientBehaviorSupport;

	private String overlayClientId;

	@SetupRender
	public void setup() {
		// allocate the ids we will need
		overlayClientId = renderSupport.allocateClientId("overlay");
	}

	@AfterRender
	void after(MarkupWriter writer) {
		writer.element("div", "class", "overlay", "id", overlayClientId);

		// render an inner div where we'll attach the zone for update
		writer.element("div", "id", zone);
		writer.end();
		writer.end();
		clientBehaviorSupport.addZone(zone, "show", "show");

		boolean form = (container instanceof Form);
		renderSupport.addInit("overlayMixinLoad", form ? "true" : "false",
				overlayClientId, container.getClientId(), expose ? exposeColour
						: "none");
	}

	private boolean defaultExpose() {
		return true;
	}

	private String defaultExposeColour() {
		return "#333333";
	}

}
