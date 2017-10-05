package com.communicate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
@RequestMapping(value = "/login.html")
public class LoginController extends AbstractController {

	@Override
	//@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String  name = req.getParameter("email");
		StringBuffer sb = new StringBuffer();
	    BufferedReader bufferedReader = null;
	    String content = "";

	    try {
	        //InputStream inputStream = request.getInputStream();
	        //inputStream.available();
	        //if (inputStream != null) {
	        bufferedReader =  req.getReader() ; //new BufferedReader(new InputStreamReader(inputStream));
	        char[] charBuffer = new char[128];
	        int bytesRead;
	        while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
	            sb.append(charBuffer, 0, bytesRead);
	        }
	        //} else {
	        //        sb.append("");
	        //}

	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

		Map m = new HashMap();
		m.put("message", "I LOVE "+ sb);
		ModelAndView mav = new ModelAndView("/success",m);
		return mav;
	}

}
