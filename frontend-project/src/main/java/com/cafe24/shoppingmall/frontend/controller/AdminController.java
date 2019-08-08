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
import com.cafe24.shoppingmall.frontend.vo.MemberVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;
	
	@GetMapping({"/admin", "/admin/main", "/admin/product/list"})
	public String main(Model model) {
		
		List<ProductVo> products = productService.getListAll(); 
		
		model.addAttribute("products", products);
		
		
		
		return "admin/product-list";
	}
	
	@GetMapping("/loginAdmin")
	public String login() {
		
		return "admin/login";
	}
	
	
	@GetMapping("/admin/product/register")
	public String productAdd() {
		
		return "admin/product-register";
	}
	@GetMapping("/admin/category")
	public String category() {
		
		return "admin/category";
	}
	@GetMapping({"/admin/user", "/admin/user/list"})
	public String user(Model model) {
		List<MemberVo> members = adminService.getMemberListAll();
		
		model.addAttribute("members", members);
		
		return "admin/user-list";
	}
	
//	@PostMapping("/auth")
//	public String auth() {
//		return "admin/index";
//	}
	
	
	
}
