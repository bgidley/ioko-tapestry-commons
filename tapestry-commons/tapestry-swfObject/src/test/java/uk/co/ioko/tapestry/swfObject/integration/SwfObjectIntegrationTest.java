/*
 * Copyright (c) 2009 ioko365 Ltd
 *
 * This file is part of ioko tapestry-commons.
 *
 *     ioko tapestry-commons is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ioko tapestry-commons is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with ioko tapestry-commons.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.ioko.tapestry.swfObject.integration;

import org.apache.tapestry5.test.AbstractIntegrationTestSuite;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 11, 2009 Time: 2:17:01 PM
 */
public class SwfObjectIntegrationTest extends AbstractIntegrationTestSuite {
	public SwfObjectIntegrationTest() {
		super("src/test/webapp");
	}

	@Test(groups = "flashRequired")
	public void testFlashLoader() throws Exception {
		open(BASE_URL + "SwfObjectTestPage");

		// We need to wait for flash to load and the page javascript to get the flash params from it
		waitForCondition("var contents = selenium.getText(\"//div[@id='flashvarResults']\"); contents != \"\";", "5000");

		assertText("//div[@id='flashvarResults']/dl/dt[1]", "Cat");
		assertText("//div[@id='flashvarResults']/dl/dd[1]", "Parsnip");
		assertText("//div[@id='flashvarResults']/dl/dt[2]", "Vegetable");
		assertText("//div[@id='flashvarResults']/dl/dd[2]", "Turnip");


	}

}
