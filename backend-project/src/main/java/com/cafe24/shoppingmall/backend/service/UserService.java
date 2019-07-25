package com.cafe24.shoppingmall.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.backend.repository.UserDao;
import com.cafe24.shoppingmall.backend.vo.AddressVo;
import com.cafe24.shoppingmall.backend.vo.LoginVo;
import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.backend.vo.MemberVo;

@Service
public class UserService {
	
	@Autowired
	public UserDao userDao;
	
	//회원가입
	@Transactional
	public Long join(MemberVo memberVo) {
		Long member_no = userDao.insertMember(memberVo).getNo();
		if(memberVo.getAddress() != null && memberVo.getAddress() != "") {
			AddressVo addressVo = new AddressVo(member_no, 
					memberVo.getAddress(), memberVo.getName(), memberVo.getName(), 
					memberVo.getTelephone(),"Y");
			userDao.insertAddress(addressVo);
		}
		
		return member_no;
	}
	//회원정보 수정
	
	//회원 탈퇴
	
	//배송지 추가
	public Long addAddress(AddressVo addressVo) {
		return userDao.insertAddress(addressVo).getNo();
	}
	
	// 비회원 추가
	public NonMemberVo addNonMember(NonMemberVo nonMemberVo) {
		return userDao.insertNonMember(nonMemberVo); 
	}
	// id 중복체크 
	public Boolean idCheck(String id) {
		// null이 아니면 id 있으니까 중복 (true)
		// null 이면 id 없으니까 중복아님 (false) - 여야 가입가능
		return (userDao.checkIdDuplicated(id) != null);
	}

	//로그인 - id,pwd 일치하는 회원정보 get
	public MemberVo getLoginMember(LoginVo loginVo) {
		return userDao.getLoginMember(loginVo);
	}
	
}
