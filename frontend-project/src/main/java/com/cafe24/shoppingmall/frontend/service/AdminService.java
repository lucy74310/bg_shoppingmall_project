package com.cafe24.shoppingmall.frontend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private ObjectMapper om = new ObjectMapper();
	private String HOST = "http://localhost:8080/backend-project";
	
	@SuppressWarnings("unchecked")
	public List<MemberVo> getMemberListAll() {
		
		JSONResult jsonResult = null;
		
		String uri = "/api/user/list";
		
		try {
			jsonResult = restTemplate.getForObject(HOST+uri, JSONResult.class);
		} catch(HttpClientErrorException e) {
			String responseBody = e.getResponseBodyAsString();
			try {
				jsonResult = om.readValue(responseBody, JSONResult.class);
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if("success".equals(jsonResult.getResult())) {
			
			List<MemberVo> members = new ArrayList<MemberVo>();
			for(Object o : (ArrayList<MemberVo>) jsonResult.getData()) {
				MemberVo m = om.convertValue(o, MemberVo.class);
				members.add(m);
			}
			
			return members;
		} else {
			return null;
		}
		
		
		
		
	}

}
