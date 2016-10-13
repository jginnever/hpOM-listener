package com.appdynamics.extension.hpOM;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.appdynamics.extension.auth.hpOMAuthenticator;
import com.appdynamics.extension.auth.hpOMAuthorizer;
import com.appdynamics.extension.hpOM.resources.hpOMAPI;
import com.appdynamics.extension.hpOM.resources.hpOMAPIVersion;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
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
		environment.jersey().register(new hpOMAPI(configuration.getpathToExecutable()));
		environment.jersey().register(new AuthDynamicFeature(
					new BasicCredentialAuthFilter.Builder<User>()
		                .setAuthenticator(new hpOMAuthenticator(configuration.getUserName(),
		                		configuration.getPassword()))
		                .setAuthorizer(new hpOMAuthorizer())
		                .setRealm("SECURITY REALM")
		                .buildAuthFilter()));
		
		environment.jersey().register(RolesAllowedDynamicFeature.class);
		//If you want to use @Auth to inject a custom Principal type into your resource
		environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
	}

}
