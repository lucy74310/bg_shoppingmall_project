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
	
	private String orderer_zipcode;
	private String orderer_addr1;
	private String orderer_addr2;
	
	private String orderer_phone;
	
	@Length(min=1, message="주문자 휴대번호는 필수입력 사향입니다.")
	@NotEmpty(message="주문자 휴대번호는 필수입력 사향입니다.")
	private String orderer_telephone;
	
	@Length(min=1, message="수취자 이름은 필수입력 사향입니다.")
	@NotEmpty(message="수취자 이름은 필수입력 사향입니다.")
	private String receiver_name;
	
	
	private String receiver_zipcode;
	@Length(min=1, message="수취자 주소는 필수입력 사향입니다.")
	@NotEmpty(message="수취자 주소는 필수입력 사향입니다.")
	private String receiver_addr1;
	private String receiver_addr2;
	
	@Length(min=1, message="수취자 휴대번호는 필수입력 사향입니다.")
	@NotEmpty(message="수취자 휴대번호는 필수입력 사향입니다.")
	private String receiver_telephone;
	
	private String receiver_phone;
	
	private int pay_amount;
	private String shipping_msg;
	
	private String order_state;
	
	private Long member_no;
	private String order_check_password;
	
	
	private List<OrderProductVo> order_list;
	
	
	
	public OrderVo() {
	}
	
	//회원주문테스트 생성자
	public OrderVo(String orderer_name, String orderer_email, String orderer_telephone,
			String receiver_name, String receiver_addr, String receiver_telephone, int pay_amount, Long member_no,
			List<OrderProductVo> order_list) {
		this.orderer_name = orderer_name;
		this.orderer_email = orderer_email;
		this.orderer_telephone = orderer_telephone;
		this.receiver_name = receiver_name;
		this.receiver_addr1 = receiver_addr;
		this.receiver_telephone = receiver_telephone;
		this.pay_amount = pay_amount;
		this.member_no = member_no;
		this.order_list = order_list;
	}
	
	//비회원주문테스트 생성자
	public OrderVo(String orderer_name, String orderer_email, String orderer_telephone,
			String receiver_name, String receiver_addr, String receiver_telephone, int pay_amount,
			String order_check_password, List<OrderProductVo> order_list) {
		this.orderer_name = orderer_name;
		this.orderer_email = orderer_email;
		this.orderer_telephone = orderer_telephone;
		this.receiver_name = receiver_name;
		this.receiver_addr1 = receiver_addr;
		this.receiver_telephone = receiver_telephone;
		this.pay_amount = pay_amount;
		this.order_check_password = order_check_password;
		this.order_list = order_list;
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

	public List<OrderProductVo> getOrder_list() {
		return order_list;
	}

	public void setOrder_list(List<OrderProductVo> order_list) {
		this.order_list = order_list;
	}

	public String getOrderer_zipcode() {
		return orderer_zipcode;
	}

	public void setOrderer_zipcode(String orderer_zipcode) {
		this.orderer_zipcode = orderer_zipcode;
	}

	public String getOrderer_addr1() {
		return orderer_addr1;
	}

	public void setOrderer_addr1(String orderer_addr1) {
		this.orderer_addr1 = orderer_addr1;
	}

	public String getOrderer_addr2() {
		return orderer_addr2;
	}

	public void setOrderer_addr2(String orderer_addr2) {
		this.orderer_addr2 = orderer_addr2;
	}

	public String getReceiver_zipcode() {
		return receiver_zipcode;
	}

	public void setReceiver_zipcode(String receiver_zipcode) {
		this.receiver_zipcode = receiver_zipcode;
	}

	public String getReceiver_addr1() {
		return receiver_addr1;
	}

	public void setReceiver_addr1(String receiver_addr1) {
		this.receiver_addr1 = receiver_addr1;
	}

	public String getReceiver_addr2() {
		return receiver_addr2;
	}

	public void setReceiver_addr2(String receiver_addr2) {
		this.receiver_addr2 = receiver_addr2;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", order_code=" + order_code + ", order_name=" + order_name + ", order_date="
				+ order_date + ", orderer_name=" + orderer_name + ", orderer_email=" + orderer_email
				+ ", orderer_zipcode=" + orderer_zipcode + ", orderer_addr1=" + orderer_addr1 + ", orderer_addr2="
				+ orderer_addr2 + ", orderer_phone=" + orderer_phone + ", orderer_telephone=" + orderer_telephone
				+ ", receiver_name=" + receiver_name + ", receiver_zipcode=" + receiver_zipcode + ", receiver_addr1="
				+ receiver_addr1 + ", receiver_addr2=" + receiver_addr2 + ", receiver_telephone=" + receiver_telephone
				+ ", receiver_phone=" + receiver_phone + ", pay_amount=" + pay_amount + ", shipping_msg=" + shipping_msg
				+ ", order_state=" + order_state + ", member_no=" + member_no + ", order_check_password="
				+ order_check_password + ", order_list=" + order_list + "]";
	}


	
}
