package com.communicate.dao.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.security.sasl.AuthenticationException;

import com.communicate.dao.GenericDao;
import com.communicate.dao.UserDao;
import com.communicate.model.User;

public class UserDaoImpl extends GenericDaoImpl<User>  implements UserDao {



	public UserDaoImpl() {
		super(User.class);
	
	}

	@Override
	public User authenticateUser(String UserName, String Passowrd) throws AuthenticationException {
		// TODO Auto-generated method stub
		List<User> results = null;
		Query query = entityManager.createQuery(
		"from Person as p where p.username = :username and p.password = :password"
		);
		query.setParameter("username", UserName);
		query.setParameter("password", Passowrd);
		results = query.getResultList();
		if (results == null || results.size() <= 0) {
		throw new AuthenticationException("No users found");
		} else {
		return results.get(0);
		}
	}

	@Override
	public Boolean getUser(User user) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
