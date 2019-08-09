package com.cafe24.shoppingmall.frontend.vo;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class JoinVo {
	@Pattern(regexp="(([a-z0-9])(?=\\S+$).{3,15})", message="영문소문자/숫자, 4~16자")
	private String id;
	
	@NotEmpty(message="이름은 필수 입력 사항입니다.")
	private String name;
	
	@Pattern(regexp="((?=.*[0-9])(?=.*[a-zA-Z])(?=.*[~!@#$%^&+=_])(?=\\S+$).{7,15})", message="영문 대소문자/숫자/특수문자 3가지 조합, 8자~16자, 공백불가")
	private String password;
	
	private String phone;
	
	@NotEmpty(message="휴대전화번호는 필수 입력 사항입니다.")
	private String telephone;
	
	@NotEmpty(message="이메일은 필수 입력 사항입니다.")
	private String email;
	
	@NotEmpty(message="생일은 필수 입력 사항입니다.")
	private String birth;
	
	@AssertTrue(message="id 중복 체크를 해주세요. ")
	private boolean idcheck;
	
	@AssertTrue(message="약관 동의는 필수입니다.")
	private boolean agree;

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

	public boolean isIdcheck() {
		return idcheck;
	}

	public void setIdcheck(boolean idcheck) {
		this.idcheck = idcheck;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	@Override
	public String toString() {
		return "JoinVo [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + ", telephone="
				+ telephone + ", email=" + email + ", birth=" + birth + ", idcheck=" + idcheck + ", agree=" + agree
				+ "]";
	}
	
}
