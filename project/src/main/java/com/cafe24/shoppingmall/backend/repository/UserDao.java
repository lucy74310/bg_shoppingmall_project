package com.cafe24.shoppingmall.backend.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo insertMember(UserVo userVo) {
		sqlSession.insert("user.insert", userVo);
		return userVo;
	}
	
}