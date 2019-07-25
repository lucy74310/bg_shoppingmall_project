package com.cafe24.shoppingmall.backend.controller.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.vo.CartVo;
import com.cafe24.shoppingmall.backend.vo.OrderVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class OrderControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void orderAddTest() throws Exception {
		//선택된 상품 가져오기 
		Boolean is_member = true;
		Long no = 7L;
		// member_mo = 7L의 장바구니 리스트 전부 주문하는 전제 
		MvcResult result = 
		mockMvc.perform(get("/api/cart/list/{is_member}/{no}", is_member, no))
			.andDo(print()).andReturn();
		JSONResult jsonResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JSONResult.class);
		
		//장바구니 리스트 
		List<CartVo> cart_list =  (List<CartVo>) jsonResult.getData();
		int pay_amount = 0;
		for(CartVo c :cart_list) {
			CartVo c2 = new ObjectMapper().convertValue(c, CartVo.class);
			pay_amount += c2.getPrice();
		}
		
		OrderVo orderVo = new OrderVo("20190725-0000001",
				"배타미", "tami@barro.com", "010-8585-5555",
				"차현", "서울시 강남구 서초로 23 바로빌딩 3층", "010-9999-9999",
				pay_amount, 7L, cart_list);
		
		
		mockMvc.perform(put("/api/order/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(orderVo))
				)
			.andDo(print());
		
		
	}
}
