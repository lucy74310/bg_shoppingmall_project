package com.cafe24.shoppingmall.frontend.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.dto.JSONResult2;
import com.cafe24.shoppingmall.frontend.vo.ImageVo;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;
import com.cafe24.shoppingmall.frontend.vo.ProductVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {
	
	@Value("${myrest.url}")
	public static String restUrl = "http://localhost:8080/backend-project";
	
	
	@Autowired
	private RestTemplate restTemplate;

	private ObjectMapper om = new ObjectMapper();
	
	
	private static final String SAVE_PATH = "/shoppingmall-uploads/";

	public List<ProductVo> getListAll() {

		String url = "/api/product/list";

		JSONResult jsonResult = null;

		try {
			ResponseEntity<JSONResult> response = restTemplate.exchange(restUrl + url, HttpMethod.GET, null,
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
		System.out.println(category_no);
		String url = "/api/product/list/{category_no}";
		JSONResult jsonResult = null;

		try {
			ResponseEntity<JSONResult> response = restTemplate.exchange(restUrl + url, HttpMethod.GET, null,
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
		if ("success".equals(jsonResult.getResult().toString())) {
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

	public List<String> uploadImages(List<MultipartFile> images) {
		List<String> urls = new ArrayList<String>();
		try {
			for(MultipartFile f : images) {
				
				String originalFileName = f.getOriginalFilename();
				
				String ext = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
				
				String saveFileName = generateSaveFileName(ext);
				
				byte[] file = f.getBytes();
				
				OutputStream os = new FileOutputStream(SAVE_PATH + saveFileName);
				
				os.write(file);
				os.close();
				
				String url = "/uploads/" + saveFileName;
				urls.add(url);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urls;
		
	}

	// 파일 업로드 시 이름 생성
	public String generateSaveFileName(String ext) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);

		fileName += "." + ext;

		return fileName;
	}

	public ProductVo getDetail(Long no) {
		String url = "/api/product/detail/{no}";

		JSONResult2Product jsonResult = null;
		
		try {
			jsonResult = restTemplate.getForObject(restUrl + url, JSONResult2Product.class, no);
		} catch(HttpClientErrorException e) {
			String responseBody = e.getResponseBodyAsString();
			try {
				jsonResult = om.readValue(responseBody, JSONResult2Product.class);
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if("success".equals(jsonResult.getResult())) {
			ProductVo p = jsonResult.getData();
			return p;
		}
		return null;
	}
	
	private static class JSONResult2Product extends JSONResult2<ProductVo> {
	}

}
