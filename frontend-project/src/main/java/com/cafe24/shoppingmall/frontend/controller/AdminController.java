package com.cafe24.shoppingmall.frontend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.service.AdminService;
import com.cafe24.shoppingmall.frontend.service.CategoryService;
import com.cafe24.shoppingmall.frontend.service.ProductService;
import com.cafe24.shoppingmall.frontend.vo.CategoryVo;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;
import com.cafe24.shoppingmall.frontend.vo.OrderProductVo;
import com.cafe24.shoppingmall.frontend.vo.OrderVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({"/admin", "/admin/main", "/admin/product","/admin/product/list"})
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
	public String productAddPage(Model model) {
		List<CategoryVo> categories = categoryService.getListAll();
		System.out.println(categories);
		model.addAttribute("categories", categories);
		return "admin/product-register";
	}
	
	
	
	@PostMapping("/admin/product/register")
	public String productAdd(
			ProductVo productVo, 
			@RequestParam("image_add_list") List<MultipartFile> images,
			@RequestParam("cate_list") List<Long> category_no_list,
			Model model
	) {
		System.out.println(category_no_list);
		List<String> image_url =  productService.uploadImages(images);
		JSONResult result = adminService.registerProduct(productVo, image_url, category_no_list);
		
		if("success".equals(result.getResult())) {
			return "redirect:/admin/product";
		} else {
			model.addAttribute("error", result.getData());
			return "admin/product-register";
		}
		
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
	
	@GetMapping("/admin/order")
	public String order(Model model) {
		
		List<OrderVo> order_list = adminService.getOrderList();
		System.out.println(order_list);
		model.addAttribute("orders", order_list);
		return "admin/order-list";
	}
	
}
