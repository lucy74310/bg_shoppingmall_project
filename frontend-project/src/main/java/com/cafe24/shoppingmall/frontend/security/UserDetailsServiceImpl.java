package com.cafe24.shoppingmall.frontend.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.shoppingmall.frontend.service.UserService;
import com.cafe24.shoppingmall.frontend.vo.MemberVo;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVo memberVo = userService.getMember(id);
		
		SecurityUser securityUser = new SecurityUser();
		if(memberVo != null) {
			securityUser.setNo(memberVo.getNo());
			securityUser.setUsername(memberVo.getId());
			securityUser.setPassword(memberVo.getPassword());
			securityUser.setName(memberVo.getName());
			securityUser.setTelephone(memberVo.getTelephone());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberVo.getRole())));
		} 
		return securityUser;
	}	
}
