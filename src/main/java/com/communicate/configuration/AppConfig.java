package com.communicate.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.communicate.dao.impl.UserDaoImpl;
import com.communicate.service.RegistrationForm;
import com.communicate.service.UserManagerImplementation;
 
@Configuration
@ComponentScan({"com.communicate.configuration","com.communicate.controller"})
@PropertySource("classpath:application.properties")
public class AppConfig {
	private static final Logger logger = Logger.getLogger(AppConfig.class);
	
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean
    public UserManagerImplementation getUserManager () {
        return new UserManagerImplementation();
    }
	
	@Bean("registrationForm")
    public RegistrationForm getRegistrationForm () {
        return new RegistrationForm();
    }
	
	@Bean
    public UserDaoImpl getUserDao () {
        return new UserDaoImpl();
    }
	
 
}