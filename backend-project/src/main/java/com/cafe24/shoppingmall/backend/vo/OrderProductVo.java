package com.cafe24.shoppingmall.backend.vo;

public class OrderProductVo {
	private Long no;
	private Long order_no;
	private Long product_option_no;
	private String po_name;
	private int count;
	private Long price;
	private String order_handling_state;
	private String main_image_url;
	public OrderProductVo() {
	}
	public OrderProductVo(Long order_no, Long product_option_no, String po_name, int count, Long price,
			String order_handling_state) {
		this.order_no = order_no;
		this.product_option_no = product_option_no;
		this.po_name = po_name;
		this.count = count;
		this.price = price;
		this.order_handling_state = order_handling_state;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public Long getProduct_option_no() {
		return product_option_no;
	}
	public void setProduct_option_no(Long product_option_no) {
		this.product_option_no = product_option_no;
	}
	public String getPo_name() {
		return po_name;
	}
	public void setPo_name(String po_name) {
		this.po_name = po_name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getOrder_handling_state() {
		return order_handling_state;
	}
	public void setOrder_handling_state(String order_handling_state) {
		this.order_handling_state = order_handling_state;
	}
	public String getMain_url() {
		return main_image_url;
	}
	public void setMain_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}
	@Override
	public String toString() {
		return "OrderProductVo [no=" + no + ", order_no=" + order_no + ", product_option_no=" + product_option_no
				+ ", po_name=" + po_name + ", count=" + count + ", price=" + price + ", order_handling_state="
				+ order_handling_state + ", main_image_url=" + main_image_url + "]";
	}

	
}
