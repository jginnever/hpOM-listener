package com.appdynamics.extension.auth;


import com.appdynamics.extension.hpOM.User;
import com.google.common.base.Optional;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class hpOMAuthenticator 
implements Authenticator<BasicCredentials, User> {

	@Override
	public Optional<User> authenticate(BasicCredentials credentials) {
		if ("crimson".equals(credentials.getPassword())) {
			return Optional.of(new User());
		} else {
			return Optional.absent();
		}
	}
}
