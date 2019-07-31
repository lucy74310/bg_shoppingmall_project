package com.cafe24.shoppingmall.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.AdminDao;
import com.cafe24.shoppingmall.backend.vo.AdminVo;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	//관리자 가입
	public Long join(AdminVo adminVo) {
		return adminDao.insertAdmin(adminVo);
	}

	//로그인
	public AdminVo getByIdPwd(AdminVo adminVo) {
		return adminDao.getByIdPwd(adminVo);
	}

	
	//수정
	public int updateAdmin(AdminVo adminVo) {
		return adminDao.updateAdmin(adminVo);
	}

	
	//삭제
	public int deleteAdmin(AdminVo adminVo) {
		return adminDao.deleteAdmin(adminVo);
	}

	//id중복체크
	public Boolean idCheck(String id) {
		return adminDao.idCheck(id);
	}

}
