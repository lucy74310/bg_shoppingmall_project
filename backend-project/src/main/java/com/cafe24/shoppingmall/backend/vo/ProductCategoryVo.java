package com.cafe24.shoppingmall.backend.vo;

public class ProductCategoryVo {
	private String flag;
	private Long no;
	private Long category_no;
	private Long product_no;
	private Long category_full_name;
	public ProductCategoryVo() {
	}

	public ProductCategoryVo(Long category_no) {
		this.category_no = category_no;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}

	public Long getCategory_full_name() {
		return category_full_name;
	}

	public void setCategory_full_name(Long category_full_name) {
		this.category_full_name = category_full_name;
	}

	@Override
	public String toString() {
		return "ProductCategoryVo [flag=" + flag + ", no=" + no + ", category_no=" + category_no + ", product_no="
				+ product_no + ", category_full_name=" + category_full_name + "]";
	}
	
	
}
