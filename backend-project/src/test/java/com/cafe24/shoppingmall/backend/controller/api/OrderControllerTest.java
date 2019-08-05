package com.cafe24.shoppingmall.backend.controller.api;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.config.AppConfig;
import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.vo.CartVo;
import com.cafe24.shoppingmall.backend.vo.OrderProductVo;
import com.cafe24.shoppingmall.backend.vo.OrderVo;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;
import com.sun.glass.ui.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Transactional
public class OrderControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	// A. 주문하기 
	// A-1. 회원일 경우
	// A-2. 비회원일 경우 
	
	// B. 구매내역 조회하기
	// B-1. 회원일 경우
	// B-2. 비회원일 경우 
	
	// C. 주문 상태변경
	// D. 주문상품 상태변경
	
	
	// A. 주문하기 
	// A-1. 회원일 경우
	@Test
	@Rollback(false)
	public void memberOrderAddTest() throws Exception {
		//선택된 상품 가져오기 
		Boolean is_member = true;
		Long member_no = 1L;
		MvcResult result = 
		mockMvc.perform(get("/api/cart/list/{is_member}/{no}", is_member, member_no))
			.andDo(print()).andReturn();
		JSONResult jsonResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JSONResult.class);
		List<CartVo> cart_list_map = (List<CartVo>)jsonResult.getData();
		List<CartVo> cart_list = new ArrayList<CartVo>();
		for(int i=0; i<cart_list_map.size(); i++) {
			cart_list.add(new ObjectMapper().convertValue(cart_list_map.get(i), CartVo.class));
		}
		int pay_amount = 0;
		for(CartVo c :cart_list) {
			CartVo c2 = new ObjectMapper().convertValue(c, CartVo.class);
			pay_amount += c2.getPrice();
		}
		
		
		OrderVo orderVo = new OrderVo( 
				"배타미", "tami@barro.com", "010-8585-5555",
				"차현", "서울시 강남구 서초로 23 바로빌딩 3층", "010-9999-9999",
				pay_amount, member_no, cart_list);
		
		
		mockMvc.perform(post("/api/order/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(notNullValue())))
			;
	}
	
	// A-2. 비회원일 경우 
	@Test
	@Rollback(false)
	public void nonMemberOrderAddTest() throws Exception {
		//선택된 상품 가져오기 
		Boolean is_member = false;
		Long non_member_no = 1L;
		MvcResult result = 
		mockMvc.perform(get("/api/cart/list/{is_member}/{no}", is_member, non_member_no))
			.andDo(print()).andReturn();
		JSONResult jsonResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JSONResult.class);
		List<CartVo> cart_list_map = (List<CartVo>)jsonResult.getData();
		List<CartVo> cart_list = new ArrayList<CartVo>();
		for(int i=0; i<cart_list_map.size(); i++) {
			cart_list.add(new ObjectMapper().convertValue(cart_list_map.get(i), CartVo.class));
		}
		int pay_amount = 0;
		for(CartVo c :cart_list) {
			CartVo c2 = new ObjectMapper().convertValue(c, CartVo.class);
			pay_amount += c2.getPrice();
		}
		
		
		OrderVo orderVo = new OrderVo( 
				"배타미_비회원", "tami@barro.com", "010-8585-5555",
				"차현", "서울시 강남구 서초로 23 바로빌딩 3층", "010-9999-9999",
				pay_amount, "iamnonmember", cart_list);
		
		
		mockMvc.perform(post("/api/order/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(notNullValue())))
			;
	}
	
	
	@Test
	public void addFailTest() throws Exception {
		OrderVo orderVo = new OrderVo();
		
		mockMvc.perform(post("/api/order/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo))
				)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			;
	}
	
	// B. 구매내역 조회하기
	// B-1. 회원일 경우
	@Test
	public void memberOrderList() throws Exception {
		Long mem_no = 2L;
		mockMvc.perform(get("/api/order/history/{no}", mem_no))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	// B-2. 비회원일 경우
	// 주문자 이름, 주문번호, 주문조회비밀번호 입력받음 
	@Test
	public void nonMemberOrderList() throws Exception {
		OrderVo orderVo = new OrderVo();
		
		orderVo.setOrderer_name("배타미_비회원");
		orderVo.setOrder_code("20190804-0000002");
		orderVo.setOrder_check_password("iamnonmember");
		
		mockMvc.perform(post("/api/order/history")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
		OrderVo orderVo2 = new OrderVo();
		
		orderVo2.setOrderer_name("배타미_비회원ㅇ");
		orderVo2.setOrder_code("20190804-0000002");
		orderVo2.setOrder_check_password("iamnonmember");
		
		mockMvc.perform(post("/api/order/history")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo2)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	// C. 주문 상태변경(결제관련)
	@Test
	@Rollback(false)
	public void orderStateChange() throws Exception {
		OrderVo orderVo = new OrderVo();
		orderVo.setNo(1L);
		orderVo.setOrder_state("결제대기중");
		
		mockMvc.perform(put("/api/order/paystate").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	@Rollback(false)
	public void orderStateChangeFail() throws Exception {
		OrderVo orderVo = new OrderVo();
		orderVo.setNo(4L);
		orderVo.setOrder_state("결제대기중");
		
		mockMvc.perform(put("/api/order/paystate").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	// D. 주문상품 상태변경 (배송관련)
	@Test
	@Rollback(false)
	public void orderProductStateChange() throws Exception {
		OrderProductVo orderProductVo = new OrderProductVo();
		orderProductVo.setOrder_no(1L);
		orderProductVo.setOrder_handling_state("품절-환불처리완료");
		
		// product_option_no 값 안넘겨주면 해당 주문서의 모든 상품 update
		mockMvc.perform(put("/api/order/shipstate").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderProductVo)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(greaterThanOrEqualTo(1))));
		
		// product_option_no 값 넘겨주면 해당 주문서의 해당 상품만 update
		orderProductVo.setProduct_option_no(5L);
		orderProductVo.setOrder_handling_state("배송완료");
		mockMvc.perform(put("/api/order/shipstate").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderProductVo)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(1)));
	}
	
	// D. 주문상품 상태변경 (배송관련) -- 실패
	@Test
	@Rollback(false)
	public void orderProductStateChangeFail() throws Exception {
		OrderProductVo orderProductVo = new OrderProductVo();
		orderProductVo.setOrder_no(3L);
		orderProductVo.setOrder_handling_state("품절-환불처리완료");
		
		// product_option_no 값 안넘겨주면 해당 주문서의 모든 상품 update
		mockMvc.perform(put("/api/order/shipstate").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderProductVo)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(greaterThanOrEqualTo(1))));
		
		// product_option_no 값 넘겨주면 해당 주문서의 해당 상품만 update
		orderProductVo.setProduct_option_no(5L);
		orderProductVo.setOrder_handling_state("배송완료");
		mockMvc.perform(put("/api/order/shipstate").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderProductVo)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(1)));
	}
}
