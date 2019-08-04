package com.cafe24.shoppingmall.backend.vo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class OrderVo {
	private Long no;
	private String order_code;
	private String order_name;
	private String order_date;
	
	@Length(min=1, message="주문자 이름은 필수입력 사향입니다.")
	@NotEmpty(message="주문자 이름은 필수입력 사향입니다.")
	private String orderer_name;
	
	@Length(min=1, message="주문자 이메일은 필수입력 사향입니다.")
	@NotEmpty(message="주문자 이메일은 필수입력 사향입니다.")
	private String orderer_email;
	
	private String orderer_addr;
	
	private String orderer_phone;
	
	@Length(min=1, message="주문자 휴대번호는 필수입력 사향입니다.")
	@NotEmpty(message="주문자 휴대번호는 필수입력 사향입니다.")
	private String orderer_telephone;
	
	@Length(min=1, message="수취자 이름은 필수입력 사향입니다.")
	@NotEmpty(message="수취자 이름은 필수입력 사향입니다.")
	private String receiver_name;
	
	@Length(min=1, message="수취자 주소는 필수입력 사향입니다.")
	@NotEmpty(message="수취자 주소는 필수입력 사향입니다.")
	private String receiver_addr;
	
	@Length(min=1, message="수취자 휴대번호는 필수입력 사향입니다.")
	@NotEmpty(message="수취자 휴대번호는 필수입력 사향입니다.")
	private String receiver_telephone;
	
	private String receiver_phone;
	
	private int pay_amount;
	private String shipping_msg;
	
	private String order_state;
	
	private Long member_no;
	private String order_check_password;
	
	
	private List<CartVo> cart_list;
	
	
	
	public OrderVo() {
	}
	
	//회원주문테스트 생성자
	public OrderVo(String orderer_name, String orderer_email, String orderer_telephone,
			String receiver_name, String receiver_addr, String receiver_telephone, int pay_amount, Long member_no,
			List<CartVo> cart_list) {
		this.orderer_name = orderer_name;
		this.orderer_email = orderer_email;
		this.orderer_telephone = orderer_telephone;
		this.receiver_name = receiver_name;
		this.receiver_addr = receiver_addr;
		this.receiver_telephone = receiver_telephone;
		this.pay_amount = pay_amount;
		this.member_no = member_no;
		this.cart_list = cart_list;
	}
	
	//비회원주문테스트 생성자
	public OrderVo(String orderer_name, String orderer_email, String orderer_telephone,
			String receiver_name, String receiver_addr, String receiver_telephone, int pay_amount,
			String order_check_password, List<CartVo> cart_list) {
		this.orderer_name = orderer_name;
		this.orderer_email = orderer_email;
		this.orderer_telephone = orderer_telephone;
		this.receiver_name = receiver_name;
		this.receiver_addr = receiver_addr;
		this.receiver_telephone = receiver_telephone;
		this.pay_amount = pay_amount;
		this.order_check_password = order_check_password;
		this.cart_list = cart_list;
	}
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrderer_name() {
		return orderer_name;
	}
	public void setOrderer_name(String orderer_name) {
		this.orderer_name = orderer_name;
	}
	public String getOrderer_email() {
		return orderer_email;
	}
	public void setOrderer_email(String orderer_email) {
		this.orderer_email = orderer_email;
	}
	public String getOrderer_addr() {
		return orderer_addr;
	}
	public void setOrderer_addr(String orderer_addr) {
		this.orderer_addr = orderer_addr;
	}
	public String getOrderer_phone() {
		return orderer_phone;
	}
	public void setOrderer_phone(String orderer_phone) {
		this.orderer_phone = orderer_phone;
	}
	public String getOrderer_telephone() {
		return orderer_telephone;
	}
	public void setOrderer_telephone(String orderer_telephone) {
		this.orderer_telephone = orderer_telephone;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_addr() {
		return receiver_addr;
	}
	public void setReceiver_addr(String receiver_addr) {
		this.receiver_addr = receiver_addr;
	}
	public String getReceiver_telephone() {
		return receiver_telephone;
	}
	public void setReceiver_telephone(String receiver_telephone) {
		this.receiver_telephone = receiver_telephone;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getShipping_msg() {
		return shipping_msg;
	}
	public void setShipping_msg(String shipping_msg) {
		this.shipping_msg = shipping_msg;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public String getOrder_check_password() {
		return order_check_password;
	}
	public void setOrder_check_password(String order_check_password) {
		this.order_check_password = order_check_password;
	}
	public int getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(int pay_amount) {
		this.pay_amount = pay_amount;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public List<CartVo> getCart_list() {
		return cart_list;
	}
	public void setCart_list(List<CartVo> cart_list) {
		this.cart_list = cart_list;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", order_code=" + order_code + ", order_name=" + order_name + ", orderer_name="
				+ orderer_name + ", orderer_email=" + orderer_email + ", orderer_addr=" + orderer_addr
				+ ", orderer_phone=" + orderer_phone + ", orderer_telephone=" + orderer_telephone + ", receiver_name="
				+ receiver_name + ", receiver_addr=" + receiver_addr + ", receiver_telephone=" + receiver_telephone
				+ ", receiver_phone=" + receiver_phone + ", shipping_msg=" + shipping_msg + ", order_state="
				+ order_state + ", order_check_password=" + order_check_password + ", pay_amount=" + pay_amount
				+ ", member_no=" + member_no + ", cart_list=" + cart_list + "]";
	}
	
}
