package com.appdynamics.extension.auth;

import com.appdynamics.extension.hpOM.User;

import io.dropwizard.auth.Authorizer;

public class hpOMAuthorizer implements Authorizer<User> {

	@Override
	public boolean authorize(User user, String role) {
		return user.getName().equals("AppDynamics") && role.equals("ADMIN");
	}

}
