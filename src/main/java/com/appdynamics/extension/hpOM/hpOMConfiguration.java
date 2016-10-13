package com.appdynamics.extension.hpOM;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
//import javax.validation.constraints.*;

public class hpOMConfiguration extends Configuration {
	
	@NotEmpty
	private String pathToExecutable;

	@NotEmpty
	private String msgGroup;
	
    private String userName;
    
    private String password;
    	

	@JsonProperty
	public String getpathToExecutable() {
		return pathToExecutable;
	}

	@JsonProperty
	public String getmsgGroup() {
		return msgGroup;
	}

	@JsonProperty
    public String getUserName() {
        return userName;
    }

	@JsonProperty
    public String getPassword() {
        return password;
    }


}
