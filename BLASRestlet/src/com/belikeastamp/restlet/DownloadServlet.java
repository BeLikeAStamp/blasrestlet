package com.belikeastamp.restlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belikeastamp.restlet.model.FileStorage;
import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;


public class DownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5707515699015240565L;

	private final static Logger LOGGER = Logger.getLogger(DownloadServlet.class.getName()); 


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectifyService.register(FileStorage.class);
		Objectify ofy = ObjectifyService.begin();
		
		Long correspondingId = Long.valueOf(req.getParameter("correspondance"));
		
		Query<FileStorage> query = ofy.load().type(FileStorage.class);
		
		query = query.filter("correspondingId", correspondingId);
		

		List<FileStorage> l = query.list();	
		
		Blob image = l.get(0).getFile();
		 
	    // serve the first image 
	    resp.setContentType("image/jpeg");
	    resp.getOutputStream().write(image.getBytes());
		
	}
	

}
