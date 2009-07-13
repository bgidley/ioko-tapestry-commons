package uk.co.ioko.tapestry.mixins.mixins;

import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.corelib.components.Grid;

public class DisableSorting {
	@InjectContainer
	private Grid container;

    @BeginRender
	void begin() {
    	for ( Object n : container.getDataModel().getPropertyNames() ) {
    		// TODO: not sure why we cannot iterate as strings, cos interface says they are!
    		container.getDataModel().get(n.toString()).sortable(false);
    	}
	}
}
