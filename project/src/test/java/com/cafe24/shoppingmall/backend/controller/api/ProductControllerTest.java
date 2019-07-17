package com.cafe24.shoppingmall.backend.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.vo.OptionDetailVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Transactional
public class ProductControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void getProductListTest() throws Exception {
		mockMvc.perform(get("/api/product/list"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result",is("success")))
			.andExpect(jsonPath("$.data", hasSize(2)));
	}
	
	@Test
	public void getDetailInfoTest() throws Exception {
		mockMvc.perform(get("/api/product/{no}", 1L))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.product.no" , is(1)));
			
	}
	
	@Rollback(false)
	@Test
	public void addTest() throws Exception {
		List<OptionDetailVo> od_list = new ArrayList<OptionDetailVo>();
		od_list.add(new OptionDetailVo("아이보리", 0L));
		od_list.add(new OptionDetailVo("검정", 0L));
		od_list.add(new OptionDetailVo("하늘", 0L));
		
		
		List<OptionVo> option_list = new ArrayList<OptionVo>();
		option_list.add(new OptionVo("색상", od_list));
		
		
		List<Long> category_list = new ArrayList<Long>();
		category_list.add(3L);
		category_list.add(6L);
		ProductVo productVo = new ProductVo("여름린넨바지", "25000", "고급린넨여름바지!놓치지 마세요~",'Y','Y','Y','Y', 5, 'Y',2000, option_list, category_list);
		
		
		mockMvc.perform(put("/api/product/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productVo)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", notNullValue()));
	}
	
	
	@Rollback(false)
	@Test
	public void updateTest() throws Exception {
		mockMvc.perform(get("/api/product/update", 1L))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	}
//	
//	@Test
//	public void deleteTest() throws Exception {
//		mockMvc.perform(get("/api/product/delete/{no}", 1L))
//		.andDo(print())
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.result", is("success")))
//		.andExpect(jsonPath("$.data.product.no" , is(1)))
//		.andExpect(jsonPath("$.data.product_option" , hasSize(3)));
//	}
//	
}
