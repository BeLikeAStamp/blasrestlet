package com.belikeastamp.restlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belikeastamp.restlet.model.Tutorial;
import com.google.gson.Gson;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class TutorialRestletServlet extends HttpServlet {

	/*static {
		ObjectifyService.register(Tutorial.class);
	}*/

	private static final long serialVersionUID = -2152739529939732870L;
	private static final String TAG_ID = "id";
	private static final String TAG_TITLE = "title";
	private static final String TAG_FILE = "file";
	private static final String TAG_AVAIL = "availability";
	private static final String TAG_DATE = "date";
	private static final String TAG_DEMAND = "demand";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(Tutorial.class);
		Objectify ofy = ObjectifyService.begin();

		List<Tutorial> l = ofy.load().type(Tutorial.class).list();
		
		Gson gson = new Gson();
	    String json = gson.toJson(l);
	    System.out.println(json);
		//resp.setContentType("text/plain");
		//resp.getWriter().println("Hello, world : '"+json+"'");
	    resp.setHeader("User-Agent", "My Custom Header");
		resp.getWriter().write(json);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String title = req.getParameter(TAG_TITLE);
		String avail = req.getParameter(TAG_AVAIL);
		String file = req.getParameter(TAG_FILE);
		String date = req.getParameter(TAG_DATE);
		String demand = req.getParameter(TAG_DEMAND);

		Tutorial tuto = new Tutorial(title, Boolean.valueOf(avail), file, date, Integer.valueOf(demand));
		
		ObjectifyService.register(Tutorial.class);
		Objectify ofy = ObjectifyService.begin();
		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy.save().entity(tuto).now();
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter(TAG_ID));
		String title = req.getParameter(TAG_TITLE);
		String avail = req.getParameter(TAG_AVAIL);
		String file = req.getParameter(TAG_FILE);
		String date = req.getParameter(TAG_DATE);
		String demand = req.getParameter(TAG_DEMAND);

		Tutorial tuto = new Tutorial(title, Boolean.valueOf(avail), file, date, Integer.valueOf(demand));
		tuto.setId(id);
		
		ObjectifyService.register(Tutorial.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.save().entity(tuto).now();
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Long id = Long.valueOf(req.getParameter(TAG_ID));
		ObjectifyService.register(Tutorial.class);
		Objectify ofy = ObjectifyService.begin();
		ofy.delete().type(Tutorial.class).id(id).now();
	}

}
