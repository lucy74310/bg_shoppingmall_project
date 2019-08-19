package com.cafe24.shoppingmall.frontend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.frontend.dto.JSONResult2;
import com.cafe24.shoppingmall.frontend.vo.OrderVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private ObjectMapper om = new ObjectMapper();
	
	public OrderVo addOrder(OrderVo orderVo) {
		
		JSONResult2Order jsonResult = null;
		
		String uri = "/api/order/add";
		
		try {
			jsonResult = restTemplate.postForObject(ProductService.restUrl+uri,orderVo, JSONResult2Order.class);
		} catch(HttpClientErrorException e) {
			String responseBody = e.getResponseBodyAsString();
			try {
				jsonResult = om.readValue(responseBody, JSONResult2Order.class);
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		return jsonResult.getData();
	}
	
	private static class JSONResult2Order extends JSONResult2<OrderVo> {
		
	}
}
