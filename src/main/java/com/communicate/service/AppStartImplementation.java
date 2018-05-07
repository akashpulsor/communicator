package com.communicate.service;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.communicate.dao.RolesRepository;
import com.communicate.model.Role;
import com.communicate.model.Roles;

@Component
public class AppStartImplementation implements AppStart{

	private static final Logger logger = Logger.getLogger( AppStartImplementation.class );
	@Autowired
	RolesRepository roleRepository;
	
	@Autowired
	ImageStorageService imageService;
	
	@Override
	public void setUpRoles() {
		// TODO Auto-generated method stub
		for (Role role : Role.values()) {
			if(roleRepository.findByRoleName(role) == null ) {
				Roles rol = new Roles();
				rol.setRoleName(role);
				logger.info("Creating roles in database" + rol.toString());
				roleRepository.save(rol);
			}
		}
	}

	@Override
	public void contentNetwork() {
		// TODO Auto-generated method stub
		imageService.init();
	}
	
	private void checkRolesSetup() {
		for (Role role : Role.values()) {
			if(roleRepository.findByRoleName(role) == null ) {
				logger.error("Failed to create role" + role );
			}
		}
	}

	@Override
	public void checkInitialSetup() {
		// TODO Auto-generated method stub
		checkRolesSetup();
	}
	
	

}
