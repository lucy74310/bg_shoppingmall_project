package com.cafe24.shoppingmall.backend.vo;

import javax.validation.constraints.NotNull;


public class CartVo {

	private Long no;
	private Long member_no;
	private Long non_member_no;
	@NotNull(message="상품옵션 번호는 필수항목입니다.")
	private Long product_option_no;
	
	private Long price;
	
	private int count;
	private String po_name;
	
	private String flag;
	private String main_image_url;
	
	
	public CartVo() {
	}
	
	public CartVo(Long member_no, Long product_option_no) {
		this.member_no = member_no;
		this.product_option_no = product_option_no;
	}

	public CartVo( Long product_option_no, Long price, int count) {
		this.product_option_no = product_option_no;
		this.price = price;
		this.count = count;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
	public Long getProduct_option_no() {
		return product_option_no;
	}
	public void setProduct_option_no(Long product_option_no) {
		this.product_option_no = product_option_no;
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

	public String getPo_name() {
		return po_name;
	}
	public void setPo_name(String po_name) {
		this.po_name = po_name;
	}
	
	public String getMain_url() {
		return main_image_url;
	}
	public void setMain_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", member_no=" + member_no + ", non_member_no=" + non_member_no
				+ ", product_option_no=" + product_option_no + ", price=" + price + ", count=" + count + ", po_name="
				+ po_name + ", flag=" + flag + ", main_image_url=" + main_image_url + "]";
	}
	
}
