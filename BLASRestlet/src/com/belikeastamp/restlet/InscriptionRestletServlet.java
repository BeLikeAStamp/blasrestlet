package com.belikeastamp.restlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belikeastamp.restlet.model.Inscription;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class InscriptionRestletServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6301082063204167890L;
	private static final String TAG_ID = "id";
	private static final String TAG_WID = "workshopid";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "number";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_EXPERT = "expertise";
	private static final String TAG_PART = "participant";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(Inscription.class);
		Objectify ofy = ObjectifyService.begin();

		List<Inscription> l = ofy.load().type(Inscription.class).list();
		
		Gson gson = new Gson();
	    String json = gson.toJson(l);
	    System.out.println(json);
	    resp.setHeader("User-Agent", "My Custom Header");
		resp.getWriter().write(json);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Long workshopId = Long.valueOf(req.getParameter(TAG_WID));
		String name = req.getParameter(TAG_NAME);
		String phone = req.getParameter(TAG_PHONE);
		String email = req.getParameter(TAG_EMAIL);
		String expertise = req.getParameter(TAG_EXPERT);
		Integer partcipants =  Integer.valueOf(req.getParameter(TAG_PART));
	
		Inscription i = new Inscription(workshopId, name, phone, email, expertise, partcipants);
		ObjectifyService.register(Inscription.class);
		Objectify ofy = ObjectifyService.begin();
		
		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy.save().entity(i).now();
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		Long id = Long.valueOf(req.getParameter(TAG_ID));
		Long workshopId = Long.valueOf(req.getParameter(TAG_WID));
		String name = req.getParameter(TAG_NAME);
		String phone = req.getParameter(TAG_PHONE);
		String email = req.getParameter(TAG_EMAIL);
		String expertise = req.getParameter(TAG_EXPERT);
		Integer partcipants =  Integer.valueOf(req.getParameter(TAG_PART));
	
		Inscription i = new Inscription(workshopId, name, phone, email, expertise, partcipants);
		i.setId(id);
		
		ObjectifyService.register(Inscription.class);
		Objectify ofy = ObjectifyService.begin();
		
		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy.save().entity(i).now();
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter(TAG_ID));
		ObjectifyService.register(Inscription.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.delete().type(Inscription.class).id(id).now();
	}

}
