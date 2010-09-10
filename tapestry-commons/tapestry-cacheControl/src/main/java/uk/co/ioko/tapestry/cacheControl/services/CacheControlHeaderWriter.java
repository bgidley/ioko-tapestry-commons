package uk.co.ioko.tapestry.cacheControl.services;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Response;
import uk.co.ioko.tapestry.cacheControl.annotations.CacheType;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jan 13, 2010 Time: 4:32:58 PM
 */
public class CacheControlHeaderWriter {

	private final int mediumCacheTime;
	private final int shortCacheTime;
	private final int longCacheTime;
	private final int farFutureCacheTime;

	private static final String CACHE_CONTROL_HEADER = "Cache-Control";
	private static final String PRAGMA_HEADER = "Pragma";

	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss z",
			TimeZone.getTimeZone("GMT"),
			Locale.UK);


	public CacheControlHeaderWriter(@Symbol("cacheControl.short") final int shortCacheTime,
			@Symbol("cacheControl.medium") final int mediumCacheTime,
			@Symbol("cacheControl.long") final int longCacheTime,
			@Symbol("cacheControl.farFuture") final int farFutureCacheTime) {

		this.longCacheTime = longCacheTime;
		this.shortCacheTime = shortCacheTime;
		this.mediumCacheTime = mediumCacheTime;
		this.farFutureCacheTime = farFutureCacheTime;


	}

	public void setHeaderOnResponse(Response response, CacheType cacheType) {
		if (!(cacheType == CacheType.NONE)) {
			// We should process headers
			if (cacheType == CacheType.NEVER) {
				addNeverHeaders(response);
			} else {
				addRelativeHeaders(response, calculate(cacheType));
			}
		}

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
	private void addNeverHeaders(Response response) {
		response.setHeader(CACHE_CONTROL_HEADER, "no-cache");
		response.setHeader(PRAGMA_HEADER, "No-Cache");

	}

	/**
	 * Add relative header
	 *
	 * @param timeInSeconds
	 */
	private void addRelativeHeaders(Response response, int timeInSeconds) {
		response.setHeader(CACHE_CONTROL_HEADER, String.format("max-age=%d", timeInSeconds));
		Calendar expires = Calendar.getInstance();
		expires.add(Calendar.SECOND, timeInSeconds);
		response.setHeader("Expires", DATE_FORMAT.format(expires));
	}
}
