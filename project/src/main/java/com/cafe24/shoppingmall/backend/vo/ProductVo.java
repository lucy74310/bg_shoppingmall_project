package com.cafe24.shoppingmall.backend.vo;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javafx.beans.DefaultProperty;

public class ProductVo {
	private Long no;
	
	@NotEmpty(message="상품명을 입력해 주세요.")
	@Length(min=1, max=20)
	private String product_name;
	@NotEmpty(message="판매가를 입력해 주세요.")
	private String product_price;
	
	private String product_short_explain;
	private String product_detail;
	
	private char displayed;
	private char selling;
	private char use_option;
	private char use_stock;
	
	private int stock;
	private char soldout_mark;
	private String reg_date;
	private int save_percentage;
	private int shipping_price;
	
	private List<OptionVo> po_list; 
	
	private List<Long> category_list;
	
	
	
	public ProductVo() {
	}
	
	
	
	
	public ProductVo(String product_name, String product_price, String product_short_explain, char displayed,
			char selling, char use_option, char use_stock, int stock, char soldout_mark, int shipping_price,
			List<OptionVo> po_list, List<Long> category_list) {
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_short_explain = product_short_explain;
		this.displayed = displayed;
		this.selling = selling;
		this.use_option = use_option;
		this.use_stock = use_stock;
		this.stock = stock;
		this.soldout_mark = soldout_mark;
		this.shipping_price = shipping_price;
		this.po_list = po_list;
		this.category_list = category_list;
	}




	public ProductVo(Long no, String product_name, String product_price, String product_short_explain,
			String product_detail, char displayed, char selling, char use_option, char use_stock, int stock,
			char soldout_mark, int save_percentage, int shipping_price) {
		this.no = no;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_short_explain = product_short_explain;
		this.product_detail = product_detail;
		this.displayed = displayed;
		this.selling = selling;
		this.use_option = use_option;
		this.use_stock = use_stock;
		this.stock = stock;
		this.soldout_mark = soldout_mark;
		this.save_percentage = save_percentage;
		this.shipping_price = shipping_price;
	}




	
	public ProductVo(String product_name, String product_price, String product_short_explain, String product_detail,
			char displayed, char selling, char use_option, char use_stock, int stock, char soldout_mark,
			int save_percentage, int shipping_price) {
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_short_explain = product_short_explain;
		this.product_detail = product_detail;
		this.displayed = displayed;
		this.selling = selling;
		this.use_option = use_option;
		this.use_stock = use_stock;
		this.stock = stock;
		this.soldout_mark = soldout_mark;
		this.save_percentage = save_percentage;
		this.shipping_price = shipping_price;
	}






	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getProduct_short_explain() {
		return product_short_explain;
	}
	public void setProduct_short_explain(String product_short_explain) {
		this.product_short_explain = product_short_explain;
	}
	public String getProduct_detail() {
		return product_detail;
	}
	public void setProduct_detail(String product_detail) {
		this.product_detail = product_detail;
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
	public char getUse_option() {
		return use_option;
	}
	public void setUse_option(char use_option) {
		this.use_option = use_option;
	}
	public char getUse_stock() {
		return use_stock;
	}
	public void setUse_stock(char use_stock) {
		this.use_stock = use_stock;
	}
	public char getSoldout_mark() {
		return soldout_mark;
	}
	public void setSoldout_mark(char soldout_mark) {
		this.soldout_mark = soldout_mark;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getSave_percentage() {
		return save_percentage;
	}
	public void setSave_percentage(int save_percentage) {
		this.save_percentage = save_percentage;
	}
	public int getShipping_price() {
		return shipping_price;
	}
	public void setShipping_price(int shipping_price) {
		this.shipping_price = shipping_price;
	}

	public List<OptionVo> getPo_list() {
		return po_list;
	}

	public void setPo_list(List<OptionVo> po_list) {
		this.po_list = po_list;
	}

	public List<Long> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<Long> category_list) {
		this.category_list = category_list;
	}

	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", product_short_explain=" + product_short_explain + ", product_detail=" + product_detail
				+ ", displayed=" + displayed + ", selling=" + selling + ", use_option=" + use_option + ", use_stock="
				+ use_stock + ", stock=" + stock + ", soldout_mark=" + soldout_mark + ", reg_date=" + reg_date
				+ ", save_percentage=" + save_percentage + ", shipping_price=" + shipping_price + ", po_list=" + po_list
				+ ", category_list=" + category_list + "]";
	}



	
	
	
	
	
	
}
