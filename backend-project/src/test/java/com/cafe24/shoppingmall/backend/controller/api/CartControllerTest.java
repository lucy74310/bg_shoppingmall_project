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
import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Transactional
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
	@Test
	public void checkAlreadyHas() throws Exception {
		Boolean is_member = true;
		Long member_no = 1L;
		Long po_no = 7L;
		
		// 있는 상품 : count 반환 
		mockMvc.perform(
				get("/api/cart/hascheck/{is_member}/{memno}/{po_no}", is_member, member_no, po_no))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(not(-1))))
				.andReturn();
	}
	
	// A-2. 장바구니에 없는 상품 (-1 반환)
	@Test
	public void checkAlreadyHasNot() throws Exception{
		Boolean is_member = true;
		Long member_no = 1L;
		Long po_no = 6L;
		
		mockMvc.perform(
				get("/api/cart/hascheck/{is_member}/{memno}/{po_no}", is_member, member_no, po_no))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.data", is(-1)));
	}
	
	
	// B. 장바구니 담기
	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void addTest() throws Exception {
		//Long non_member_no = Long.parseLong(userAdd().toString());
		// 들어가있는 non_member_no 쓰기로
		Long member_no = 1L;
		MvcResult result;
		Long po_no = 5L;
		JSONResult jsonResult;
		int count = 1;
		Map<String,Object> data;
		CartVo insertCartVo = new CartVo(po_no, 18500L, count);
		insertCartVo.setMember_no(member_no);
		
		// B. 장바구니 담기
		// 재고의 수량보다 적게 선택했고, 장바구니에 없는 상품이라고 전제 
		result = mockMvc.perform(post("/api/cart/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(insertCartVo)))
						.andDo(print())
						.andExpect(jsonPath("$.result", is("success")))
						.andExpect(jsonPath("$.data.no", notNullValue()))
						.andReturn();
	
		jsonResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), JSONResult.class);
		data = (Map<String, Object>) jsonResult.getData();
		insertCartVo = new ObjectMapper().convertValue(data, CartVo.class);
		
		
	}
	@Test
	public void addFailTest() throws Exception {
		Long member_no = 1L;
		Long po_no = 5L;
		int count = 1;
		Map<String,Object> data;
		CartVo insertCartVo = new CartVo(po_no, 18500L, count);
		insertCartVo.setMember_no(member_no); 
		insertCartVo.setCount(0);
		
		// B. 장바구니 담기 실패
		mockMvc.perform(post("/api/cart/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(insertCartVo)))
						.andDo(print())
						.andExpect(jsonPath("$.result", is("fail")));
	}
	// C. 장바구니 수정
	@Test
	public void updateTest() throws Exception {
		Long member_no = 1L;
		Long po_no = 5L;
		int count = 2;
		Map<String,Object> data;
		CartVo updateCartVo = new CartVo(po_no, 18500L, count);
		updateCartVo.setMember_no(member_no); 
		
		// C. 장바구니 수정
		// 재고의 수량보다 적게 선택했고, 장바구니에 있는 상품이라고 전제 
		updateCartVo.setCount(3);
		
		mockMvc.perform(put("/api/cart/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(updateCartVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(true)));
	}
	// C. 장바구니 수정 실패
	@Test
	public void updateFailTest() throws Exception {
		Long member_no = 1L;
		Long po_no = 6L;
		int count = 2;
		Map<String,Object> data;
		CartVo updateCartVo = new CartVo(po_no, 18500L, count);
		updateCartVo.setMember_no(member_no); 
		
		mockMvc.perform(put("/api/cart/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(updateCartVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("fail")));
	}
	
	// D. 장바구니 삭제
	@Rollback(true)
	@Test
	public void deleteTest() throws Exception {
		Long product_option_no = 5L;
		Long mem_no = 1L;
		CartVo delVo = new CartVo(mem_no , product_option_no);
		// D. 장바구니 삭제
		mockMvc.perform(delete("/api/cart/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(delVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")));
	}
	// D. 장바구니 삭제 실패
	@Test
	public void deleteFailTest() throws Exception {
		Long product_option_no = 7L;
		Long mem_no = 1L;
		CartVo delVo = new CartVo(mem_no , product_option_no);
		// D. 장바구니 삭제
		mockMvc.perform(delete("/api/cart/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(delVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("fail")));
	}
	
	
	private Object userAdd() throws Exception {
		MvcResult result = 
		mockMvc.perform(post("/api/user/nonmember/add")
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
		Long non_member_no = 65L;
		CartVo cartVo = new CartVo(9L, 25500L, 0);
		cartVo.setNon_member_no(non_member_no);
		mockMvc.perform(post("/api/cart/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(cartVo)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("수량은 1개 이상이여야 합니다.")));
		
		mockMvc.perform(put("/api/cart/update")
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
	@Test
	public void getCartListTest() throws Exception {
		Boolean is_member = true;
		Long no = 1L;
		
		mockMvc.perform(get("/api/cart/list/{is_member}/{no}", is_member, no))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	// E. 장바구니 목록 가져오기 - 비어있을 때
	@Test
	public void getCartListFailTest() throws Exception {
		Boolean is_member = true;
		Long no =2L;
		
		mockMvc.perform(get("/api/cart/list/{is_member}/{no}", is_member, no))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
}
