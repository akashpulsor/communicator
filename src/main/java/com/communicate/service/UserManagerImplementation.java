package com.communicate.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communicate.dao.GenericDao;
import com.communicate.dao.UserDao;
import com.communicate.model.User;

import junit.framework.Assert;

@Service
public class UserManagerImplementation implements UserManager {

	private static final Logger logger = Logger.getLogger(UserManagerImplementation.class);
	
	
	@Autowired
	GenericDao<User,Long> userDao;
	


	public User createUser(RegistrationForm regForm) throws Exception {
		User user = new User();
		user.setEmail(regForm.getEmail());
		user.setName(regForm.getName());
		user.setPassword(regForm.getPassword());
		user.setMobileNumber(regForm.getPassword());
		Assert.assertNotNull(userDao);
		userDao.save(user);
		return user;
	}

}
