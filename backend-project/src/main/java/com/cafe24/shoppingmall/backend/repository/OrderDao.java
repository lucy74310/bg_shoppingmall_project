package com.cafe24.shoppingmall.backend.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.OrderProductVo;
import com.cafe24.shoppingmall.backend.vo.OrderVo;

@Repository
public class OrderDao {

	@Autowired
	private SqlSession sqlSession;

	public Long insertOrder(OrderVo orderVo) {
		sqlSession.insert("order.insert_order", orderVo);
		return orderVo.getNo();
	}

	public void insertOrderProduct(OrderProductVo opvo) {
		sqlSession.insert("order.insert_order_product", opvo);
	}

	public List<OrderProductVo> getMemberOrderHistory(Long member_no) {
		return sqlSession.selectList("order.get_member_order_history", member_no);
	}

	public List<OrderProductVo> getNonMemberOrderHistory(OrderVo orderVo) {
		return sqlSession.selectList("order.get_nonmember_order_history", orderVo);
	}

	public int updateOrderState(OrderVo orderVo) {
		return sqlSession.update("order.update_order_state", orderVo);
	}

	public int updateOrderProductState(OrderProductVo orderProductVo) {
		return sqlSession.update("order.update_order_handling_state", orderProductVo);
	}

	/*주문서 가져오기*/
	public OrderVo getOrder(Long order_no) {
		return sqlSession.selectOne("order.select_order", order_no);
	}

	public List<OrderVo> getOrderList() {
		return sqlSession.selectList("order.select_order_list");
	}
	
	
}
