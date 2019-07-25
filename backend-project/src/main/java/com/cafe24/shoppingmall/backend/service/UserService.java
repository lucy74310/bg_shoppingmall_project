package com.cafe24.shoppingmall.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.UserDao;
import com.cafe24.shoppingmall.backend.vo.AddressVo;
import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.backend.vo.MemberVo;

@Service
public class UserService {
	
	@Autowired
	public UserDao userDao;
	
	//회원가입
	public Long join(MemberVo userVo) {
		userVo.setNo(1L);
		return userDao.insertMember(userVo).getNo();
	}
	//회원정보 수정
	
	//회원 탈퇴
	
	//배송지 추가
	public Long addAddress(AddressVo addressVo) {
		addressVo.setNo(1L);
		return addressVo.getNo();
	}
	
	// 비회원 추가
	public NonMemberVo addNonMember(NonMemberVo nonMemberVo) {
		return userDao.insertNonMember(nonMemberVo); 
	}
	
}
