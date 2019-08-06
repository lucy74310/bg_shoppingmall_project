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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jdk.nashorn.internal.parser.JSONParser;

@Controller
public class MainController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	@GetMapping(value={"/", "/{no}"})
	public String main(
			Model model,
			@PathVariable("no") Optional<Long> category_no
	) {
		List<CategoryVo> categories = categoryService.getListAll();
		List<ProductVo> products = productService.getListAll();;
		if(category_no.isPresent()) {
			products = productService.getByCategory(category_no.get()); 
		} else {
			products = productService.getListAll(); 
		}
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json_categories = "";
		try {
			json_categories = ow.writeValueAsString(categories);
			model.addAttribute("json_categories", json_categories);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		
		return "main/index"; 
	}
}
