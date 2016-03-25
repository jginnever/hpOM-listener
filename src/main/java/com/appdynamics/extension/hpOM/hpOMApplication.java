package com.appdynamics.extension.hpOM;

import com.appdynamics.extension.hpOM.resources.hpOMAPI;
import com.appdynamics.extension.hpOM.resources.hpOMAPIVersion;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class hpOMApplication extends Application<hpOMConfiguration> {

	public static void main(final String[] args) throws Exception {
		new hpOMApplication().run(args);
	}

	@Override
	public String getName() {
		return "HP OM REST API";
	}

	@Override
	public void initialize(final Bootstrap<hpOMConfiguration> bootstrap) {
		// TODO: application initialization
	}

	@Override
	public void run(final hpOMConfiguration configuration, final Environment environment) {
		environment.jersey().register(new hpOMAPIVersion());
		environment.jersey().register(new hpOMAPI());
	}

}
