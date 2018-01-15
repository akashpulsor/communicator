package com.communicate.dao;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.communicate.model.User;


public interface UserRepository extends  Repository<User, Long> {
	
	
	
	@SuppressWarnings("unchecked")
	User  save(User user);
	
	Optional<User> findByEmailIgnoreCase(String email);
	
	Optional<User> findById(String id);
	
	Optional<User> findByMobileNumber(String number);

	//User authenticateUser(String UserName,String Passowrd) throws AuthenticationException;	

}
