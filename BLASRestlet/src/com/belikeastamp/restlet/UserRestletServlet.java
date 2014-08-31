package com.belikeastamp.restlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private final static Logger LOGGER = Logger.getLogger(UserRestletServlet.class.getName()); 
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		LOGGER.setLevel(Level.INFO);
		ObjectifyService.register(User.class);
		Objectify ofy = ObjectifyService.begin();
		//Query query 
		String email = req.getParameter("email");
		String userId = req.getParameter("id");
		
		List<User> l;
		
		if (email == null) {
			
			if (userId == null) {
				l = ofy.load().type(User.class).list();
				LOGGER.info("Email null && userId null");
			}
			else
			{
				//ofy.load().key(userId)
				l = ofy.load().type(User.class).filter("id", Long.valueOf(userId)).list();			
				LOGGER.info("UserId NON null");
			}
			
		}
		else
		{
			l = ofy.load().type(User.class).filter("email", email).list();			
			LOGGER.info("Email NON null");
		}

		LOGGER.info("Taille de l ='"+l.size()+"'");
		Gson gson = new Gson();
		String json = gson.toJson(l);
		System.out.println(json);

		resp.setHeader("User-Agent", "My Custom Header");
		resp.setCharacterEncoding("UTF-8");
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
