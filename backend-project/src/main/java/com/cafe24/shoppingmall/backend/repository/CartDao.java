package com.cafe24.shoppingmall.backend.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {

	@Autowired
	private SqlSession sqlSession;

	public void getStockInfo(Long product_no) {
		sqlSession.selectOne("cart.get_stock_info",product_no );
		
	}
	
	
}
