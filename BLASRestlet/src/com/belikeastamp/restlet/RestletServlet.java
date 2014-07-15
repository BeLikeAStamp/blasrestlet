package com.belikeastamp.restlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RestletServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2152739529939732870L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Enumeration parameterNames = 
				req.getParameterNames();

		// acquire text stream for response
		PrintWriter out = resp.getWriter ();

		while (parameterNames.hasMoreElements()) {
			String name = 
					(String)parameterNames.nextElement();
			String value = req.getParameter(name);
			out.println(name + " = " + value + "<br/>");
		}
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();

		// servlet configuration initialization parameters
		Enumeration params = 
				getServletConfig().getInitParameterNames();
		while (params.hasMoreElements()) 
		{
			String param = (String) params.nextElement();
			String value = 
					getServletConfig().getInitParameter(param);
			writer.println(param + " = " + value);
		}
	}

}
