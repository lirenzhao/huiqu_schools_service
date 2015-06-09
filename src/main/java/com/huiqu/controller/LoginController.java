package com.huiqu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private String config_password = "SYN_password_1234";

	public void setConfig_password(String config_password) {
		this.config_password = config_password;
	} 
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("Controller Login execute password=" + request.getParameter("password") +" " + config_password);
		HttpSession session = request.getSession(true);
		if(session != null){
			if(request.getParameter("password").equals(config_password)){
				session.setAttribute("user","12312231");
				return new ModelAndView("redirect:/genkey");
			}else{
				return new ModelAndView("login/login");
			}
		}
		System.out.println("session is null");
		return new ModelAndView("login/login");
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("LoginController run..");
		return new ModelAndView("/login/login");
	}
}
