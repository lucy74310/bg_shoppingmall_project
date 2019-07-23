package com.cafe24.shoppingmall.backend.vo;

public class ProductOptionVo {
	private Long no;
	private String po_name;
	private String po_code;
	private int stock;
	private char use_stock;
	private char displayed;
	private char selling;
	private Long plus_price;
	private int po_order;
	private Long product_no;
	private String flag;
	
	public ProductOptionVo() {
		
	}
	public ProductOptionVo(String po_name, char displayed, char selling, Long plus_price, int po_order) {
		this.po_name = po_name;
		this.displayed = displayed;
		this.selling = selling;
		this.plus_price = plus_price;
		this.po_order = po_order;
	}
	
	public char getDisplayed() {
		return displayed;
	}
	public void setDisplayed(char displayed) {
		this.displayed = displayed;
	}
	public char getSelling() {
		return selling;
	}
	public void setSelling(char selling) {
		this.selling = selling;
	}
	public int getPo_order() {
		return po_order;
	}
	public void setPo_order(int po_order) {
		this.po_order = po_order;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getPo_name() {
		return po_name;
	}
	public void setPo_name(String po_name) {
		this.po_name = po_name;
	}
	public String getPo_code() {
		return po_code;
	}
	public void setPo_code(String po_code) {
		this.po_code = po_code;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public char getUse_stock() {
		return use_stock;
	}
	public void setUse_stock(char use_stock) {
		this.use_stock = use_stock;
	}
	public Long getPlus_price() {
		return plus_price;
	}
	public void setPlus_price(Long plus_price) {
		this.plus_price = plus_price;
	}
	public int getOrder() {
		return po_order;
	}
	public void setOrder(int order) {
		this.po_order = order;
	}
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public ProductOptionVo(Long no, String po_name, String po_code, int stock, char use_stock, char displayed,
			char selling, Long plus_price, int po_order, Long product_no) {
		this.no = no;
		this.po_name = po_name;
		this.po_code = po_code;
		this.stock = stock;
		this.use_stock = use_stock;
		this.displayed = displayed;
		this.selling = selling;
		this.plus_price = plus_price;
		this.po_order = po_order;
		this.product_no = product_no;
	}
	@Override
	public String toString() {
		return "ProductOptionVo [no=" + no + ", po_name=" + po_name + ", po_code=" + po_code + ", stock=" + stock
				+ ", use_stock=" + use_stock + ", displayed=" + displayed + ", selling=" + selling + ", plus_price="
				+ plus_price + ", po_order=" + po_order + ", product_no=" + product_no + ", flag=" + flag + "]";
	}

	
}
