package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController("orderAPIController")
@RequestMapping("/api/order")
public class OrderController {
	
	@ApiOperation("주문서 저장")
	@PostMapping("/add")
	public ResponseEntity<JSONResult> orderAdd(
			@RequestBody @Valid ProductVo productVo,
			BindingResult result) throws IOException {
		
		if(result.hasErrors()) {
			//에러! 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수 입력 항목을 입력해 주세요.", result.getAllErrors()));
		} else {
			//에러없음~
			// insert 하고 no 값을 받아온다 ~
			
			//insert 성공시 
			productVo.setNo(1L);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
		}
	}
	
	@ApiOperation("회원 주문 내역 조회")
	@PostMapping("/member/{memberNo}")
	public ResponseEntity<JSONResult> memberOrderList(
			@RequestBody @Valid ProductVo productVo,
			BindingResult result) throws IOException {
		
		if(result.hasErrors()) {
			//에러! 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수 입력 항목을 입력해 주세요.", result.getAllErrors()));
		} else {
			//에러없음~
			// insert 하고 no 값을 받아온다 ~
			
			//insert 성공시 
			productVo.setNo(1L);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
		}
	}
	
	@ApiOperation("비회원 조회")
	@PostMapping("/nonmember")
	public ResponseEntity<JSONResult> nonMemberOrderCheck(
			@RequestBody @Valid ProductVo productVo,
			BindingResult result) throws IOException {
		
		if(result.hasErrors()) {
			//에러! 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수 입력 항목을 입력해 주세요.", result.getAllErrors()));
		} else {
			//에러없음~
			// insert 하고 no 값을 받아온다 ~
			
			//insert 성공시 
			productVo.setNo(1L);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
		}
	}
	

}
