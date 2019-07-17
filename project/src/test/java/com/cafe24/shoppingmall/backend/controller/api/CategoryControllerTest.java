package com.cafe24.shoppingmall.backend.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;

import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.vo.CategoryVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Transactional
public class CategoryControllerTest {
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	
	@Test
	public void categoryListTest() throws Exception {
		ResultActions resultActions =
				mockMvc.perform(get("/api/category/list"));
						
		resultActions
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success")));
		
	}
	
	
	
	/* 카테고리추가 - 성공 */
	@Rollback(true)
	@Test
	public void categoryAddSuccessTest() throws Exception {
		CategoryVo categoryVo = new CategoryVo("액세서리");
		mockMvc.perform(post("/api/category/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data", notNullValue()));
	}
	
	/* 카테고리추가 - 실패 - empty */
	@Test
	public void categoryAddFailEmptyTest() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		mockMvc.perform(post("/api/category/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("1~20자 사이로 입력해 주세요")));
	}
	
	/* 카테고리추가 - 실패 - 20자 이상 */
	@Test
	public void categoryAddFailLengtTest() throws Exception {
		CategoryVo categoryVo = new CategoryVo("ThisIsAccessary123456");
		mockMvc.perform(post("/api/category/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("1~20자 사이로 입력해 주세요")));
	}
	
	
	/* 카테고리수정 - 성공 */
	@Rollback(true)
	@Test
	public void categoryUpdateTest() throws Exception {
		
		//변경시킬 vo insert
		CategoryVo categoryVo = new CategoryVo("Top", "상의카테고리입니다.");
		
		ResultActions resultAction =mockMvc.perform(post("/api/category/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data", notNullValue()));
		 
		
		MvcResult result = resultAction.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		JSONResult jsonResult = new ObjectMapper().readValue(contentAsString, JSONResult.class);
		
		categoryVo.setNo(Long.parseLong(jsonResult.getData().toString()));
		 
		//vo update
		categoryVo.setCategory_name("Top수정");
		mockMvc.perform(
				post("/api/category/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		//vo delete
		//mockMvc.perform(delete("/api/category/delete/{categoryNo}", categoryVo.getNo()));
		
	}
	/* 카테고리수정 - 실패 - 이름 empty */
	@Test
	public void categoryUpdateFailEmptyTest() throws Exception {
		CategoryVo categoryVo = new CategoryVo("");
		mockMvc.perform(post("/api/category/update").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("1~20자 사이로 입력해 주세요")));
	}
	
	/* 카테고리수정 - 실패 - 이름 20자 이상 */
	@Test
	public void categoryUpdateFailLengtTest() throws Exception {
		CategoryVo categoryVo = new CategoryVo("ThisIsAccessary123456");
		mockMvc.perform(post("/api/category/update").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("1~20자 사이로 입력해 주세요")));
	}
	
	/* 카테고리수정 - 실패 - DB에러 */
	@Test
	public void categoryUpdateFailDBTest() throws Exception {
		CategoryVo categoryVo = new CategoryVo("ThisIsAccessary");
		//없는 no 넣어줌
		categoryVo.setNo(1L);
		mockMvc.perform(post("/api/category/update").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("수정이 되지 않았습니다.")))
				.andExpect(jsonPath("$.data", is(false)));
	}
	
	
	/* 카테고리 삭제 - 성공 */
	@Rollback(true)
	@Test
	public void categoryDeleteSuccessTest() throws Exception {
		//삭제할 vo insert
		CategoryVo categoryVo = new CategoryVo("test", "test");
		
		ResultActions resultAction =mockMvc.perform(post("/api/category/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVo)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data", notNullValue()));
		MvcResult result = resultAction.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		JSONResult jsonResult = new ObjectMapper().readValue(contentAsString, JSONResult.class);
		
		ResultActions resultActions =
				mockMvc.perform(delete("/api/category/delete/{categoryNo}", jsonResult.getData().toString()));
						
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	/* 카테고리 삭제 - DB에러 
	 * 없는 no  */
	@Test
	public void categoryDeleteFailTest() throws Exception {
		//없는 no 삭제 요청 
		ResultActions resultActions =
				mockMvc.perform(delete("/api/category/delete/{categoryNo}", 1L));
						
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", is("삭제가 되지 않았습니다.")))
			.andExpect(jsonPath("$.data", is(false)));
	}
}
