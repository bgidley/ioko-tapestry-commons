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

package uk.co.ioko.tapestry.caching.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Environment;
import uk.co.ioko.tapestry.caching.services.ContentCache;
import uk.co.ioko.tapestry.caching.services.RenderSupportPlayer;
import uk.co.ioko.tapestry.caching.services.RenderSupportRecorder;
import uk.co.ioko.tapestry.caching.services.support.CacheRegion;
import uk.co.ioko.tapestry.caching.services.support.CachedContent;

@SupportsInformalParameters
public class Cache {

	private static final String CACHE_KEY_SEPARATOR = ":";

	/**
	 * Specifies how long this content should be cached for - should be 'short', 'medium' or 'long'
	 * (case-insensitive) as defined by the CacheRegion enum.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, value="medium")
	private CacheRegion cacheRegion;

	/**
	 * By default, cached components are keyed on their full class name.  If you wish to provide your
	 * own key you can do so by providing a cacheKey.  Note that this will override the cacheKeySuffix
	 * parameter.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String cacheKey;

	/**
	 * By default, cached components are keyed on their full class name.  If this granularity is
	 * insufficient, you can provide a cacheKeySuffix that will differentiate between different
	 * instances of the same component.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String cacheKeySuffix;

	@Inject
	private ComponentResources componentResources;

	// ==== STUFF REQUIRED FOR JS/CSS CACHING ====

	@Environmental
	private RenderSupport renderSupport;

	@Inject
	private Environment environment;

	@Inject
	private ContentCache contentCache;

	// ===========================================

	@Parameter(allowNull = false, defaultPrefix = BindingConstants.LITERAL)
	private String element = componentResources.getElementName();

	private Element currentElement;

	private String key;

	boolean setupRender(MarkupWriter markupWriter) {
		key = getComponentKey();
		CachedContent content = contentCache.getContent(key, cacheRegion);
		if (content == null) {
			return true;
		}
		markupWriter.writeRaw(content.getContent());
		// this will re-call all the methods we called previously
		RenderSupportPlayer rsPlayer = new RenderSupportPlayer(renderSupport);
		rsPlayer.playbackMethodCalls(content.getMethodCalls());
		return false;
	}

	@BeginRender
	void beginRender(MarkupWriter markupWriter) {
		currentElement = markupWriter.element(element);
		componentResources.renderInformalParameters(markupWriter);

		// System.out.println("begin render: " + ++br);
		RenderSupportRecorder rsRecorder = new RenderSupportRecorder(renderSupport);
		// this replaces the instance of RenderSupport for this thread only
		environment.push(RenderSupport.class, rsRecorder);
	}

	@AfterRender
	void afterRender(MarkupWriter markupWriter) {

		// end markup
		markupWriter.end();

		// System.out.println("after render: " + ++ar);
		// set RenderSupport back to whatever it was before
		RenderSupportRecorder rsRecorder = (RenderSupportRecorder) environment.pop(RenderSupport.class);

		// cache content
		CachedContent content = new CachedContent();
		content.setContent(currentElement.toString());
		content.setMethodCalls(rsRecorder.getMethodCalls());
		contentCache.addContent(key, content, cacheRegion);
	}

	/**
	 * Returns the key that we want to cache our component against.  Depending on the parameters, this
	 * is either the cacheKey, the component name + cacheKeySuffix or just the component name.  The
	 * cacheKey will take precedence over any other parameters.
	 */
	private String getComponentKey() {
		// if cacheKey is set then it overrides everything else
		if ((cacheKey == null) || (cacheKey.length() == 0)) {
			// otherwise use the content key as a starting point
			String myCacheKey = componentResources.getCompleteId();
			// and add the cache key suffix if there is one
			if ((cacheKeySuffix != null) && (cacheKeySuffix.length() > 0)) {
				myCacheKey += CACHE_KEY_SEPARATOR + cacheKeySuffix;
			}
			return myCacheKey;
		}
		return cacheKey;
	}
}