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
		MemberVo userVo = null;
		AdminVo adminVo = userService.adminGet(id);
		
		SecurityUser securityUser = new SecurityUser();
		
//		if(userVo != null) {
//			securityUser.setNo(userVo.getNo());
//			securityUser.setName(userVo.getName());
//			securityUser.setUsername(userVo.getId());
//			securityUser.setPassword(userVo.getPassword());
//			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userVo.getRole())));
//		}
		
		if(adminVo != null) {
			securityUser.setNo(adminVo.getNo());
			securityUser.setUsername(adminVo.getId());
			securityUser.setPassword(adminVo.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(adminVo.getRole())));
		}
		
		
		System.out.println(securityUser.toString());
		
		
		
		
		return securityUser;
	}	
}
