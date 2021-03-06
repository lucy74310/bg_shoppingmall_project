package com.cafe24.shoppingmall.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.frontend.service.CategoryService;
import com.cafe24.shoppingmall.frontend.service.ProductService;
import com.cafe24.shoppingmall.frontend.vo.CategoryVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/detail")
	public String detail(Model  model) {
		
		List<CategoryVo> categories = categoryService.getListAll();
		System.out.println(categories);
		model.addAttribute("categories", categories);
		return "product/item";
	}
	
	@GetMapping("/detail/{no}")
	public String detailNo(Model model, @PathVariable("no") Optional<Long> no) {
		List<CategoryVo> categories = categoryService.getListAll();
		model.addAttribute("categories", categories);
		if(no.isPresent()) {
			ProductVo p = productService.getDetail(no.get());
			model.addAttribute("p", p);
		} else {
			model.addAttribute("p", null);
		}
		
		
		return "product/item";
	}
	
	@ResponseBody
	@GetMapping("/api/detail/{no}")
	public ResponseEntity<ProductVo> detailJsonReturn(Model model, @PathVariable("no") Optional<Long> no) {
		ProductVo p = null;
		if(no.isPresent()) {
			p = productService.getDetail(no.get());
		} 
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
}
