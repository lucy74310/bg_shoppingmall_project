package com.cafe24.shoppingmall.backend.vo;

public class ProductCategoryVo {
	private String flag;
	private Long no;
	private Long category_no;
	private Long product_no;
	
	public ProductCategoryVo() {
	}

	public ProductCategoryVo(Long category_no) {
		this.category_no = category_no;
	}

	@Override
	public String toString() {
		return "ProductCategoryVo [flag=" + flag + ", no=" + no + ", category_no=" + category_no + ", product_no="
				+ product_no + "]";
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
	
	
}
