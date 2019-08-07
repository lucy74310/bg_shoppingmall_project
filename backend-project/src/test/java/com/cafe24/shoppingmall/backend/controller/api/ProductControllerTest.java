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

import com.cafe24.shoppingmall.backend.config.AppConfig;
import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.vo.ImageVo;
import com.cafe24.shoppingmall.backend.vo.OptionDetailVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductCategoryVo;
import com.cafe24.shoppingmall.backend.vo.ProductOptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;
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
	@Test
	public void getProductListTest() throws Exception {
		mockMvc.perform(get("/api/product/list"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result",is("success")));
	}
	
	// 리스트가 없을 때
	@Rollback(true)
	@Test
	public void getProductListNoDataTest() throws Exception {
		mockMvc.perform(delete("/api/product/delete/{no}", 2L))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data" , notNullValue()));
		mockMvc.perform(delete("/api/product/delete/{no}", 10L))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data" , notNullValue()));
		
		mockMvc.perform(get("/api/product/list"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result",is("fail")))
			.andExpect(jsonPath("$.message",is("등록된 상품이 없습니다")));
	}
	
	@Test
	public void getDetailInfoTest() throws Exception {
		
		//있는 상품
		mockMvc.perform(get("/api/product/detail/{no}", 2L))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.no" , is(2)));
		
		// 없는 상품 
		mockMvc.perform(get("/api/product/detail/{no}", 3L))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Ignore
	@Rollback(false)
	@Test
	public void addSuccessTest() throws Exception {
		List<ImageVo> image_list = new ArrayList<ImageVo>();

		image_list.add(new ImageVo("/bgshop/goodsimages/A03.png", "Y", 1));
		image_list.add(new ImageVo("/bgshop/goodsimages/A04.png", "N", 2));
		image_list.add(new ImageVo("/bgshop/goodsimages/B03.png", "N", 3));
		image_list.add(new ImageVo("/bgshop/goodsimages/B04.png", "N", 4));
		
		List<OptionDetailVo> od_list = new ArrayList<OptionDetailVo>();

		od_list.add(new OptionDetailVo("소라", 1500L, 1, "Y"));
		od_list.add(new OptionDetailVo("퍼플", 2500L, 2, "Y"));
		
		List<OptionVo> option_list = new ArrayList<OptionVo>();
		option_list.add(new OptionVo("색상", od_list));
		
		od_list = new ArrayList<OptionDetailVo>();

		od_list.add(new OptionDetailVo("short", 0L, 1, "Y"));
		od_list.add(new OptionDetailVo("long", 1000L, 1, "Y"));
		
		
		option_list.add(new OptionVo("기장", od_list));
		
		// 전제조건 : 실재로 db에 있는 카테고리 번호를 넣어야 한다.
		List<ProductCategoryVo> category_list = new ArrayList<ProductCategoryVo>();
		category_list.add(new ProductCategoryVo(31L));
		
		List<ProductOptionVo> po_list =  new ArrayList<ProductOptionVo>();
		
		
//		int size = option_list.size();
//		int i = 0;
//		int order = 1;
//		List<String> poname = new ArrayList<String>();
//		List<Long> plus_price = new ArrayList<Long>(); 
		//po_list = makeProductOpionList(option_list, size, i, po_list, poname, plus_price, order);
		
		po_list.add(new ProductOptionVo("여름무지셔츠2|소라/short", "Y", "Y", 1500L, 1));
		po_list.add(new ProductOptionVo("여름무지셔츠2|소라/long", "Y", "Y", 2500L, 2));
		po_list.add(new ProductOptionVo("여름무지셔츠2|퍼플/short", "Y", "Y", 2500L, 3));
		po_list.add(new ProductOptionVo("여름무지셔츠2|퍼플/long", "Y", "Y", 3500L, 4));

		ProductVo productVo = new ProductVo(
				"여름무지셔츠2", 17000L, "여름 기본템 무지 컬러 셔츠! 배송비 무료","Y","Y","Y","Y", 15,
				"Y",0, option_list, category_list, image_list, po_list);
		
		
		
		
		MvcResult result = 
		mockMvc.perform(post("/api/product/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productVo)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", notNullValue()))
				.andReturn();
		
		String contentAsString = result.getResponse().getContentAsString();
		JSONResult jsonResult = new ObjectMapper().readValue(contentAsString, JSONResult.class);
		
		Long insert_product_no = new Long(jsonResult.getData().toString());
		
	}
	
	@Ignore
	@Rollback(true)
	@Test
	public void addFailTest() throws Exception {
		
		ProductVo productVo = new ProductVo("", "Y","Y","Y","Y","Y");
		
		mockMvc.perform(post("/api/product/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productVo)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("상품명을 입력해 주세요.(1~20자)") ));
	}
	
	@Ignore
	@Rollback(false)
	@Test
	public void updateTest() throws Exception {
		
		ProductVo productVo = getProductVo();
		
		
		
		MvcResult result = 
		mockMvc.perform(post("/api/product/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productVo)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", notNullValue()))
				.andReturn();
		
		String contentAsString = result.getResponse().getContentAsString();
		JSONResult jsonResult = new ObjectMapper().readValue(contentAsString, JSONResult.class);
		
		Long insert_product_no = new Long(jsonResult.getData().toString());
		
		
		if (insert_product_no != null) {
			productVo.setNo(insert_product_no);
			productVo.setProduct_name("여름무지셔츠_수정");
			
			List<ProductOptionVo>po_list =  new ArrayList<ProductOptionVo>();
			
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|소라/short", "Y", "Y", 1500L, 1));
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|소라/long", "Y", "Y", 2500L, 2));
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|퍼플/short", "Y", "Y", 2500L, 3));
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|퍼플/long", "Y", "Y", 3500L, 4));
	
			productVo.setPo_list(po_list);
			
			mockMvc.perform(put("/api/product/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(productVo)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		}
	}
	
	@Ignore
	@Rollback(true)
	@Test
	public void updateFailTest() throws Exception {
			ProductVo productVo = getProductVo();
		
			productVo.setProduct_name("여름무지셔츠_수정");
			
			List<ProductOptionVo>po_list =  new ArrayList<ProductOptionVo>();
			
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|소라/short", "Y", "Y", 1500L, 1));
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|소라/long", "Y", "Y", 2500L, 2));
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|퍼플/short", "Y", "Y", 2500L, 3));
			po_list.add(new ProductOptionVo("여름무지셔츠_수정|퍼플/long", "Y", "Y", 3500L, 4));
	
			productVo.setPo_list(po_list);
			
			System.out.println(productVo);
			
			mockMvc.perform(put("/api/product/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(productVo)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.message", is("상품이 수정되지 않았습니다.")));
	}
	
	//상품 삭제 성공
	@Ignore
	@Rollback(false)
	@Test
	public void deleteSuccessTest() throws Exception {
		
		mockMvc.perform(delete("/api/product/delete/{no}", 2L))
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
		
		mockMvc.perform(delete("/api/product/delete/{no}", 3L))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data" , is(not(1))))
		.andExpect(jsonPath("$.message" , is("상품이 삭제되지 않았습니다.")));
	}
	
	
	private ProductVo getProductVo() {
		List<ImageVo> image_list = new ArrayList<ImageVo>();

		image_list.add(new ImageVo("/bgshop/goodsimages/A03.png", "Y", 1));
		image_list.add(new ImageVo("/bgshop/goodsimages/A04.png", "N", 2));
		image_list.add(new ImageVo("/bgshop/goodsimages/B03.png", "N", 3));
		image_list.add(new ImageVo("/bgshop/goodsimages/B04.png", "N", 4));
		
		List<OptionDetailVo> od_list = new ArrayList<OptionDetailVo>();

		od_list.add(new OptionDetailVo("소라", 1500L, 1, "Y"));
		od_list.add(new OptionDetailVo("퍼플", 2500L, 2, "Y"));
		
		List<OptionVo> option_list = new ArrayList<OptionVo>();
		option_list.add(new OptionVo("색상", od_list));
		
		od_list = new ArrayList<OptionDetailVo>();

		od_list.add(new OptionDetailVo("short", 0L, 1, "Y"));
		od_list.add(new OptionDetailVo("long", 1000L, 1, "Y"));
		
		
		option_list.add(new OptionVo("기장", od_list));
		
		// 전제조건 : 실재로 db에 있는 카테고리 번호를 넣어야 한다.
		List<ProductCategoryVo> category_list = new ArrayList<ProductCategoryVo>();
		category_list.add(new ProductCategoryVo(31L));
		
		List<ProductOptionVo> po_list =  new ArrayList<ProductOptionVo>();
		
		po_list.add(new ProductOptionVo("여름무지셔츠|소라/short", "Y", "Y", 1500L, 1));
		po_list.add(new ProductOptionVo("여름무지셔츠|소라/long", "Y", "Y", 2500L, 2));
		po_list.add(new ProductOptionVo("여름무지셔츠|퍼플/short", "Y", "Y", 2500L, 3));
		po_list.add(new ProductOptionVo("여름무지셔츠|퍼플/long", "Y", "Y", 3500L, 4));

		ProductVo productVo = new ProductVo(
				"여름무지셔츠", 17000L, "여름 기본템 무지 컬러 셔츠! 배송비 무료","Y","Y","Y","Y", 15,
				"Y",0, option_list, category_list, image_list, po_list);
		
		return productVo;
		
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
	
	
	@Test
	public void stockInfoTest() throws Exception {
		
		// 존재하는 상품
		mockMvc.perform(get("/api/product/stock/{pno}", 2L))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.no", is(2)))
		;
		
		
		// 존재하지 않는 상품
		mockMvc.perform(get("/api/product/stock/{pno}", 3L))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.message", is("상품정보가 존재하지 않습니다.")))
			;
		
	}
	
	@Test
	public void optionStockInfoTest() throws Exception {
		
		// 존재하는 상품
		mockMvc.perform(get("/api/product/stock/po/{pono}", 5L))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.no", is(5)))
		;
		
		
		// 존재하지 않는 상품
		mockMvc.perform(get("/api/product/stock/po/{pono}", 20L))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")))
			.andExpect(jsonPath("$.message", is("상품정보가 존재하지 않습니다.")))
			;
		
	}
}
