package com.communicate.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//WebMvcConfig extends WebMvcConfigurerAdapter  {
@Configuration
@ComponentScan(basePackages = "com.communicate.configuration")
public class DispatcherConfig {
	
	

}
