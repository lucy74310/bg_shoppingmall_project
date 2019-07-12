package com.cafe24.shoppingmall.scenario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.*;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.cafe24.shoppingmall.vo.UserVo;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberJoinScenarioTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before 
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	/*
	 *  1. 회원 가입 요청
	 *  2. 회원 약관 페이지 
	 *  3. 회원 약관 동의  - 
	 *  4. 회원정보 입력 페이지 
	 *  5. 아이디 중복 체크 요청 -  
	 *  6. 아이디 중복 조회
	 *  7. 아이디 중복 여부 결과
	 *  8. 아이디 중복 시 재시도 
	 *  9. 회원 등록 요청 -
	 *  10. 입력 정보 유효성 검사 
	 *  11. 회원 등록 요청
	 *  12. 회원 정보 저장 
	 *  13. 회원 가입 완료 페이지 반환
	 */
	
	//
	//3. 회원 약관 동의 
	//
	
	//3-1. 약관에 동의x 
	@Test
	public void joinNotAgreeTest() throws Exception {
		mockMvc.perform(get("/api/user/agreecheck").param("agree", "false"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.result", is("fail")))
						.andExpect(jsonPath("$.message", is("약관에 동의해야 회원가입이 가능합니다.")));
	}
	
	//3-2. 약관 동의
	@Test
	public void joinAgreeTest() throws Exception {
		mockMvc.perform(get("/api/user/agreecheck").param("agree", "true"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(Boolean.TRUE)));
				
	}
	
	
	//
	// 5. 아이디 중복 체크 요청 -> 6. 아이디 중복 조회 -> 7. 아이디 중복 여부 결과  ->  8. 아이디 중복 시 재시도
	//
	
	// 5-1. ID 중복될 때 
	@Test
	public void idDuplicateTrueTest() throws Exception {
		
		String email = "lucy74310@gmail.com";
		
		mockMvc.perform(get("/api/user/checkemail").param("email", email ))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("fail"))) 
				.andExpect(jsonPath("$.message", is("존재하는 이메일입니다.")));
		
	}
	
	// 5-2. ID 중복되지 않을 때 
	@Test
	public void idDuplicateFalseTest() throws Exception {
		
		String email = "test@gmail.com";
		
		mockMvc.perform(get("/api/user/checkemail").param("email", email))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success"))) 
			.andExpect(jsonPath("$.data", is(false))); 
	}
	
	//
	// 9. 회원 등록 요청  ->  10. 입력 정보 유효성 검사
	//
	// 9-1. 필수 입력 사항입니다. : id, password, name, telephone, email, address, birthday
	
	@Test
	public void joinNotEmptyFailTest() throws Exception {
		Map<String, String> userNotValid = new HashMap<String, String>();
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(userNotValid)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.data.id", is("필수 입력 사항입니다.")))
				.andExpect(jsonPath("$.data.password", is("필수 입력 사항입니다.")))
				.andExpect(jsonPath("$.data.telephone", is("필수 입력 사항입니다.")))
				.andExpect(jsonPath("$.data.email", is("필수 입력 사항입니다.")))
				.andExpect(jsonPath("$.data.address", is("필수 입력 사항입니다.")))
				.andExpect(jsonPath("$.data.birthday", is("필수 입력 사항입니다.")));
	}
		
		
	// 9-2. id : 영문소문자/숫자, 4~16자
	
	@Test
	public void joinIDFailTest() throws Exception {
		// 성공조건 vo
		UserVo vo = new UserVo("test1234", "tester1", "12345Test@","01011112222", "lucy1010@naver.com", "2000-10-10", "서울시성동구");
		// 4자 미만
		vo.setId("tes");
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.id", is("영문소문자/숫자, 4~16자")));
		
		// 16자 초과
		vo.setId("testtesttesttest0");
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.id", is("영문소문자/숫자, 4~16자")));
		
		//영소문자 아닐때 
		vo.setId("TEST입니다.");
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.id", is("영문소문자/숫자, 4~16자")));
	}
	
	// 9-3. 영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자, 공백불가
	@Test
	public void joinPasswordFailTest() throws Exception {
		// 성공조건 vo
		UserVo vo = new UserVo("test1234", "tester1", "12345Test@","01011112222", "lucy1010@naver.com", "2000-10-10", "서울시성동구");
		
		// 8자 미만
		vo.setPassword("!Tt1");
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자, 공백불가")));
		
		// 16자 초과
		vo.setPassword("!TesttestTest1234");
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자, 공백불가")));
		
		// 공백포함
		vo.setPassword("Test! test@");
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자, 공백불가")));
		
		// 3가지 이상 조합 안되었을 경우 
		vo.setPassword("testistest1");
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자, 공백불가")));
	}
	
	/*
	 * userNotValid.put("name", "user1"); userNotValid.put("password", "1234");
	 * userNotValid.put("telephone", "010-1111-2222"); userNotValid.put("gender",
	 * "female"); userNotValid.put("birthday", "1999-07-10");
	 */
	
	// 9-2. 필수 데이터를 다 넣었을 때 (validation 통과 성공 시나리오 ) -> 10.유효성검사 통과 ->  11. DB에 회원 등록 요청 -> 12. 회원정보 저장 
	@Test
	public void joinRequestSuccessTest() throws Exception {
		UserVo vo = new UserVo("test1234", "tester1", "12345Test@","01011112222", "lucy1010@naver.com", "2000-10-10", "서울시성동구");
		// 성공
		mockMvc.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(vo)))
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.addr_no", is(1)))
			.andExpect(jsonPath("$.data.user_no", notNullValue()));
	}
	
}
