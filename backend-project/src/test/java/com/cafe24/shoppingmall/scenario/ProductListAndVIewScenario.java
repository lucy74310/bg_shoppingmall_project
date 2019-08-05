package com.cafe24.shoppingmall.scenario;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class ProductListAndVIewScenario {
private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before 
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	/**
	 * 
	1. 상품 리스트 요청 
	2. 상품 리스트 조회
	3. 상품 리스트 반환
	4. 카테고리 선택
	5. 상품 리스트 조회
	6. 상품 리스트 반환
	7. 상품 상세 보기 요청
	8. 상품 정보 조회
	9. 상품 옵션 정보 조회
	10. 상품 상세 정보 반환
	 * 
	 */
	
	
	/**
	 * 1. 상품 리스트 요청
	 * @throws Exception
	 */
	@Test
	public void getProductListTest() throws Exception {
		mockMvc.perform(get("/api/product/list"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data", hasSize(2)));
	}
	/**
	 * 7. 상품 상세 보기 요청
	 * @throws Exception
	 */
	@Test
	public void getProductViewTest() throws Exception {
		mockMvc.perform(get("/api/product/{no}", 1L))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.product.no" , is(1)))
		.andExpect(jsonPath("$.data.product_option" , hasSize(3)));
	}
	
}
