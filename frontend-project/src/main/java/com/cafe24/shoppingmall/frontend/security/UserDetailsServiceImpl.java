package com.cafe24.shoppingmall.frontend.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.shoppingmall.frontend.service.UserService;
import com.cafe24.shoppingmall.frontend.vo.AdminVo;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVo memberVo = userService.getMember(id);
		AdminVo adminVo = userService.adminGet(id);
		
		SecurityUser securityUser = new SecurityUser();
		
		if(memberVo == null && adminVo != null) {
			securityUser.setNo(adminVo.getNo());
			securityUser.setUsername(adminVo.getId());
			securityUser.setPassword(adminVo.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(adminVo.getRole())));
		} else if(memberVo != null && adminVo == null) {
			securityUser.setNo(memberVo.getNo());
			securityUser.setName(memberVo.getName());
			securityUser.setUsername(memberVo.getId());
			securityUser.setPassword(memberVo.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberVo.getRole())));
		} else if(memberVo != null && adminVo != null) {
			// id가 member 테이블, admin 테이블 모두 있을 경우는 어떡하지.... 
			//로그인 실패 처리 
		}
		System.out.println(securityUser.toString());
		return securityUser;
	}	
}
