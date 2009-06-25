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

package uk.co.ioko.tapestry.swfObject.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.util.TextStreamResponse;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 24, 2009 Time: 3:42:11 PM
 */
public class SwfObjectAjaxPage {

	@Property
	private JSONObject flashvars;

	@Inject
	private ComponentResources componentResources;


	public void setupRender() {
		flashvars = new JSONObject();
		flashvars.append("ajaxRequestUrl", componentResources.createEventLink("ajaxRequest").toAbsoluteURI() );
	}

	/**
	 * Called on the AJAX Request
	 *
	 * Note we could return the json object directly if we got flash to set the XMLHTTPRequest headers.
	 * @return
	 */
	public Object onAjaxRequest(){
		JSONObject myResults = new JSONObject();
		myResults.append("Cat", "Parsnip");
		return new TextStreamResponse("application/json", myResults.toString());
	}
}
