package com.cafe24.shoppingmall.backend.vo;



import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class MemberVo {
	
	private Long no;
	
	@NotEmpty(message="필수 입력 사항입니다.")
	@Pattern(regexp = "(([a-z0-9])(?=\\S+$).{4,15})", message="영문소문자/숫자, 4~16자")
	private String id;
	
	@NotEmpty(message="필수 입력 사항입니다.")
	private String name;
	
	@NotEmpty(message="필수 입력 사항입니다.")
	@Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,15})", message="영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자, 공백불가")
	private String password;
	
	private String phone;
	
	@NotEmpty(message="필수 입력 사항입니다.")
	private String telephone;
	
	@NotEmpty(message="필수 입력 사항입니다.")
	private String email;
	
	private String gender;
	
	@NotEmpty(message="필수 입력 사항입니다.")
	private String birthday;
	
	private String join_date;
	
	@NotEmpty(message="필수 입력 사항입니다.")
	private String address;
	
	public MemberVo() {}
	public MemberVo(String id, String name, String password, String telephone,
			String email, String birthday, String address) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone
				+ ", telephone=" + telephone + ", email=" + email + ", gender=" + gender + ", birthday=" + birthday
				+ ", join_date=" + join_date + ", address=" + address + "]";
	}
	
	
	
	
	


	
}


