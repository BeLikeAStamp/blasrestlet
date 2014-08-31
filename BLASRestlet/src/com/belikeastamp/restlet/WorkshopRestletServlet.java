package com.belikeastamp.restlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belikeastamp.restlet.model.Inscription;
import com.belikeastamp.restlet.model.Workshop;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

public class WorkshopRestletServlet extends HttpServlet {

	/*static {
		ObjectifyService.register(Workshop.class);
	}*/

	private static final long serialVersionUID = -2152739529939732870L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(Workshop.class);
		Objectify ofy = ObjectifyService.begin();

		String id = req.getParameter("id");
		
		List<Workshop> l;
		
		Query<Workshop> query = ofy.load().type(Workshop.class);
		
		if (id != null)
			query = query.filter("id", Long.valueOf(id));
		
		l = query.list();	
		
		Gson gson = new Gson();
	    String json = gson.toJson(l);
	    System.out.println(json);

	    resp.setHeader("User-Agent", "My Custom Header");
	    resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String theme = req.getParameter("theme");
		String address = req.getParameter("address");
		String town = req.getParameter("town");
		String date = req.getParameter("date");
		String hostname = req.getParameter("hostname");
		Integer capacity = Integer.valueOf(req.getParameter("capacity"));
		Integer registered = Integer.valueOf(req.getParameter("registered"));
		Integer price = Integer.valueOf(req.getParameter("price"));
		
		Workshop ws = new Workshop(theme,address,hostname,town,date,capacity, registered, price);
		
		ObjectifyService.register(Workshop.class);
		Objectify ofy = ObjectifyService.begin();
		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy.save().entity(ws).now();
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		String theme = req.getParameter("theme");
		String address = req.getParameter("address");
		String town = req.getParameter("town");
		String date = req.getParameter("date");
		String hostname = req.getParameter("hostname");
		Integer capacity = Integer.valueOf(req.getParameter("capacity"));
		Integer registered = Integer.valueOf(req.getParameter("registered"));
		Integer price = Integer.valueOf(req.getParameter("price"));
		
		Workshop ws = new Workshop(theme,address,hostname,town,date,capacity, registered, price);
		ws.setId(id);
		
		ObjectifyService.register(Workshop.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.save().entity(ws).now();
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		ObjectifyService.register(Workshop.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.delete().type(Workshop.class).id(id).now();
	}

}
