package com.communicate.configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
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
	
	 @Bean(name = "multipartResolver")
	    public CommonsMultipartResolver getResolver() throws IOException{
	        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	        
	        
	         
	        //Set the maximum allowed size (in bytes) for each individual file.
	        resolver.setMaxUploadSizePerFile(5242880);
	        resolver.setDefaultEncoding("utf-8");//5MB
	         
	        //You may also set other available properties.
	         
	        return resolver;
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
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		 
		      registry.addViewController("/home.html");
		      registry.addViewController("/register.html");
		      registry.addViewController("/login.html");
		      registry.addViewController("/uploadimg.html");
		      
	}
	// Equivalent for <mvc:default-servlet-handler/> tag
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	 @Override
	  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.defaultContentType(MediaType.APPLICATION_XML).
	    mediaType("multi_part_form_data",MediaType.MULTIPART_FORM_DATA);
	  }

}
