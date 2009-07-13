package uk.co.ioko.tapestry.mixins.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import uk.co.ioko.tapestry.mixins.vo.MyDataRow;
import uk.co.ioko.tapestry.mixins.services.MyDataGenerator;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 1:26:22 PM
 */
public class DisableSortingPage {

	@Inject
	private MyDataGenerator myDataGenerator;

	public MyDataRow[] getDummyItems(){

		return myDataGenerator.getDummyData();

	}
}
