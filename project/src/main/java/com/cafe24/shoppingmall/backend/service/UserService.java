package com.cafe24.shoppingmall.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.UserDao;
import com.cafe24.shoppingmall.backend.vo.AddressVo;
import com.cafe24.shoppingmall.backend.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	public UserDao userDao;
	
	public Long join(UserVo userVo) {
		userVo.setNo(1L);
		return userDao.insertMember(userVo).getNo();
	}

	public Long addAddress(AddressVo addressVo) {
		addressVo.setNo(1L);
		return addressVo.getNo();
	}
	
}
