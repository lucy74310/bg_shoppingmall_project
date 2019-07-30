package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;

import io.swagger.annotations.ApiOperation;

@RestController("adminAPIContorller")
@RequestMapping("/api/admin")
public class AdminController {
	
	@ApiOperation(value="관리자 로그인")
	@PostMapping("/login")
	public ResponseEntity<JSONResult> login(
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			HttpServletResponse response
	) throws IOException {
		
		if("admin".equals(id)) {
			if("1234".equals(password)) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(JSONResult.success(true));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail("비밀번호가 틀렸습니다.", false));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("비밀번호가 틀렸습니다.", false));
		}
		
	}
	
	@ApiOperation(value="관리자 가입")
	@PostMapping("/join")
	public ResponseEntity<JSONResult> join(
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			HttpServletResponse response
	) throws IOException {
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(true));
	}
	
	@ApiOperation(value="관리자 수정")
	@PostMapping("/join")
	public ResponseEntity<JSONResult> join(
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			HttpServletResponse response
	) throws IOException {
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(true));
	}
	
	
}
