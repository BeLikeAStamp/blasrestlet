package com.belikeastamp.restlet;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import com.belikeastamp.restlet.model.Workshop;

public interface WorkshopControllerInterface {
	@Put
	 void create(Workshop ws);

	 @Get
	 Container getAllWorkshops();
	 
	 @Post
	 void update(Workshop ws);
	 
	 @Delete
	 void delete(Long id);
}
