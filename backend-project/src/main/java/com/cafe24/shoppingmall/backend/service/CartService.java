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
	public int updateCart(CartVo cartVo) {
		return cartDao.updateCart(cartVo);
	}
	// 장바구니 삭제
	public int deleteCart(CartVo cartVo) {
		return cartDao.deleteCart(cartVo);
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
		CartVo cartVo = cartDao.checkAleradyHas(is_member,mem_no, po_no);
		
		if(cartVo == null) {
			return -1;
		}
		
		return cartVo.getCount();
	}

}
