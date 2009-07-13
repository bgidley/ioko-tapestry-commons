var ioko = Class.create();

ioko.BoundCheckboxMaster = Class.create({
    initialize: function(clientId)
    {
		this.childArray = new Array();
        this.element = $(clientId);
        this.element.observe("click", this.onClick.bindAsEventListener(this));
        this.element.__boundcheckbox=this;
    },

    registerChild : function(child) {
    	this.childArray.push(child);
    },
    
    onClick : function(event)
    {
        for each ( var child in this.childArray ) {
        	// TODO: we seem to register everything twice 
        	// but the second set are not valid (child.element is null)
        	child.element.checked=this.element.checked;
        }
        return false;
    }
});

ioko.BoundCheckboxChild = Class.create({
    initialize: function(clientId, masterId)
    {
		this.childArray = new Array();
        this.element = $(clientId);
        this.element.observe("click", this.onClick.bindAsEventListener(this));
        
        this.master = $(masterId);
        this.master.__boundcheckbox.registerChild(this);
    },

    onClick : function(event)
    {
        if ( this.master.checked && !this.element.checked ) {
        	this.master.checked=false;
        }
        return false;
    }
});

Tapestry.Initializer.boundCheckboxLoad = function(clientId, masterId) {
	if ( masterId != "" ) {
		new ioko.BoundCheckboxChild(clientId, masterId);
	} else {
		new ioko.BoundCheckboxMaster(clientId);
	}
};
