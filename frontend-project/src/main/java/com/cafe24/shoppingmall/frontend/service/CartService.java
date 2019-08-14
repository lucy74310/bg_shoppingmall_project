package com.cafe24.shoppingmall.frontend.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;
import com.cafe24.shoppingmall.frontend.dto.JSONResult2;
import com.cafe24.shoppingmall.frontend.vo.CartVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CartService {
	@Autowired
	private RestTemplate restTemplate;

	private ObjectMapper om = new ObjectMapper();

	public List<CartVo> getCartList(Long member_no) {

		String uri = "/api/cart/list/{is_member}/{no}";

		Map<String, Object> variable = new HashMap<>();

		variable.put("is_member", true);
		variable.put("no", member_no.toString());

		JSONResult2CartList jsonResult = null;
		
		try {
			jsonResult = restTemplate.getForObject(ProductService.restUrl + uri, JSONResult2CartList.class, variable);
		} catch (HttpClientErrorException e) {
			return null;
		}
		System.out.println(jsonResult);
		if ("success".equals(jsonResult.getResult())) {
			System.out.println(jsonResult.getData());
			return jsonResult.getData();
		}

		return null;
	}

	public JSONResult add(Long pono, Long memno, Integer count) {
		String uri = "/api/cart/hascheck/{is_member}/{memno}/{po_no}";

		JSONResult jsonResult = null;
		JSONResult2CartList jsonResultCartList = null;

		// 1 . 담겨져 있는 상품인지 확인

		Map<String, Object> variable = new HashMap<>();
		variable.put("is_member", true);
		variable.put("memno", memno);
		variable.put("po_no", pono);

		jsonResult = restTemplate.getForObject(ProductService.restUrl + uri, JSONResult.class, variable);

		CartVo cart = new CartVo();
		// 2. -1 이면 없는 상품이므로 cart add
		if (Integer.parseInt(jsonResult.getData().toString()) == -1) {
			uri = "/api/cart/add";
			cart.setMember_no(memno);
			cart.setProduct_option_no(pono);
			cart.setCount(count);
			jsonResultCartList = restTemplate.postForObject(ProductService.restUrl + uri, cart,
					JSONResult2CartList.class);
		} else {
			// 2. -1 이상이면 있는 상품이므로 장바구니 숫자 update
			uri = "/api/cart/update";

		}

		return null;
	}

	private static class JSONResult2CartList extends JSONResult2<List<CartVo>> {

	}

	public JSONResult add(CartVo cartVo) {
		String uri = "/api/cart/hascheck/{is_member}/{memno}/{po_no}";

		JSONResult jsonResult = null;

		// 1 . 담겨져 있는 상품인지 확인

		Map<String, Object> variable = new HashMap<>();
		variable.put("is_member", true);
		variable.put("memno", cartVo.getMember_no());
		variable.put("po_no", cartVo.getProduct_option_no());
		try {
			jsonResult = restTemplate.getForObject(ProductService.restUrl + uri, JSONResult.class, variable);
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

		System.out.println(jsonResult);

		// 2. -1 이면 없는 상품이므로 cart add
		if (Integer.parseInt(jsonResult.getData().toString()) == -1) {
			uri = "/api/cart/add";
			try {
				jsonResult = restTemplate.postForObject(ProductService.restUrl + uri, cartVo, JSONResult.class);
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

		} else {
			// 2. -1 이상이면 있는 상품이므로 장바구니 숫자 update
			uri = "/api/cart/update";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CartVo> requestEntity = new HttpEntity<>(cartVo, headers);
			try {
				ResponseEntity<JSONResult> response = restTemplate.exchange(ProductService.restUrl + uri,
						HttpMethod.PUT, requestEntity, JSONResult.class);
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
		}

		return jsonResult;
	}

	public JSONResult delete(Long pono, Long mem_no) {
		String uri = "/api/cart/delete";
		JSONResult jsonResult = null;
		CartVo cartVo = new CartVo();
		cartVo.setProduct_option_no(pono);
		cartVo.setMember_no(mem_no);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CartVo> requestEntity = new HttpEntity<>(cartVo, headers);
		try {
			ResponseEntity<JSONResult> response = restTemplate.exchange(ProductService.restUrl + uri,
					HttpMethod.DELETE, requestEntity, JSONResult.class);
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
		
		
		return jsonResult;
	}

}
