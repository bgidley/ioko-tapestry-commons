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

package uk.co.ioko.tapestry.cacheControl.pages;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheControl;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheType;
import uk.co.ioko.tapestry.cacheControl.services.CacheControlSupport;

import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 26, 2009 Time: 4:35:09 PM
 */
@CacheControl(cacheType = CacheType.FAR_FUTURE)
public class CacheControlAjaxPage {

	@Inject
	@Property
	private Block ajaxResponse;


	@Inject
	private CacheControlSupport cacheControlSupport;

	@Property
	@Persist(org.apache.tapestry5.PersistenceConstants.FLASH)
	private Boolean showNotAjax;

	public void setupRender(){
		if (showNotAjax == null){
			showNotAjax = false;
		}
	}

	public Date getNow() {
		return new Date();
	}

	public Block onActionFromAjax() {
		return ajaxResponse;
	}

	public Block onActionFromNoneAjax() {

		cacheControlSupport.setCacheType(CacheType.NONE);
		return ajaxResponse;
	}

	public void onActionFromNotAjax() {
		showNotAjax = true;
	}


}
