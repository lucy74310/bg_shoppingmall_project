package com.cafe24.shoppingmall.frontend.vo;


public class AdminVo {
	private Long no;
	
	private String id;
	
	private String password;
	private String role;
	
	private String update_password;
	
	public AdminVo() {
		
	}
	public AdminVo(String id) {
		this.id = id;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpdate_password() {
		return update_password;
	}
	public void setUpdate_password(String update_password) {
		this.update_password = update_password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AdminVo [no=" + no + ", id=" + id + ", password=" + password + ", role=" + role + ", update_password="
				+ update_password + "]";
	}
	
	
}
