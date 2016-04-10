package com.appdynamics.extension.hpOM.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.appdynamics.extension.hpOM.User;
import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;

import io.dropwizard.auth.Auth;


@Path("/postMsg")
public class hpOMAPI {
	private static Logger logger = Logger.getLogger(hpOMAPI.class);

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)

	public Response postOVO(String incomingData) throws Exception {

		String returnString = null;
		// JSONP jsonObject;

		try {
			System.out.println("incomingData: " + incomingData);
			logger.info("incomingData: " + incomingData);

			// JSONObject jsonData = new JSONObject(incomingData);
			// System.out.println( "jsonData: " + jsonData.toString() );

			int http_code = 200;

			if (http_code == 200) {
				// logger.info("OVO Message Sent (description): " +
				// jsonData.optString("description")
				// + " | OVO Message Sent (id): " + jsonData.optString("id"));
				// returnString = "OVO Message Sent (description): " +
				// jsonData.optString("description")
				// + " | OVO Message Sent (id): " + jsonData.optString("id");
				returnString = "OVO Message Sent: ";
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request.  Please check the format of your request.")
					.build();
		}

		return Response.ok(returnString).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/secured")
	
	public Response postAuthOVO(@Auth User user, String incomingData) throws Exception {

		String returnString = null;
		// JSONP jsonObject;

		try {
			System.out.println("incomingData: " + incomingData);
			logger.info("incomingData: " + incomingData);

			// JSONObject jsonData = new JSONObject(incomingData);
			// System.out.println( "jsonData: " + jsonData.toString() );

			int http_code = 200;

			if (http_code == 200) {
				// logger.info("OVO Message Sent (description): " +
				// jsonData.optString("description")
				// + " | OVO Message Sent (id): " + jsonData.optString("id"));
				// returnString = "OVO Message Sent (description): " +
				// jsonData.optString("description")
				// + " | OVO Message Sent (id): " + jsonData.optString("id");
				returnString = "Secure OVO Message Sent: ";
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request.  Please check the format of your request.")
					.build();
		}

		return Response.ok(returnString).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/securedGet")
	public String getSecuredGreeting(@Auth User user) {
		return "Hello secured world";
	}

}
