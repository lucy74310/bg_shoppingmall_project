package com.cafe24.shoppingmall.scenario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.hamcrest.Matchers.*;


import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


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

import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.cafe24.shoppingmall.vo.ProductVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class ProductAddScenarioTest {
	
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
	1. 관리자 인증 요구
	2. id,pwd 입력하여 인증 요청
	3. 관리자 table 확인
	4. 로그인 성공시 관리자 페이지 리다이렉트 
	5. 상품 등록 페이지 요청
	6. 상품 등록 페이지 
	7. 상품을 입력하고 제출
	8. 상품 저장 ( 상품, 상품옵션, 옵션,세부옵션)
	9. 상품 목록 페이지
	10. 상품 수정
	11. 상품 삭제 
	 * @throws Exception 
	 */
	
	
	
	//
	// 2. id,pwd 입력하여 인증 요청 -> 3. 관리자 table 확인 -> 4. 로그인 성공시 관리자 페이지 리다이렉트 
	//
	
	// 2-1. 로그인 실패 시나리오 
	// 2-1-1. 아이디가 틀렸을 때
	@Test
	public void loginFailTest1() throws Exception {
		Map<String, String> adminNotValid = new HashMap<String, String>();
		adminNotValid.put("id", "admn");
		adminNotValid.put("password", "1111");
		mockMvc.perform(post("/api/admin/login")
				.param("id", adminNotValid.get("id"))
				.param("password", adminNotValid.get("password")))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.result", is("fail")))
			   .andExpect(jsonPath("$.data", is(false)))
			   .andExpect(jsonPath("$.message", is("아이디가 틀렸습니다.")));
	}
	// 2-1-2. 비밀번호가 틀렸을 때
	@Test
	public void loginFailTest2() throws Exception {
		Map<String, String> adminNotValid = new HashMap<String, String>();
		adminNotValid.put("id", "admin");
		adminNotValid.put("password", "1111");
		
		mockMvc.perform(post("/api/admin/login")
				.param("id", adminNotValid.get("id"))
				.param("password", adminNotValid.get("password")))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.result", is("fail")))
			   .andExpect(jsonPath("$.data", is(false)))
			   .andExpect(jsonPath("$.message", is("비밀번호가 틀렸습니다.")));
	}
	
	// 2-2. 로그인 성공 시나리오 
	@Test
	public void loginSuccessTest() throws Exception {
		Map<String, String> adminValid = new HashMap<String, String>();
		adminValid.put("id", "admin");
		adminValid.put("password", "1234");
		
		mockMvc.perform(post("/api/admin/login")
				.param("id", adminValid.get("id")).param("password", adminValid.get("password")))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	//5. 상품 등록 페이지 요청
	//6. 상품 등록 페이지 
	
	//
	//7. 상품을 입력하고 제출
	//
	
	//7-1. 유효성 검사 실패 시나리오
	@Test
	public void produectAddRequestFailTest() throws Exception {
		ProductVo vo = new ProductVo();
		vo.setProduct_price(12500L);
		vo.setProduct_short_explain("여름 필수템! 시원한 재질의 무지 티셔츠!");
		vo.setProduct_detail(""
				+ "<div style=\"width:1000px;margin:0 auto;\">"
				+ "<div type=\"application/json\" class=\"-edibot-metadata\" "
				+ "data-version=\"2.0.0\" data-api=\"1.0.0\" "
				+ "data-created=\"2019-07-11 21:21:18\" "
				+ "data-projectid=\"N190711_2120\" data-groupid=\"g000001\" "
				+ "data-sessionid=\"1\" data-classid=\"undefined\" data-shopno=\"1\""
				+ "data-shoplang=\"ko_KR\">"
				+ "<!--{\"api\":\"1.0.0\",\"groupid\":\"g000001\",\"mallid\":\"neomart\",\"projectid\":\"N190711_2120\",\"sessionid\":1,\"version\":\"2.0.0\",\"shopno\":1,\"shoplang\":\"ko_KR\",\"created\":\"2019-07-11 21:21:18\"}--></div>\r\n" + 
				"<div>"
				+ "<div style=\"width:100%;height:100%;position:relative;font-family:verdana,sans-serif;"
				+ "font-size:14px;color:#000000;font-style:normal;font-weight:400;text-align:center;"
				+ "line-height:1.5;letter-spacing:0;word-spacing:0;background-color:rgba(255,255,255,0);\">"
				+ "<div><b><span style=\"font-size: 16px;\">hi</span></b></div>"
				+ "<div><span style=\"font-size: 16px;\"><b>this is t-shirts for summer!!</b></span></div>"
				+ "<div><br>"
				+ "</div></div></div>"
				+ "<br style=\"display: block;content: ' ';height:50px;\">\r\n" + 
				"</div>");
		vo.setDisplayed('Y');
		vo.setSelling('Y');
		vo.setUse_option('Y');
		vo.setUse_stock('Y');
		vo.setStock(30);
		vo.setSoldout_mark('Y');
		vo.setSave_percentage(5);
		vo.setShipping_price(2500);
		
		
		vo.setProduct_name("");
		mockMvc.perform(put("/api/manage/product/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)))
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.message", is("필수 입력 항목을 입력해 주세요.")));
		
	}
	
	// 7-2. 요효성 검사 통과 시나리오  -> 8. 상품 저장 & 상품 업데이트( 상품, 상품옵션, 옵션,세부옵션) 
	@Test
	public void produectAddRequestSuccessTest() throws Exception {
		ProductVo vo = new ProductVo();
		vo.setProduct_price(12500L);
		vo.setProduct_short_explain("여름 필수템! 시원한 재질의 무지 티셔츠!");
		vo.setProduct_detail(""
				+ "<div style=\"width:1000px;margin:0 auto;\">"
				+ "<div type=\"application/json\" class=\"-edibot-metadata\" "
				+ "data-version=\"2.0.0\" data-api=\"1.0.0\" "
				+ "data-created=\"2019-07-11 21:21:18\" "
				+ "data-projectid=\"N190711_2120\" data-groupid=\"g000001\" "
				+ "data-sessionid=\"1\" data-classid=\"undefined\" data-shopno=\"1\""
				+ "data-shoplang=\"ko_KR\">"
				+ "<!--{\"api\":\"1.0.0\",\"groupid\":\"g000001\",\"mallid\":\"neomart\",\"projectid\":\"N190711_2120\",\"sessionid\":1,\"version\":\"2.0.0\",\"shopno\":1,\"shoplang\":\"ko_KR\",\"created\":\"2019-07-11 21:21:18\"}--></div>\r\n" + 
				"<div>"
				+ "<div style=\"width:100%;height:100%;position:relative;font-family:verdana,sans-serif;"
				+ "font-size:14px;color:#000000;font-style:normal;font-weight:400;text-align:center;"
				+ "line-height:1.5;letter-spacing:0;word-spacing:0;background-color:rgba(255,255,255,0);\">"
				+ "<div><b><span style=\"font-size: 16px;\">hi</span></b></div>"
				+ "<div><span style=\"font-size: 16px;\"><b>this is t-shirts for summer!!</b></span></div>"
				+ "<div><br>"
				+ "</div></div></div>"
				+ "<br style=\"display: block;content: ' ';height:50px;\">\r\n" + 
				"</div>");
		vo.setDisplayed('Y');
		vo.setSelling('Y');
		vo.setUse_option('Y');
		vo.setUse_stock('Y');
		vo.setStock(30);
		vo.setSoldout_mark('Y');
		vo.setSave_percentage(5);
		vo.setShipping_price(2500);
		
		vo.setProduct_name("여름 티셔츠");
		mockMvc.perform(put("/api/manage/product/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.no", notNullValue()));
	}
	
	@Test
	public void productDeleteTest() throws Exception{
		Long deleteNo = 1L;
		
		ResultActions ra = mockMvc.perform(delete("/api/manage/product/delete/{no}", deleteNo).param("test", "true"));
		
		ra.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(1)));
		
		
	}
	
	
}
