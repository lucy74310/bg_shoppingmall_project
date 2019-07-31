package com.cafe24.shoppingmall.backend.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.AdminVo;

@Repository
public class AdminDao {
	@Autowired
	private SqlSession sqlSession;
	
	
	//관리자 추가
	public Long insertAdmin(AdminVo adminVo) {
		sqlSession.insert("admin.insert_admin", adminVo);
		return adminVo.getNo();
	}

	//로그인
	public AdminVo getByIdPwd(AdminVo adminVo) {
		return sqlSession.selectOne("admin.select_by_id_pwd", adminVo);
	}

	//수정
	public int updateAdmin(AdminVo adminVo) {
		return sqlSession.update("admin.update_admin", adminVo);
	}

	//삭제
	public int deleteAdmin(AdminVo adminVo) {
		return sqlSession.update("admin.delete_admin", adminVo);
	}

	public Boolean idCheck(String id) {
		AdminVo adminVo = sqlSession.selectOne("admin.id_check", id);
		return adminVo != null;
	}
}
