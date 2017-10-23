package com.communicate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.communicate.model.User;
import com.communicate.service.UserManagerImplementation;

@Controller
@RequestMapping(value = "/web")
public class MainController {

	@Autowired
	UserManagerImplementation userManager;
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	protected String handleRequestInternal( @ModelAttribute("User") User user, Model model)throws Exception {
			
		
/*		userManager.createUser();*/
		userManager.createUser(user);
		return "/index";
	}
	
	/**
	    * Rest web service
	    */
	    @RequestMapping(value = "/home", method = RequestMethod.GET)
	    public @ResponseBody String getUsersRest() {
	        return "AKASH";
	    }

	

}
