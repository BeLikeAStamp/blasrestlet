package com.belikeastamp.restlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belikeastamp.restlet.model.User;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class UserRestletServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7672697487205312984L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(User.class);
		Objectify ofy = ObjectifyService.begin();

		List<User> l = ofy.load().type(User.class).list();
		
		Gson gson = new Gson();
	    String json = gson.toJson(l);
	    System.out.println(json);

	    resp.setHeader("User-Agent", "My Custom Header");
		resp.getWriter().write(json);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String firstname = req.getParameter("firstname");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		Boolean isPartener = (req.getParameter("partener").equals("true")) ? true : false;
		Boolean isHost = (req.getParameter("host").equals("true")) ? true : false;
		
		User u = new User(firstname, name, phone, email);
		u.setAddress(address);
		u.setIsHost(isHost);
		u.setIsPartener(isPartener);
		
		ObjectifyService.register(User.class);
		Objectify ofy = ObjectifyService.begin();
		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy.save().entity(u).now();
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		String firstname = req.getParameter("firstname");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		Boolean isPartener = (req.getParameter("partener").equals("true")) ? true : false;
		Boolean isHost = (req.getParameter("host").equals("true")) ? true : false;
		
		User u = new User(firstname, name, phone, email);
		u.setId(id);
		u.setAddress(address);
		u.setIsHost(isHost);
		u.setIsPartener(isPartener);
		
		ObjectifyService.register(User.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.save().entity(u).now();
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		ObjectifyService.register(User.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.delete().type(User.class).id(id).now();
	}

}
