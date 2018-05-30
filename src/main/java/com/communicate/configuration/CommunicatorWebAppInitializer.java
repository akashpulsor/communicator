package com.communicate.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class CommunicatorWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	private static final Logger logger = Logger.getLogger(CommunicatorWebAppInitializer.class);
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {AppConfig.class,HibernateConfig.class,WebSecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {com.communicate.configuration.WebMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}

}
