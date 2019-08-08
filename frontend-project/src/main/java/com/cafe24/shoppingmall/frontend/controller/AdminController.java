package com.cafe24.shoppingmall.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shoppingmall.frontend.service.AdminService;
import com.cafe24.shoppingmall.frontend.service.ProductService;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;
	
	@GetMapping({"/manage", "/manage/main", "/manage/product/list"})
	public String main(Model model) {
		
		List<ProductVo> products = productService.getListAll(); 
		
		model.addAttribute("products", products);
		
		
		
		return "admin/product-list";
	}
	
	@GetMapping("/managelogin")
	public String login() {
		
		return "admin/login";
	}
	@GetMapping("/managelogout")
	public String logout() {
		
		return "main/index";
	}
	
	@GetMapping("/manage/product/register")
	public String productAdd() {
		
		return "admin/product-register";
	}
	@GetMapping("/manage/category")
	public String category() {
		
		return "admin/category";
	}
	@GetMapping({"/manage/user", "/manage/user/list"})
	public String user() {
		
		return "admin/user-list";
	}
	
//	@PostMapping("/auth")
//	public String auth() {
//		return "admin/index";
//	}
	
	
	
}
