package com.communicate.configuration;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.communicate.service.RegistrationForm;
 

@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages="com.communicate.configuration,com.communicate.controller,"
		+ "com.communicate.dao,com.communicate.service")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.communicate.dao")
@EntityScan({"com.communicate.model"})

public class AppConfig {
	private static final Logger logger = Logger.getLogger(AppConfig.class);
	
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	
	

	
	
	
	
	
 
}