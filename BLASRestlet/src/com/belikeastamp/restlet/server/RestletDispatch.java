package com.belikeastamp.restlet.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.belikeastamp.restlet.WorkshopController;

public class RestletDispatch extends Application {
	@Override
	public synchronized Restlet createInboundRoot()
	{
		final Router router = new Router(getContext());
		router.attach("/workshop", WorkshopController.class);
		return router;
	}
}
