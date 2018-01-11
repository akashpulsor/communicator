package com.communicate.dao;

import org.springframework.data.repository.Repository;

import com.communicate.model.User;


public interface UserRepository extends  Repository<User, Long> {
	
	
	
	@SuppressWarnings("unchecked")
	User  save(User user);
	
	User findByEmailIgnoreCase(String email);
	
	User findById(String id);

	//User authenticateUser(String UserName,String Passowrd) throws AuthenticationException;	

}
