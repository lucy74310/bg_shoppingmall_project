package com.cafe24.shoppingmall.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.ProductOptionVo;
import com.cafe24.shoppingmall.vo.ProductVo;

@RestController("productAPIController")
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	
	@GetMapping("/list")
	public ResponseEntity<JSONResult> getList() {
		
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		list.add(new ProductVo("test_product1", 5000L, "test1입니다", "<h1>Test1입니다</h1>", 'Y', 'Y', 'N', 'Y', 20, 'Y', 5, 2000));
		list.add(new ProductVo("test_product2", 12000L, "test2입니다", "<h1>Test2입니다</h1>", 'Y', 'Y', 'N', 'Y', 20, 'Y', 5, 2000));
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(list));
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<JSONResult> getProductInfo(
			@PathVariable(value="no") Long no
	) {
		ProductVo productVo = productService.getProductInfo(no);
		List<ProductOptionVo> productOptionList = productService.getProductOptionInfo(productVo.getNo());
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("product", productVo);
		data.put("product_option", productOptionList);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(data));
	}
	

}
