package uk.co.ioko.tapestry.properties;

import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Loads properties from files on the class path and returns them as symbols when requested. Currently this classes
 * class loader will resolve conflicts by its own strategy, so the higher up the classpath file takes precedence (first
 * loaded wins) to override properties you can
 * <ul>
 * <li>Use a -Dsystem.propety=yourValue or</li>
 * <li>put that property in a file higher up the classpath</li>
 * </ul>
 * <br/> If two files contain the same key then the value in the file listed last will be the symbols value. <br/> The
 * property key names are lower cased before they are put in the map so that we can be case insensitive like T5 is
 * general.
 *
 * @author russellb
 */
public class PropertiesFileSymbolProvider {
	private Map<String, String> symbols = new HashMap<String, String>();

	/**
	 * Inits a map of all properties from all files named in propertiesFiles
	 *
	 * @param propertiesFiles
	 *            an array of strings. These should be names of properties files on the class path
	 * @throws java.io.IOException
	 *             if Properties.load throws one. Shouldn't happen since we check the resource's existenace first
	 */
	public PropertiesFileSymbolProvider(String[] propertiesFiles) throws IOException {
		Assert.notEmpty(propertiesFiles, "Please provide at least one file name");
		Assert.noNullElements(propertiesFiles, "Cannot load from null file name");
		// enumerate over the array of file names and try to load each one.
		for (String file : propertiesFiles) {
			InputStream stream = PropertiesFileSymbolProvider.class.getResourceAsStream(file);
			if (stream != null) {
				// in the case that the file is found then load into a properties object
				Properties properties = new Properties();
				properties.load(stream);
				// for each property create an entry in the symbols map
				for (Map.Entry<Object, Object> entry : properties.entrySet()) {
					// store key as lower case (so we can ignore case
					symbols.put(((String) entry.getKey()).toLowerCase(), (String) entry.getValue());
				}
			}
		}
	}

	/**
	 * Delegates to PropertiesFileSymbolProvider(String[] propertiesFiles) throws IOException; but wraps file in an
	 * array
	 *
	 * @param file
	 *            a String of a properties file name
	 * @throws java.io.IOException if there is a problem reading the named file
	 */
	public PropertiesFileSymbolProvider(String file) throws IOException {
		this(new String[] { file });
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apache.tapestry5.ioc.services.SymbolProvider#valueForSymbol(java.lang.String)
	 */
	public String valueForSymbol(String symbolName) {
		// lower case key so we can ignore case
		return symbols.get(symbolName.toLowerCase());
	}

}
