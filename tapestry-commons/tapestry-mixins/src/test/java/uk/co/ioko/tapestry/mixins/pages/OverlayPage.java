package uk.co.ioko.tapestry.mixins.pages;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 1:47:47 PM
 */
public class OverlayPage {

	@Inject
	@Property
	private Block contentBlock;

	@Property
	private String textValue;

	public Block onActionFromTestActionLink() {
		textValue = "ActionLink";
		return contentBlock;
	}

	public Block onSuccessFromTestForm() {
		textValue = "Form";
		return contentBlock;
	}
}
