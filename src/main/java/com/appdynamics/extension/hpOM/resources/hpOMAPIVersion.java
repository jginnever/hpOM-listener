package com.appdynamics.extension.hpOM.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import org.apache.log4j.Logger;

@Path("/version")
public class hpOMAPIVersion {
	// private static Logger logger = Logger.getLogger(hpOMAPI.class);

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting() {
		return "HP OM REST API version 1.0";
	}

}
