package com.cafe24.shoppingmall.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.AddressVo;
import com.cafe24.shoppingmall.vo.UserVo;

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
	@RequestMapping(value="/checkemail", method=RequestMethod.GET)
	public JSONResult emailCheck(
			@RequestParam(value="email", required=true, defaultValue="") String email
	) {
		
		if("lucy74310@gmail.com".equals(email)) {
			// 이메일 존재 
			return JSONResult.fail("존재하는 이메일입니다."); 
		} else {
			// DB에 존재하는 이메일과 다름
			return JSONResult.success(false);
		}
		
	}
	
	@ApiOperation(value="약관 동의")
	@ApiImplicitParam(name="agree", value="동의여부", required = true, dataType="Boolean")
	@RequestMapping(value="/agreecheck", method=RequestMethod.GET)
	public JSONResult agreeCheck(@RequestParam(value="agree", required=true) Boolean agree,
			HttpServletResponse response) throws IOException {
		
		if(false == agree) {
			// 약관에 동의하지 않는경우 
			return JSONResult.fail("약관에 동의해야 회원가입이 가능합니다.");
		} else {
			//약관에 동의한 경우
			return JSONResult.success(true);
		}
	}
	
	@ApiOperation(value="회원 가입")
	@ApiModelProperty(required = true, value = "id")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(
		@RequestBody @Valid UserVo userVo,
		BindingResult valid,
		HttpServletResponse response
	) throws IOException {
		
		if(valid.hasErrors()) {
			
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			
			response.setStatus(400);
			return JSONResult.fail("필수 요구사항이 만족되지 않았습니다.", errMap);
			
		}
		
		Long user_no = userService.join(userVo);
		 
		AddressVo addressVo = 
			new AddressVo(userVo.getAddress(), userVo.getName(), userVo.getName(), userVo.getTelephone(), 'Y');
		
		Long addr_no = userService.addAddress(addressVo);
		
		Map<String, Long> data = new HashMap<String, Long>();
		data.put("user_no", user_no);
		data.put("addr_no", addr_no);
		
		return JSONResult.success(data);
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login() {
		return JSONResult.success("login");
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JSONResult userInfoUpdate() {
		return JSONResult.success("userInfoUpdate");
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public JSONResult deleteUser() {
		return JSONResult.success("deleteUser");
	}
	
	
	@RequestMapping(value="/address/add", method=RequestMethod.POST)
	public JSONResult addressAdd() {
		return JSONResult.success("addressAdd");
	}
	
	@RequestMapping(value="/address/update", method=RequestMethod.POST)
	public JSONResult addressUpdate() {
		return JSONResult.success("addressUpdate");
	}
	
	@RequestMapping(value="/address/delete", method=RequestMethod.POST)
	public JSONResult addressDelete() {
		return JSONResult.success("addressDelete");
	}
	
	
	
	
	
	
	
	
	
	

	
	
}
