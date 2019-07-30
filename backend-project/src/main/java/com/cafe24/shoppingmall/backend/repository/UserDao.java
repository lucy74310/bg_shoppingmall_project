package com.cafe24.shoppingmall.backend.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.NonMemberVo;
import com.cafe24.shoppingmall.backend.vo.UpdateMemberVo;
import com.cafe24.shoppingmall.backend.vo.AddressVo;
import com.cafe24.shoppingmall.backend.vo.LoginVo;
import com.cafe24.shoppingmall.backend.vo.MemberVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberVo insertMember(MemberVo memberVo) {
		sqlSession.insert("user.insert", memberVo);
		return memberVo;
	}

	public NonMemberVo insertNonMember(NonMemberVo nonMemberVo) {
		sqlSession.insert("user.insert_non_member", nonMemberVo);
		return nonMemberVo;
	}

	public MemberVo checkIdDuplicated(String id) {
		return sqlSession.selectOne("user.check_id", id); 
	}

	public AddressVo insertAddress(AddressVo addressVo) {
		sqlSession.insert("user.insert_address", addressVo);
		return addressVo;
	}

	public MemberVo getLoginMember(LoginVo loginVo) {
		return sqlSession.selectOne("user.get_login_member", loginVo);
	}

	public int updateMember(UpdateMemberVo memVo) {
		return sqlSession.update("user.update_member_info", memVo);
	}

	public int deleteMember(Long no) {
		return sqlSession.delete("user.delete_member", no);
	}

	public Boolean ownerCheck(LoginVo loginVo) {
		MemberVo mem = sqlSession.selectOne("user.get_login_member", loginVo);
		return mem != null;
	}

	public List<MemberVo> getMemberList() {
		return sqlSession.selectList("user.get_member_list");
	}
	
}
