package com.communicate.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.communicate.dao.UserRepository;
import com.communicate.model.User;

@Service
public class UserManagerImplementation implements UserManager{

	@Autowired
	UserRepository userDao;
	
    @PersistenceContext
    private EntityManager entityManager;
	 
	@Override
	public User createUser(RegistrationForm regForm) throws Exception {
		User user = new User();
		user.setEmail(regForm.getEmail());
		user.setName(regForm.getName());
		user.setPassword(regForm.getPassword());
		user.setMobileNumber(regForm.getMobileNumber());
		userDao.save(user);
		// TODO Auto-generated method stub
		return user;
	}
	
	
}
