package uk.co.ioko.tapestry.mixins.services;

import uk.co.ioko.tapestry.mixins.vo.MyDataRow;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 12:58:06 PM
 */
public class MyDataGenerator {

	public MyDataRow[] getDummyData() {

		MyDataRow[] result = new MyDataRow[50];
		for (int i = 0; i < 50; i++) {
			result[i] = new MyDataRow("Bob", "The Baker", false);
		}

		return result;

	}
}
