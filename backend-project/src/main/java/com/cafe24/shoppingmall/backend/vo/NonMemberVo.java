package com.cafe24.shoppingmall.backend.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class NonMemberVo {
	private Long no;
	
	@Length(min=1,message="session값이 없습니다.")
	private String sessionID;
	private String expire_date;
	
	public NonMemberVo() {}
	
	public NonMemberVo(String sessionID) {
		this.sessionID = sessionID;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	@Override
	public String toString() {
		return "NonMemberVo [no=" + no + ", sessionID=" + sessionID + ", expire_date=" + expire_date + "]";
	}
	
	
}
