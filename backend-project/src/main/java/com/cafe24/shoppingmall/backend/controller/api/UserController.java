package com.cafe24.shoppingmall.backend.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.cafe24.shoppingmall.backend.vo.LoginVo;
import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.backend.vo.UpdateMemberVo;
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
	
	@ApiOperation(value="중복 아이디 체크")
    @ApiImplicitParam(name="id", value="아이디", required = true, dataType="string")
	@GetMapping("/checkid/{id}")
	public ResponseEntity<JSONResult> emailCheck(
			@PathVariable(value="id") String id
	) {
		
		Boolean is_duplicated = userService.idCheck(id); 
		
		if(is_duplicated) {
			// 아이디 중복
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("중복되는 id입니다.", is_duplicated )); 
		} else {
			// 아이디 중복 x 
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(is_duplicated));
		}
		
	}
	
	
	@ApiOperation(value="회원 가입")
	@ApiModelProperty(required = true, value = "id")
	@PostMapping("/join")
	public ResponseEntity<JSONResult> join(
		@RequestBody @Valid MemberVo memberVo,
		BindingResult valid
	) throws IOException {
		
		// 필수 양식 체크 
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목 양식을 맞춰 주세요.", errMap));
		}
		
		// 양식체크 통과 후 실제 insert
		Long member_no = userService.join(memberVo);
		 
		
		return  ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(member_no));
	}
	
	@ApiOperation(value="로그인")
	@PostMapping(value= {"/login"})
	public ResponseEntity<JSONResult> login(
		@RequestBody @Valid LoginVo loginVo,
		BindingResult valid
	) {
		//id, pwd 입력 체크 
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
		}
		
		//db에서 id,pwd 일치하는 회원 정보 가져오기
		MemberVo memVo = userService.getLoginMember(loginVo);
		
		if(memVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(JSONResult.fail("아이디 혹은 비밀번호가 맞지 않습니다."));
		} 
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(memVo));
	}
	
	
	@ApiOperation(value="비회원 추가")
	@PostMapping(value="/nonmember/join")
	public ResponseEntity<JSONResult> nonMemberAdd(
			@RequestBody @Valid NonMemberVo nonMemberVo,
			BindingResult valid
	) {
		
		//양식 체크 (sessionID필수)
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
		
		}
		
		// 양식 통과 후 insert
		nonMemberVo = userService.addNonMember(nonMemberVo);
		
		if(nonMemberVo.getNo() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("비회원 추가 실패"));
		} 
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(nonMemberVo.getNo()));
	}
	
	
	@ApiOperation(value="회원정보 수정")
	@PutMapping("/update")
	public ResponseEntity<JSONResult> updateMember(
		@RequestBody @Valid MemberVo memVo,
		BindingResult valid
	){
		// 필수 양식 체크 
		if(valid.hasErrors()) {
			Map<String, String> errMap = new HashMap<String, String>();
			for(ObjectError err : valid.getAllErrors()) {
				FieldError f = (FieldError) err;
				errMap.put(f.getField(), f.getDefaultMessage());
			}
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("필수항목을 입력해주세요", errMap));
		}
		
		int count = userService.updateMember(memVo);
		
		if(count == 1) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(count));
		} else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("회원정보 수정에 실패하였습니다.", count));
		}
		
	}
	
	
	@ApiOperation(value="회원 삭제")
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<JSONResult> deleteMember(
		@PathVariable("no") Long no
	) {
		int count = userService.deleteMember(no);
		
		if(count == 1) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(count));
		} else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("회원 삭제에 실패하였습니다.", count));
		}
	}
	@ApiOperation(value="회원 탈퇴")
	@DeleteMapping("/leave")
	public ResponseEntity<JSONResult> leaveMember(
		@RequestBody MemberVo memVo
	) {
		int count = userService.leaveMember(memVo);
		
		if(count == 1) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(count));
		} else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("회원 탈퇴 처리가 되지 않았습니다.", count));
		}
	}
	
	@ApiOperation(value="비밀번호 확인")
	@PostMapping("/ownercheck")
	public ResponseEntity<JSONResult> ownerCheck(
		@RequestBody LoginVo loginVo
	) {
		
		Boolean is_owner = userService.ownerCheck(loginVo);
		if (is_owner) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(is_owner));
		} else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("비밀번호가 틀립니다.", is_owner));
		}
	}

	
	@ApiOperation(value="회원리스트")
	@GetMapping("/list")
	public ResponseEntity<JSONResult> memberList() {
		
		List<MemberVo> mem_list = userService.getMemberList();
		if(mem_list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(JSONResult.success(mem_list));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("회원 목록이 없습니다."));
		}
	}

	
	
}
