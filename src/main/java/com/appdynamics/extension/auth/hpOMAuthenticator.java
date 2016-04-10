package com.appdynamics.extension.auth;


import com.appdynamics.extension.hpOM.User;
import com.google.common.base.Optional;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


public class hpOMAuthenticator 
implements Authenticator<BasicCredentials, User> {

	private String userName;
	private String password;

	public hpOMAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
		
	@Override
	public Optional<User> authenticate(BasicCredentials credentials) {
		if (password.equals(credentials.getPassword())
				&& userName.equals(credentials.getUsername())) {
			return Optional.of(new User());
		} else {
			return Optional.absent();
		}
	}
}
