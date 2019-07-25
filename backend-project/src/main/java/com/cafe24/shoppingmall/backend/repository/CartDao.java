package com.cafe24.shoppingmall.backend.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.CartVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

@Repository
public class CartDao {

	@Autowired
	private SqlSession sqlSession;

	public CartVo insertCart(CartVo cartVo) {
		sqlSession.insert("cart.insert_cart", cartVo);
		return cartVo;
	}

	public int updateCart(CartVo cartVo) {
		return sqlSession.update("cart.update_cart", cartVo);
	}

	public int deleteCart(CartVo cartVo) {
		return sqlSession.update("cart.delete_cart", cartVo);
	}

	public List<CartVo> getMemberCartList(Long no) {
		return sqlSession.selectList("cart.get_member_cart_list", no);
	}

	public List<CartVo> getNonMemberCartList(Long no) {
		return sqlSession.selectList("cart.get_nonmember_cart_list", no);
	}

	public int checkAleradyHasPOWhenMember(Long mem_no, Long po_no) {
		Map<String, Long> data = new HashMap<String, Long>();
		data.put("member_no", mem_no);
		data.put("po_no", po_no);
		int count = sqlSession.selectOne("cart.check_has_when_member", data);
		
		return count;
	}

	public int checkAleradyHasPOWhenNonMember(Long non_mem_no, Long po_no) {
		Map<String, Long> data = new HashMap<String, Long>();
		data.put("non_member_no", non_mem_no);
		data.put("po_no", po_no);
		String result = sqlSession.selectOne("cart.check_has_when_nonmember", data);
		if(result == null) result = "-1";
		
		return Integer.parseInt(result);
	}


	
	
}
