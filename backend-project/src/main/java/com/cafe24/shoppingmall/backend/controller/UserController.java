package com.cafe24.shoppingmall.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/user")
public class UserController {
	
	
	
//	@RequestMapping(value="/join/agree", method=RequestMethod.GET)
//	public String joinAgreementForm() {
//		return "user/agreement";
//	}
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinForm() {
		return "user/joinform";
	}
	
//	
//	@RequestMapping(value="/loginform", method=RequestMethod.GET)
//	public String loginForm() {
//		return "user/login";
//	}
//	
//	@RequestMapping(value="/updateform", method=RequestMethod.GET)
//	public String userInfoUpdateForm() {
//		return "user/updateform";
//	}
//	
//	@RequestMapping(value="/leaveform", method=RequestMethod.GET)
//	public String deleteUserForm() {
//		return "user/leaveform";
//	}
//		
//	@RequestMapping(value="/address/addform", method=RequestMethod.GET)
//	public String addressAddPage() {
//		return "user/address/add";
//	}
	

	
	@RequestMapping(value="/join/success", method=RequestMethod.GET)
	public String joinSuccess() {
		
		String view = "user/joinsuccess";
		return view;
	}
	

	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteUserForm() {
		return "user/deleteForm";
	}
	
	
	@RequestMapping(value="/address/list", method=RequestMethod.GET)
	public String addressListPage() {
		return "admin/userList";
	}
	
	
}
