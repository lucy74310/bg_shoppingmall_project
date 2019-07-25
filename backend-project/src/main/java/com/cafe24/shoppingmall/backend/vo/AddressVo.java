package com.cafe24.shoppingmall.backend.vo;

public class AddressVo {
	private Long no;
	private Long member_no;
	private String address;
	private String name;
	private String addr_name;
	private String phone;
	private String is_default;

	
	
	public AddressVo() {
	}


	public AddressVo(Long member_no, String address, String name, String addr_name, String phone, String is_default) {
		this.member_no = member_no;
		this.address = address;
		this.name = name;
		this.addr_name = addr_name;
		this.phone = phone;
		this.is_default = is_default;
	}


	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr_name() {
		return addr_name;
	}
	public void setAddr_name(String addr_name) {
		this.addr_name = addr_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIs_default() {
		return is_default;
	}
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	@Override
	public String toString() {
		return "AddressVo [member_no=" + member_no + ", address=" + address + ", name=" + name + ", addr_name="
				+ addr_name + ", phone=" + phone + ", is_default=" + is_default + "]";
	}
}
