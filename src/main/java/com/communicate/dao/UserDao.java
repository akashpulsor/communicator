package com.communicate.dao;

import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;


import com.communicate.model.User;

@Repository
public interface UserDao extends GenericDao<User,Long> {

	public User authenticateUser(String UserName,String Passowrd) throws AuthenticationException;
	
	public Boolean getUser (User user) throws EntityNotFoundException;
	
	@Async
	public User saveUser (User user) throws Exception;
}
