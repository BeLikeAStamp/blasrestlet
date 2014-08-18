package com.belikeastamp.restlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;

import com.belikeastamp.restlet.model.FileStorage;
import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;
import com.googlecode.objectify.cmd.Query;


public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5707515699015240565L;

	private final static Logger LOGGER = Logger.getLogger(UploadServlet.class.getName()); 

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  


		LOGGER.setLevel(Level.INFO);

		Long correspondingId = Long.valueOf(request.getParameter("correspondance"));

		LOGGER.info("ID ='"+correspondingId+"'");

		String fileName = "";

		MultipartParser parser = new MultipartParser(request,10 * 1024 * 1024);
		Part part = null; 

		while ((part = (Part)parser.readNextPart()) != null) {
			if (part.isFile()) {
				FilePart filePart = (FilePart) part;
				fileName = (String) filePart.getFileName();

				LOGGER.info("fileName ='"+fileName+"'");

				InputStream in = filePart.getInputStream();
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				Streams.copy(in, bytes, true /* close stream after copy */);
				Blob blob = new Blob(bytes.toByteArray());
				FileStorage tf = new FileStorage(correspondingId, blob); 
				ObjectifyService.register(FileStorage.class);
				Objectify ofy = ObjectifyService.begin();
				// Enregistrement de l'objet dans le Datastore avec Objectify
				ofy.save().entity(tf).now();
			}
			else
			{
				//Get the name of the parameter  
				String paramname = part.getName();  
				//Get ParamPart from the multipart request  
				LOGGER.info("paramname ='"+paramname+"'");
			}
		}



		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  


		out.print("successfully uploaded");  
	}

}
