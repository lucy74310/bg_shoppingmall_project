package com.cafe24.shoppingmall.vo;

public class AddressVo {
	private Long no;
	private String address;
	private String name;
	private String addr_name;
	private String phone;
	private char is_default;

	
	
	public AddressVo() {
	}



	public AddressVo(String address, String name, String addr_name, String phone, char is_default) {
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



	public char getIs_default() {
		return is_default;
	}



	public void setIs_default(char is_default) {
		this.is_default = is_default;
	}



	@Override
	public String toString() {
		return "AddressVo [no=" + no + ", address=" + address + ", name=" + name + ", addr_name=" + addr_name
				+ ", phone=" + phone + ", is_default=" + is_default + "]";
	}




	
	
	
	
	
}
