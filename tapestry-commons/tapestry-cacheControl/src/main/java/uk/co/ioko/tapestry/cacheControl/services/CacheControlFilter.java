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

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.MetaDataLocator;
import org.apache.tapestry5.services.PageRenderRequestFilter;
import org.apache.tapestry5.services.PageRenderRequestHandler;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheType;

import java.io.IOException;
import java.util.Locale;
import java.util.Calendar;

/**
 * Page render request filter to write headers for annotated pages
 */
public class CacheControlFilter implements PageRenderRequestFilter {

	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss z",
			Locale.UK);
	private MetaDataLocator metaDataLocator;
	private int mediumCacheTime;
	private int shortCacheTime;
	private int longCacheTime;
	private Response response;
	private static final String CACHE_CONTROL_HEADER = "Cache-Control";
	private static final String PRAGMA_HEADER = "Pragma";
	private int farFutureCacheTime;

	/**
	 *
	 */
	public CacheControlFilter(final MetaDataLocator metaDataLocator, @Symbol("cacheControl.short") int shortCacheTime,
							  @Symbol("cacheControl.medium") int mediumCacheTime,
							  @Symbol("cacheControl.long") int longCacheTime,
							  @Symbol("cacheControl.farFuture") int farFutureCacheTime, Response response) {
		this.metaDataLocator = metaDataLocator;
		this.longCacheTime = longCacheTime;
		this.shortCacheTime = shortCacheTime;
		this.mediumCacheTime = mediumCacheTime;
		this.response = response;
		this.farFutureCacheTime = farFutureCacheTime;
	}

	/**
	 * On each page request check if we should be setting headers on this page
	 *
	 * @param parameters
	 * @param handler
	 * @throws IOException
	 */
	public void handle(PageRenderRequestParameters parameters, PageRenderRequestHandler handler) throws IOException {

		// Get the page metadata without loading the page (note a type coercer is used here)
		CacheType cacheType = metaDataLocator.findMeta(CacheControlTransformer.CACHE_TYPE_METADATA,
				parameters.getLogicalPageName(), CacheType.class);
		if (!(cacheType == CacheType.NONE)) {
			// We should process headers
			if (cacheType == CacheType.NEVER) {
				addNeverHeaders();
			}  else {
				addRelativeHeaders(calculate(cacheType));
			}
		}

		// Pass it down the chain
		handler.handle(parameters);
	}

	/**
	 * Returns the cache time for this cache type
	 *
	 * @param cacheType
	 * @return The time (in seconds) to cache)
	 */

	private int calculate(CacheType cacheType) {
		if (cacheType == CacheType.LONG) {
			return longCacheTime;
		} else if (cacheType == CacheType.MEDIUM) {
			return mediumCacheTime;
		} else if (cacheType == CacheType.FAR_FUTURE) {
			return farFutureCacheTime;
		} else {
			return shortCacheTime;
		}
	}

	/**
	 * Add never (or no-cache headers) to try and force no caching
	 */
	private void addNeverHeaders() {
		response.setHeader(CACHE_CONTROL_HEADER, "no-cache");
		response.setHeader(PRAGMA_HEADER, "No-Cache");
	}

	/**
	 * Add relative header
	 *
	 * @param timeInSeconds
	 */
	private void addRelativeHeaders(int timeInSeconds) {
		response.setHeader(CACHE_CONTROL_HEADER, String.format("max-age=%d", timeInSeconds));
		Calendar expires = Calendar.getInstance();
		expires.add(Calendar.SECOND, timeInSeconds);
		response.setHeader("Expires", DATE_FORMAT.format(expires));
	}
}
