package com.ioko.tapestry.sample.pages;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.HashSet;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.TapestryFilter;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Checkbox;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Index {
	@Inject
	@Property
	private Block contentBlock;

	@Property
	private String textValue;
	
	@InjectComponent
	@Property
	private Checkbox selectAllCheckbox;

	@Property
	@Persist
	private boolean selectAll;

	@Persist
	private HashSet<URL> selectedSet;

	@Property
	private URL current;
	
	public Block onActionFromTestLink() {
		textValue=new Date().toString();
		return contentBlock;
	}

	public Block onSuccessFromTestForm() {
		return contentBlock;
	}

	public URL[] getDummyItems() {
		return ((URLClassLoader) TapestryFilter.class.getClassLoader()).getURLs();
	}
	
	public boolean getCurrentSelected() {
		if ( selectedSet == null ) {
			selectedSet=new HashSet<URL>();
		}
		return selectedSet.contains(current);
	}
	
	public void setCurrentSelected(boolean value) {
		if ( selectedSet == null ) {
			selectedSet=new HashSet<URL>();
		}
		if ( value ) {
			selectedSet.add(current);
		} else {
			selectedSet.remove(current);
		}		
	}
}
