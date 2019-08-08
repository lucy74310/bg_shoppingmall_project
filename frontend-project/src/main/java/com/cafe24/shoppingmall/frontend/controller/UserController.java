package com.cafe24.shoppingmall.frontend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.shoppingmall.frontend.service.UserService;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/loginUser")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/joinUser")
	public String joinPage(@ModelAttribute("MemberVo") MemberVo memberVo) {
		return "user/join";
	}
	
	@PostMapping("/joinUser")
	public String join(
			@ModelAttribute("MemberVo") MemberVo memberVo,
			BindingResult valid,
			Model model
	) {
		if(valid.hasErrors()) {
			model.addAllAttributes(valid.getModel());
		}
		
		userService.joinMember(memberVo);
		return "user/join";
	}
	
	
}
