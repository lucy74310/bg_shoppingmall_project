package com.cafe24.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		
		String view = "admin/login";
		
		return view;
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String adminHome() {
		
		String view = "admin/home";
		return view;
	}
}
