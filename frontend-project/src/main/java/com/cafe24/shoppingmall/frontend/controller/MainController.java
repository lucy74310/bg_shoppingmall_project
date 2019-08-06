package com.cafe24.shoppingmall.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.shoppingmall.frontend.service.CategoryService;
import com.cafe24.shoppingmall.frontend.service.ProductService;
import com.cafe24.shoppingmall.frontend.vo.CategoryVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;

@Controller
public class MainController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	@GetMapping(value={"/{category_no}", "/main/{category_no}"})
	public String main(
			Model model, 
			@PathVariable("category_no") Optional<Long> category_no 
	) {
		
		List<CategoryVo> categories = categoryService.getListAll();
		List<ProductVo> products = null;
		if(category_no.isPresent()) {
			products = productService.getByCategory(category_no.get());
		} else {
			products = productService.getListAll();
		}
		
		
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		
		return "main/index"; 
	}
}
