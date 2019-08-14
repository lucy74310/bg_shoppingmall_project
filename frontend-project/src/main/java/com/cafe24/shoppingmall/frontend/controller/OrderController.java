package com.cafe24.shoppingmall.frontend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.shoppingmall.frontend.dto.CartListDTO;
import com.cafe24.shoppingmall.frontend.security.SecurityUser;
import com.cafe24.shoppingmall.frontend.service.CartService;
import com.cafe24.shoppingmall.frontend.service.CategoryService;
import com.cafe24.shoppingmall.frontend.vo.CategoryVo;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;

@Controller
public class OrderController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/orderform")
	public String cart(@ModelAttribute CartListDTO order_list, Authentication authentication, Model model) {
		SecurityUser securityUser = null;
		try {
			securityUser = (SecurityUser) authentication.getPrincipal();
		} catch ( NullPointerException e ) {
			return "redirect:/";
		}
		
		
		model.addAttribute("order_list", order_list.getCart_list());
		List<CategoryVo> categories = categoryService.getListAll();
		
		model.addAttribute("categories", categories);
		MemberVo vo = new MemberVo();
		vo.setNo(securityUser.getNo());
		vo.setName(securityUser.getName());
		model.addAttribute("member", vo);
		return "order/order";
	}
}
