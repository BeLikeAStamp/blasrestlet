package com.belikeastamp.restlet;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.restlet.resource.ServerResource;

import com.belikeastamp.restlet.model.Workshop;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class WorkshopController extends ServerResource implements
WorkshopControllerInterface {

	public WorkshopController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Workshop ws) {
		// TODO Auto-generated method stub
		ObjectifyService.register(Workshop.class);
		Objectify ofy = ObjectifyService.begin();

		Workshop tp = new Workshop();
		tp.setAddress(ws.getAddress());
		tp.setCapacity(ws.getCapacity());
		tp.setDate(ws.getDate());
		tp.setHostname(ws.getHostname());
		tp.setRegistered(ws.getRegistered());
		tp.setTheme(ws.getTheme());
		tp.setTown(ws.getTown());

		Logger log = Logger.getGlobal();
		log.fine("I'm HERE !");
		
		ofy.save().entity(tp).now();
	}

	@Override
	public Container getAllWorkshops() {
		// TODO Auto-generated method stub
		Container content = null;
		List workshops = new ArrayList();
		
		ObjectifyService.register(Workshop.class);
		Objectify ofy = ObjectifyService.begin();

		List<Workshop> l = ofy.load().type(Workshop.class).list();
		for(Workshop w : l)
			workshops.add(w);
		Logger log = Logger.getGlobal();
		log.fine("I'm HERE !");
		
		content = new Container();
		content.setWkList(workshops);
		return content;
	}

	@Override
	public void update(Workshop ws) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
