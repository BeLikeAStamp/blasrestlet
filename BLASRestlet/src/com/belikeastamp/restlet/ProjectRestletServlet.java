package com.belikeastamp.restlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belikeastamp.restlet.model.Project;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

public class ProjectRestletServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4543801344958314134L;

	private final static Logger LOGGER = Logger.getLogger(ProjectRestletServlet.class.getName()); 
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		LOGGER.setLevel(Level.INFO);
		ObjectifyService.register(Project.class);
		Objectify ofy = ObjectifyService.begin();
		//Query query 
		String status = req.getParameter("status");
		String name = req.getParameter("name");
		String userId = req.getParameter("userid");
		
		List<Project> l;
		Query<Project> query = ofy.load().type(Project.class);
		
		if (name != null) {
			query = query.filter("name", name);
		}
		
		if (status != null)
			query = query.filter("status", Integer.valueOf(status));
		
		if (userId != null)
			query = query.filter("userId", Long.valueOf(userId));
		
		l = query.list();			
		
		
		LOGGER.info("Taille de l ='"+l.size()+"'");
		Gson gson = new Gson();
		String json = gson.toJson(l);
		System.out.println(json);

		resp.setHeader("User-Agent", "My Custom Header");
		resp.getWriter().write(json);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Long userId = Long.valueOf(req.getParameter("userid"));
		String name = req.getParameter("name");
		String subDate = req.getParameter("subdate");
		String theme = req.getParameter("theme");
		String type = req.getParameter("type");
		String orderDate = req.getParameter("orderdate");
		String perso = req.getParameter("perso");
		Integer status = Integer.valueOf(req.getParameter("status"));
		Integer quantity = Integer.valueOf(req.getParameter("quantity"));
		String colors = req.getParameter("colors");
		
		Project p = new Project(userId, name, subDate, status, theme, type, orderDate, quantity, perso);
		p.setColors(colors);
		
		ObjectifyService.register(Project.class);
		Objectify ofy = ObjectifyService.begin();
		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy.save().entity(p).now();
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		Long userId = Long.valueOf(req.getParameter("userid"));
		String name = req.getParameter("name");
		String subDate = req.getParameter("subdate");
		String theme = req.getParameter("theme");
		String type = req.getParameter("type");
		String orderDate = req.getParameter("orderdate");
		String perso = req.getParameter("perso");
		Integer status = Integer.valueOf(req.getParameter("status"));
		Integer quantity = Integer.valueOf(req.getParameter("quantity"));
		String colors = req.getParameter("colors");
		
		Project p = new Project(userId, name, subDate, status, theme, type, orderDate, quantity, perso);

		p.setId(id);
		p.setColors(colors);
		
		ObjectifyService.register(Project.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.save().entity(p).now();
	}

	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		ObjectifyService.register(Project.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.delete().type(Project.class).id(id).now();
	}

}
