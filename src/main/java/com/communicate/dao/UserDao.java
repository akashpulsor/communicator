package com.communicate.dao;

import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;

import org.springframework.stereotype.Repository;


import com.communicate.model.User;


public interface UserDao extends GenericDao<User> {

	public User authenticateUser(String UserName,String Passowrd) throws AuthenticationException;
	
	public Boolean getUser (User user) throws EntityNotFoundException;
}
