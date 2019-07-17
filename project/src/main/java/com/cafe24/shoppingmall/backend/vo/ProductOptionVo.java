package com.cafe24.shoppingmall.backend.vo;

public class ProductOptionVo {
	private Long no;
	private String po_name;
	private String po_code;
	private int stock;
	private char use_stock;
	private Long plus_price;
	private Long product_no;
	
	
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
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}
	@Override
	public String toString() {
		return "ProductOptionVo [no=" + no + ", po_name=" + po_name + ", po_code=" + po_code + ", stock=" + stock
				+ ", use_stock=" + use_stock + ", plus_price=" + plus_price + ", product_no=" + product_no + "]";
	}
	
	
	
	
}
