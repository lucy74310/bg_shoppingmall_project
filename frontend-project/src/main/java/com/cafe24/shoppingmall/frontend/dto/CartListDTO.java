package com.cafe24.shoppingmall.frontend.dto;

import java.util.List;

import com.cafe24.shoppingmall.frontend.vo.CartVo;

public class CartListDTO {
	private List<CartVo> cart_list;

	public List<CartVo> getCart_list() {
		return cart_list;
	}

	public void setCart_list(List<CartVo> cart_list) {
		this.cart_list = cart_list;
	}

	@Override
	public String toString() {
		return "CartListDTO [cart_list=" + cart_list + "]";
	}
	
	
}
