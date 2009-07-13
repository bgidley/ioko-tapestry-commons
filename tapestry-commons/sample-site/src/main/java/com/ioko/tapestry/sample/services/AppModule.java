package com.ioko.tapestry.sample.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.SubModule;

import uk.co.ioko.tapestry.jquery.services.JqueryModule;
import uk.co.ioko.tapestry.mixins.services.MixinsModule;


// this is only required when running in multi-module mode in an IDE because the manifest entry will not be there
@SubModule({ MixinsModule.class, JqueryModule.class })
public class AppModule {
	public static void bind(ServiceBinder binder) {
		
	}

	public static void contributeApplicationDefaults(
			MappedConfiguration<String, String> configuration) {

		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
	}
}
