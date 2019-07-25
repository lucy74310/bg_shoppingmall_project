package com.cafe24.shoppingmall.backend.controller.api;

import java.util.List;
import java.util.Map;

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
	@GetMapping("/list/{is_member}/{no}")
	public ResponseEntity<JSONResult> getProductsInCart(
		@PathVariable(value="is_member") Boolean is_member,
		@PathVariable(value="no") Long no
	) {
		List<CartVo> cart_list = cartService.getCartList(is_member, no);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(cart_list));
	}
	
	@ApiOperation("장바구니에 상품저장")
	@PutMapping("/add")
	public ResponseEntity<JSONResult> addProductToCart(
		@RequestBody @Valid CartVo cartVo,
		BindingResult valid
	) {
		if(valid.hasErrors()) {
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail(f.getDefaultMessage()));
			}
		}
		
		cartVo = cartService.addCart(cartVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(cartVo));
	}
	@ApiOperation("장바구니 상품수정")
	@PostMapping("/update")
	public ResponseEntity<JSONResult> updateProductInCart(
		@RequestBody @Valid CartVo cartVo,
		BindingResult valid
	) {
		if(valid.hasErrors()) {
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail(f.getDefaultMessage()));
			}
		}
		boolean result = cartService.updateCart(cartVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}
	
	@ApiOperation("장바구니 상품 삭제")
	@DeleteMapping("/delete")
	public ResponseEntity<JSONResult> deleteProductInCart(
		@RequestBody @Valid CartVo cartVo
	) {
		boolean result = cartService.deleteCart(cartVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}
	
	@ApiOperation("장바구니 이미 들어간 상품인지 확인")
	@GetMapping("/hascheck/{is_member}/{memno}/{po_no}")
	public ResponseEntity<JSONResult> alreadyHasCheck(
			@PathVariable(value="is_member") Boolean is_member,
			@PathVariable(value="memno") Long mem_no,
			@PathVariable(value="po_no") Long po_no
	){
		
		
		int count = cartService.alreadyHasCheck(is_member, mem_no, po_no);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(count));
	}
	
}
