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

package uk.co.ioko.tapestry.caching.pages;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 23, 2009 Time: 3:29:04 PM
 */
public class RenderSupportPageTest {

	@Test
	public void testRenderSupportSurvivesCaching() throws InterruptedException {

		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.caching", "Test", "src/test/webapp");
		Document page1 = pageTester.renderPage("RenderSupportPage");

		Element head = (Element) page1.getRootElement().getChildren().get(0);

		String headText = head.getChildMarkup();

		Assert.assertTrue(headText.contains("components/RenderSupport.js"));
		Assert.assertTrue(headText.contains(
				"components/RenderSupport.css"));

		Document page2 = pageTester.renderPage("RenderSupportPage");
		Element head2 = (Element) page2.getRootElement().getChildren().get(0);

		String headText2 = head.getChildMarkup();
		Assert.assertTrue(headText2.contains("components/RenderSupport.js"));
		Assert.assertTrue(headText2.contains(
				"components/RenderSupport.css"));


	}
}
