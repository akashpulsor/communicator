package com.communicate.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
import com.communicate.controller.MainController;
 
@Configuration
@ComponentScan({"com.communicate.configuration","com.communicate.controller"})
@PropertySource("classpath:application.properties")
public class AppConfig {
	private static final Logger logger = Logger.getLogger(AppConfig.class);
	
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
 
}