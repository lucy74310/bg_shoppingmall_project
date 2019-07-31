package com.cafe24.shoppingmall.backend.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.notNullValue;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.vo.AdminVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminControllerTest {
	
private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	// A.관리자 추가
	// B.관리자 비밀번호 수정
	// B-1. 비밀번호 수정 성공
	// B-2. 비밀번호 수정 실패
	// C.관리자 로그인 
	// D.관리자 삭제
	
	
	
	// A.관리자 추가
	@Ignore
	@Test 
	public void insertTest() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test");
		
		mockMvc.perform(put("/api/admin/join").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", notNullValue()));
		
	}
	
	// B-1. 비밀번호 수정 성공
	@Ignore
	@Test 
	public void updateTest() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test_update");
		adminVo.setUpdate_password("test");
		
		mockMvc.perform(post("/api/admin/modify").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(1)));
		
	}
	
	// B-2. 비밀번호 수정 실패 (비밀번호 틀림)
	@Ignore
	@Test 
	public void updateFailTest() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test_fail");
		adminVo.setUpdate_password("test_update");
		
		mockMvc.perform(post("/api/admin/modify").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(0)));
		
	}
	
	// C.관리자 로그인 
	@Ignore
	@Test 
	public void adminLogin() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test_update");
		
		mockMvc.perform(post("/api/admin/login").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.id", is("test")));
		
	}
	
	
	
	// D.관리자 삭제
	@Ignore
	@Test
	public void adminDelete() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test_update");
		
		mockMvc.perform(post("/api/admin/delete").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(1)));
		
	}
	
}
