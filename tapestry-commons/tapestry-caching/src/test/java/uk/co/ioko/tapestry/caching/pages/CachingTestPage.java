/*
 * Copyright (c) 2009 ioko365 Ltd
 *
 * This file is part of ioko tapestry-commons.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with ioko tapestry-commons.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.ioko.tapestry.caching.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Zone;

/**
 * Created by IntelliJ IDEA. User: ben Date: Mar 25, 2009 Time: 4:47:32 PM
 */
public class CachingTestPage {

	@InjectComponent
	private Zone helloZone1;

	@InjectComponent
	private Zone helloZone2;

	private String name;

	private Date now;

	public Date getNow() {
		return now;
	}

	public String getName() {
		return name;
	}

	public void setupRender() {
		this.now = new Date();
		this.name = "Sam";
	}
}
