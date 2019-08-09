package com.cafe24.shoppingmall.frontend.vo;

import javax.validation.constraints.AssertTrue;


public class MemberVo {
	
	private Long no;
	private String id;
	private String name;
	private String password;
	private String phone;
	private String telephone;
	private String email;
	private String birth;
	private String join_date;
	private String update_date;
	private String leave_date;
	private String address;
	private int point;
	private String m_state;
	private String role;
	
	
	
	public MemberVo() {}
	
	
	public MemberVo(String id, String name, String password, String telephone,
			String email, String birth, String address) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.birth = birth;
		
	}
	
	//이름, 전화번호, 휴대전화번호, 이메일, 생일 수정 가능
	public MemberVo(Long no, String id, String name, String password, String phone, String telephone, String email,
			String birth) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.telephone = telephone;
		this.email = email;
		this.birth = birth;
	}
	
	//삭제 테스트 시 
	public MemberVo(Long no, String id, String password) {
		this.no = no;
		this.id = id;
		this.password = password;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getM_state() {
		return m_state;
	}
	public void setM_state(String m_state) {
		this.m_state = m_state;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getLeave_date() {
		return leave_date;
	}
	public void setLeave_date(String leave_date) {
		this.leave_date = leave_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone
				+ ", telephone=" + telephone + ", email=" + email + ", birth=" + birth + ", join_date=" + join_date
				+ ", update_date=" + update_date + ", leave_date=" + leave_date + ", address=" + address + ", point="
				+ point + ", m_state=" + m_state + ", role=" + role + "]";
	}


}


