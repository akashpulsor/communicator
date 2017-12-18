package com.communicate.configuration;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

@ComponentScan({"com.communicate.configuration","com.communicate.controller"})
@Component
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(WebMvcConfig.class);
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
	@Bean
	public BeanNameUrlHandlerMapping  beanNameHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}
	



	@Bean
	public InternalResourceViewResolver  getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".jsp");
		return viewResolver; 
	}
	// Config UTF-8 Encoding.
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setSupportedMediaTypes
		(Arrays.asList(new MediaType("text", "plain", UTF8)));
		converters.add(stringConverter);
		// Add other converters ...
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		
		registry.addResourceHandler("/web/webjars/**").
		addResourceLocations("classpath:/META-INF/resources/webjars/")
		.resourceChain(false);
	}
	
	// Equivalent for <mvc:default-servlet-handler/> tag
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
