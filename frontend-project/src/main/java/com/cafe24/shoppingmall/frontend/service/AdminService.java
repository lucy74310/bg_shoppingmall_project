package com.cafe24.shoppingmall.frontend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.vo.ImageVo;
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

	public JSONResult registerProduct(ProductVo productVo, List<String> image_url) {
		
		List<ImageVo> images = new ArrayList<ImageVo>();
		int i = 1;
		ImageVo vo;
		if(image_url.size() > 0) {
			for(String s : image_url) {
				if (i == 1) {
					vo = new ImageVo(s, "Y", i++);	
				} else {
					vo = new ImageVo(s, "N", i++);
				}
				images.add(vo);
			}
			
			productVo.setImage_list(images);
		}
		JSONResult jsonResult = null;
		String uri = "/api/product/add";
		System.out.println(productVo);
		try {
			jsonResult = restTemplate.postForObject(HOST+ uri, productVo, JSONResult.class );
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
			System.out.println("400");
			System.out.println(jsonResult);
			return jsonResult;
		}
		System.out.println("200");
		System.out.println(jsonResult);
		if("success".equals(jsonResult.getResult())){
			return jsonResult;
		}
		
		return null;
	}

}
