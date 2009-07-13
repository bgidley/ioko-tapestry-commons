package uk.co.ioko.tapestry.mixins.services;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.services.LibraryMapping;

import uk.co.ioko.tapestry.javascript.services.JavascriptModule;

@SubModule({JavascriptModule.class})
public class MixinsModule {
	public static void contributeJavascriptStack(
			@Path("classpath:/uk/co/ioko/tapestry/mixins/jquery.expose-1.0.1.min.js") Asset expose,
			@Path("classpath:/uk/co/ioko/tapestry/mixins/jquery.overlay-1.0.1.min.js") Asset overlay,
			OrderedConfiguration<Asset> stack) {

		stack.add("jquery-expose", expose, "after:jquery");
		stack.add("jquery-overlay", overlay, "after:jquery");
	}

	public static void contributeComponentClassResolver(
			Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("ioko",
				"uk.co.ioko.tapestry.mixins"));
	}

	public static void contributeClasspathAssetAliasManager(
			MappedConfiguration<String, String> configuration) {

		// TODO: get this from the maven build
//		configuration.add("ioko/1.0.0", "uk/co/ioko/tapestry/mixins");
	}
}
