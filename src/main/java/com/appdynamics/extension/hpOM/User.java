package com.appdynamics.extension.hpOM;

import java.security.Principal;


public class User
implements Principal {
	protected String name;
	
	public User(String userName) {
		this.name = userName;
	}

	public User() {
		this.name = "AppDynamics";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}

