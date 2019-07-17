package com.cafe24.shoppingmall.backend.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.OptionDetailVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

@Repository
public class ProductDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	public Long insertProduct(ProductVo productVo) {
		int count = sqlSession.insert("product.insert", productVo);
		System.out.println(productVo);
		if(count == 1) {
			return productVo.getNo();
		} else {
			return null;
		}
		
	}

	public Long insertOption(OptionVo option) {
		sqlSession.insert("product.op_insert", option);
		return option.getNo();
	}

	public boolean insertOptionDetail(OptionDetailVo od) {
		return 1 == sqlSession.insert("product.opd_insert", od);
	}

	public boolean insertProductCategort(Long no, Long categoryNo) {
		Map<String,Long> data = new HashMap<String,Long>();
		data.put("product_no", no);
		data.put("category_no", categoryNo);
		return 1 == sqlSession.insert("product.product_category_insert",data);
	}

}
