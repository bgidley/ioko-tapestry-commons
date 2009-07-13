package uk.co.ioko.tapestry.mixins.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.SymbolConstants;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 12:28:00 PM
 */
@SubModule(MixinsModule.class)
public class TestModule {
	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {

		configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
	}

	public static void bind(ServiceBinder binder) {
		binder.bind(MyDataGenerator.class);	
	}
}
