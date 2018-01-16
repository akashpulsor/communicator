package com.communicate.dao;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.communicate.model.Role;
import com.communicate.model.Roles;
import com.communicate.model.User;

public interface RolesRepository extends  Repository<Roles, Long>{

	@SuppressWarnings("unchecked")
	Roles  save(Roles role);
	
	Roles findByRoleName(Role roleUser);
}
