package com.communicate.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communicate.controller.MainController;
import com.communicate.dao.impl.UserDaoImpl;
import com.communicate.model.User;

@Service("UserManager")
public class UserManagerImplementation implements UserManager {

	private static final Logger logger = Logger.getLogger(UserManagerImplementation.class);
	
	@Autowired
	UserDaoImpl userDao;

	public User createUser(RegistrationForm regForm) throws Exception {
		User user = new User();
		user.setEmail(regForm.getEmail());
		user.setName(regForm.getName());
		user.setPassword(regForm.getPassword());
		user.setMobileNumber(regForm.getPassword());
		userDao.save(user);
		return user;
	}

}
