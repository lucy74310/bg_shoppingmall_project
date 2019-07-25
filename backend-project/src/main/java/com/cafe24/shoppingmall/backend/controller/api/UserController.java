package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.backend.dto.JSONResult;
import com.cafe24.shoppingmall.backend.service.UserService;
import com.cafe24.shoppingmall.backend.vo.AddressVo;
import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.backend.vo.MemberVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController("userAPIController")
@RequestMapping("/api/user")
public class UserController {
	
	
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="중복 이메일 체크")
    @ApiImplicitParam(name="email", value="이메일주소", required = true, dataType="string")
	@GetMapping("/checkemail")
	public ResponseEntity<JSONResult> emailCheck(
			@RequestParam(value="email", required=true, defaultValue="") String email
	) {
		
		if("lucy74310@gmail.com".equals(email)) {
			// 이메일 존재 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("존재하는 이메일입니다.")); 
		} else {
			// DB에 존재하는 이메일과 다름
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(false));
		}
		
	}
	
	@ApiOperation(value="약관 동의")
	@ApiImplicitParam(name="agree", value="동의여부", required = true, dataType="Boolean")
	@GetMapping("/agreecheck")
	public ResponseEntity<JSONResult> agreeCheck(@RequestParam(value="agree", required=true) Boolean agree) throws IOException {
		
		if(false == agree) {
			// 약관에 동의하지 않는경우 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("약관에 동의해야 회원가입이 가능합니다."));
		} else {
			//약관에 동의한 경우
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(true));
		}
	}
	
	@ApiOperation(value="회원 가입")
	@ApiModelProperty(required = true, value = "id")
	@PostMapping("/join")
	public ResponseEntity<JSONResult> join(
		@RequestBody @Valid MemberVo userVo,
		BindingResult valid
	) throws IOException {
		
		if(valid.hasErrors()) {
			//Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				//errMap.put(f.getField(), f.getDefaultMessage());
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail("필수 요구사항이 만족되지 않았습니다.", f.getDefaultMessage()));
			}
			
		}
		
		Long user_no = userService.join(userVo);
		 
		AddressVo addressVo = 
			new AddressVo(userVo.getAddress(), userVo.getName(), userVo.getName(), userVo.getTelephone(), 'Y');
		
		Long addr_no = userService.addAddress(addressVo);
		
		Map<String, Long> data = new HashMap<String, Long>();
		data.put("user_no", user_no);
		data.put("addr_no", addr_no);
		
		return  ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(data));
	}
	
	
	@PostMapping(value="/login")
	public ResponseEntity<JSONResult> login() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success("login"));
	}
	
	
	@ApiOperation(value="비회원 추가")
	@PutMapping(value="/nonmember/add")
	public ResponseEntity<JSONResult> nonMemberAdd(
			@RequestBody @Valid NonMemberVo nonMemberVo,
			BindingResult valid
	) {
		if(valid.hasErrors()) {
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail(f.getDefaultMessage()));
			}
		}
		
		nonMemberVo = userService.addNonMember(nonMemberVo);
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(nonMemberVo));
	}
	
	
	
	

	
	
}
