package com.cafe24.shoppingmall.backend.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoryVo {
	
	private Long no;
	@NotEmpty(message="1~20자 사이로 입력해 주세요")
	@Length(min=1, max=20, message="1~20자 사이로 입력해 주세요")
	private String category_name;
	private String category_explain;
	private Long upper_no;
	
	
	public CategoryVo() {
	}

	public CategoryVo(String category_name, Long upper_no) {
		this.category_name = category_name;
		this.upper_no = upper_no;
	}

	public CategoryVo(String category_name) {
		this.category_name = category_name;
	}
	

	public CategoryVo(String category_name, String category_explain) {
		this.category_name = category_name;
		this.category_explain = category_explain;
	}

	public CategoryVo(Long no, String category_name) {
		this.no = no;
		this.category_name = category_name;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_explain() {
		return category_explain;
	}

	public void setCategory_explain(String category_explain) {
		this.category_explain = category_explain;
	}

	public Long getUpper_no() {
		return upper_no;
	}

	public void setUpper_no(Long upper_no) {
		this.upper_no = upper_no;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", category_name=" + category_name + ", category_explain=" + category_explain
				+ ", upper_no=" + upper_no + "]";
	}


	
}

