package com.cafe24.shoppingmall.frontend.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.service.UserService;
import com.cafe24.shoppingmall.frontend.vo.JoinVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/loginUser")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/joinUser")
	public String joinPage(@ModelAttribute("joinVo") JoinVo joinVo) {
		return "user/join";
	}
	
	@ResponseBody
	@GetMapping("/checkid/{id}")
	public JSONResult idcheck(@PathVariable("id") String id) {
		if(!id.isEmpty()) {
			JSONResult result = userService.idCheck(id);
			return result;
		} else {
			return JSONResult.fail("id값을 넣어주세요.", true);
		}
		
		
	}
	@PostMapping("/joinUser")
	public String join(
			@ModelAttribute("joinVo") @Valid JoinVo joinVo,
			BindingResult valid,
			Model model
	) {
		if(valid.hasErrors()) {
			model.addAllAttributes(valid.getModel());
			return "user/join";
		}
		
		Boolean result = userService.joinMember(joinVo);
		if(result) {
			model.addAttribute("result", "joinsuccess");
			return "redirect:/loginUser";
		} else {
			return "user/join";
		}
	}
	
	
}
