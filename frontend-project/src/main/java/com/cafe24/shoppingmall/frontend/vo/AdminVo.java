package com.cafe24.shoppingmall.frontend.vo;

import org.hibernate.validator.constraints.Length;

public class AdminVo {
	private Long no;
	
	@Length(min=4, max=12, message="idλ₯? ?? ₯?΄μ£ΌμΈ?.(4~12?)")
	private String id;
	
	@Length(min=4, max=16, message="λΉλ?λ²νΈλ₯? ?? ₯?΄μ£ΌμΈ?.(4~16?)")
	private String password;
	private String ROLE = "ADMIN";
	
	@Length(min=4, max=16, message="λΉλ?λ²νΈλ₯? ?? ₯?΄μ£ΌμΈ?.(4~16?)")
	private String update_password;
	
	
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
	public String getROLE() {
		return ROLE;
	}
	public void setROLE(String ROLE) {
		this.ROLE = ROLE;
	}
	@Override
	public String toString() {
		return "AdminVo [no=" + no + ", id=" + id + ", password=" + password + ", ROLE=" + ROLE + ", update_password="
				+ update_password + "]";
	}
	
	
}
