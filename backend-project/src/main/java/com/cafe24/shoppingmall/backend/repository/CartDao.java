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

	public CartVo checkAleradyHas(Boolean is_member, Long mem_no, Long po_no) {
		Map<String, Long> data = new HashMap<String, Long>();
		if(is_member) {
			data.put("member_no", mem_no);
			data.put("po_no", po_no);
			data.put("non_member_no", null);
		} else {
			data.put("member_no", null);
			data.put("po_no", po_no);
			data.put("non_member_no", mem_no);
		}
		CartVo cartVo = sqlSession.selectOne("cart.check_has", data);
		return cartVo ;
	}

	/*
	 * public CartVo checkAleradyHasPOWhenNonMember(Long non_mem_no, Long po_no) {
	 * Map<String, Long> data = new HashMap<String, Long>();
	 * data.put("non_member_no", non_mem_no); data.put("po_no", po_no); CartVo
	 * cartVo = sqlSession.selectOne("cart.check_has_when_nonmember", data); return
	 * cartVo; }
	 */

	
	
}
