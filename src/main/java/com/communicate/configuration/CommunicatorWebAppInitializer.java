package com.communicate.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class CommunicatorWebAppInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext container) throws ServletException {
		// TODO Auto-generated method stub
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext(); 
		rootContext.register(WebMvcConfig.class);
		// Manage the lifecycle of the root application context
	    container.addListener(new ContextLoaderListener(rootContext));
	    
	    // Create the dispatcher servlet's Spring application context
	    AnnotationConfigWebApplicationContext dispatcherContext = 
	    		new AnnotationConfigWebApplicationContext();
	    dispatcherContext.register(DispatcherConfig.class);
	 // Register and map the dispatcher servlet
	    ServletRegistration.Dynamic dispatcher =
	    container.addServlet("app", new DispatcherServlet(dispatcherContext));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/app");

	    
	    

		
	}

}
