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

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.PageRenderRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheType;

/**
 * Module for cache control.
 * <p/>
 * This registers the required services and binds them into tapestry's lifecycle
 */
public class CacheControlModule {

	private static final Logger logger = LoggerFactory.getLogger(CacheControlModule.class);

	public static void bind(ServiceBinder binder) {
		binder.bind(CacheControlFilter.class);

	}

	public void contributePageRenderRequestHandler(OrderedConfiguration<PageRenderRequestFilter> configuration,
												   final CacheControlFilter cacheControlFilter) {

		configuration.add("CacheControlFilter", cacheControlFilter);
	}

	public static void contributeComponentClassTransformWorker(
			OrderedConfiguration<ComponentClassTransformWorker> configuration) {
		configuration.add("CacheControlTransformer", new CacheControlTransformer());

	}

	public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration) {
		// Set the default caching to none
		configuration.add(CacheControlTransformer.CACHE_TYPE_METADATA, CacheType.NEVER.name());
		// 5 minutes
		configuration.add("cacheControl.short", String.valueOf(300));
		// 1 hour
		configuration.add("cacheControl.medium", String.valueOf(3600));
		// 1 day
		configuration.add("cacheControl.long", String.valueOf(86400));
		// 10 years
		configuration.add("cacheControl.farFuture", String.valueOf(315360000));

	}

	public static void contributeTypeCoercer(Configuration<CoercionTuple> configuration) {
		Coercion<String, CacheType> coercion = new Coercion<String, CacheType>() {
			public CacheType coerce(String input) {
				if (input == CacheType.NEVER.name()) {
					return CacheType.NEVER;
				}
				if (input == CacheType.SHORT.name()) {
					return CacheType.SHORT;
				}
				if (input == CacheType.MEDIUM.name()) {
					return CacheType.MEDIUM;
				}
				if (input == CacheType.LONG.name()) {
					return CacheType.LONG;
				}
				if (input == CacheType.FAR_FUTURE.name()) {
					return CacheType.FAR_FUTURE;
				} else {
					// We don't understand this and that should be impossible
					logger.error("CacheType is not defined in enum. This shouldn't be possible");
					throw new RuntimeException("CacheType is not defined in enum. This shouldn't be possible");
				}
			}
		};

		configuration.add(new CoercionTuple<String, CacheType>(String.class, CacheType.class, coercion));
	}
}
