package com.cafe24.shoppingmall.backend.vo;

public class CartVo {

	private Long cart_no;
	private Long member_no;
	private Long non_member_no;
	private Long price;
	private int count;
	private String flag;
	public Long getCart_no() {
		return cart_no;
	}
	public void setCart_no(Long cart_no) {
		this.cart_no = cart_no;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public Long getNon_member_no() {
		return non_member_no;
	}
	public void setNon_member_no(Long non_member_no) {
		this.non_member_no = non_member_no;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "CartVo [cart_no=" + cart_no + ", member_no=" + member_no + ", non_member_no=" + non_member_no
				+ ", price=" + price + ", count=" + count + ", flag=" + flag + "]";
	}
	
	
	
}
