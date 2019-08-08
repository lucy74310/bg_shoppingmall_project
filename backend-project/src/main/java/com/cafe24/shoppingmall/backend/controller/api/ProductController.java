package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.cafe24.shoppingmall.backend.vo.CategoryVo;
import com.cafe24.shoppingmall.backend.vo.ImageVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductCategoryVo;
import com.cafe24.shoppingmall.backend.vo.ProductOptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController("productAPIController")
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	@ApiOperation("상품등록")
	@PostMapping("/add")
	public ResponseEntity<JSONResult> productAdd(
			@RequestBody @Valid ProductVo productVo,
			BindingResult valid
	) throws IOException {
		
		if(valid.hasErrors()) {
//			Map<String, String> errMap = new HashMap<String, String>();
//			for(ObjectError err : valid.getAllErrors()) {
//				FieldError f = (FieldError) err;
//				errMap.put(f.getField(), f.getDefaultMessage());
//			}
			System.out.println(valid.getModel().getClass().getName());
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("양식을 맞춰주세요.", valid.getModel()));
		} 
		
		Long productNo = productService.addProduct(productVo);
		if(productNo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("상품이 등록되지 않았습니다."));
		}
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productNo));
	}
	
	
	
	@ApiOperation("상품수정")
	@PutMapping("/update")
	public ResponseEntity<JSONResult> productUpdate(
			@RequestBody @Valid ProductVo productVo,
			BindingResult valid
	) {
		
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
		} 
		
		int count = productService.updateProduct(productVo);
		
		if(count != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("상품이 수정되지 않았습니다.", count));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(count));
	}
	
	
	
	@ApiOperation("상품삭제")
	@DeleteMapping("/delete/{pno}")
	public ResponseEntity<JSONResult> productDelete(
			@PathVariable(value="pno") Long no
	) {
		int deleterowcount = productService.deleteProduct(no);
		if(deleterowcount == 1 ) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleterowcount));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					JSONResult.fail("상품이 삭제되지 않았습니다.", deleterowcount));
		}
	}
	
	
	
	@ApiOperation("상품목록")
	@GetMapping({"/list", "/list/{category_no}"})
	public ResponseEntity<JSONResult> getList(
		@PathVariable("category_no") Optional<Long> category_no 
	) {
		System.out.println("list");
		List<ProductVo> list = null;
		if(category_no.isPresent()) { 
			list = productService.getListByCategory(category_no.get());
		} else {
			list = productService.getList();
		}
		System.out.println(list);
		if(list.size() > 0) { 
			return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(list));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("등록된 상품이 없습니다", null));
			
		}
		
	}
	
	
	
	
	@ApiOperation("상품정보")
	@GetMapping("/detail/{no}")
	public ResponseEntity<JSONResult> getProductInfo(
			@PathVariable(value="no") Long no
	) {
		ProductVo pvo = productService.getProductInfo(no);
		if(pvo == null ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("상품정보가 존재하지 않습니다."));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(pvo));
	}
	
	@ApiOperation("재고정보(상품&상품옵션)")
	@GetMapping("/stock/{pno}")
	public ResponseEntity<JSONResult> getProducStockInfo(
			@PathVariable(value="pno") Long no
	) {
		ProductVo pvo = productService.getStockInfo(no);
		System.out.println(pvo);
		if(pvo == null ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("상품정보가 존재하지 않습니다."));
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(pvo));
	}
	
	
	@ApiOperation("재고정보(only상품옵션)")
	@GetMapping("/stock/po/{pono}")
	public ResponseEntity<JSONResult> getProductOptionStockInfo(
			@PathVariable(value="pono") Long pono
	) {
		ProductOptionVo povo = productService.getProductOptionInfo(pono);
		
		if(povo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("상품정보가 존재하지 않습니다."));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(povo));
	}
	
	
}
