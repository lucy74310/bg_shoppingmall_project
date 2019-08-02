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

import com.cafe24.shoppingmall.backend.vo.LoginVo;
import com.cafe24.shoppingmall.backend.vo.MemberVo;
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
	// A. 회원가입
	// B. 로그인
	// C. 회원정보 조회
	// D. 회원정보수정
	// E. 회원탈퇴
	// F. 비회원 추가 
	// G. 배송지 추가
	// H. 배송지 조회
	// I. 배송지 수정
	// J. 배송지 삭제

	
	
	/* A. 회원가입 */
	// A-1. id 중복 체크
	// A-1-1. id 중복아님
	@Ignore
	@Test
	public void idCheckPass() throws Exception {
		// id 조건 : 공백 안되고, 영문 소문자/숫자 4~16자 (프론트에서 체크)
		String id = "test1234";
		
		mockMvc.perform(get("/api/user/checkid/{id}", id))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.member_no", is(notNullValue())))
			.andExpect(jsonPath("$.data.address_no", is(notNullValue())));
		
	}
  
	// A-1-2. id 중복임
	@Ignore
	@Test
	public void idCheckFail() {
		
	}
	
	// A-2. 필수 항목 체크
	// A-2-1. 필수 항목 completed => insert 진행
	@Ignore
	@Test
	public void requiredFeildCompleted() throws Exception {
		//id 중복 없다는 전제 
		MemberVo memVo = new MemberVo("lucyhi1", "조부광", "Lucy7443!", 
				"01049047443", "lucy74310@gmail.com", 
				"1993-10-10", "12345 서울시 강남구");
		
		mockMvc.perform(
				post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo))
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(notNullValue())));
		
	}
	
	// A-2-2. 필수 항목 uncompleted => fail 메세지
	@Ignore
	@Test
	public void requiredFieldUncompleted() throws Exception {
		MemberVo memVo = new MemberVo();
		// name
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andExpect(status().isBadRequest())
			.andDo(print())
			.andExpect(jsonPath("$.data.id", is("id는 필수 입력 사항입니다.")))
			.andExpect(jsonPath("$.data.name", is("이름은 필수 입력 사항입니다.")))
			.andExpect(jsonPath("$.data.password", is("비밀번호는 필수 입력 사항입니다.")))
			.andExpect(jsonPath("$.data.telephone", is("휴대전화번호는 필수 입력 사항입니다.")))
			.andExpect(jsonPath("$.data.email", is("이메일은 필수 입력 사항입니다.")))
			.andExpect(jsonPath("$.data.birthday", is("생일은 필수 입력 사항입니다.")))
			;
	}
	
	// A-2-3. 아이디  양식 체크
	@Ignore
	@Test
	public void idFeildCheck() throws Exception {
		MemberVo memVo = new MemberVo();
		//id제외 필수항목 설정
		memVo.setName("조부광");
		memVo.setTelephone("010-4904-7443");
		memVo.setEmail("lucy@gmail.com");
		memVo.setBirthday("1993-10-10");
		memVo.setPassword("password7443!");
		
		//id 양식미충족 - 4자 미안
		memVo.setId("te");
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.id", is("영문소문자/숫자, 4~16자")));
		
		//id 양식미충족 - 16자 초과
		memVo.setId("abcdabcdabcdabcd1");
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.id", is("영문소문자/숫자, 4~16자")));
		
	}
	
	// A-2-4. 비밀번호  양식 체크
	@Ignore
	@Test
	public void passwordFeildCheck() throws Exception {
		MemberVo memVo = new MemberVo();
		//password제외 필수항목 설정
		memVo.setName("조부광");
		memVo.setTelephone("010-4904-7443");
		memVo.setEmail("lucy@gmail.com");
		memVo.setBirthday("1993-10-10");
		memVo.setId("test");
		
		//password 양식미충족 - 8자 미만
		memVo.setPassword("passw");
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 3가지 조합, 8자~16자, 공백불가")));
		
		//password 양식미충족 - 16자 초과
		memVo.setPassword("abcdabcdabcdabcd1");
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 3가지 조합, 8자~16자, 공백불가")));
		
		//password 양식미충족 - 영문 대소문자/숫자/특수문자 3가지 조합 (숫자x,특수문자x)
		memVo.setPassword("abcdabcdabc");
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 3가지 조합, 8자~16자, 공백불가")));
		
		//password 양식미충족 - 영문 대소문자/숫자/특수문자 3가지 조합 (특수문자x)
		memVo.setPassword("abcdabcc744");
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.data.password", is("영문 대소문자/숫자/특수문자 3가지 조합, 8자~16자, 공백불가")));
		
	}
	
	// A-2-5. 양식 모두 통과  => 실제 insert됨
	@Ignore
	@Test
	public void FeildCheckSuccess_memInsert() throws Exception {
		MemberVo memVo = new MemberVo();
		memVo.setName("조부광");
		memVo.setTelephone("010-4904-7443");
		memVo.setEmail("lucy@gmail.com");
		memVo.setBirthday("1993-10-10");
		
		memVo.setId("test_user1");
		memVo.setPassword("test_user1!");
		
		mockMvc.perform(post("/api/user/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(memVo)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	/* B. 로그인 */
	// B-1. 로그인 성공
	@Test
	public void loginTest() throws Exception{
		LoginVo login = new LoginVo();
		login.setId("test_user1");
		login.setPassword("test_user1!");
		
		mockMvc.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(login)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id", is(login.getId())));
	}
	
	// B-2. 로그인 실패
	// B-2-1. id,pwd 입력 체크
	@Ignore
	@Test
	public void loginFailTest1() throws Exception{
		LoginVo login = new LoginVo();
		login.setId("");
		login.setPassword("");
		
		mockMvc.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(login)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.data.password", is("비밀번호를 입력해주세요.")))
			.andExpect(jsonPath("$.data.id", is("id를 입력해주세요.")));
	}
	// B-2-2. 로그인 실패 - 해당 id, pwd와 일치하는 회원 없음
	@Test
	public void loginFailTest2() throws Exception {
		LoginVo login = new LoginVo();
		login.setId("test_user");
		login.setPassword("test_user1!");
		
		mockMvc.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(login)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", is("아이디 혹은 비밀번호가 맞지 않습니다.")));
	}
	
	// F. 비회원추가
	// F-1. 추가성공
	@Ignore
	@Test
	public void addNonmemberSuccessTest() throws Exception {
		mockMvc.perform(post("/api/user/nonmember/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(new NonMemberVo("test1234567"))))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.no", is(notNullValue())))	
				;
	}
	
	// F-2 . 추가실패 (sessionID 값이 없을 때)
	@Ignore
	@Test
	public void addNonmemberFailTest() throws Exception {
		mockMvc.perform(post("/api/user/nonmember/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(new NonMemberVo(""))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("session값이 없습니다.")));	
	}
	
	
	
}
