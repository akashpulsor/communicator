package com.communicate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class HomeController extends AbstractController  {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String  name = req.getParameter("name");
		Map m = new HashMap();
		m.put("message", "I LOVE "+ name);
		ModelAndView mav = new ModelAndView("/index",m);
		return mav;
	}

}
