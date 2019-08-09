package com.cafe24.shoppingmall.frontend.service;

import java.io.IOException;

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
import com.cafe24.shoppingmall.frontend.vo.JoinVo;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	private ObjectMapper om = new ObjectMapper();

	public MemberVo get(String id) {
		String uri = "/api/admin/login";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AdminVo adminVo = new AdminVo("test");

		JSONResult result = null;
		MemberVo memberVo = null;

		try {
			result = restTemplate.postForObject(ProductService.restUrl + uri, adminVo, JSONResult.class);
		} catch (HttpClientErrorException e) {
			String result2 = e.getResponseBodyAsString();
		}
		adminVo = om.convertValue(result.getData(), AdminVo.class);
		System.out.println(memberVo);

		return memberVo;
	}

	public AdminVo adminGet(String id) {
		String uri = "/api/admin/login";

		AdminVo adminVo = new AdminVo("test");

		JSONResult result = null;

		try {
			result = restTemplate.postForObject(ProductService.restUrl + uri, adminVo, JSONResult.class);
		} catch (HttpClientErrorException e) {
			String result2 = e.getResponseBodyAsString();
		}
		adminVo = om.convertValue(result.getData(), AdminVo.class);
		return adminVo;
	}

	public Boolean joinMember(JoinVo joinVo) {
		String uri = "/api/user/join";

		JSONResult result = null;

		try {
			result = restTemplate.postForObject(ProductService.restUrl + uri, joinVo, JSONResult.class);
		} catch (HttpClientErrorException e) {
			String result2 = e.getResponseBodyAsString();
			try {
				result = om.readValue(result2, JSONResult.class);
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if("success".equals(result.getResult())) {
			return true;
		} else {
			return false;
		}

	}

	public JSONResult idCheck(String id) {
		String uri = "/api/user/checkid/{id}";
		JSONResult result = null;
		try {
			result = restTemplate.getForObject(ProductService.restUrl + uri, JSONResult.class, id);
		} catch (HttpClientErrorException e) {
			String result2 = e.getResponseBodyAsString();
			try {
				result = om.readValue(result2, JSONResult.class);
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

}
