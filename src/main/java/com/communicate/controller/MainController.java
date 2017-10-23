package com.communicate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.communicate.service.UserManagerImplementation;

@Controller
@RequestMapping(value = "/web")
public class MainController  extends SimpleFormController{

	@Autowired
	UserManagerImplementation userManager;
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse arg1) throws Exception {
			
		
/*		userManager.createUser();*/
		request.getAttribute()
		ModelAndView mav = new ModelAndView("/index");
		return mav;
	}
	
	/**
	    * Rest web service
	    */
	    @RequestMapping(value = "/home", method = RequestMethod.GET)
	    public @ResponseBody String getUsersRest() {
	        return "AKASH";
	    }

	

}
