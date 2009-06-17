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

package uk.co.ioko.tapestry.cacheControl.integration;

import org.apache.tapestry5.test.AbstractIntegrationTestSuite;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 16, 2009 Time: 9:31:22 AM
 */
public class CacheControlIntegrationTest extends AbstractIntegrationTestSuite {
	public CacheControlIntegrationTest() {
		super("src/test/webapp");
	}

	@Test
	public void testCachedPage() throws Exception {
		open(BASE_URL + "CacheControlTestPage");

		// Get the contents of the date time field
		String date = getText("//dl[@id='live']/dt[2]");

		// Wait 2 seconds
		Thread.sleep(2000);

		// Refresh the page it shouldn't have changed
		open(BASE_URL + "CacheControlTestPage");

		this.assertText("//dl[@id='live']/dt[2]", date);


	}

	@Test
	public void testNotCachedPage() throws Exception {
		open(BASE_URL + "NotCachedTestPage");

		// Get the contents of the date time field
		String date = getText("//dl[@id='live']/dt[2]");

		// Wait 2 seconds
		Thread.sleep(2000);

		// Refresh the page it shouldn't have changed
		open(BASE_URL + "CacheControlTestPage");

		String date2 = getText("//dl[@id='live']/dt[2]");

		Assert.assertTrue(!date2.equals(date));


	}
}
