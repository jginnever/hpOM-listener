package com.appdynamics.extension.hpOM.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
//import com.appdynamics.extension.hpopenview.Configuration;
import com.appdynamics.extension.hpopenview.api.Alert;
import com.appdynamics.extension.hpopenview.common.CommandExecutor;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.appdynamics.extension.hpOM.User;
import org.json.JSONObject;

import io.dropwizard.auth.Auth;

@Path("/postMsg")
public class hpOMAPI 
{
	private static Logger logger = Logger.getLogger(hpOMAPI.class);
	protected static String commandPath;
	protected static String[] appls;
	
	public hpOMAPI (String pathToExecutable, String[] acceptableAppls)
	{
		commandPath = pathToExecutable;
		appls = acceptableAppls; 
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)

	public Response postOVO(String incomingData) throws Exception {

		String returnString = null;
		
		try {
			System.out.println("incomingData: Message Group - " + incomingData);			
			JSONObject jsonData = new JSONObject(incomingData);

			Alert alert = new Alert();
    		alert.setApplication(jsonData.getString("Application").trim());
    		alert.setMsgGroup(jsonData.getString("MessageGroup").trim());
    		alert.setObject(jsonData.getString("Object").trim());
    		alert.setSeverity(jsonData.getString("Severity").trim());
    		alert.setMsgText(jsonData.getString("MsgText").trim());    	    		  

			logger.info("incomingData - " 
				+ " Message Group - " + alert.getMsgGroup()	
				+ ", Application - " + alert.getApplication()
				+ ", Object - " + alert.getObject()
				+ ", Severity - " + alert.getSeverity()
				+ ", MsgText - " + alert.getMsgText()			
			);
 
			if (checkApplication(alert.getApplication()))
			{
				if (processAlert(alert)) 
				{
					logger.info("Message Sent");	
					return Response.ok(returnString).build();
				} else {
					logger.info("Unable to process message");
					return Response.status(500).entity("Unable to process message").build();
				}
			
			} else {
				logger.info("Cannot process request.  Application group is not allowed.");
				return Response.status(500).entity("Unable to process Item").build();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request.  Please check the format of your request.")
					.build();
		}

	}

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/secured")
	
	public Response postAuthOVO(@Auth User user, String incomingData) throws Exception {

		String returnString = null;
		
		try {
			System.out.println("incomingData: Message Group - " + incomingData);			
			JSONObject jsonData = new JSONObject(incomingData);

			Alert alert = new Alert();
    		alert.setApplication(jsonData.getString("Application").trim());
    		alert.setMsgGroup(jsonData.getString("MessageGroup").trim());
    		alert.setObject(jsonData.getString("Object").trim());
    		alert.setSeverity(jsonData.getString("Severity").trim());
    		alert.setMsgText(jsonData.getString("MsgText").trim());    	    		    
    		
			logger.info("incomingData - " 
				+ " Message Group - " + alert.getMsgGroup()	
				+ ", Application - " + alert.getApplication()
				+ ", Object - " + alert.getObject()
				+ ", Severity - " + alert.getSeverity()
				+ ", MsgText - " + alert.getMsgText()		
			);
    		
			if (checkApplication(alert.getApplication()))
			{
				if (processAlert(alert)) 
				{
					returnString = "{\"Message\" : \"Secure OVO Message Sent\"}";
					return Response.ok(returnString).build();
				} else {
					return Response.status(500).entity("Unable to process Item").build();
				}
			} else {
				logger.info("Cannot process request.  Application group is not allowed.");
				return Response.status(500).entity("Unable to process Item").build();
			}
		} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500)
						.entity("Server was not able to process your request.  Please check the format of your request.")
						.build();
		}
	
	}
	
	private boolean processAlert(Alert alert) 
	{
		CommandExecutor commandExecutor = new CommandExecutor();

		try 
		{      
            if (alert != null) 
            {
                try 
                {
                    return commandExecutor.execute(hpOMAPI.commandPath, alert);
                } 
                catch (Exception e) 
                {
                    logger.error("Executing command execution failed " + e);
                    return false;
                }
            }
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
			return false;
		}

		return true;

	}	

	private boolean checkApplication(String appl_passed) 
	{
		int j = appls.length;
		boolean found = false;
		for (int i=0;i<j && found==false; i++)
		{
			if (appl_passed.equals(appls[i]))
				found = true;
		}
		
		return found;

	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/securedGet")
	public String getSecuredGreeting(@Auth User user) 
	{
		return "Hello secured world";
	}

	
}
