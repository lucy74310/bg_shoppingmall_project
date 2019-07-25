package com.cafe24.shoppingmall.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.CartDao;
import com.cafe24.shoppingmall.backend.vo.CartVo;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	//장바구니 담기
	public CartVo addCart(CartVo cartVo) {
		return cartDao.insertCart(cartVo);
	}
	// 장바구니 수정
	public boolean updateCart(CartVo cartVo) {
		boolean result = (1 == cartDao.updateCart(cartVo));
		return result;
	}
	// 장바구니 삭제
	public boolean deleteCart(CartVo cartVo) {
		boolean result = ( 1== cartDao.deleteCart(cartVo));
		return result;
	}
	
	// 장바구니 목록
	public List<CartVo> getCartList(Boolean is_member, Long no) {
		List<CartVo> cart_list;
		if(is_member) {
			cart_list = cartDao.getMemberCartList(no);
		} else {
			cart_list = cartDao.getNonMemberCartList(no);
		}
		return cart_list;
	}
	
	//장바구니에 이미 존재하는 상품인지 확인 
	public int alreadyHasCheck(Boolean is_member, Long mem_no, Long po_no) {
		int count;
		if(is_member) {
			count = cartDao.checkAleradyHasPOWhenMember(mem_no, po_no);
		} else {
			count = cartDao.checkAleradyHasPOWhenNonMember(mem_no, po_no);
		}
		return count;
	}

}
