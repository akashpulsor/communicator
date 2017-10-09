package com.communicate.service;

import org.springframework.web.bind.annotation.RequestMapping;

import com.communicate.model.User;

public class LoginService {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
