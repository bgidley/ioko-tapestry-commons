package uk.co.ioko.tapestry.mixins.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Checkbox;
import org.apache.tapestry5.ioc.annotations.Inject;
import uk.co.ioko.tapestry.mixins.services.MyDataGenerator;
import uk.co.ioko.tapestry.mixins.vo.MyDataRow;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 12:28:22 PM
 */
public class BoundCheckboxPage {
	@InjectComponent
	@Property
	private Checkbox selectAllCheckbox;

	@Property
	@Persist
	private boolean selectAll;

	@Persist
	private HashSet<MyDataRow> selectedSet;

	@Property
	private MyDataRow current;

	@Inject
	private MyDataGenerator myDataGenerator;

	public MyDataRow[] getDummyItems() {
		return myDataGenerator.getDummyData();
	}

	public boolean getCurrentSelected() {
		if (selectedSet == null) {
			selectedSet = new HashSet<MyDataRow>();
		}
		return selectedSet.contains(current);
	}

	public void setCurrentSelected(boolean value) {
		if (selectedSet == null) {
			selectedSet = new HashSet<MyDataRow>();
		}
		if (value) {
			selectedSet.add(current);
		} else {
			selectedSet.remove(current);
		}
	}

}
