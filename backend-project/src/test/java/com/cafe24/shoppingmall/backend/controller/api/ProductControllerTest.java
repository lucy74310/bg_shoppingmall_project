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
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result",is("success")));
	}
	
	// 리스트가 없을 때
	@Ignore
	@Test
	public void getProductListNoDataTest() throws Exception {
		mockMvc.perform(get("/api/product/list"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result",is("fail")))
			.andExpect(jsonPath("$.message",is("등록된 상품이 없습니다")));
	}
	
	@Ignore
	@Test
	public void getDetailInfoTest() throws Exception {
		mockMvc.perform(get("/api/product/{deleteNo}", 1L))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.product.no" , is(1)));
			
	}
	

	@Rollback(false)
	@Test
	public void addSuccessTest() throws Exception {
		insertProcess();
	}
	
	@Ignore
	@Rollback(true)
	@Test
	public void addFailTest() throws Exception {
		
		ProductVo productVo = new ProductVo("", 25000L, "Y","Y","Y","Y","Y");
		
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
		
		mockMvc.perform(delete("/api/product/delete/{no}", 6L))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data" , notNullValue()));
	}

	//상품 삭제 실패
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
//		image_list.add(new ImageVo("/bgshop/goodsimages/A01.png", "Y", 1));
//		image_list.add(new ImageVo("/bgshop/goodsimages/A02.png", "N", 2));
//		image_list.add(new ImageVo("/bgshop/goodsimages/B01.png", "N", 3));
//		image_list.add(new ImageVo("/bgshop/goodsimages/B02.png", "N", 4));
		image_list.add(new ImageVo("/bgshop/goodsimages/A03.png", "Y", 1));
		image_list.add(new ImageVo("/bgshop/goodsimages/A04.png", "N", 2));
		image_list.add(new ImageVo("/bgshop/goodsimages/B03.png", "N", 3));
		image_list.add(new ImageVo("/bgshop/goodsimages/B04.png", "N", 4));
		
		List<OptionDetailVo> od_list = new ArrayList<OptionDetailVo>();
//		od_list.add(new OptionDetailVo("아이보리", 500L, 1, "Y"));
//		od_list.add(new OptionDetailVo("검정", 500L, 2, "Y"));
		od_list.add(new OptionDetailVo("소라", 1500L, 1, "Y"));
		od_list.add(new OptionDetailVo("퍼플", 2500L, 2, "Y"));
		
		List<OptionVo> option_list = new ArrayList<OptionVo>();
		option_list.add(new OptionVo("색상", od_list));
		
		od_list = new ArrayList<OptionDetailVo>();
		
//		od_list.add(new OptionDetailVo("90", 0L, 1, "Y"));
//		od_list.add(new OptionDetailVo("95", 0L, 1, "Y"));
		od_list.add(new OptionDetailVo("short", 0L, 1, "Y"));
		od_list.add(new OptionDetailVo("long", 1000L, 1, "Y"));
		
		
//		option_list.add(new OptionVo("사이즈", od_list));
		option_list.add(new OptionVo("기장", od_list));
		
		// 전제조건 : 실재로 db에 있는 카테고리 번호를 넣어야 한다.
		List<ProductCategoryVo> category_list = new ArrayList<ProductCategoryVo>();
		category_list.add(new ProductCategoryVo(4L));
		
		List<ProductOptionVo> po_list =  new ArrayList<ProductOptionVo>();
		
		
//		int size = option_list.size();
//		int i = 0;
//		int order = 1;
//		List<String> poname = new ArrayList<String>();
//		List<Long> plus_price = new ArrayList<Long>(); 
		//po_list = makeProductOpionList(option_list, size, i, po_list, poname, plus_price, order);
		
//		po_list.add(new ProductOptionVo("아이보리/90", "Y", "Y", 500L, 1));
//		po_list.add(new ProductOptionVo("아이보리/95", "Y", "Y", 500L, 2));
//		po_list.add(new ProductOptionVo("검정/90", "Y", "Y", 500L, 3));
//		po_list.add(new ProductOptionVo("검정/95", "Y", "Y", 500L, 4));
		
		po_list.add(new ProductOptionVo("여름무지셔츠|소라/short", "Y", "Y", 1500L, 1));
		po_list.add(new ProductOptionVo("여름무지셔츠|소라/long", "Y", "Y", 2500L, 2));
		po_list.add(new ProductOptionVo("여름무지셔츠|퍼플/short", "Y", "Y", 2500L, 3));
		po_list.add(new ProductOptionVo("여름무지셔츠|퍼플/long", "Y", "Y", 3500L, 4));
		
//		ProductVo productVo = new ProductVo(
//				"여름린넨바지", 25000L, "고급린넨여름바지!놓치지 마세요~","Y","Y","Y","Y", 5,
//				"Y",2000, option_list, category_list, image_list, po_list);
		
		ProductVo productVo = new ProductVo(
				"여름무지셔츠", 17000L, "여름 기본템 무지 컬러 셔츠! 배송비 무료","Y","Y","Y","Y", 15,
				"Y",0, option_list, category_list, image_list, po_list);
		
		
		
		
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
		
		Long insert_product_no = new Long(jsonResult.getData().toString());
		
		return insert_product_no;
	}
	
	private void updateSuccessProcess(Long insert_product_no) throws Exception {
		
		mockMvc.perform(post("/api/product/update", insert_product_no))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
	}
	
//	private List<ProductOptionVo> makeProductOpionList(List<OptionVo> option_list, int size, int index,
//			List<ProductOptionVo> po_list, List<String> poname, List<Long> plus_price, int order) {
//		List<OptionDetailVo> od_list = option_list.get(index).getOd_list();
//		int od_list_size = od_list.size();
//		int new_index = index + 1;
//		for(int i = 0; i< od_list_size; i++) {
//			List<String> parent_poname = new ArrayList<String>();
//			List<Long> parent_plus_price = new ArrayList<Long>();
//			for(String n : poname) {
//				parent_poname.add(n);
//			}
//			for(Long price : plus_price ) {
//				parent_plus_price.add(price);
//			}
//			parent_poname.add(od_list.get(i).getOpd_name());
//			parent_plus_price.add(od_list.get(i).getPlus_price());
//			if(index == (size-1)) {
//				Long sub_plus_price = 0L;
//				for(Long price : parent_plus_price ) {
//					sub_plus_price += price;
//				}
//				po_list.add(new ProductOptionVo(String.join("/", parent_poname), sub_plus_price , order++));
//			} else if (index < size-1){
//				makeProductOpionList(option_list, size, new_index, po_list, parent_poname, parent_plus_price, order);
//			}
//		}
//		return po_list;
//	}
	
}
