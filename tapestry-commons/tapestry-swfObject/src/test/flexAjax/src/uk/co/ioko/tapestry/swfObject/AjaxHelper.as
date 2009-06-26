package uk.co.ioko.tapestry.swfObject
{
	import com.adobe.serialization.json.JSON;
	
	import flash.events.Event;
	import flash.external.ExternalInterface;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	
	import mx.utils.URLUtil;
	
	public class AjaxHelper
	{
		private var url:URLRequest
		private var dataReciever:Function;
		public var lastData:Object;
		
		public function AjaxHelper(passedUrl:String, dataReciever:Function)
		{
			// Passed URL is relative to current site so we need to add the domain name etc on the front
			var pageUrl:String = ExternalInterface.call("window.location.href.toString");
			var fixedUrl:String = URLUtil.getProtocol(pageUrl) + "://" + URLUtil.getServerNameWithPort(pageUrl) + passedUrl;
			
			url = new URLRequest(fixedUrl);
			this.dataReciever = dataReciever;		}
		
		public function ExecuteAjax():void{
			var loader:URLLoader = new URLLoader;
			loader.addEventListener(Event.COMPLETE, function(e:Event):void {
			   lastData = JSON.decode(loader.data);
			   dataReciever(lastData);
		    })
			loader.load(url);
		}

	}
}