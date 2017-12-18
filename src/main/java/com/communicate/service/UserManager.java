package com.communicate.service;

import com.communicate.model.User;

public interface UserManager {
	
	public DashBoard createUser( RegistrationForm regform ) throws Exception;
	
	public DashBoard authenticateUser( String userName, String password ) throws Exception;
}
