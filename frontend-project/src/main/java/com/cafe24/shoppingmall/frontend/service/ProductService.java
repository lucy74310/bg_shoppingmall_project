package com.cafe24.shoppingmall.frontend.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.vo.ImageVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {
	
	private ObjectMapper om = new ObjectMapper();
	private JSONResult jsonResult = null;
	String host = "http://localhost:8080";
	
	public List<ProductVo> getListAll() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "/backend-project/api/product/list";
		try {
			jsonResult = restTemplate.getForObject(new URI(host + url), JSONResult.class );
			
			System.out.println("all");
			if("success".equals(jsonResult.getResult())) {
				List<?> list2 = om.convertValue(jsonResult.getData(), ArrayList.class);
				List<ProductVo> product_list = new ArrayList<ProductVo>();
				for(Object o : list2) {
					ProductVo product = om.convertValue(o, ProductVo.class);
					product_list.add(product);
				}
				System.out.println(product_list);
				return product_list;
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	public List<ProductVo> getByCategory(Long category_no) {
		String url = "/backend-project/api/product/list/" + category_no;
		RestTemplate restTemplate = new RestTemplate();
		try {
			jsonResult = restTemplate.getForObject(new URI(host + url), JSONResult.class );
			System.out.println("category");
			if("success".equals(jsonResult.getResult())) {
				List<?> list2 = om.convertValue(jsonResult.getData(), ArrayList.class);
				
				List<ProductVo> product_list = new ArrayList<ProductVo>();
				for(Object o : list2) {
					ProductVo product = om.convertValue(o, ProductVo.class);
					product_list.add(product);
				}
				System.out.println(product_list);
				return product_list;
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
