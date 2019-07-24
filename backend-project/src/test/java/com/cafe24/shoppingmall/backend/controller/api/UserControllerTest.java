package com.cafe24.shoppingmall.backend.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class UserControllerTest {
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	//회원가입
	//회원정보수정
	//회원탈퇴
	//배송지등록
	//배송지수정
	//배송지삭제
	
	//비회원추가 성공
	@Test
	public void addNonmemberSuccessTest() throws Exception {
		mockMvc.perform(put("/api/user/nonmember/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(new NonMemberVo("test1234567"))))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.no", is(notNullValue())))	
				;
	}
	
	//비회원추가 실패
	@Test
	public void addNonmemberFailTest() throws Exception {
		mockMvc.perform(put("/api/user/nonmember/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(new NonMemberVo(""))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("session값이 없습니다.")))	
				;
	}
	
	
	
}
