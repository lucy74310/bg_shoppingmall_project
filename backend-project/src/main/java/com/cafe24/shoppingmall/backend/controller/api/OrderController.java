package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.service.OrderService;
import com.cafe24.shoppingmall.backend.vo.CartVo;
import com.cafe24.shoppingmall.backend.vo.OrderProductVo;
import com.cafe24.shoppingmall.backend.vo.OrderVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

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
		
		if(valid.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수 입력 항목을 입력해 주세요.", valid.getAllErrors()));
		}
		System.out.println(orderVo);
		Long addedOrder = orderService.addOrder(orderVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(addedOrder));
	}
	
	@ApiOperation("회원 주문 내역 조회")
	@GetMapping("/history/member/{no}")
	public ResponseEntity<JSONResult> memberOrderList(
		@PathVariable("no") Long member_no
	) throws IOException {
		
		List<OrderProductVo> order_product_list = orderService.getMemberHistory(member_no);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(order_product_list));
	}
	@ApiOperation("비회원 주문 내역 조회")
	@PostMapping("/history/nonmember")
	public ResponseEntity<JSONResult> nonMemberOrderList(
		@RequestBody OrderVo orderVo
	) throws IOException {
		
		List<OrderProductVo> order_product_list = orderService.getNonMemberHistory(orderVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(order_product_list));
	}
	
	@ApiOperation("주문 상태 변경(결제관련)")
	@PutMapping("/paystate/change")
	public ResponseEntity<JSONResult> orderStateChange(
		@RequestBody OrderVo orderVo
	) throws IOException {
		
		int count = orderService.orderStateChange(orderVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(count));
	}
	
	@ApiOperation("주문 처리 상태 변경(주문상품별배송관련)")
	@PutMapping("/shipstate/change")
	public ResponseEntity<JSONResult> orderProductStateChange(
		@RequestBody OrderProductVo orderProductVo
	) throws IOException {
		
		int count = orderService.orderProductStateChange(orderProductVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(count));
	}
	
}
