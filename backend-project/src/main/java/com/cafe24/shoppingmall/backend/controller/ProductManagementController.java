package com.cafe24.shoppingmall.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/product")
public class ProductManagementController {
	
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String productAddForm() {
		
		String view = "productManage/addForm";
		
		return view;
	}
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String productList() {
		
		String view = "productManage/list";
		
		return view;
	}
}
