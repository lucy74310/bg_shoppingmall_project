package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.service.ProductService;
import com.cafe24.shoppingmall.backend.vo.ProductOptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController("productAPIController")
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	@ApiOperation("상품등록")
	@PutMapping("/add")
	public ResponseEntity<JSONResult> productAdd(
			@RequestBody @Valid ProductVo productVo,
			BindingResult valid) throws IOException {
		System.out.println(productVo);
		
		if(valid.hasErrors()) {
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail(f.getDefaultMessage()));
			}
		} 
		
		Long productNo = productService.addProduct(productVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productNo));
	}
	
	@ApiOperation("상품수정")
	@PostMapping("/update")
	public ResponseEntity<JSONResult> productUpdate(
			@RequestBody @Valid ProductVo productVo,
			BindingResult result
	) {
		
		if(result.hasErrors()) {
			//에러! 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수 입력 항목을 입력해 주세요.", result.getAllErrors()));
		} else {
			
			boolean updateResult = productService.updateProduct(productVo);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(updateResult));
		}
	}
	
	@ApiOperation("상품삭제")
	@DeleteMapping("/delete/{deleteNo}")
	public ResponseEntity<JSONResult> productDelete(
			@PathVariable(value="deleteNo") Long deleteNo,
			@RequestParam Boolean test
	) {
		Long result;
		if(test) {
			result =  productService.deleteProduct(deleteNo);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
		} else {
			result = null;
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("fail", result));
		}
	}
	
	@ApiOperation("재고수량변경")
	@GetMapping("/stock/update/{optionNo}/{count}")
	public ResponseEntity<JSONResult> updateStock(
			@PathVariable(value="optionNo") Long deleteNo,
			@PathVariable(value="count") int count,
			@RequestParam Boolean test
	) {
		Long result;
		if(test) {
			result =  productService.deleteProduct(deleteNo);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
		} else {
			result = null;
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("fail", result));
		}
	}
	
	@ApiOperation("상품목록")
	@GetMapping("/list")
	public ResponseEntity<JSONResult> getList() {
		
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		list.add(new ProductVo("test_product1", "5000", "test1입니다", "<h1>Test1입니다</h1>", 'Y', 'Y', 'N', 'Y', 20, 'Y', 5, 2000));
		list.add(new ProductVo("test_product2", "12000L", "test2입니다", "<h1>Test2입니다</h1>", 'Y', 'Y', 'N', 'Y', 20, 'Y', 5, 2000));
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(list));
	}
	@ApiOperation("상세보기")
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
	
	@ApiOperation("재고정보")
	@GetMapping("/stock/list/{productNo}")
	public ResponseEntity<JSONResult> getProductOptionInfo(
			@PathVariable(value="productNo") Long no
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
