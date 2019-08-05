package com.cafe24.shoppingmall.frontend.vo;

import org.hibernate.validator.constraints.Length;

public class LoginVo {
	
	@Length(min=1, message="idë¥? ?? ¥?´ì£¼ì¸?.")
	private String id;
	
	@Length(min=1, message="ë¹ë?ë²í¸ë¥? ?? ¥?´ì£¼ì¸?.")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginVo [id=" + id + ", password=" + password + "]";
	}
}
