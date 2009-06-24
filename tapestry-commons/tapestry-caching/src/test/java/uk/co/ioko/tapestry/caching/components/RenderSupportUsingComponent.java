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

package uk.co.ioko.tapestry.caching.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.FieldFocusPriority;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

import java.util.Date;


/**
 * This is a test tapestry component that users render support a lot (and pointlessly)
 *
 * Created by IntelliJ IDEA. User: ben Date: Jun 23, 2009 Time: 3:19:53 PM
 */
@IncludeJavaScriptLibrary("RenderSupport.js")
@IncludeStylesheet("RenderSupport.css")
public class RenderSupportUsingComponent {

	@Environmental
	private RenderSupport renderSupport;

	@Inject
	private ComponentResources componentResources;

	public Date getNow(){
		return new Date();
	}

	public void setupRender(){
		renderSupport.addClasspathScriptLink("/RenderSupportClassPath.js");

		JSONObject jsonObject = new JSONObject();
		jsonObject.append("Bob", "the builder");
		renderSupport.addInit("renderSupportInitJsonObject", jsonObject);

		JSONArray jsonArray = new JSONArray();
		jsonArray.put("parnsip");
		jsonArray.put("buddy");
		renderSupport.addInit("renderSupportInitJsonArray", jsonArray);
		renderSupport.addInit("renderSupportInitParams", "sprout", "toby");

		renderSupport.addScript("var addedScript;");

		renderSupport.addScript("var %s;", "addedScriptVariable");

		String clientId = renderSupport.allocateClientId(componentResources);

		renderSupport.autofocus(FieldFocusPriority.OPTIONAL, "name");

	}
}
