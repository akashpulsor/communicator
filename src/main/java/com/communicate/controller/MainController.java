package com.communicate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
@RequestMapping(value = "/app")
public class MainController extends AbstractController {


	@Override
	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String  name = request.getParameter("name");
		Map m = new HashMap();
		m.put("message", "I LOVE "+ name);
		ModelAndView mav = new ModelAndView("/index",m);
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
