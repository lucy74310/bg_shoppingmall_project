package com.cafe24.shoppingmall.frontend.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cafe24.shoppingmall.frontend.service.CategoryService;
import com.cafe24.shoppingmall.frontend.service.ProductService;
import com.cafe24.shoppingmall.frontend.vo.CategoryVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;


@Controller
public class MainController {
	
	public static Logger log = Logger.getLogger("My Logger");
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	@GetMapping(value={"/", "/main"})
	public String main(
			Model model,
			@PathVariable("no") Optional<Long> category_no
	) {
		log.log(Level.ALL , "Log testing");
		List<CategoryVo> categories = categoryService.getListAll();
		List<ProductVo> products = productService.getListAll(); 
		
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		
		return "main/index"; 
	}
	
	@GetMapping(value={"/list", "/list/{no}"})
	public String list(
			Model model,
			@PathVariable("no") Optional<Long> category_no
	) {
		List<CategoryVo> categories = categoryService.getListAll();
		List<ProductVo> products = null;
		if(category_no.isPresent()) {
			products = productService.getByCategory(category_no.get());
		} else {
			products = productService.getListAll(); 
		}
		System.out.println(products);
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		String json_categories = "";
//		try {
//			json_categories = ow.writeValueAsString(categories);
//			model.addAttribute("json_categories", json_categories);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		
		return "main/index"; 
	}
}
