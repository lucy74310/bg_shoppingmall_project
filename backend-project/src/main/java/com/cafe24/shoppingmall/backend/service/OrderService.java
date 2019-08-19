package com.cafe24.shoppingmall.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.backend.repository.OrderDao;
import com.cafe24.shoppingmall.backend.vo.CartVo;
import com.cafe24.shoppingmall.backend.vo.OrderProductVo;
import com.cafe24.shoppingmall.backend.vo.OrderVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	// 주문서 insert
	@Transactional
	public OrderVo addOrder(OrderVo orderVo) {
		System.out.println(orderVo);
		Long order_no = null;
		if(orderVo.getOrder_list() != null) {
			int product_count = orderVo.getOrder_list().size();
			String product_name = orderVo.getOrder_list().get(0).getProduct_name();
			
			if(product_count > 1) {
				orderVo.setOrder_name(product_name + " 외 " + (product_count-1) + "개");
			} else if (product_count == 1) {
				orderVo.setOrder_name(product_name);
			}
			orderVo.setOrder_state("결제완료");
			order_no = orderDao.insertOrder(orderVo);
			System.out.println("주문서 insert ing");
			for(OrderProductVo c : orderVo.getOrder_list()) {
				OrderProductVo opvo = new OrderProductVo(order_no,
						c.getProduct_option_no(),c.getProduct_name(), c.getPo_name(), c.getCount(),
						c.getPrice(), "배송완료", c.getMain_image_url());
				orderDao.insertOrderProduct(opvo);
			}
		}
		System.out.println("주문서 insert done");
		OrderVo insertOrder = null;
		if(order_no != null) {
			insertOrder = orderDao.getOrder(order_no);
		}
		System.out.println("주문서 info : " + insertOrder.toString());
		return insertOrder;
	}
	// 회원 주문내역조회 
	public List<OrderProductVo> getMemberHistory(Long member_no) {
		List<OrderProductVo> order_product_list = orderDao.getMemberOrderHistory(member_no);
		return order_product_list;
	}
	//비회원 주문내역조회
	public List<OrderProductVo> getNonMemberHistory(OrderVo orderVo) {
		List<OrderProductVo> order_product_list = orderDao.getNonMemberOrderHistory(orderVo);
		return order_product_list;
	}
	
	//주문 상태 변경
	public int orderStateChange(OrderVo orderVo) {
		int count = orderDao.updateOrderState(orderVo);
		return count;
	}
	public int orderProductStateChange(OrderProductVo orderProductVo) {
		return orderDao.updateOrderProductState(orderProductVo);
	}
	
	//주문목록조회
	public List<OrderVo> getList() {
		return orderDao.getOrderList();
	}
	
	
	
	
}
