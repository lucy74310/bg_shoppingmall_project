package com.cafe24.shoppingmall.backend.vo;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javafx.beans.DefaultProperty;

public class ProductVo {
	private Long no;
	
	@NotEmpty(message="상품명을 입력해 주세요.(1~20자)")
	@Length(min=1, max=20, message="상품명을 입력해 주세요.(1~20자)")
	private String product_name;
	private Long product_price;
	
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
	private int shipping_price = 2500;
	
	private List<OptionVo> o_list; 
	
	private List<ProductCategoryVo> category_list;
	
	private List<ImageVo> image_list;
	
	private List<ProductOptionVo> po_list;
	
	public ProductVo() {
	}
	//테스트에서 사용
	public ProductVo(String product_name, Long product_price, String product_short_explain, char displayed,
			char selling, char use_option, char use_stock, int stock, char soldout_mark, int shipping_price,
			List<OptionVo> o_list, List<ProductCategoryVo> category_list, List<ImageVo> image_list, 
			List<ProductOptionVo> po_list) {
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
		this.o_list = o_list;
		this.category_list = category_list;
		this.image_list = image_list;
		this.po_list = po_list;
	}

	

	public ProductVo(String product_name, Long product_price, char displayed, char selling, char use_option,
			char use_stock, char soldout_mark) {
		super();
		this.product_name = product_name;
		this.product_price = product_price;
		this.displayed = displayed;
		this.selling = selling;
		this.use_option = use_option;
		this.use_stock = use_stock;
		this.soldout_mark = soldout_mark;
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
	public Long getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Long product_price) {
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

	public List<OptionVo> getO_list() {
		return o_list;
	}

	public void setO_list(List<OptionVo> o_list) {
		this.o_list = o_list;
	}

	public List<ProductCategoryVo> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<ProductCategoryVo> category_list) {
		this.category_list = category_list;
	}
	public List<ImageVo> getImage_list() {
		return image_list;
	}
	public void setImage_list(List<ImageVo> image_list) {
		this.image_list = image_list;
	}

	public List<ProductOptionVo> getPo_list() {
		return po_list;
	}
	public void setPo_list(List<ProductOptionVo> po_list) {
		this.po_list = po_list;
	}
	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", product_short_explain=" + product_short_explain + ", product_detail=" + product_detail
				+ ", displayed=" + displayed + ", selling=" + selling + ", use_option=" + use_option + ", use_stock="
				+ use_stock + ", stock=" + stock + ", soldout_mark=" + soldout_mark + ", reg_date=" + reg_date
				+ ", save_percentage=" + save_percentage + ", shipping_price=" + shipping_price + ", o_list=" + o_list
				+ ", category_list=" + category_list + ", image_list=" + image_list + ", po_list=" + po_list + "]";
	}
	
	
	
	
	
}
