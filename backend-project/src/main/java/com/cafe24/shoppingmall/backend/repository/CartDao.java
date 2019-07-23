package com.cafe24.shoppingmall.backend.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.CartVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

@Repository
public class CartDao {

	@Autowired
	private SqlSession sqlSession;

	public CartVo insertCart(CartVo cartVo) {
		sqlSession.insert("cart.insert_cart", cartVo);
		return cartVo;
	}


	
	
}
