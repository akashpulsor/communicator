package com.communicate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.communicate.dao.RolesRepository;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	AppStartImplementation appStart;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		appStart.setUpRoles();
		appStart.checkInitialSetup();
	}

}
