package com.belikeastamp.restlet;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.belikeastamp.restlet.model.Workshop;

public interface WorkshopControllerInterface {
	@Put
	 void create(Workshop ws);

	 @Get
	 Container getAllWorkshops();
}
