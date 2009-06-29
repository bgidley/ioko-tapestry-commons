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

package uk.co.ioko.tapestry.cacheControl.services;

import org.apache.tapestry5.ioc.annotations.Scope;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheType;

/**
 * This holds the current requests state for cache type.
 *
 * This is set by EITHER uk.co.ioko.tapestry.cacheControl.services.CacheControlMarkupRenderer
 * OR by code in the page. Whichever sets it last wins.
 *
 * Created by IntelliJ IDEA. User: ben Date: Jun 29, 2009 Time: 12:36:29 PM
 */
@Scope(org.apache.tapestry5.ioc.ScopeConstants.PERTHREAD)
public class CacheControlSupportImpl implements CacheControlSupport {

	public CacheType getCacheType() {
		return cacheType;
	}

	public void setCacheType(CacheType cacheType) {
		this.cacheType = cacheType;
	}

	private CacheType cacheType = CacheType.NONE;
}
