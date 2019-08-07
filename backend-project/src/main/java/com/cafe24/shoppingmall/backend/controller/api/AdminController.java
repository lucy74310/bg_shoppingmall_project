package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.service.AdminService;
import com.cafe24.shoppingmall.backend.vo.AdminVo;

import io.swagger.annotations.ApiOperation;

@RestController("adminAPIContorller")
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@ApiOperation(value="관리자 id 중복확인")
	@GetMapping("/idcheck/{id}")
	public ResponseEntity<JSONResult> idCheck(
		@PathVariable("id") String id
	){	
		if( id != null ) {
			
			Boolean is_exist = adminService.idCheck(id);
			if(is_exist) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail("존재하는 아이디 입니다. 다른 id를 입력해주세요.",is_exist ));
			} 
			
			return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(is_exist));	
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("아이디를 입력해 주세요."));	
		}
		
	}
	
	@ApiOperation(value="관리자 가입")
	@PutMapping("/join")
	public ResponseEntity<JSONResult> join(
			@RequestBody @Valid AdminVo adminVo,
			BindingResult valid
	) throws IOException {
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
		}
		
		Long admin_no = adminService.join(adminVo);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(admin_no));
	}
	
	@ApiOperation(value="관리자 로그인")
	@PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<JSONResult> login(
			@RequestBody AdminVo adminVo
	) throws IOException {
		
//		if(valid.hasErrors()) {
//			Map<String, String> errMap = new HashMap<String, String>();
//			for(ObjectError err : valid.getAllErrors()) {
//				FieldError f = (FieldError) err;
//				errMap.put(f.getField(), f.getDefaultMessage());
//			}
			
//			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
//		}

		AdminVo getAdminVo = adminService.getByIdPwd(adminVo);
		if(getAdminVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("id및 password를 확인해 주세요."));
		}

		//		ResponseEntity.HeadersBuilder<HeadersBuilder<B>>
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(getAdminVo));
	}
	
	
	
	@ApiOperation(value="관리자 비밀번호 변경")
	@PostMapping("/modify")
	public ResponseEntity<JSONResult> modify(
			@RequestBody @Valid AdminVo adminVo,
			BindingResult valid
	) throws IOException {
		
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
		}
		
		
		int count = adminService.updateAdmin(adminVo);
		if(count == 1) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(count));
		} else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("비밀번호 변경 실패",count));
		}
		
		
	}
	
	@ApiOperation(value="관리자 삭제")
	@PostMapping("/delete")
	public ResponseEntity<JSONResult> delete(
			@RequestBody @Valid AdminVo adminVo,
			BindingResult valid
	) throws IOException {
		

		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
		}
		
		int count = adminService.deleteAdmin(adminVo);
		if(count == 1) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(count));
		} else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("삭제 실패", count));
		}
		
	}
	
}
