package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.service.OrderService;
import com.cafe24.shoppingmall.backend.vo.OrderProductVo;
import com.cafe24.shoppingmall.backend.vo.OrderVo;

import io.swagger.annotations.ApiOperation;

@RestController("orderAPIController")
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@ApiOperation("주문서 저장")
	@PostMapping("/add")
	public ResponseEntity<JSONResult> orderAdd(
			@RequestBody @Valid OrderVo orderVo,
			BindingResult valid
	) throws IOException {
		System.out.println(orderVo);
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목 양식을 맞춰 주세요.", errMap));
		}
		Long addedOrderNo = orderService.addOrder(orderVo);
		
		if(addedOrderNo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("주문 실패"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(addedOrderNo));
	}
	
	@ApiOperation("회원 주문 내역 조회")
	@GetMapping("/history/{no}")
	public ResponseEntity<JSONResult> memberOrderList(
		@PathVariable("no") Long member_no
	) throws IOException {
		
		List<OrderProductVo> order_product_list = orderService.getMemberHistory(member_no);
		
		if(order_product_list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(order_product_list));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("주문 내역이 없습니다."));
		}
	}
	@ApiOperation("비회원 주문 내역 조회")
	@PostMapping("/history")
	public ResponseEntity<JSONResult> nonMemberOrderList(
		@RequestBody OrderVo orderVo
	) throws IOException {
		
		List<OrderProductVo> order_product_list = orderService.getNonMemberHistory(orderVo);
		if(order_product_list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(order_product_list));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("주문 내역이 없습니다."));
		}
	}
	
	@ApiOperation("주문 상태 변경(결제관련)")
	@PutMapping("/paystate")
	public ResponseEntity<JSONResult> orderStateChange(
		@RequestBody OrderVo orderVo
	) throws IOException {
		
		int count = orderService.orderStateChange(orderVo);
		if(count == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(count));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("상태변경이 일어나지 않았습니다.",count));
		}
		
	}
	
	@ApiOperation("주문 처리 상태 변경(주문상품별배송관련)")
	@PutMapping("/shipstate")
	public ResponseEntity<JSONResult> orderProductStateChange(
		@RequestBody OrderProductVo orderProductVo
	) throws IOException {
		
		int count = orderService.orderProductStateChange(orderProductVo);
		if(count == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(count));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("상태변경이 일어나지 않았습니다.",count));
		}
		
	}
	
}
