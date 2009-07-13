package uk.co.ioko.tapestry.jquery.services;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Symbol;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 8, 2009 Time: 4:39:50 PM
 */
public class JqueryModule {

	public static void contributeJavascriptStack(
			@Path("classpath:/jquery-1.3.2.js") Asset jqueryDev,
			@Path("classpath:/jquery-1.3.2.min.js") Asset jquery,
			@Path("classpath:/jquery-noconflict.js") Asset noConflict,
			@Symbol("tapestry.production-mode") Boolean productionMode,
			OrderedConfiguration<Asset> stack) {
		if (productionMode) {
			stack.add("jquery-base", jquery);
		} else {
			stack.add("jquery-base", jqueryDev);
		}
		stack.add("jquery", noConflict, "after:jquery-base");
	}
}
