package com.cafe24.shoppingmall.frontend.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.vo.ImageVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();

	private ObjectMapper om = new ObjectMapper();
	private String HOST = "http://localhost:8080/backend-project";

	public List<ProductVo> getListAll() {

		String url = "/api/product/list";
		
		JSONResult jsonResult = null;
		
		try {
			ResponseEntity<JSONResult> response = restTemplate.exchange(HOST + url, HttpMethod.GET, null,
					JSONResult.class);
			jsonResult = response.getBody();
		} catch (HttpClientErrorException e) {
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
		
		if ("success".equals(jsonResult.getResult())) {
			List<?> list2 = om.convertValue(jsonResult.getData(), ArrayList.class);
			List<ProductVo> product_list = new ArrayList<ProductVo>();
			for (Object o : list2) {
				ProductVo product = om.convertValue(o, ProductVo.class);
				product_list.add(product);
			}
			return product_list;
		}
		return null;
	}

	public List<ProductVo> getByCategory(Long category_no) {
		
		String url = "/backend-project/api/product/list/{category_no}";
		JSONResult jsonResult = null;
		
		
		try {
			ResponseEntity<JSONResult> response = restTemplate.exchange(HOST + url, HttpMethod.GET, null,
					JSONResult.class, category_no);
			jsonResult = response.getBody();
		} catch (HttpClientErrorException e) {
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
		
		if ("success".equals(jsonResult.getResult())) { 
			List<?> list2 = om.convertValue(jsonResult.getData(), ArrayList.class);
		    List<ProductVo> product_list = new ArrayList<ProductVo>(); 
		    for (Object o :	list2) { 
		    	ProductVo product = om.convertValue(o, ProductVo.class);
		    	product_list.add(product); 
		    } 
		    
		    return product_list; 
		}
		 
		return null;
	}

}
