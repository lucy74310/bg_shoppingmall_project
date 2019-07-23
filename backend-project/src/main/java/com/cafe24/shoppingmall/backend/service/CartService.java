package com.cafe24.shoppingmall.backend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.CartDao;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	public Map<String, Object> getStockInfo(Long product_no) {
		cartDao.getStockInfo(product_no);
		return null;
	}
}
