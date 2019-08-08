package com.cafe24.shoppingmall.frontend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.cafe24.shoppingmall.frontend.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/loginUser")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/joinUser")
	public String join() {
		return "user/join";
	}
	
	
	
}
