package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.ProductManageDao;
import com.cafe24.shoppingmall.repository.UserDao;

@Service
public class ProductManageService {

	@Autowired
	public ProductManageDao productManageDao;

	public Long deleteProduct(Long deleteNo) {
		return 1L;
	}
}
