package com.cafe24.shoppingmall.frontend.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdateMemberVo {
private Long no;
	
	@Pattern(regexp="(([a-z0-9])(?=\\S+$).{3,15})", message="?˜ë¬¸ì†Œë¬¸ì/?ˆ«?, 4~16?")
	@NotEmpty(message="id?Š” ?•„?ˆ˜ ?…? ¥ ?‚¬?•­?…?‹ˆ?‹¤.")
	private String id;
	@NotEmpty(message="?´ë¦„ì? ?•„?ˆ˜ ?…? ¥ ?‚¬?•­?…?‹ˆ?‹¤.")
	private String name;
	private String phone;
	@NotEmpty(message="?œ´??? „?™”ë²ˆí˜¸?Š” ?•„?ˆ˜ ?…? ¥ ?‚¬?•­?…?‹ˆ?‹¤.")
	private String telephone;
	@NotEmpty(message="?´ë©”ì¼?? ?•„?ˆ˜ ?…? ¥ ?‚¬?•­?…?‹ˆ?‹¤.")
	private String email;
	@NotEmpty(message="?ƒ?¼?? ?•„?ˆ˜ ?…? ¥ ?‚¬?•­?…?‹ˆ?‹¤.")
	private String birthday;
	private String join_date;
	private String address;
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
		return "UpdateMemberVo [no=" + no + ", id=" + id + ", name=" + name + ", phone=" + phone + ", telephone="
				+ telephone + ", email=" + email + ", birthday=" + birthday + ", join_date=" + join_date + ", address="
				+ address + "]";
	}
	
	
}
