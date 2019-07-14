package com.cafe24.shoppingmall.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.ProductManageService;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.ProductVo;

@RestController("productManagementAPIController")
@RequestMapping("/api/manage/product")
public class ProductManageController {
	

	@Autowired
	private ProductManageService productManageService;
	
	@RequestMapping(value="/add", method=RequestMethod.PUT)
	public JSONResult productAdd(
			@RequestBody @Valid ProductVo productVo,
			BindingResult result,
			HttpServletResponse response) throws IOException {
		
		if(result.hasErrors()) {
			//에러! 
			response.setStatus(400);
			return JSONResult.fail("필수 입력 항목을 입력해 주세요.", result.getAllErrors());
		} else {
			//에러없음~
			// insert 하고 no 값을 받아온다 ~
			
			//insert 성공시 
			productVo.setNo(1L);
			return JSONResult.success(productVo);
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JSONResult productDelete(
			@RequestBody @Valid ProductVo productVo,
			BindingResult result,
			HttpServletResponse response
	) {
		
		if(result.hasErrors()) {
			//에러! 
			response.setStatus(400);
			return JSONResult.fail("필수 입력 항목을 입력해 주세요.", result.getAllErrors());
		} else {
			//에러없음~
			// insert 하고 no 값을 받아온다 ~
			
			//insert 성공시 
			productVo.setNo(1L);
			return JSONResult.success(productVo);
		}
	}
	
	
	@RequestMapping(value="/delete/{deleteNo}", method=RequestMethod.DELETE)
	public JSONResult productDelete(
			@PathVariable(value="deleteNo") Long deleteNo,
			@RequestParam Boolean test
	) {
		Long result;
		if(test) {
			result =  productManageService.deleteProduct(deleteNo);
			return JSONResult.success(result);
		} else {
			result = null;
			return  JSONResult.fail("fail", result);
		}
	}
	

	
	
	
	
}
