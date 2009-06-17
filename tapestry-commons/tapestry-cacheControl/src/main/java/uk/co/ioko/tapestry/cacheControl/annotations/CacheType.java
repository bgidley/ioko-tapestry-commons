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

package uk.co.ioko.tapestry.cacheControl.annotations;

/**
 * Defines the length of the cache control in 'relative' terms.
 * <p/>
 * The actual values for SHORT, MEDIUM and LONG is set as a contribution. It is possible (but clearly silly) to have
 * SHORT longer than MEDIUM etc.
 * <p/>
 * NEVER means set header to suggest no caching should take place.
 * FAR_FUTURE means to set 'far future' headers.
 * NONE means take no action
 *
 * The default is set as a contribution to symbols to the key defined in CacheControlTransformer.
 *
 * @See uk.co.ioko.tapestry.cacheControl.services.CacheControlTransformer
 * @See uk.co.ioko.tapestry.cacheControl.services.CacheControlModule
 * @See uk.co.ioko.tapestry.cacheControl.services.CacheControlFilter
 */
public enum CacheType {
	NONE, NEVER, SHORT, MEDIUM, LONG, FAR_FUTURE

}
