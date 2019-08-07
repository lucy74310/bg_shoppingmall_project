package com.cafe24.shoppingmall.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class AdminController {
	
	@GetMapping("/")
	public String main() {
		return "admin/index";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "admin/login";
	}
	
//	@PostMapping("/auth")
//	public String auth() {
//		return "admin/index";
//	}
}
