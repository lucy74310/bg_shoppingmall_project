package com.cafe24.shoppingmall.frontend.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.vo.CategoryVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CategoryService {
	
	private ObjectMapper om = new ObjectMapper();
	private JSONResult jsonResult = null;
	
	public List<CategoryVo> getListAll() {
		List<CategoryVo> cate_list = new ArrayList<CategoryVo>();
		RestTemplate restTemplate = new RestTemplate();
		String host = "http://localhost:8080";
		String url = "/backend-project/api/category/list";
		try {
			jsonResult = restTemplate.getForObject(host + url, JSONResult.class );
			
			
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
			List<?> list2 = om.convertValue(jsonResult.getData(), ArrayList.class);
			
			for(Object o : list2) {
				CategoryVo category = om.convertValue(o, CategoryVo.class);
				cate_list.add(category);
			}
			//c의 상위 no가 있으면 그 상위 categoryVo의 sub_categories에 넣음
			for(CategoryVo c : cate_list) {
				if(c.getUpper_no() != null) {
					for(CategoryVo upper : cate_list) {
						if(upper.getNo() == c.getUpper_no()) {
							List<CategoryVo> sub_list;
							if(upper.getSub_categories() == null) {
								sub_list = new ArrayList<CategoryVo>();
							} else {
								sub_list = upper.getSub_categories();
							}
							sub_list.add(c);
							upper.setSub_categories(sub_list);
						}
					}
					
				}
			}
			
			List<CategoryVo> final_cate_list = new ArrayList<CategoryVo>();
			for(CategoryVo c : cate_list) {
				if(c.getUpper_no()== null) {
					final_cate_list.add(c);
				}
			}
			
			return final_cate_list;
		}
		
		return null;
	}

}
