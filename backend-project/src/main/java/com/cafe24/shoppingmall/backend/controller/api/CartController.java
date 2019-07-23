package com.cafe24.shoppingmall.backend.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.service.CartService;
import com.cafe24.shoppingmall.backend.vo.CartVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

import io.swagger.annotations.ApiOperation;


@RestController("cartAPIController")
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@ApiOperation("장바구니 목록")
	@GetMapping("/list")
	public ResponseEntity<JSONResult> getProductsInCart(
	) {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
	}
	
	@ApiOperation("장바구니에 상품저장")
	@PutMapping("/add")
	public ResponseEntity<JSONResult> addProductToCart(
		@RequestBody CartVo cartVo
	) {
		cartVo = cartService.addCart(cartVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
	}
	@ApiOperation("장바구니 상품수정")
	@PostMapping("/update")
	public ResponseEntity<JSONResult> updateProductInCart(
		@RequestBody CartVo cartVo
	) {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
	}
	
	@ApiOperation("장바구니 상품 삭제")
	@DeleteMapping("/delete")
	public ResponseEntity<JSONResult> deleteProductInCart(
		@RequestBody CartVo cartVo
	) {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
	}
	
	
}
