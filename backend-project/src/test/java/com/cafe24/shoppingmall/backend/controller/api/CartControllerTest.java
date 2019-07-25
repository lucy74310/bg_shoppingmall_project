package com.cafe24.shoppingmall.backend.controller.api;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
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
import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class CartControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	// Test List
	// A. 장바구니에 있는 상품인지 확인 (갯수 반환)
	// B. 장바구니 담기
	// C. 장바구니 수정
	// D. 장바구니 삭제
	// E. 장바구니 리스트 
	
	// 장바구니 담기 시나리오는 아래와 같다.
	// 1. 장바구니에 있는 상품인지 확인 
	// 1-1. 장바구니에 있으면, 갯수와 현재 선택한 갯수를 더해 재고보다 많은지 확인
	// 1-1-1. 재고보다 많으면 메세지 띄워줌 (ex : "상품의 갯수가 재고의 수량보다 많습니다.") -> 다시 선택(-> 갯수 더해 재고와 다시 비교) or 취소 
	// 1-1-2. 재고보다 적으면 장바구니 수정
	// 1-2. 장바구니에 없는 상품
	// 1-2-1. 재고와 현재 선택한 갯수 비교 
	// 1-2-1. 1-1-1 
	// 1-2-2. 1-1-2
	
	// A. 장바구니에 있는 상품인지 확인 ( 있는 상품 일 때 갯수 반환) 
	// A-1. 장바구니에 있는 상품 (갯수 반환) 
	@Ignore
	@Test
	public void checkAlreadyHas() throws Exception {
		Boolean is_member = false;
		Long non_member_no = 64L;
		Long po_no = 9L;
		
		// 있는 상품 : count 반환 
		mockMvc.perform(
				get("/api/cart/hascheck/{is_member}/{memno}/{po_no}", is_member, non_member_no, po_no))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(not(-1))))
				.andReturn();
		
		// MvcResult result;
		// JSONResult jsonResult;
		// jsonResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JSONResult.class);
		// int count = Integer.parseInt(jsonResult.getData().toString());
		
	}
	
	// A-2. 장바구니에 없는 상품 (-1 반환)
	@Ignore
	@Test
	public void checkAlreadyHasNot() throws Exception{
		Boolean is_member = false;
		Long non_member_no = 64L;
		Long po_no = 10L;
		
		mockMvc.perform(
				get("/api/cart/hascheck/{is_member}/{memno}/{po_no}", is_member, non_member_no, po_no))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(-1)))
				.andReturn();
	}
	
	
	// B. 장바구니 담기
	// C. 장바구니 수정
	// D. 장바구니 삭제
	@SuppressWarnings("unchecked")
	@Ignore
	@Test
	public void addUpdateDeleteCartSuccessTest() throws Exception {
		//Long non_member_no = Long.parseLong(userAdd().toString());
		// 들어가있는 non_member_no 쓰기로
		Long non_member_no = 64L;
		Long member_no = 7L;
		MvcResult result;
		// Long po_no = 9L;
		//Long po_no = 16L;
		Long po_no = 21L;
		JSONResult jsonResult;
		int count = 1;
		Map<String,Object> data;
		CartVo insertCartVo = new CartVo(po_no, 25500L, count);
		//insertCartVo.setNon_member_no(non_member_no);
		insertCartVo.setMember_no(member_no); 
		
		// B. 장바구니 담기
		// 재고의 수량보다 적게 선택했고, 장바구니에 없는 상품이라고 전제 
		result = mockMvc.perform(put("/api/cart/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(insertCartVo)))
						.andDo(print())
						.andExpect(jsonPath("$.result", is("success")))
						.andExpect(jsonPath("$.data.no", notNullValue()))
						.andReturn();
	
		jsonResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JSONResult.class);
		data = (Map<String, Object>) jsonResult.getData();
		insertCartVo = new ObjectMapper().convertValue(data, CartVo.class);
		
		
		// C. 장바구니 수정
		// 재고의 수량보다 적게 선택했고, 장바구니에 있는 상품이라고 전제 
		insertCartVo.setCount(1);
		
		mockMvc.perform(post("/api/cart/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(insertCartVo)))
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(true)));

		
		// D. 장바구니 삭제
//		mockMvc.perform(delete("/api/cart/delete")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new Gson().toJson(insertCartVo)))
//				.andDo(print())
//				.andExpect(jsonPath("$.result", is("success")))
//				.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
	
	
	private Object userAdd() throws Exception {
		MvcResult result = 
		mockMvc.perform(put("/api/user/nonmember/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(new NonMemberVo("user1"))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.no", is(notNullValue())))	
				.andReturn();
		
		JSONResult jsonResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JSONResult.class);
		Map<String,Object> data = (Map<String, Object>) jsonResult.getData();
		return data.get("no");
	}
	
	// B-2. & C-2 장바구니 담기,수정 에러 (선택수량 0)  
	@Ignore
	@Test
	public void updateCartFailTest() throws Exception {
		//Long non_member_no = Long.parseLong(userAdd().toString());
		Long non_member_no = 64L;
		CartVo cartVo = new CartVo(9L, 25500L, 0);
		cartVo.setNon_member_no(non_member_no);
		mockMvc.perform(put("/api/cart/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(cartVo)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("수량은 1개 이상이여야 합니다.")));
		
		mockMvc.perform(post("/api/cart/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(cartVo)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("수량은 1개 이상이여야 합니다.")));
	}
	
	
	// D-2. 장바구니 삭제 에러 
	// no+member_no 조합 또는 no+non_member_no 조합이 없을떄 
	@Ignore
	@Test
	public void deleteCartFailTest() throws Exception {
		//Long non_member_no = Long.parseLong(userAdd().toString());
		Long non_member_no = 64L;
		
	}
	
	// E. 장바구니 목록 가져오기
//	@Ignore
	@Test
	public void getCartListTest() throws Exception {
		Boolean is_member = true;
		Long no = 7L;
		
		mockMvc.perform(get("/api/cart/list/{is_member}/{no}", is_member, no))
			.andDo(print());
	}
}
