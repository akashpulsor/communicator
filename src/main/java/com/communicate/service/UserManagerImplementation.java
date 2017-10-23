package com.communicate.service;

import org.springframework.stereotype.Service;

import com.communicate.model.User;

@Service("UserManager")
public class UserManagerImplementation implements UserManager {


	public User createUser(User user) {
		return user;
	}

}
