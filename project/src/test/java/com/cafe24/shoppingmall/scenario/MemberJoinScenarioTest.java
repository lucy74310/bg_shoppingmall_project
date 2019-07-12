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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;

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
						.andDo(print())
						.andExpect(jsonPath("$.result", is("fail")))
						.andExpect(jsonPath("$.message", is("약관에 동의해야 회원가입이 가능합니다.")));
	}
	
	//3-2. 약관 동의
	@Test
	public void joinAgreeTest() throws Exception {
		mockMvc.perform(get("/api/user/agreecheck").param("agree", "true"))
				.andExpect(status().isOk())
				.andDo(print())
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
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success"))) // 통신성공
				.andExpect(jsonPath("$.data", is(true))); // 중복있음 
		
	}
	
	// 5-2. ID 중복되지 않을 때 
	@Test
	public void idDuplicateFalseTest() throws Exception {
		
		String email = "test@gmail.com";
		
		mockMvc.perform(get("/api/user/checkemail").param("email", email))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success"))) // 통신성공
			.andExpect(jsonPath("$.data", is(false))); // 중복없음
	}
	
	//
	// 9. 회원 등록 요청  ->  10. 입력 정보 유효성 검사
	//
	
	// 9-1. 필수 데이터를 다 넣지 않았을 때 (validation 통과 실패 시나리오 )
	@Test
	public void joinRequestFailTest() throws Exception {
		Map<String, String> userNotValid = new HashMap<String, String>();
		userNotValid.put("id", "test");
		userNotValid.put("name", "user1");
		userNotValid.put("password", "1234");
		userNotValid.put("telephone", "010-1111-2222");
		userNotValid.put("gender", "female");
		userNotValid.put("birthday", "1999-07-10");
		
		ResultActions resultAction = 
				mockMvc.perform(post("/api/user/join").param("id", userNotValid.get("id"))
						.param("name", userNotValid.get("name"))
						.param("password", userNotValid.get("password"))
						.param("telephone", userNotValid.get("telephone"))
						.param("gender", userNotValid.get("gender"))
						.param("birthday", userNotValid.get("birthday"))
						);
		
		resultAction
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data[0].defaultMessage", is("may not be empty")));
		
	}
	
	// 9-2. 필수 데이터를 다 넣었을 때 (validation 통과 성공 시나리오 ) -> 10.유효성검사 통과 ->  11. DB에 회원 등록 요청 -> 12. 회원정보 저장 
	@Test
	public void joinRequestSuccessTest() throws Exception {
		Map<String, String> userValid = new HashMap<String, String>();
		userValid.put("id", "test");
		userValid.put("name", "user1");
		userValid.put("password", "1234");
		userValid.put("telephone", "010-1111-2222");
		userValid.put("email", "test@gmail.com");
		userValid.put("gender", "female");
		userValid.put("birthday", "1999-07-10");
		
		// 10~12
		ResultActions resultAction = mockMvc.perform(post("/api/user/join").param("id", userValid.get("id"))
						.param("name", userValid.get("name"))
						.param("password", userValid.get("password"))
						.param("telephone", userValid.get("telephone"))
						.param("email", userValid.get("email"))
						.param("gender", userValid.get("gender"))
						.param("birthday", userValid.get("birthday"))
						);
		
		resultAction
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.no", is(notNullValue())));
	}
	
}
