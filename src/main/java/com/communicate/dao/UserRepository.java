package com.communicate.dao;

import javax.security.sasl.AuthenticationException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.communicate.model.User;

@Repository
public interface UserRepository extends  CrudRepository<User, Long> {
	
	User findOne(Long id);
	
	@SuppressWarnings("unchecked")
	User  save(User user);

	//User authenticateUser(String UserName,String Passowrd) throws AuthenticationException;	

}
