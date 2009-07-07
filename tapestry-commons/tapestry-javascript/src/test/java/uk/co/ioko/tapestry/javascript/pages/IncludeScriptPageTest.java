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

package uk.co.ioko.tapestry.javascript.pages;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 7, 2009 Time: 2:40:39 PM
 */
public class IncludeScriptPageTest {

	@Test
	public void testIncludeScripts() {
		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.javascript", "Test", "src/test/webapp");
		Document page1 = pageTester.renderPage("IncludeScriptPage");

		Element head = (Element) page1.getRootElement().getChildren().get(0);


		boolean foundJquery = false;
		boolean foundJqueryMin = false;

		for (Node node : head.getChildren()) {
			// Interating over the tags
			Element element = (Element) node;
			if (element.getName().equals("script")) {
				if (element.getAttribute("src").contains("jquery-1.3.2.js")) {
					if (foundJquery) {
						Assert.fail("found jquery twice");
					} else {
						foundJquery = true;
					}
				} else if (element.getAttribute("src").contains("jquery-1.3.2.min.js")) {
					if (foundJqueryMin) {
						Assert.fail("found jquery min twice");
					} else {
						foundJqueryMin = true;
					}
				}
			}
		}

		Assert.assertTrue(foundJquery);
		Assert.assertTrue(foundJqueryMin);
	}
}
