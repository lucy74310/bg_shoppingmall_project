package com.cafe24.shoppingmall.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.UserService;
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
	
	@ApiOperation(value="이메일 존재 여부")
    @ApiImplicitParam(name="email", value="이메일주소", required = true, dataType="string")
	@RequestMapping(value="/checkemail", method=RequestMethod.GET)
	public JSONResult emailCheck(
			@RequestParam(value="email", required=true, defaultValue="") String email
	) {
		
		if("lucy74310@gmail.com".equals(email)) {
			// 이메일 존재 
			return JSONResult.success(true); 
		} else {
			// DB에 존재하는 이메일과 다름
			return JSONResult.success(false);
		}
		
	}
	
	
	@ApiImplicitParam(name="agree", value="동의여부", required = true, dataType="Boolean")
	@RequestMapping(value="/agreecheck", method=RequestMethod.GET)
	public JSONResult joinForm(@RequestParam(value="agree", required=true) Boolean agree,
			HttpServletResponse response) throws IOException {
		
		if(false == agree) {
			// 약관에 동의하지 않는경우 
			return JSONResult.fail("약관에 동의해야 회원가입이 가능합니다.");
		} else {
			//약관에 동의한 경우
			return JSONResult.success(true);
		}
	}
	
	
	@ApiModelProperty(required = true, value = "id")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(
		@ModelAttribute @Valid UserVo userVo,
		BindingResult result,
		HttpServletResponse response
	) throws IOException {
		System.out.println(userVo.toString());
		/**
		 * 200 ok
		 * {
		 * 		reault : "fail",
		 * 		message: "이메일 형식이 맞지 않습니다",
		 * 		data: null
		 * }
		 * 
		 * --이게 restful 한 응답임 
		 * 400 bad request
		 * {
		 * 		reault : "fail",
		 * 		message: "이메일 형식이 맞지 않습니다",
		 * 		data: null
		 * }
		 * */
	
		if(result.hasErrors()) {
			response.setStatus(400);
			return JSONResult.fail("필수 요구사항이 만족되지 않았습니다.",result.getAllErrors());
		}
		
		UserVo insertVo = userService.join(userVo);
		if(null != userVo.getEmail()) {
			//no 넣어서 배송지 저장 
		} 
		
		return JSONResult.success(insertVo);
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
