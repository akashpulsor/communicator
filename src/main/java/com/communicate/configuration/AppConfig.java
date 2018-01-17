package com.communicate.configuration;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.communicate.service.InitialSetup;
 



@SpringBootApplication(scanBasePackages="com.communicate.configuration,"
		+ "com.communicate.controller,"
		+ "com.communicate.dao,com.communicate.service")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.communicate.dao")
@EntityScan({"com.communicate.model"})
//@EnableConfigurationProperties(StorageProperties.class)
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
public class AppConfig {
	private static final Logger logger = Logger.getLogger(AppConfig.class);
	
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	
	

	
	
	
	
	
 
}