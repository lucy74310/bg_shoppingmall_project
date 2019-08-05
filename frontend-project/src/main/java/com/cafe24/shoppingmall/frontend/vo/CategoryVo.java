package com.cafe24.shoppingmall.frontend.vo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoryVo {
	
	private Long no;
	
	@NotEmpty(message="1~20?ûê ?Ç¨?ù¥Î°? ?ûÖ?†•?ï¥ Ï£ºÏÑ∏?öî")
	@Length(min=1, max=20, message="1~20?ûê ?Ç¨?ù¥Î°? ?ûÖ?†•?ï¥ Ï£ºÏÑ∏?öî")
	private String category_name;
	private String category_explain;
	private Long upper_no;
	private int category_order;
	private String upper_category_name;
	private Long upper_no2;
	
	
	private List<CategoryVo> sub_categories;
	private String flag;
	
	public CategoryVo() {
	}

	public CategoryVo(String category_name, Long upper_no) {
		this.category_name = category_name;
		this.upper_no = upper_no;
	}

	public CategoryVo(String category_name) {
		this.category_name = category_name;
	}

	public CategoryVo(String category_name, int category_order) {
		this.category_name = category_name;
		this.category_order = category_order;
	}

	public CategoryVo(String category_name, int category_order, List<CategoryVo> sub_categories) {
		this.category_name = category_name;
		this.category_order = category_order;
		this.sub_categories = sub_categories;
	}

	public CategoryVo(String category_name, String category_explain) {
		this.category_name = category_name;
		this.category_explain = category_explain;
	}

	public CategoryVo(Long no, String category_name) {
		this.no = no;
		this.category_name = category_name;
	}

	public CategoryVo(long no) {
		this.no = no;
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
	
	public List<CategoryVo> getSub_categories() {
		return sub_categories;
	}

	public void setSub_categories(List<CategoryVo> sub_categories) {
		this.sub_categories = sub_categories;
	}

	public int getCategory_order() {
		return category_order;
	}

	public void setCategory_order(int category_order) {
		this.category_order = category_order;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getUpper_category_name() {
		return upper_category_name;
	}
	public void setUpper_category_name(String upper_category_name) {
		this.upper_category_name = upper_category_name;
	}
	public Long getUpper_no2() {
		return upper_no2;
	}
	public void setUpper_no2(Long upper_no2) {
		this.upper_no2 = upper_no2;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", category_name=" + category_name + ", category_explain=" + category_explain
				+ ", upper_no=" + upper_no + ", category_order=" + category_order + ", upper_category_name="
				+ upper_category_name + ", upper_no2=" + upper_no2 + ", sub_categories=" + sub_categories + ", flag="
				+ flag + "]";
	}

	
	

	

	
}

