package com.cafe24.shoppingmall.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.OrderDao;
import com.cafe24.shoppingmall.backend.vo.OrderVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public OrderVo addOrder(OrderVo orderVo) {
		return null;
	}
	
}
