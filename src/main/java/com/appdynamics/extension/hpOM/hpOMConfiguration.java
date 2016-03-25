package com.appdynamics.extension.hpOM;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class hpOMConfiguration extends Configuration {
	private String pathToExecutable;

	@NotEmpty
	private String msgGroup;

	@JsonProperty
	public String getpathToExecutable() {
		return pathToExecutable;
	}

	@JsonProperty
	public String getmsgGroup() {
		return msgGroup;
	}

}
