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
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.PartialMarkupRendererFilter;
import org.apache.tapestry5.services.PartialMarkupRenderer;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheType;

import java.util.Calendar;
import java.util.Locale;

/**
 * Adds cache control around all pages as they render
 * <p/>
 * Created by IntelliJ IDEA. User: ben Date: Jun 29, 2009 Time: 12:04:56 PM
 */
public class CacheControlMarkupRenderer implements MarkupRendererFilter, PartialMarkupRendererFilter {
	private final Asset script;

	private final Response response;

	private final CacheControlSupport cacheControlSupport;
	private final Environment environment;
	private boolean enableEventHeaders;
	private CacheControlHeaderWriter cacheControlHeaderWriter;

	public CacheControlMarkupRenderer(final Environment enviroment,
			@Path("/uk/co/ioko/tapestry/cacheControl/CacheControl.js") final Asset script,
			@Symbol("cacheControl.enableEventHeaders") final boolean enableEventHeaders,
			final Response response,
			final CacheControlSupport cacheControlSupport,
			final CacheControlHeaderWriter cacheControlHeaderWriter) {
		this.script = script;

		this.response = response;
		this.cacheControlSupport = cacheControlSupport;
		this.environment = enviroment;
		this.enableEventHeaders = enableEventHeaders;
		this.cacheControlHeaderWriter = cacheControlHeaderWriter;
	}

	public void renderMarkup(MarkupWriter writer, JSONObject reply, PartialMarkupRenderer renderer) {
		renderer.renderMarkup(writer, reply);
		CacheType cacheType = cacheControlSupport.getCacheType();
		cacheControlHeaderWriter.setHeaderOnResponse(response, cacheType);
	}

	public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {

		if (enableEventHeaders) {
			// Regardless add our javascript tweak to allow AJAX to be CacheControlled
			RenderSupport renderSupport = environment.peek(RenderSupport.class);
			renderSupport.addScriptLink(script);
		}

		renderer.renderMarkup(writer);

		CacheType cacheType = cacheControlSupport.getCacheType();
		cacheControlHeaderWriter.setHeaderOnResponse(response, cacheType);
	}


}
