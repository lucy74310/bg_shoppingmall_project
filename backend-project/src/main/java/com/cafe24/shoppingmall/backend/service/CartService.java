package com.cafe24.shoppingmall.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.CartDao;
import com.cafe24.shoppingmall.backend.vo.CartVo;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	public CartVo addCart(CartVo cartVo) {
		return cartDao.insertCart(cartVo);
	}

}
