package com.cafe24.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.ProductOptionVo;
import com.cafe24.shoppingmall.vo.ProductVo;

@Service
public class ProductService {

	public ProductVo getProductInfo(Long no) {
		return new ProductVo(1L,"test_product1", 5000L, "test1입니다", "<h1>Test1입니다</h1>", 'Y', 'Y', 'Y', 'Y', 20, 'Y', 5, 2000);
	}

	public List<ProductOptionVo> getProductOptionInfo(Long no) {
		List<ProductOptionVo> po_list = new ArrayList<ProductOptionVo>();
		po_list.add(new ProductOptionVo(1L, "화이트", "A001", 15, 'Y', 0L, 1L));
		po_list.add(new ProductOptionVo(2L, "아이보리", "A002", 12, 'Y', 2000L, 1L));
		po_list.add(new ProductOptionVo(3L, "민트", "A003", 12, 'Y', 2000L, 1L));
		return po_list;
	}

}
