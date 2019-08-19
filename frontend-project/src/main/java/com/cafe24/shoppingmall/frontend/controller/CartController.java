package com.cafe24.shoppingmall.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.security.SecurityUser;
import com.cafe24.shoppingmall.frontend.service.CartService;
import com.cafe24.shoppingmall.frontend.service.CategoryService;
import com.cafe24.shoppingmall.frontend.vo.CartVo;
import com.cafe24.shoppingmall.frontend.vo.CategoryVo;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/list")
	public String cart(Model model, Authentication authentication) {
		SecurityUser securityUser = null;
		try {
			securityUser = (SecurityUser) authentication.getPrincipal();
		} catch ( NullPointerException e ) {
			return "redirect:/";
		}
		
		List<CategoryVo> categories = categoryService.getListAll();
		Long member_no = securityUser.getNo();
		List<CartVo> cart_list = cartService.getCartList(member_no);
		System.out.println("cart_list : "  + cart_list);
		model.addAttribute("categories", categories);
		model.addAttribute("cart_list", cart_list);		
		return "cart/cart";
	}
	
	@ResponseBody
	@PostMapping("/add")
	public JSONResult add(
			@RequestBody CartVo cartVo,
			Authentication authentication) {
		SecurityUser securityUser = null;
		try {
			securityUser = (SecurityUser) authentication.getPrincipal();
		} catch ( NullPointerException e ) {
			return JSONResult.fail("로그인이 필요한 서비스입니다.");
		}
		JSONResult result = null;
		if(cartVo != null) {
			cartVo.setMember_no(securityUser.getNo());
			result = cartService.add(cartVo);
		
		}
		
		return result;
	}
	
	@ResponseBody
	@DeleteMapping("/delete/{no}")
	public JSONResult delete(@PathVariable("no") Optional<Long> pono, Authentication authentication) {
		SecurityUser securityUser = null;
		try {
			securityUser = (SecurityUser) authentication.getPrincipal();
		} catch ( NullPointerException e ) {
			return JSONResult.fail("로그인이 필요한 서비스입니다.");
		}
		
		JSONResult result = null;
		System.out.println("pono : " + pono.get());
		if(pono.isPresent()) {
			result = cartService.delete(pono.get(), securityUser.getNo());
		} else {
			result = JSONResult.fail("fail");
		}
		
		return result;
	}
	
}
