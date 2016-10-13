package com.appdynamics.extension.hpOM.resources;

//import java.io.File;
//import java.io.FileNotFoundException;

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
	
	public hpOMAPI (String pathToExecutable)
	{
		commandPath = pathToExecutable;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)

	public Response postOVO(String incomingData) throws Exception {

		String returnString = null;
		//JsonNode nodeObject;
	    //ObjectMapper mapper = new ObjectMapper();
		
		try {
			System.out.println("incomingData: Message Group - " + incomingData);			
			JSONObject jsonData = new JSONObject(incomingData);
		    //nodeObject = mapper.readTree(incomingData);

			Alert alert = new Alert();
			//alert.setApplication(nodeObject.get("Application").textValue());
			//alert.setMsgGroup(nodeObject.get("MessageGroup").textValue());
			//alert.setObject(nodeObject.get("Object").textValue());
			//alert.setSeverity(nodeObject.get("Severity").textValue());
			//alert.setMsgText(nodeObject.get("MsgText").textValue());
    		alert.setApplication(jsonData.getString("Application"));
    		alert.setMsgGroup(jsonData.getString("MessageGroup"));
    		alert.setObject(jsonData.getString("Object"));
    		alert.setSeverity(jsonData.getString("Severity"));
    		alert.setMsgText(jsonData.getString("MsgText"));    	    		

			logger.info("incomingData - " 
				+ " Message Group - " + alert.getMsgGroup()	
				+ ", Application - " + alert.getApplication()
				+ ", Object - " + alert.getObject()
				+ ", Severity - " + alert.getSeverity()
				+ ", MsgText - " + alert.getMsgText()
			);
    					
			if (processAlert(alert)) 
			{
				logger.info("Message Sent");			
			} else {
				logger.info("Unable to process message");
				return Response.status(500).entity("Unable to process message").build();
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
		//JsonNode nodeObject;
	    //ObjectMapper mapper = new ObjectMapper();
		
		try {
			System.out.println("incomingData: Message Group - " + incomingData);			
			JSONObject jsonData = new JSONObject(incomingData);
		    //nodeObject = mapper.readTree(incomingData);

			Alert alert = new Alert();
			//alert.setApplication(nodeObject.get("Application").textValue());
			//alert.setMsgGroup(nodeObject.get("MessageGroup").textValue());
			//alert.setObject(nodeObject.get("Object").textValue());
			//alert.setSeverity(nodeObject.get("Severity").textValue());
			//alert.setMsgText(nodeObject.get("MsgText").textValue());
    		alert.setApplication(jsonData.getString("Application"));
    		alert.setMsgGroup(jsonData.getString("MessageGroup"));
    		alert.setObject(jsonData.getString("Object"));
    		alert.setSeverity(jsonData.getString("Severity"));
    		alert.setMsgText(jsonData.getString("MsgText"));    	    		
    		
			logger.info("incomingData - " 
				+ " Message Group - " + alert.getMsgGroup()	
				+ ", Application - " + alert.getApplication()
				+ ", Object - " + alert.getObject()
				+ ", Severity - " + alert.getSeverity()
				+ ", MsgText - " + alert.getMsgText()
			);
    		
			
			if (processAlert(alert)) 
			{
				returnString = "{\"Message\" : \"Secure OVO Message Sent\"}";
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/securedGet")
	public String getSecuredGreeting(@Auth User user) 
	{
		return "Hello secured world";
	}

	
}
