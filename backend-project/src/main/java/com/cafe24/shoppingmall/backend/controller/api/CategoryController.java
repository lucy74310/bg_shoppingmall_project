package com.cafe24.shoppingmall.backend.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.service.CategoryService;
import com.cafe24.shoppingmall.backend.vo.CategoryVo;

import io.swagger.annotations.ApiOperation;

@RestController("categoryAPIController")
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation("카테고리 리스트")
	@GetMapping("/list")
	public ResponseEntity<JSONResult> getCategoryList(){
		
		List<CategoryVo> list = categoryService.getList();
		if(list.size() == 0 || list == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("등록된 카테고리가 없습니다."));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(list));
	}
	
	
	@ApiOperation("카테고리 등록")
	@PostMapping("/add")
	public ResponseEntity<JSONResult> addCategory(
			@RequestBody @Valid CategoryVo categoryVo,
			BindingResult valid
	){
		if(valid.hasErrors()) {
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail(f.getDefaultMessage()));
			}
		}
		
		
		Long insertNo = categoryService.addCategory(categoryVo);
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(insertNo));
	}
	
	
	
	
	@ApiOperation("카테고리 수정")
	@PostMapping("/update")
	public ResponseEntity<JSONResult> updateCategory(
			@RequestBody @Valid CategoryVo categoryVo,
			BindingResult valid
	){
		if(valid.hasErrors()) {
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail(f.getDefaultMessage()));
			}
		}
		
		Boolean result = categoryService.updateCategory(categoryVo);
		if(result) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(result));	
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("수정이 되지 않았습니다.",result));
		}
		
		
	}
	
	
	@ApiOperation("카테고리 삭제")
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<JSONResult> deleteCategory(
			@PathVariable("no") Long no
	){
		
		Boolean result = categoryService.deleteCategory(no);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(result));	
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("삭제가 되지 않았습니다.",result));
		}
	}
	
}
