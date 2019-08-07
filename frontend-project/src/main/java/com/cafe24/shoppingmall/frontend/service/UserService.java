package com.cafe24.shoppingmall.frontend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.vo.AdminVo;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	private String HOST = "http://localhost:8080/backend-project";
	private ObjectMapper om = new ObjectMapper();
	
	
	public MemberVo get(String id) {
		String uri = "/api/admin/login";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AdminVo adminVo = new AdminVo("test");

		JSONResult result= null;
		MemberVo memberVo = null;
		
		
		try {
			result = restTemplate.postForObject(HOST + uri, adminVo, JSONResult.class);
		} catch(HttpClientErrorException e) {
			String result2 = e.getResponseBodyAsString();
			System.out.println("catch");
			System.out.println(result2);
		}
		adminVo = om.convertValue(result.getData(), AdminVo.class);
		System.out.println(memberVo);
		
		return memberVo;
	}

	public AdminVo adminGet(String id) {
		String uri = "/api/admin/login";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AdminVo adminVo = new AdminVo("test");

		JSONResult result= null;
		
		
		try {
			result = restTemplate.postForObject(HOST + uri, adminVo, JSONResult.class);
		} catch(HttpClientErrorException e) {
			String result2 = e.getResponseBodyAsString();
			System.out.println("catch");
			System.out.println(result2);
		}
		System.out.println(result);
		adminVo = om.convertValue(result.getData(), AdminVo.class);
		System.out.println(adminVo);
		return adminVo;
	}
	
	
}
