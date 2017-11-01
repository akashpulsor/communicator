package com.communicate.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.communicate.configuration.AppConfig;
import com.communicate.model.User;
import com.communicate.service.RegistrationForm;
import com.communicate.service.UserManagerImplementation;

@Controller
@RequestMapping(value = "/web")
public class MainController {
	private static final Logger logger = Logger.getLogger(MainController.class);
	
	@Autowired
	UserManagerImplementation userManager;

	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("/home", "registrationForm", new RegistrationForm());
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm regform, BindingResult result,
			Model model) throws Exception {

		logger.info("Regitration for data" + regform.toString());

		if (result.hasErrors()) {
			logger.error("not able to create user " + result.getAllErrors());
			return "/error";
		}

		logger.info("Recieved registration form " + regform.getName());
		Assert.assertNotNull("User manager Implementation is null", userManager);
		User user = userManager.createUser(regform);
		model.addAttribute("user", user);
		return "/dashboard";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public @ResponseBody String getUsersRest() {
		return "AKASH";
	}

}
