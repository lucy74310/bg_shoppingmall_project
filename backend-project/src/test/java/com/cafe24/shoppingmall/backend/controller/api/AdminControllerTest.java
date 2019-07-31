package com.cafe24.shoppingmall.backend.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.notNullValue;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.vo.AdminVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Transactional
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
	// A-0.중복확인
	@Ignore
	@Test 
	public void idCheckTest() throws Exception {
		
		//존재하는 id
		mockMvc.perform(get("/api/admin/idcheck/{id}", "test"))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.data", is(true)));
		
		//존재하지 않는 id
		mockMvc.perform(get("/api/admin/idcheck/{id}", "test1"))
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(false)));
		
	}
	// A-1.관리자 추가 성공
	@Ignore
	@Test 
	@Rollback(true)
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
	
	// A-2.관리자 추가 실패 (입력조건미충족)
	@Ignore
	@Test 
	public void insertFailTest() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("tes");
		adminVo.setPassword("tet");
		
		mockMvc.perform(put("/api/admin/join").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(jsonPath("$.result", is("fail")));
		
	}
	

	
	
	// B-1. 비밀번호 수정 성공
	@Ignore
	@Test 
	@Rollback(true)
	public void updateTest() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test");
		adminVo.setUpdate_password("test_update");
		
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
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("비밀번호 변경 실패")));
		
	}
	
	// C.관리자 로그인 
	@Ignore
	@Test 
	public void adminLogin() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test");
		
		mockMvc.perform(post("/api/admin/login").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.id", is("test")));

		
		adminVo.setId("test123");
		mockMvc.perform(post("/api/admin/login").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("id및 password를 확인해 주세요.")));
		
	}
	
	
	
	// D.관리자 삭제
	@Test
	@Rollback(true)
	public void adminDelete() throws Exception {
		AdminVo adminVo = new AdminVo();
		adminVo.setId("test");
		adminVo.setPassword("test");
		
		mockMvc.perform(post("/api/admin/delete").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(1)));
		
		adminVo.setId("test_fail");
		mockMvc.perform(post("/api/admin/delete").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVo)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.data", is(0)));
		
	}
	
}
