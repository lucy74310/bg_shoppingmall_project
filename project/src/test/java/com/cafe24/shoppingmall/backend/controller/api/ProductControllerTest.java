package com.cafe24.shoppingmall.backend.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.vo.ImageVo;
import com.cafe24.shoppingmall.backend.vo.OptionDetailVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductCategoryVo;
import com.cafe24.shoppingmall.backend.vo.ProductOptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;
import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Transactional
public class ProductControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	// 리스트가 있을 때
	@Ignore
	@Test
	public void getProductListTest() throws Exception {
		mockMvc.perform(get("/api/product/list"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result",is("success")));
	}
	
	// 리스트가 없을 때
	@Ignore
	@Test
	public void getProductListNoDataTest() throws Exception {
		mockMvc.perform(get("/api/product/list"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result",is("fail")))
			.andExpect(jsonPath("$.message",is("등록된 상품이 없습니다")));
	}
	
	@Ignore
	@Test
	public void getDetailInfoTest() throws Exception {
		mockMvc.perform(get("/api/product/{deleteNo}", 1L))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.product.no" , is(1)));
			
	}
	
	@Rollback(true)
	@Test
	public void addSuccessTest() throws Exception {
		insertProcess();
	}
	
	@Ignore
	@Rollback(true)
	@Test
	public void addFailTest() throws Exception {
		
		ProductVo productVo = new ProductVo("", 25000L, 'Y','Y','Y','Y','Y');
		
		mockMvc.perform(put("/api/product/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productVo)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("상품명을 입력해 주세요.(1~20자)") ));
	}
	
	@Ignore
	@Rollback(true)
	@Test
	public void updateTest() throws Exception {
		Long insert_product_no = insertProcess();
		
		updateSuccessProcess(insert_product_no);
		
	}
	
	
	//상품 삭제 성공
	@Ignore
	@Rollback(false)
	@Test
	public void deleteSuccessTest() throws Exception {
		
		mockMvc.perform(delete("/api/product/delete/{no}", 7L))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data" , notNullValue()));
	}

	//상품 삭제 성공
	@Ignore
	@Rollback(false)
	@Test
	public void deleteFailTest() throws Exception {
		
		mockMvc.perform(delete("/api/product/delete/{no}", 7L))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data" , is("null")))
		.andExpect(jsonPath("$.message" , is("상품이 삭제되지 않았습니다.")));
	}
	
	private Long insertProcess() throws Exception {
		List<ImageVo> image_list = new ArrayList<ImageVo>();
		image_list.add(new ImageVo("/bgshop/goodsimages/A01.png", 'Y'));
		image_list.add(new ImageVo("/bgshop/goodsimages/A02.png", 'N'));
		image_list.add(new ImageVo("/bgshop/goodsimages/B01.png", 'N'));
		image_list.add(new ImageVo("/bgshop/goodsimages/B02.png", 'N'));
		
		
		List<OptionDetailVo> od_list = new ArrayList<OptionDetailVo>();
		od_list.add(new OptionDetailVo("아이보리", 0L));
		od_list.add(new OptionDetailVo("검정", 0L));
		
		
		List<OptionVo> option_list = new ArrayList<OptionVo>();
		option_list.add(new OptionVo("색상", od_list));
		
		od_list = new ArrayList<OptionDetailVo>();
		
		od_list.add(new OptionDetailVo("90", 0L));
		od_list.add(new OptionDetailVo("95", 0L));
		
		option_list.add(new OptionVo("사이즈", od_list));
		
		// 전제조건 : 실재로 db에 있는 카테고리 번호를 넣어야 한다.
		List<ProductCategoryVo> category_list = new ArrayList<ProductCategoryVo>();
		category_list.add(new ProductCategoryVo(3L));
		
		List<ProductOptionVo> po_list =  new ArrayList<ProductOptionVo>();
		
		
		po_list.add(new ProductOptionVo("아이보리/90", 'Y', 'Y', 500L, 1));
		po_list.add(new ProductOptionVo("아이보리/95", 'Y', 'Y', 500L, 2));
		po_list.add(new ProductOptionVo("검정/90", 'Y', 'Y', 500L, 3));
		po_list.add(new ProductOptionVo("검정/95", 'Y', 'Y', 500L, 4));
		
		ProductVo productVo = new ProductVo(
				"여름린넨바지", 25000L, "고급린넨여름바지!놓치지 마세요~",'Y','Y','Y','Y', 5,
				'Y',2000, option_list, category_list, image_list, po_list);
		MvcResult result = 
		mockMvc.perform(put("/api/product/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productVo)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", notNullValue()))
				.andReturn();
		
		String contentAsString = result.getResponse().getContentAsString();
		JSONResult jsonResult = new ObjectMapper().readValue(contentAsString, JSONResult.class);
		
		Long insert_product_no = (Long) jsonResult.getData();
		
		return insert_product_no;
	}
	
	private void updateSuccessProcess(Long insert_product_no) throws Exception {
		
		mockMvc.perform(post("/api/product/update", insert_product_no))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
	}
	
	
	
}
