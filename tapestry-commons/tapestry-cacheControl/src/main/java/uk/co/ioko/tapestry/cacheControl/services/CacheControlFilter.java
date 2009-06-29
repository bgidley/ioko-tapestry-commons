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

import org.apache.tapestry5.services.ComponentEventRequestFilter;
import org.apache.tapestry5.services.ComponentEventRequestHandler;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.MetaDataLocator;
import org.apache.tapestry5.services.PageRenderRequestFilter;
import org.apache.tapestry5.services.PageRenderRequestHandler;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheType;

import java.io.IOException;

/**
 * Page render request filter to write headers for annotated pages
 */
public class CacheControlFilter implements PageRenderRequestFilter, ComponentEventRequestFilter {

	private MetaDataLocator metaDataLocator;
	private Request request;
	private CacheControlSupport cacheControlSupport;


	/**
	 *
	 */
	public CacheControlFilter(final MetaDataLocator metaDataLocator,
							  Request request, CacheControlSupport cacheControlSupport) {
		this.metaDataLocator = metaDataLocator;
		this.request = request;
		this.cacheControlSupport = cacheControlSupport;
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
		cacheControlSupport.setCacheType(cacheType);


		// Pass it down the chain
		handler.handle(parameters);
	}

	/**
	 * On component events IFF AJAX then also add headers
	 *
	 * @param parameters
	 * @param handler
	 * @throws IOException
	 */
	public void handle(ComponentEventRequestParameters parameters,
					   ComponentEventRequestHandler handler) throws IOException {

		if (request.isXHR()) {
			CacheType cacheType = metaDataLocator.findMeta(CacheControlTransformer.CACHE_TYPE_METADATA,
					parameters.getContainingPageName(), CacheType.class);
			cacheControlSupport.setCacheType(cacheType);
		}
		// Pass it down the chain NOTE by the time this returns the AJAX response will have gone so we can't work after this.
		handler.handle(parameters);
	}
}
