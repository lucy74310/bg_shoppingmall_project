package com.cafe24.shoppingmall.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping({"/", "/main"})
	public String main() {
		System.out.println("---------");
		return "main/index";
	}
}
