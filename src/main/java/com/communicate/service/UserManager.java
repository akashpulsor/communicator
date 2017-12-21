package com.communicate.service;

import com.communicate.model.User;

public interface UserManager {
	
	public User createUser( RegistrationForm regform ) throws Exception;
	
	public User authenticateUser( String userName, String password ) throws Exception;
}
