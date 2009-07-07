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

import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Simple test page the uses render support just to make tapestry include the stack
 * Created by IntelliJ IDEA. User: ben Date: Jul 7, 2009 Time: 1:55:43 PM
 */
public class IncludeScriptPage {

	@Inject
	private RenderSupport renderSupport;

	public void setupRender(){
		renderSupport.addScript("var x = '1';");
	}
}
